package commonFuntions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
	//method for login
public static boolean verifyLogin(String username, String password)
{
	driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
	driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
	driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
	String expected ="adminflow";
	String actual =driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login success  "+expected+"   "+actual);
		return true;
	}
	else
	{
		Reporter.log("Login success  "+expected+"   "+actual);
		return false;
	}
}
//click for branches
public static void clickBranches() throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	Thread.sleep(3000);
}
//methods for branch creation
public static boolean verifyBranch(String branchname, String Address1,String Address2, String Address3, String Area, String Zipcode, String Country, String State, String City) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjNewBrach"))).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(config.getProperty("ObjBranch"))).sendKeys(branchname);
	driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
	driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
	driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
	driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
	driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(Zipcode);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
	Thread.sleep(2000);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(State);
	Thread.sleep(2000);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(City);
	Thread.sleep(2000);
	driver.findElement(By.xpath(config.getProperty("ObjSubmit"))).click();
	Thread.sleep(2000);
	String ExpectedAlertbranch=driver.switchTo().alert().getText();
	Thread.sleep(2000);
	driver.switchTo().alert().accept();
	String ActualAlertbranch="created Sucessfully";
	if(ExpectedAlertbranch.toLowerCase().contains(ActualAlertbranch.toLowerCase()))
	{
		Reporter.log(ExpectedAlertbranch,true);
		return true;
	}
	else
	{
		Reporter.log("unable to create branch",true);
		return false;
	}
	
}
//method for update branch
public static boolean verifyBranchUpdate(String Branch, String Address, String AreaName, String Zip) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
	Thread.sleep(2000);
	WebElement element1 =driver.findElement(By.xpath(config.getProperty("ObjBranchName")));
	element1.clear();
	element1.sendKeys(Branch);
	WebElement element2 =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
	element2.clear();
	element2.sendKeys(Address);
	WebElement element3 =driver.findElement(By.xpath(config.getProperty("ObjAreaName")));
	element3.clear();
	element3.sendKeys(AreaName);
	WebElement element4 =driver.findElement(By.xpath(config.getProperty("ObjZip")));
	element4.clear();
	element4.sendKeys(Zip);
	driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
	String ExpectedAlertUpdate=driver.switchTo().alert().getText();
	Thread.sleep(2000);
	driver.switchTo().alert().accept();
	String ActualAlertUpdate=" updated Sucessfully";
	if(ExpectedAlertUpdate.toLowerCase().contains(ActualAlertUpdate.toLowerCase()))
	{
		Reporter.log(ExpectedAlertUpdate,true);
		return true;
	}
	else
	{
		Reporter.log("Unable to updating",true);
		return false;
	}
}
//click for Roles
public static void clickRoles() throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjRoles"))).click();
	Thread.sleep(3000);
}
//method for Role creation
public static boolean verifyRoleCreation(String RoleName, String RoleDisc, String RoleType) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjNewRole"))).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(config.getProperty("ObjRoleName"))).sendKeys(RoleName);
	driver.findElement(By.xpath(config.getProperty("ObjRoleDescription"))).sendKeys(RoleDisc);
	new Select(driver.findElement(By.xpath(config.getProperty("ObjRoleType")))).selectByVisibleText(RoleType);
	Thread.sleep(2000);
	driver.findElement(By.xpath(config.getProperty("ObjSubmitRole"))).click();
	Thread.sleep(2000);
	String ExpectedAlertRole=driver.switchTo().alert().getText();
	Thread.sleep(2000);
	driver.switchTo().alert().accept();
	String ActualAlertRole="created Sucessfully";
	if(ExpectedAlertRole.toLowerCase().contains(ActualAlertRole.toLowerCase()))
	{
		Reporter.log(ExpectedAlertRole,true);
		return true;
	}
	else
	{
		Reporter.log("unable to create Role",true);
		return false;
	}
}
//method for logout
public static boolean verifyLogout() throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
	Thread.sleep(3000);
	if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
	{
		Reporter.log("Logout sucess",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
}
}
