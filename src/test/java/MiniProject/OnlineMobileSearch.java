package MiniProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class OnlineMobileSearch {

	public static void main(String[] args) throws IOException {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver=new ChromeDriver();
		WebDriver driver=DriverSetup.getWebDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Navigating to the Amazon application
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src1=ts.getScreenshotAs(OutputType.FILE);
		File trg1=new File("C:\\Users\\2303518\\Testing\\seleniumproject\\Screenshot\\SearchPage.png");
		FileUtils.copyFile(src1, trg1);
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("mobile smartphones under 30000");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		//Validating the no of pages and no of results
		String text=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]")).getText();
		System.out.println(text);
		String noOfPages=text.substring(0,4);
		String noOfResults=text.substring(text.indexOf('r')+2,text.indexOf('r',text.indexOf('0')+1)-1);
		if(noOfPages.equals("1-16"))
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
		if(Integer.parseInt(noOfResults.replaceAll(",",""))>90000)
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
		}
		
		//Handling the Dropdown element
		WebElement drpElement=driver.findElement(By.xpath("//select[@name='s']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", drpElement);
		Select option=new Select(drpElement);
		List<WebElement> options=option.getOptions();
		
		//Validating the no. of option in sort by list
		if((options.size()-1)==4)
		{
			System.out.println("No of Option Displayed is 4");
		}
		else {
			System.out.println("Test Failed");
		}
		
		//Validating Newest Arrival Option is selected or not
		option.selectByVisibleText("Newest Arrivals");
		String selectedOption=driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
		
		if(selectedOption.equals("Newest Arrivals"))
		{
			System.out.println("Correct option is selected");
		}
		else {
			System.out.println("Test Failed");
		}
		
		File src2=ts.getScreenshotAs(OutputType.FILE);
		File trg2=new File("C:\\Users\\2303518\\Testing\\seleniumproject\\Screenshot\\Mobile.png");
		FileUtils.copyFile(src2, trg2);
		
		driver.quit();
	
	}

}
