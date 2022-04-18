package com.csi.jcl.service;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.AdJclExeroderRepository;
import com.csi.jcl.model.AdJclExeorderModel;
import com.csi.jcl.model.AdJclModel;
import com.csi.jcl.model.CheckPointJoinTestCaseModel;
import com.csi.jcl.model.CheckPointModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 實作AdJclExeorderService功能
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/09/28
 */
@Service
public class AdJclExeorderService {
    private static final Logger logger =
            LogManager.getLogger(AdJclExeorderService.class);

    /**
     * 注入AdJclExeroderRepository
     *
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    private final AdJclExeroderRepository adJclExeroderRepository;

    public AdJclExeorderService(AdJclExeroderRepository adJclExeroderRepository) {
        this.adJclExeroderRepository = adJclExeroderRepository;
    }


    /**
     * 依adName取得描述
     *
     * @param adName adName參數的值
     * @return selfJclSet
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    public Set<String> getAllSelfJcl(String adName) {

        logger.info("call getAllSelfJcl()");
        String sql = " SELECT ad, jcl, before_jcl_ad, beforejcl, afterjcl FROM ad_jcl_exeorder WHERE AD= :adName";

        Map<String, Object> params = new HashMap<>();

        params.put("adName", adName);

        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);

        List<AdJclExeorderModel> adJclExeorderModelList = JSONObject.parseArray(JSONObject.toJSONString(list), AdJclExeorderModel.class);

        // 定義selfJclSet來裝selfJcl的資料
        Set<String> selfJclSet = new HashSet<String>();

        // 加入資料
        for (int i = 0; i < adJclExeorderModelList.size(); i++) {
            if (adJclExeorderModelList.get(i).getJcl() != null) {
                selfJclSet.add(adJclExeorderModelList.get(i).getJcl());
            }
        }
        return selfJclSet;
    }

    /**
     * 依adName取得Jcl執行順序
     *
     * @param adName adName參數的值
     * @return finalJclMap
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    public Map<Integer, List<String>> getJclSequence(String adName) {

        logger.info("call getJclSequence()");
        String sql = " SELECT ad, jcl, before_jcl_ad, beforejcl, afterjcl FROM ad_jcl_exeorder WHERE AD= :adName";

        Map<String, Object> params = new HashMap<>();

        params.put("adName", adName);

        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);

        List<AdJclExeorderModel> adJclExeorderModelList = JSONObject.parseArray(JSONObject.toJSONString(list), AdJclExeorderModel.class);

        // 定義afterJclSet來裝afterJcl的資料
        Set<String> afterJclSet = new HashSet<String>();

        // 定義selfJclSet來裝selfJcl的資料
        Set<String> selfJclSet = new HashSet<String>();

        // 依JCL執行順序將JCL放入List
        List<String> jclSequence = new LinkedList<String>();

        // 讓jclSequence不重複
        Set<String> jclSequenceSet = new LinkedHashSet<>();

        // 依JCL執行順序將Before JCL放入List
        List<List<String>> beforeJclSequence = new LinkedList<List<String>>();

        // 定義jclAmountByJclName將個別JCL的數量與名稱綁定
        Map<String, Integer> jclAmountByJclName = new HashMap<String, Integer>();

        // 定義jclSequenceByJclName將目前AD名稱與前面須執行的AD綁定
        Map<String, List<String>> jclSequenceByJclName = new LinkedHashMap<String, List<String>>();

        // 整理jcl順序
        Map<Integer, List<String>> preJclSort = new LinkedHashMap<Integer, List<String>>();

        // 最終排序
        Map<Integer, List<String>> finalJclMap = new LinkedHashMap<Integer, List<String>>();

        // 加入資料
        for (int i = 0; i < adJclExeorderModelList.size(); i++) {
            if (adJclExeorderModelList.get(i).getAfterjcl() != null) {
                afterJclSet.add(adJclExeorderModelList.get(i).getAfterjcl());
            }
            if (adJclExeorderModelList.get(i).getJcl() != null) {
                selfJclSet.add(adJclExeorderModelList.get(i).getJcl());
            }
        }

        // 取得第一個JCL
        Set<String> findFirstJcl = new HashSet<String>();
        findFirstJcl.addAll(selfJclSet);
        findFirstJcl.removeAll(afterJclSet);
        Iterator<String> findFirstJclItreator = findFirstJcl.iterator();
        String firstJcl = "";

        // 找jcl的順序
        while (findFirstJclItreator.hasNext()) {
            firstJcl = findFirstJclItreator.next();
            jclSequence.add(firstJcl);
        }
        try {
            for (int i = 0; i < selfJclSet.size(); i++) {
                String nextJcl = "";
                for (AdJclExeorderModel x : adJclExeorderModelList) {
                    if (jclSequence.get(i).equals(x.getJcl()) && x.getAfterjcl() != null) {
                        nextJcl = x.getAfterjcl();
                        jclSequence.add(nextJcl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        將需要顯示的欄位依順序裝入beforeJclSequence
        for (int i = 0; i < jclSequence.size(); i++) {
            for (AdJclExeorderModel jcl : adJclExeorderModelList) {
                List<String> beforeJclDescription = new LinkedList<String>();
                if (jclSequence.get(i).equals(jcl.getJcl())) {
                    if (jcl.getBeforeJclAd() == null && jcl.getBeforejcl() == null) {
                        beforeJclDescription.add("");
                    } else if (jcl.getBeforeJclAd() == null) {
                        beforeJclDescription.add(jcl.getBeforejcl());
                    } else {
                        beforeJclDescription.add(jcl.getBeforeJclAd() + jcl.getBeforejcl());
                    }
                }
                if (beforeJclDescription.size() > 0) {
                    beforeJclSequence.add(beforeJclDescription);
                }
            }
            // 將個別JCL的數量與名稱綁定到jclAmountByJclName裡
            if (i > 0) {
                int amount = 0;
                for (int j = 0; j < i; j++) {
                    amount = amount + jclAmountByJclName.get(jclSequence.get(j));
                }
                jclAmountByJclName.put(jclSequence.get(i), beforeJclSequence.size() - amount);
            } else {
                jclAmountByJclName.put(jclSequence.get(i), beforeJclSequence.size());
            }
        }


//        將目前AD名稱與前面須執行的AD用LinkedHashMap結合起來
        int beforeLength = 0;
        for (int i = 0; i < jclSequence.size(); i++) {
            int nowLength = beforeLength + jclAmountByJclName.get(jclSequence.get(i));
            List<String> jclList = new ArrayList<String>();
            if (i == 0) {
                for (int k = 0; k < nowLength; k++) {
                    String beforeJcl = beforeJclSequence.get(k).toString();
                    jclList.add(beforeJcl);
                }
                jclSequenceByJclName.put(jclSequence.get(i), jclList);
                if (jclSequence.size() - 1 == i) {
                    jclSequenceByJclName.put("last", Collections.singletonList(jclSequence.get(jclSequence.size() - 1)));
                }
            } else {
                jclList.clear();
                for (int k = beforeLength; k < nowLength; k++) {
                    String beforeJcl = beforeJclSequence.get(k).toString();
                    jclList.add(beforeJcl);
                }

                jclSequenceByJclName.put(jclSequence.get(i), jclList);
                if (jclSequence.size() - 1 == i) {
                    jclSequenceByJclName.put("last", Collections.singletonList(jclSequence.get(jclSequence.size() - 1)));
                }
            }
            beforeLength = nowLength;
        }

        jclSequence.add("last");
        jclSequenceSet.addAll(jclSequence);

        // 取得未整理的jclSequence
        List<String> preJclSequence = new ArrayList<>(jclSequenceSet);
        for (int i = 0; i < jclSequenceByJclName.size(); i++) {
            int index = 0;
            preJclSort.put(i, jclSequenceByJclName.get(preJclSequence.get(i)));
            if (i > 0 && preJclSort.get(i - 1).equals(preJclSort.get(i))) {
                preJclSort.remove(i - 1);
            }
        }

        Set keyset = preJclSort.keySet();
        List<Integer> keySequence = new ArrayList<>(keyset);

        for (int i = 0; i < keySequence.size(); i++) {
            finalJclMap.put(i, preJclSort.get(keySequence.get(i)));
        }
        return finalJclMap;
    }

    /**
     * 依beforeAd,beforeJcl取得下一個執行的AD
     *
     * @param beforeAd,beforeJcl beforeAd,beforeJcl參數的值
     * @return findNextAd
     * @author si1206 Sam Chen
     * @date 2021/09/28
     */
    public List<String> findNextAd(String beforeAd, String beforeJcl) {
        logger.info("call findNextAd(String beforeAd, String beforeJcl)");
        String sql = " SELECT aj.AD, aj.addesc FROM ad_jcl aj JOIN ad_jcl_exeorder aje ON aj.ad = aje.ad  WHERE beforead = :beforeAd AND beforejcl LIKE :beforeJcl ";

        Map<String, Object> params = new HashMap<>();

        params.put("beforeAd", beforeAd);
        params.put("beforeJcl", "%" + beforeJcl);
        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
        List<AdJclModel> adJclModelList = JSONObject.parseArray(JSONObject.toJSONString(list), AdJclModel.class);
        List<String> findNextAd = adJclModelList.stream()
                .filter(a -> a.getAd() != null && a.getAddesc() != null)
                .map(a -> a.getAd() + a.getAddesc())
                .collect(Collectors.toList());
        return findNextAd;
    }

