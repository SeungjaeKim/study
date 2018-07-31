package com.study.common;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import static com.study.common.FileUtils.*;

@Slf4j
@Component
public class FileUploadHelper {
	
	private static String[] EXCEPT_EXTENSIONS = {"asp", "aspx", "jsp", "java", "php", "htaccess", "cer", "cdx", "asa", "php3", "html", "htm", "war", "exe", "bat", "sh", "zip", "js", "css", "xml"};

	/**
	 * 첨부파일 업로드 처리
	 * @param file
	 * @param rootPath
	 * @param filePath
	 * @param checkExtensions
	 * @return HashMap.data
	 * @throws Exception
	 */
	public static HashMap<String, Object> upload(MultipartFile file, String rootPath, String filePath, String[] checkExtensions) throws Exception {
		
		HashMap<String, Object> data = new HashMap<>();
		
		try {
			
			String originalFileName = file.getOriginalFilename();
			// TODO FilenameUtils.getExtension() 사용시 nullPointException 발생..
			//String fileExtension = FilenameUtils.getExtension(originalFileName).toLowerCase();
			
			String[] fileNameArr = originalFileName.split("\\.");
			String fileExtension = fileNameArr[(fileNameArr.length - 1)].toLowerCase();
			
			for(String except: EXCEPT_EXTENSIONS) {
				if(fileExtension.equals(except)) {
					throw new Exception("file.extension.error");
				}
			}
			
			if(!StringUtils.isEmpty(checkExtensions)) {
				boolean isExtension = checkFileExtensions(fileExtension, checkExtensions);
				if(!isExtension) {
					throw new Exception("file.extension.error");
				}
			}
			
			String uuid = UUID.randomUUID().toString();
			String physicalFileName = uuid+"."+fileExtension;
			String saveFilePath = filePath+getDatePath();
			String fullPath = rootPath+saveFilePath;
			
			boolean isMakeDir = makeDirectory(fullPath);
			
			if(isMakeDir) {
				fullPath += File.separator+physicalFileName;
				
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fullPath));
				
				data.put("physicalFileName", physicalFileName);
				data.put("saveFilePath", saveFilePath+File.separator+physicalFileName);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return data;
	}

	/**
	 * 파일 디렉토리 생성
	 * @param fullPath
	 * @return boolean
	 */
	private static boolean makeDirectory(String fullPath) {
		if(StringUtils.isEmpty(fullPath)) {
			throw new IllegalArgumentException("fullPath can't be null");
		}
		if(isExistDirectory(fullPath)) {
			return true;
		}
		File dir = new File(fullPath);
		
		return dir.mkdirs();	
	}

	/**
	 * 파일 디렉토리 체크
	 * @param fullPath
	 * @return boolean 
	 */
	private static boolean isExistDirectory(String fullPath) {
		File dir = new File(fullPath);
		if(dir.exists() && dir.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * 날짜 형식의 파일경로 설정
	 * @return String.datePath
	 */
	private static String getDatePath() {
		String datePath = "";
		String delimiter = "/";
		Calendar calendar = Calendar.getInstance();
		
		datePath = delimiter + calendar.get(Calendar.YEAR) + delimiter
				+ new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1) + delimiter
				+ new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		
		return datePath;
	}

	/**
	 * 파일 확장자 체크
	 * @param fileExtension
	 * @param checkExtensions
	 * @return boolean.isPassExtension
	 */
	private static boolean checkFileExtensions(String fileExtension, String[] checkExtensions) {
		boolean isPassExtension = false;
		for(String ext: checkExtensions) {
			if(fileExtension.equals(ext)) {
				isPassExtension = true;
				break;
			}
		}
		return isPassExtension;
	}

    private static final long DEFAULT_ALLOWED_FILE_SIZE = 10485760;	//10MB

    public static Map<String, String> saveSingleFile(MultipartFile multipartFile, String strSaveDirPath) {
        return FileUploadHelper.saveSingleFile(multipartFile, strSaveDirPath, DEFAULT_ALLOWED_FILE_SIZE, null);
    }

    public static Map<String, String> saveSingleFile(MultipartFile multipartFile, String strSaveDirPath, long intAllowedFileSize) {
        return FileUploadHelper.saveSingleFile(multipartFile, strSaveDirPath, intAllowedFileSize, null);
    }

    public static Map<String, String> saveSingleFile(MultipartFile multipartFile, String strSaveDirPath, String[] arrAllowedMimeType) {
        return FileUploadHelper.saveSingleFile(multipartFile, strSaveDirPath, DEFAULT_ALLOWED_FILE_SIZE, arrAllowedMimeType);
    }

    public static Map<String, String> saveSingleFile(MultipartFile multipartFile, String strSaveDirPath, long intAllowedFileSize, String[] arrAllowedMimeType) {
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFileName);
        long fileSize = multipartFile.getSize();

        Map<String, String> objRtMap = new LinkedHashMap<>();
        objRtMap.put("RT_CODE"           , FILE_UPLOAD_SUCCESS_RT_CODE);
        objRtMap.put("RT_MSG"            , "파일 업로드를 완료했습니다.");
        objRtMap.put("ORIGINAL_FILE_NAME", originalFileName);

        //File Directory Check
        if (!FileUtils.makeDirGenerator(strSaveDirPath)) {
            objRtMap.put("RT_CODE"  , FILE_UPLOAD_DIRECTORY_RT_CODE);
            objRtMap.put("RT_MSG"   , "파일 업로드를 진행할 수 없습니다. 파일 위치가 제공되지 않았습니다.");
        }

        //File MimeType Check
        if( null != arrAllowedMimeType ) {
            if (!FileUtils.isAllowedMimeType(multipartFile, arrAllowedMimeType)) {
                objRtMap.put("RT_CODE"  , FILE_UPLOAD_MIME_TYPE_RT_CODE);
                objRtMap.put("RT_MSG"   , "파일 업로드를 진행할 수 없습니다. 파일 유형이 허용되지 않습니다.");
            }
        }

        //File Size Check
        if (fileSize > intAllowedFileSize) {
            objRtMap.put("RT_CODE"  , FILE_UPLOAD_SIZE_RT_CODE);
            objRtMap.put("RT_MSG"   , "파일 업로드를 진행할 수 없습니다. 최대 파일 크기를 초과했습니다.");
        }

        try {
            if(FILE_UPLOAD_SUCCESS_RT_CODE.equals(objRtMap.get("RT_CODE"))) {
                byte fileData[] = multipartFile.getBytes();
                String strRtFileSaveName = makeFileName() + "." + extension.toLowerCase(),
                        strRtFileFullPath = FileUtils.getUniqueFileName(strSaveDirPath + strRtFileSaveName);

                FileOutputStream fileOutputStream = new FileOutputStream(strRtFileFullPath);
                fileOutputStream.write(fileData);
                fileOutputStream.close();

                objRtMap.put("SAVE_FILE_SIZE"    , String.valueOf(fileSize));
                objRtMap.put("SAVE_FILE_PATH"    , strSaveDirPath);
                objRtMap.put("SAVE_FILE_NAME"    , FilenameUtils.getBaseName(strRtFileFullPath) +"."+FilenameUtils.getExtension(strRtFileFullPath));
            }
        } catch (Exception e){
            objRtMap.put("RT_MSG", e.getMessage());
            objRtMap.put("RT_CODE", FILE_UPLOAD_ERROR_RT_CODE);
        }
        return objRtMap;
    }

    public static String makeFileName() {
        SimpleDateFormat sdfCurrent = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return sdfCurrent.format(ts.getTime());
    }


}
