package com.truleagueprospectbase;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.truleagueprospect.utiles.Utilites;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	//constructor
	public Base() {
		
		prop=new Properties();
		
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\truleagueprospect\\config\\config.properties"); 
		
		dataProp=new Properties();
		File dataPropFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\truleagueprospect\\testdata\\testdata.properties");
		try {
			FileInputStream fis2=new FileInputStream(dataPropFile);
			dataProp.load(fis2);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		try {
		FileInputStream fis=new FileInputStream(propFile);
		prop.load(fis);
		}catch (Throwable e ) {
			e.printStackTrace();
		}
	}
	public WebDriver intializeBrowserAndOpenApplicationUrl(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			 driver=new ChromeDriver();
		 }else if(browserName.equalsIgnoreCase("firefox")) {
			 driver=new FirefoxDriver();
		 }else if(browserName.equalsIgnoreCase("edge")) {
			 driver=new EdgeDriver();
		 }else {
			 System.out.println("None of the Browser Invoked !");
		 }
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilites.IMPLICIT_WAIT_TIME));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilites.PAGE_LOAD_TIME));//coming from utilites.java 
	driver.get(prop.getProperty("url"));//getting url from config.properties file 
	
	return driver;
	}
}
