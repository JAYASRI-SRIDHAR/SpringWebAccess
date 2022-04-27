package com.exampledemo.service.scan.serviceImpl;

import java.net.URL;

import org.json.JSONArray;
//import org.junit.Assert;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import com.exampledemo.service.scan.AccessibilityScanService;
import com.deque.axe.AXE;
import java.awt.Desktop;
import java.io.File;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class AccessibilityScan implements AccessibilityScanService {
    private WebDriver driver;

    private static final URL scriptURL = AccessibilityScan.class.getResource("/axe.min.js");

    private void setup(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    // public static void main(String args[]) {
    // // String url="http://localhost:8080/home.html";
    // String url = "https://www.facebook.com";
    // AccessibilityScan obj = new AccessibilityScan();
    // obj.launch(url);
    // }

    private void scanWebsite() {
        // SoftAssert soft= new SoftAssert();
        JSONObject responseJson = new AXE.Builder(driver, scriptURL).analyze();
        JSONArray violations = responseJson.getJSONArray("violations");

        if (violations.length() == 0) {
            System.out.println("no errors");

        } else {
            AXE.writeResults("JsonNewReport", responseJson);
            // soft.assertAll();

        }
    }

    private void tearDown() {
        driver.quit();
    }

    @Override
    public void launch(String url) {
        // AccessibilityScan obj = new AccessibilityScan();
        setup(url);
        scanWebsite();
        tearDown();
    }

    @Override
    public void scanPdf(String fileLocation) {
        try {

            File pdfFile = new File(fileLocation);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " + pdfFile);
                    System.out.println("Awt Desktop is not supported!");
                }

            } else {
                System.out.println("File does not exists!");
            }

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        AccessibilityScan sc = new AccessibilityScan();
        sc.scanPdf("");
    }

}
