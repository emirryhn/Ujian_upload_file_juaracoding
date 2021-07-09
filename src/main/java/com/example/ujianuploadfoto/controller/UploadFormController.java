package com.example.ujianuploadfoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ujianuploadfoto.entity.CuriculumVitae;
import com.example.ujianuploadfoto.repository.CvRepository;
import com.example.ujianuploadfoto.util.FileUploadUtil;

@Controller
public class UploadFormController {

	@Autowired
	CvRepository cvRepo;
	
	@GetMapping("/")
	public String firstPage(Model model) {
		model.addAttribute("addcv", new CuriculumVitae());
		
		
		return "uploadForm.html";
		
	}
	
	@PostMapping("/addCv")
	public String addCv(@RequestParam ("fullName") String fullName, @RequestParam ("email") String email,
			@RequestParam ("platform") String platform, @RequestParam("photo") MultipartFile file, Model model, RedirectAttributes redirAttrs) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		CuriculumVitae cv = new CuriculumVitae(0, fullName, email, platform, fileName);
		cv.setPhoto(fileName);
		this.cvRepo.save(cv);
		
		String fileDir = "c:/cvupload/"+fileName;
		try {
			FileUploadUtil.uploadFile(fileDir, fileName, file);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		redirAttrs.addFlashAttribute("messageDone", "CV uploaded!!");
		return "redirect:/" ;
		
	}
	
}
