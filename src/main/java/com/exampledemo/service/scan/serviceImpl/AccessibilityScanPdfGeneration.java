package com.exampledemo.service.scan.serviceImpl;



import com.exampledemo.service.scan.AccessibilityScanServicePdfGeneration;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

@Component
public class AccessibilityScanPdfGeneration implements AccessibilityScanServicePdfGeneration{

	@Override
	public void GeneratePDF() throws DocumentException, IOException, ParseException{
		// TODO Auto-generated method stub
		
			String FILE_NAME = ".\\GeneratedPDF\\PDF_from_JsonReport.pdf";
	        Document document = new Document();


			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

	        //open
	        document.open();
	        Font f = new Font();
	        f.setStyle(Font.BOLD);
	        f.setSize(16);
	        
	        Paragraph p = new Paragraph();
	        p.setFont(f);
	        p.add("Accessibility Scan Report:");
	        p.setAlignment(Element.ALIGN_CENTER);
	        
	        Font fnt = new Font();
	        fnt.setStyle(Font.BOLD);
	        fnt.setSize(12);
			
	        document.add(p);

	       
			
			//Json file reading
		
			JSONParser jsonparser=new JSONParser();
			
			FileReader reader=new FileReader(".\\JsonNewReport.json");
			
			Object obj=jsonparser.parse(reader);
			
			JSONObject sampjsonObj=(JSONObject)obj;
			
			
			
			String url=(String)sampjsonObj.get("url");
			String timestamp=(String)sampjsonObj.get("timestamp");
	        document.add(new Paragraph("\n URL : "+url,fnt));
	        document.add(new Paragraph("\n TIMESTAMP : "+timestamp,fnt));


			JSONArray array=(JSONArray)sampjsonObj.get("violations");
	        document.add(new Paragraph("\n VIOLATIONS : "+array.size()+ "\n",fnt));

			for(int i=0;i<array.size();i++) {
				
				JSONObject violations=(JSONObject)array.get(i);
				String description=(String)violations.get("description");
				String impact=(String)violations.get("impact");
				System.out.println(i+1+". "+description+"\n    IMPACT:"+impact);
		        document.add(new Paragraph(i+1+". "+description+"\n    IMPACT:"+impact));

			}
			

			JSONArray array1=(JSONArray)sampjsonObj.get("passes");
			document.add(new Paragraph("\n PASSES : "+array1.size()+ "\n",fnt));

			for(int i=0;i<array1.size();i++) {
				
				JSONObject passes=(JSONObject)array1.get(i);
				String description=(String)passes.get("description");
				System.out.println(i+1+". "+description+"\n    STANDARD:  wcag2a");
		        document.add(new Paragraph(i+1+". "+description+"\n    STANDARD:  wcag2a"));

			}
			

			JSONArray array2=(JSONArray)sampjsonObj.get("inapplicable");
			document.add(new Paragraph("\n INAPPLICABLE : "+array2.size()+ "\n",fnt));

			for(int i=0;i<array2.size();i++) {
				
				JSONObject inapplicable=(JSONObject)array2.get(i);
				String description=(String)inapplicable.get("description");
				System.out.println(i+1+". "+description+"\n    STANDARD:+  wcag2a");
		        document.add(new Paragraph(i+1+". "+description+"\n    ISTANDARD:  wcag2a"));

			}

			JSONArray array3=(JSONArray)sampjsonObj.get("incomplete");
			document.add(new Paragraph("\n INCOMPLETE :"+array3.size()+ "\n",fnt));

			for(int i=0;i<array3.size();i++) {
				
				JSONObject incomplete=(JSONObject)array3.get(i);
				String description=(String)incomplete.get("description");
				System.out.println(i+1+". "+description+"\n    STANDARD:  wcag2a");
		        document.add(new Paragraph(i+1+". "+description+"\n    STANDARD:  wcag2a"));

			}
		        //close
		        document.close();

		        System.out.println("Done");    
		}

		
	

	
}



