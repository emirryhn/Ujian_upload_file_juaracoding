package com.example.ujianuploadfoto.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static void uploadFile(String uploaddir, String filename, MultipartFile file) throws IOException {
		
		Path path = Paths.get(uploaddir);
		
		if(!Files.exists(path)) {
			Files.createDirectories(path);
		}		
		
		try(InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING );
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	  
}
	   
