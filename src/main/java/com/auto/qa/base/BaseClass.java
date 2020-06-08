package com.auto.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver=null;
	public static WebDriverManager driverManager=null;
	public static Properties prop;

	@Test
	public void readProperty() {
		try {
			prop = new Properties();
			FileInputStream configfile = new FileInputStream(
			System.getProperty("user.dir") + "/resources/config.properties");
			prop.load(configfile);
			System.out.println();
		} catch (IOException e) {
			System.out.println("error loading file" +e);
		} }

	@Test
	public void startBrowser() {
		
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")){ 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(); 
			} else {
				System.out.println("specified browser not found");
			}
	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize(); 
		driver.get(prop.getProperty("url"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
		
	}
}
