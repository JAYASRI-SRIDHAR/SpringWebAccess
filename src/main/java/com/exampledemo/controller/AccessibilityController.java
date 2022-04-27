package com.exampledemo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
//import org.junit.Assert;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import com.deque.axe.AXE;
import com.exampledemo.service.scan.AccessibilityScanService;
import com.exampledemo.service.scan.AccessibilityScanServicePdfGeneration;

import java.awt.Desktop;
import java.io.File;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;

import com.deque.axe.AXE;
import com.exampledemo.model.AccessibilityBean;
import com.exampledemo.service.AccessibilityService;
import com.exampledemo.service.scan.AccessibilityScanService;
import com.exampledemo.service.scan.serviceImpl.AccessibilityScan;
import com.itextpdf.text.DocumentException;

import io.github.bonigarcia.wdm.WebDriverManager;

@Controller
public class AccessibilityController {
	
//    @Autowired
//    private AccessibilityService service;
//
//   
	
	String folderPath=".\\GeneratedPDF\\";

    @Autowired
    private AccessibilityScanService scan;
    @Autowired
    private AccessibilityScanServicePdfGeneration generate;
    
    @RequestMapping("/")
	public String home() {
		System.out.println("This is the homepg url");
		
		return "Myhtml";

	}
	
	@RequestMapping("/Myhtml.html")
	public String homepgnavigation() {
		System.out.println("This is the homepg url");
		
		return "Myhtml.html";

	}
	@RequestMapping(path="/showFiles.html",method=RequestMethod.POST)
	public String handelurl(HttpServletRequest request,RedirectAttributes redirAttrs,Model model) throws DocumentException, IOException, ParseException
	{
	    redirAttrs.addFlashAttribute("message", "This is message from flash");

		String url=request.getParameter("urlname");
		System.out.println(url);
		scan.launch(url);
		generate.GeneratePDF();
		File folder=new File(folderPath);
	 	File[] listOfFiles=folder.listFiles();
	 	model.addAttribute("files",listOfFiles);
	 	return "showFiles.html";
	}
	
//	@RequestMapping("/Myhtml2.html")
//	public String handelpdf() {
//		System.out.println("This is the homepg2 url");
//		
//		return "Myhtml2.html";
//
//	}
	
//	 @RequestMapping(path = "/analyzePdf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	    public String analyzePdf(HttpServletRequest request) {
//	        System.out.println("Inside analyze PDF");
//	        String FileLocation=".\\WebAccessibilityPrototypeV3\\Web Accessibility Scan Results.pdf";
//	        System.out.println("File Location:" +FileLocation);
//	        scan.scanPdf(FileLocation);
//	        return "PDF analyzed";
//	    }
//	 @RequestMapping("/showFiles.html")
//	 public String showFiles(Model model) {
//	 	File folder=new File(folderPath);
//	 	File[] listOfFiles=folder.listFiles();
//	 	model.addAttribute("files",listOfFiles);
//	 	return "showFiles.html";
//	 }
	 @RequestMapping("file/{fileName}")
	 @ResponseBody
	 public void show(@PathVariable("fileName")String fileName, HttpServletResponse response)
	 {
	 	if(fileName.indexOf(".pdf")>-1) response.setContentType("application/pdf");
	 	response.setHeader("Content-Disposition", "attachment: filename="+fileName);
	 	try {
	 		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
	 		try (FileInputStream fis = new FileInputStream(folderPath+fileName)) {
	 			int len;
	 			byte[] buf=new byte[1024];
	 			
	 			while((len = fis.read(buf))>0) {
	 				bos.write(buf,0,len);
	 			}
	 		}
	 		bos.close();
	 		response.flushBuffer();
	 	}
	 	catch(IOException e){
	 		e.printStackTrace();
	 	}
	 	
	 	

	 }
	
    }

	  
