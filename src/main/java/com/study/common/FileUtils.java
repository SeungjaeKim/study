package com.study.common;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FileUtils {
    public static final String FILE_UPLOAD_SUCCESS_RT_CODE		= "0000";	/* file success */
    public static final String FILE_UPLOAD_ERROR_RT_CODE		= "9999";	/* file error */
    public static final String FILE_UPLOAD_MIME_TYPE_RT_CODE	= "1000";	/* file mimetype */
    public static final String FILE_UPLOAD_SIZE_RT_CODE			= "2000";	/* file size */
    public static final String FILE_UPLOAD_DIRECTORY_RT_CODE	= "3000";	/* file path*/
    public static final String[] IMAGE_MIME_TYPE = {"image/jpg", "image/jpeg", "image/png", "image/gif", "image/x-ms-bmp"};

	public static String getMimeType(MultipartFile multipartFile) {
		
		File file = new File(multipartFile.getOriginalFilename());

		AutoDetectParser parser = new AutoDetectParser();
		parser.setParsers(new HashMap<MediaType, Parser>());

		Metadata metadata = new Metadata();
		metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());

		InputStream stream;
		String mimeType = "";
		
		try {
			stream = multipartFile.getInputStream();
			parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
			stream.close();
			mimeType = metadata.get(HttpHeaders.CONTENT_TYPE);
		} catch (IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			mimeType = "";
		}
		
		return mimeType;
		
	}
	
	public static boolean isAllowedMimeType(MultipartFile multipartFile, String[] allowedMimeTypes) {
		if (multipartFile.isEmpty())
			return false;
		
		if (allowedMimeTypes.length == 0)
			return true;
		
		String strMimeType = getMimeType(multipartFile);
		
		if( "".equals(strMimeType) ){
			return false;
		}
		for (String allowdMimeType : allowedMimeTypes) {
			if(strMimeType.startsWith(allowdMimeType) )
				return true;
		}
		
		return false;
	}
	
	public static boolean makeDirGenerator(String strDirPath){
		File objFile = new File(strDirPath);
		if (!objFile.exists()) {
			try{
				org.apache.commons.io.FileUtils.forceMkdir(objFile);
				return true;
			}catch(Exception e){
				return false;
			}
		} else {
			return true;
		}
	}
	
    public static String getUniqueFileName(String strFullFilePath) throws IOException {

        StringBuffer sb = new StringBuffer();
        String strFullPath, strBaseName, strExtension;
        char underLine = '_';
        File objFile;
        int intCnt;

		do {
			objFile = new File(strFullFilePath);
			
			if( objFile.exists() ){
				
				sb.setLength(0);
				
				strFullPath = FilenameUtils.getFullPath(strFullFilePath);
				strBaseName = FilenameUtils.getBaseName(strFullFilePath);
				strExtension = FilenameUtils.getExtension(strFullFilePath);
				
				try{
					intCnt = Integer.parseInt(strBaseName.substring( strBaseName.lastIndexOf(underLine) + 1, strBaseName.length() ));
					strBaseName = strBaseName.substring(0, strBaseName.lastIndexOf(underLine));
				}catch (NumberFormatException e) {
					// TODO: handle exception
					intCnt = 0;
				}
				
				intCnt++;
				
				sb.append( strFullPath )
					.append( strBaseName )
					.append( underLine )
					.append( intCnt )
					.append( FilenameUtils.EXTENSION_SEPARATOR_STR )
					.append( strExtension );
				
				strFullFilePath = sb.toString();
				
			}
			
		} while (objFile.exists());
		
		return strFullFilePath;
	}

    private FileUtils(){}
	
}