    /**
     * 依adName取得資料庫比對斷點
     *
     * @param adName adName參數的值
     * @return breakPointList
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    public List<String> findBreakPoint(String adName) {
        logger.info("call findBreakPoint(String adName)");
        String sql = " SELECT jcl FROM checkpoint WHERE pause='Y' AND AD = :adName ";
        Map<String, Object> params = new HashMap<>();

        params.put("adName", adName);

        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);

        List<CheckPointModel> checkPointModelList = JSONObject.parseArray(JSONObject.toJSONString(list), CheckPointModel.class);

        List<String> breakPointList = checkPointModelList.stream()
                .filter(a -> a.getJcl() != null)
                .map(a -> a.getJcl())
                .collect(Collectors.toList());
        return breakPointList;
    }

    /**
     * 依adName取得檔案查核點
     *
     * @param adName adName參數的值
     * @return CheckPointSet
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    public Set<String> findCheckPoint(String adName) {
        logger.info("call findCheckPoint(String adName)");
        String sql = "SELECT * FROM checkpoint WHERE AD = :adName AND (PASSFORM='Y' OR IOCHECKLIST='Y' OR ALLJCL='Y')";
        Map<String, Object> params = new HashMap<>();

        params.put("adName", adName);

        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);

        List<CheckPointModel> checkPointModelList = JSONObject.parseArray(JSONObject.toJSONString(list), CheckPointModel.class);

        Set<String> CheckPointSet = checkPointModelList.stream()
                .filter(cps -> cps.getJcl() != null)
                .map(cps -> cps.getJcl())
                .collect(Collectors.toSet());
        return CheckPointSet;
    }

    /**
     * 依jcl取得資料庫比對斷點細項
     *
     * @param jcl jcl參數的值
     * @return checkPointModelList
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    public List<CheckPointModel> findBreakPointDetail(String jcl) {
        logger.info("findBreakPointDetail(String jcl)");
        String sql = "SELECT sprintno, ad, addesc, jcl, dd, owner FROM checkpoint WHERE jcl = :jcl ";
        Map<String, Object> params = new HashMap<>();
        params.put("jcl", jcl);
        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
        List<CheckPointModel> checkPointModelList = JSONObject.parseArray(JSONObject.toJSONString(list), CheckPointModel.class);
        return checkPointModelList;
    }

    /**
     * 依jcl取得檔案查核點InputFile細項
     *
     * @param jcl jcl參數的值
     * @return checkPointModelList
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    public List<CheckPointJoinTestCaseModel> findCheckPointInput(String jcl) {
        logger.info("call findCheckPointInput(String jcl)");
        String sql = "SELECT cp.jcl,cp.sprintno,cp.ad,cp.addesc,cp.dd AS cpdd,ts.dd AS tsdd,cp.alljcl,cp.passform,cp.iochecklist,ch.cht_ap, ch.cht_dc,ts.open_mode,ts.dsn \n" +
                "FROM checkpoint cp, cht_ap_dc ch, testcase ts\n" +
                "WHERE cp.system = ch.systemtype\n" +
                "AND cp.sprintno = ch.sprint\n" +
                "AND cp.jcl=:jcl \n" +
                "AND cp.ad = ts.ad\n" +
                "AND cp.jcl = ts.jcl\n" +
                "AND (PASSFORM='Y' OR IOCHECKLIST='Y' OR ALLJCL='Y')\n" +
                "AND(ts.dd = 'SORTIN' OR ts.open_mode = 'INPUT' OR ts.open_mode ='I-O')";
        Map<String, Object> params = new HashMap<>();
        params.put("jcl", jcl);
        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
        List<CheckPointJoinTestCaseModel> checkPointJoinTestCaseModelList = JSONObject.parseArray(JSONObject.toJSONString(list), CheckPointJoinTestCaseModel.class);
//        List<CheckPointJoinTestCaseModel> checkPointInput = checkPointJoinTestCaseModelList.stream()
//                .filter(cpi -> cpi.getCpdd().equals("SORTIN"))
//                .collect(Collectors.toList());
        return checkPointJoinTestCaseModelList;
    }

    /**
     * 依jcl取得檔案查核點OutputFile細項
     *
     * @param jcl jcl參數的值
     * @return checkPointJoinTestCaseModelList
     * @author si1206 Sam Chen
     * @date 2021/10/07
     */
    public List<CheckPointJoinTestCaseModel> findCheckPointOutput(String jcl) {
        logger.info("call findCheckPointInput(String jcl)");
        String sql = "SELECT cp.jcl,cp.sprintno,cp.ad,cp.addesc,cp.dd AS cpdd,ts.dd AS tsdd,cp.alljcl,cp.passform,cp.iochecklist,ch.cht_ap, ch.cht_dc,ts.open_mode,ts.dsn \n" +
                "FROM checkpoint cp, cht_ap_dc ch, testcase ts\n" +
                "WHERE cp.system = ch.systemtype\n" +
                "AND cp.sprintno = ch.sprint\n" +
                "AND cp.jcl=:jcl \n" +
                "AND cp.ad = ts.ad\n" +
                "AND cp.jcl = ts.jcl\n" +
                "AND (PASSFORM='Y' OR IOCHECKLIST='Y' OR ALLJCL='Y')\n" +
                "AND(ts.dd = 'SORTOUT' OR ts.open_mode = 'OUTPUT' OR ts.open_mode ='I-O')";
        Map<String, Object> params = new HashMap<>();
        params.put("jcl", jcl);
        List<Map<String, Object>> list = adJclExeroderRepository.query(sql, params);
        List<CheckPointJoinTestCaseModel> checkPointJoinTestCaseModelList = JSONObject.parseArray(JSONObject.toJSONString(list), CheckPointJoinTestCaseModel.class);

        return checkPointJoinTestCaseModelList;
    }
}
