package com.csi.jcl.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * 定義UploadExcelService介面
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */

public interface UploadExcelService {

    String getExcel(MultipartFile file) throws Exception;

}
