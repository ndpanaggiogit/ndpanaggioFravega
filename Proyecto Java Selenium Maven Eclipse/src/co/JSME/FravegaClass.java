package co.JSME;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import java.util.List;

public class FravegaClass {
	private WebDriver driver;
	public String urlBase = "https://www.fravega.com";
	
	@BeforeTest
	public void launchChromeAndEnterMainPage() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver 86\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(urlBase);
		Thread.sleep(1000);

		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		WebElement SearchBox = driver.findElement(By.xpath("//input[@placeholder='Buscar productos']"));
		SearchBox.sendKeys("Heladeras");
		Thread.sleep(1500);
		SearchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		WebElement FiltroHeladeras = driver.findElement(By.xpath("//a[@class='styled__Toggle-c638c2-2 fHtnBi']"));
		Thread.sleep(500);
		FiltroHeladeras.click();
		Thread.sleep(5000);
		
		WebElement fatherUl = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div/div[2]")).findElement(By.tagName("ul"));
		List<WebElement> childIl = fatherUl.findElements(By.tagName("li"));
		
		for (int i = 0; i < childIl.size(); i++)
		{
			String childOfChilds = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div/div[2]/section/ul[1]/li[9]/div/a/article/div/h4")).getText();
			assertTrue(childOfChilds.contains("Vondom"));
		}
		
		WebElement fatherUlLength = (WebElement) driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div/div[2]")).findElement(By.tagName("ul")).getSize();
		int childIlLength = fatherUlLength.findElements(By.tagName("li")).size();
		assertEquals(childIlLength,fatherUlLength);
        }
	
	@AfterTest
	public void endSession() {
		driver.quit();
	}
}
