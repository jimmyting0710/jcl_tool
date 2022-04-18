package com.csi.jcl.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.Put;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.service.ThisService;
import com.csi.jcl.service.getCsv;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import antlr.StringUtils;
import freemarker.template.utility.StringUtil;

@RestController
public class UploadCSV {
	@Autowired
	ThisService thisService;

	@RequestMapping(value = "/csvupload", method = RequestMethod.POST)
	public ModelAndView uploadcsv(HttpServletRequest request, @RequestParam("filename") MultipartFile file,
			@RequestParam("adname") String adname, @RequestParam("url") String url) {
		String newurl = "";
		String findtid;
		List<String> aList = new ArrayList<>();

		List<Map<String, String>> intodb = new ArrayList<>();
		if (url.contains("&msg")) {
			String[] a = url.split("&");
			newurl = newurl + a[0];
			for (int i = 1; i < a.length - 1; i++) {
				newurl = newurl + "&" + a[i];
			}
			System.out.println(newurl);
		} else {
			newurl = url;
		}
		try {
			if (file.isEmpty()) {
				ModelAndView badfile = new ModelAndView();
				badfile.setViewName("redirect:" + newurl);
				badfile.addObject("msg", "請選擇檔案上傳");
				return badfile;
			}
			byte[] bytes = file.getBytes();

			XSSFWorkbook workbook = new XSSFWorkbook();
			// byte陣列轉成ByteArrayInputStream
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

			if (file.getOriginalFilename().contains(".csv")) {
				workbook = getCsv.getWorkbookByCsv(byteArrayInputStream);
			} else {

				workbook = new XSSFWorkbook(byteArrayInputStream);
			}
			// 得到上傳的表
			XSSFSheet sheet = workbook.getSheetAt(0);

			// 獲取表的總行數

			int num = sheet.getLastRowNum();

			// 總列數

			int col = sheet.getRow(0).getLastCellNum();

			for (int j = 1; j <= num; j++) {
				Row row1 = sheet.getRow(j);
				String rid = "";
				String jcl = String.valueOf(row1.getCell(1));
				String tester = String.valueOf(row1.getCell(5));
				String status = String.valueOf(row1.getCell(6));
				String fintime = String.valueOf(row1.getCell(7));
				String findtester_id = thisService.findtester_id(tester);
				String findstatus = thisService.findstatus(status);
//				System.out.println(adname + "===" + jcl + "===" + tester + "===" + status + "===" + fintime);
				// 得到tid才可以存入testresult
				findtid = thisService.findtid(adname, jcl);
				if ((status.equals(" ") || status == null) && (tester.equals(" ") || tester == null)) {
					break;
				} else {
					// 檢查csv有沒有打錯testerid
					if (findtid == null) {
						ModelAndView badjcl = new ModelAndView();
						badjcl.setViewName("redirect:" + newurl);
						badjcl.addObject("msg", "JCL式格錯誤");
						return badjcl;
					}
					if (findtester_id != null && findstatus != null) {
						if (findstatus.equals("P") || findstatus.equals("F")) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							try {
								Date date = format.parse(fintime);
							} catch (ParseException e) {
								ModelAndView badtime = new ModelAndView();
								badtime.setViewName("redirect:" + newurl);
								badtime.addObject("msg", "測試時間式格錯誤");
								return badtime;
							}
						}
					}
					if (findstatus == null) {
						ModelAndView badstatus = new ModelAndView();
						badstatus.setViewName("redirect:" + newurl);
						badstatus.addObject("msg", "測試狀態式格錯誤");
						return badstatus;
					}
					if (findtester_id == null) {
						ModelAndView badid = new ModelAndView();
						badid.setViewName("redirect:" + newurl);
						badid.addObject("msg", "測試者名字式格錯誤");
						return badid;
					}
					if (findtester_id != null && findstatus != null) {
						thisService.saveresult2(findstatus, findtid, findtester_id, fintime, rid);
						thisService.updatetestcase(findtid, findstatus, findtester_id);
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:" + newurl);
	}
}
