package com.unip.tcc.scarblade.utils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImagemUtils {
	
	public static String imageEncoding(String filePath){
		byte[] fileContent;
		try {
			fileContent = FileUtils.readFileToByteArray(new File(filePath));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			return encodedString;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
