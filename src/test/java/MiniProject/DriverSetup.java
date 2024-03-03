package MiniProject;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
 
public class DriverSetup {	
	    static WebDriver getWebDriver() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice(1/2)?");
		System.out.println("1. Chrome Browser");
		System.out.println("2. Edge Browser");
		WebDriver driver = null;
		int choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					driver = new ChromeDriver();
					break;
				case 2: 
					driver = new EdgeDriver();
					break;
				default:
					System.out.println("You have entered a wrong choice");
					break;
			}
		return driver;
	}
}