package com.exampledemo.service.scan;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

@Service
public interface AccessibilityScanServicePdfGeneration {
	
    public void GeneratePDF() throws DocumentException, IOException, ParseException;

}
