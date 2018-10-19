package edu.bsuc.service;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

	public Map<String,Object> importProduct(File tempFile, MultipartFile file);
}
