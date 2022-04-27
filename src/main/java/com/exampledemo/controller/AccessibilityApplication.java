package com.exampledemo.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import java.awt.*;


@SpringBootApplication(scanBasePackages={
	"com.exampledemo.*"})
public class AccessibilityApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AccessibilityApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);	
	}

	@EventListener(ApplicationReadyEvent.class)
	 public void launchBrowser(){

	      System.setProperty("java.awt.headless", "false");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	Desktop desktop = Desktop.getDesktop();
	try{
	desktop.browse(new URI("http://localhost:8080"));
	}catch(Exception e){}
	
	}
	}

