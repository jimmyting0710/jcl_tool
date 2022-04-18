package com.csi.jcl.service;

import com.csi.jcl.dao.AdJclRepository;
import com.csi.jcl.dao.CodeListRepository;
import com.csi.jcl.dao.DefectListRepository;
import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.entity.CodeListEntity;
import com.csi.jcl.entity.DefectListEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 實作UploadExcelService功能
 *
 * @author si1255 Morris Mao
 * @date 2021/10/25
 */

@Service
public class UploadExcelServiceImpl implements UploadExcelService{

    private static final Logger logger =
            LogManager.getLogger(UploadExcelServiceImpl.class);

    @Autowired
    private DefectListRepository defectListRepository;

    @Autowired
    private CodeListRepository codeListRepository;

    @Autowired
    private AdJclRepository adJclRepository;

    //讀取上傳的EXCEL
    @Override
    public String getExcel(MultipartFile file) {

        String AD = null;
        String JCL = null;

        int rowInfo = 0;
        try {
            // TODO Auto-generated method stub
            List<DefectListEntity> list = new ArrayList<>();
            //把檔案轉成byte陣列
            logger.info("file.getBytes()");
            byte[] bytes = file.getBytes();

            XSSFWorkbook workbook = new XSSFWorkbook();
            //byte陣列轉成ByteArrayInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            if(file.getOriginalFilename().contains(".csv")){
                 workbook =  getCsv.getWorkbookByCsv(byteArrayInputStream);
            }else {

                 workbook = new XSSFWorkbook(byteArrayInputStream);
            }
            //得到上傳的表
            XSSFSheet sheet = workbook.getSheetAt(0);

            //獲取表的總行數

            int num = sheet.getLastRowNum();


            //總列數

            int col = sheet.getRow(0).getLastCellNum();


            //遍歷excel每一行

            for (int j = 1; j <= num; j++) {

                Row row1 = sheet.getRow(j);

                Cell cellIssueKey = row1.getCell(0);

                Cell cellStatus = row1.getCell(2);

                Cell cellAD = row1.getCell(5);

                Cell cellJCL = row1.getCell(6);

                Cell cellIssueType = row1.getCell(7);
                AD = cellAD.getStringCellValue();
                JCL = cellJCL.getStringCellValue();

                CodeListEntity codeListEntity = new CodeListEntity();

                AdJclEntity adJclEntity = new AdJclEntity();

                DefectListEntity defectListEntity = new DefectListEntity();


                //取得JID
                logger.info("adJclRepository.findByAdAndJcl");
                adJclEntity = adJclRepository.findByAdAndJcl(cellAD.getStringCellValue(), cellJCL.getStringCellValue());
                //取得CODE_ID
                logger.info("codeListRepository.findByCodeDesc");
                codeListEntity = codeListRepository.findByCodeDesc(cellStatus.getStringCellValue().toUpperCase());

                //把資料寫入Defect_list資料表
                defectListEntity.setTestType("1");

                defectListEntity.setIssueKey(cellIssueKey.getStringCellValue());

                defectListEntity.setJid(adJclEntity.getJid());

                defectListEntity.setIssueType(cellIssueType.getStringCellValue());

                defectListEntity.setIssueStatus(codeListEntity.getCodeId());

                Date current = new Date();
                defectListEntity.setIssueCreateDatetime(current);
                logger.info("list.add(defectListEntity)");
                list.add(defectListEntity);
                rowInfo = j;
            }
            logger.info("defectListRepository.saveAll(list)");
            defectListRepository.saveAll(list);

            String uploadStatus = "上傳成功";
            return uploadStatus;
        } catch (NullPointerException n) {
            String uploadStatus = "內容錯誤！ 查無此筆資料 AD :"+AD+"\nJCL :"+JCL;
            return uploadStatus;
        } catch (EmptyFileException p) {
            String uploadStatus = "請選擇要上傳的檔案";
            return uploadStatus;
        } catch (Exception e) {
            String uploadStatus = "檔案格式錯誤！";
            return uploadStatus;
        }
    }
}
