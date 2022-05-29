package stepDefinitions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.*;
import pageObjects.LoginPage;

public class TestSteps {
	
	public WebDriver driver;
	public LoginPage lp;
	public String uname="";	//Please enter username/email address
	public String pwd="";	//Please enter password
	public String url="https://www.flipkart.com/";
	public String parentWindow;
	public WebElement searchItemBox;
	public WebElement searchButton;
	public Set<String> allWindows;
	public Actions action;
	
	@Given("User opens the edge browser")
	public void user_opens_the_edge_browser() {
		System.setProperty("webdriver.edge.driver", ".\\Drivers\\msedgedriver.exe");
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@When("User enters URL")
	public void user_enters_url() {
		driver.get(url);
	    parentWindow=driver.getWindowHandle();
	    System.out.println("Parent Window ID is: "+parentWindow);
	}

	@When("User enters email and password")
	public void user_enters_email_and_password() throws InterruptedException {
		Thread.sleep(3000);
		lp=new LoginPage(driver);
	    lp.Username(uname);
	    lp.Password(pwd);
	}

	@And("User clicks login")
	public void user_clicks_login() {
		 lp.LoginBtnClick();
	}

	@Then("User searches for HP laptop and adds anyone item to the cart")
	public void user_searches_for_hp_laptop_and_adds_anyone_item_to_the_cart() throws InterruptedException {
		Thread.sleep(2000);
		searchItemBox=driver.findElement(By.name("q"));
		searchItemBox.sendKeys("HP laptop");
		searchButton=driver.findElement(By.xpath("//button[@class=\"L0Z3Pu\"]"));
		searchButton.click();
		
		Thread.sleep(2000);
		WebElement laptop=driver.findElement(By
			  .xpath("//div[text()='HP OMEN 15 Ryzen 7 Octa Core 5800H - (16 GB/1 TB SSD/Windows 10 Home/6 GB Graphics/NVIDIA GeForce RTX ...']"));
		laptop.click();
		
		Thread.sleep(2000);
		allWindows=driver.getWindowHandles();
		System.out.println("All windows : "+allWindows);
		
		for(String child:allWindows)
		{
			driver.switchTo().window(child);
			System.out.println("First Child Window is: "+child);
			if(!parentWindow.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
			}
		}
		
		Thread.sleep(2000);
		driver.findElement(By.id("pincodeInputId")).sendKeys("600053");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Check']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		driver.close();
	}

	@And("Again searches for any mobile and adds anyone item to the cart")
	public void again_searches_for_any_mobile_and_adds_anyone_item_to_the_cart() throws InterruptedException {
		driver.switchTo().window(parentWindow);
		Thread.sleep(1000);
		action=new Actions(driver);
		action.moveToElement(searchItemBox).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		Thread.sleep(1000);
		searchItemBox.sendKeys("iphone 13 pro");
		searchButton.click();
		
		Thread.sleep(2000);
		WebElement mobile=driver.findElement(By.xpath("//div[text()='APPLE iPhone 13 Pro (Silver, 256 GB)']"));
		mobile.click();

		allWindows=driver.getWindowHandles();
		Thread.sleep(2000);
		
		for(String child:allWindows)
		{
			System.out.println("Second Child Window is: "+child);
			if(!parentWindow.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
			}
		}
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"ADD TO CART\"]")).click();
	}

	@Then("User removes the old item from the cart")
	public void user_removes_the_old_item_from_the_cart() throws InterruptedException {
		Thread.sleep(2000);
		WebElement removeButton1=driver.findElement(By
				  .xpath("(//a[contains(@href,'omen')]/parent::div/following::div//div[contains(text(),'Remove')])[1]"));
		JavascriptExecutor scrollVertical = (JavascriptExecutor)driver;
		scrollVertical.executeScript("arguments[0].scrollIntoView(true)", removeButton1);
		removeButton1.click();
		
		WebElement removeButton2=driver.findElement(By.xpath("(//div[text()='Cancel']/following::div[text()='Remove'])[1]"));
		removeButton2.click();
	}

	@Then("User validates if the last item is present")
	public void user_validates_if_the_last_item_is_present() {
		System.out.println("Does the last added item is in the cart? "+driver.findElement(By.linkText("APPLE iPhone 13 Pro (Silver, 256 GB)")).isDisplayed());
	}

	@And("User navigate to the parent window and logout")
	public void user_navigate_to_the_parent_window_and_logout() throws InterruptedException {
		driver.switchTo().window(parentWindow);
		WebElement user=driver.findElement(By.xpath("//div[@class='exehdJ' and not (text()='More')]"));

		Thread.sleep(3000);
		
		action.moveToElement(user).build().perform();
			  
		lp.LogoutBtnClick();
	}

	@And("User quits the chrome browser")
	public void user_quits_the_chrome_browser() {
		driver.quit();
	}

}
