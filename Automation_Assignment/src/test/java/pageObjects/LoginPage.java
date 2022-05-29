package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
public WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	WebElement email;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@class=\"_2KpZ6l _2HKlqd _3AWRsL\"]")
	WebElement loginButton;
	
	@FindBy(xpath="//div[text()='Logout']")
	WebElement logoutButton;
	
	public void Username(String uname)
	{
		email.sendKeys(uname);
	}
	
	public void Password(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void LoginBtnClick()
	{
		loginButton.click();
	}
	
	public void LogoutBtnClick()
	{
		logoutButton.click();
	}

}
