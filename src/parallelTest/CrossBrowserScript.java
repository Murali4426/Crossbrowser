package parallelTest;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserScript {

	WebDriver driver;
	
	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		
		
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.firefox.marionette", ".\\geckodriver.exe");
			 DesiredCapabilities capabilities=DesiredCapabilities.firefox();
             capabilities.setCapability("marionette", true);
             driver = new FirefoxDriver(capabilities);
			
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("disable-popup-blocking");
			options.addArguments("nativeEvents", "true");
			options.addArguments("chrome.switches","--disable-extensions");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true); 
			driver = new ChromeDriver(capabilities);
			
		}
		//Check if parameter passed as 'Edge'
				else if(browser.equalsIgnoreCase("Edge")){
					//set path to Edge driver.exe
					System.setProperty("webdriver.edge.driver",".\\IEDriverServer.exe");
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability("ignoreZoomSetting", true);
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					driver = new EdgeDriver(capabilities);
				}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.get("http://localhost:5000/home");
		System.out.println("URL LAUNCHED");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testParameterWithXML() throws InterruptedException{
		
		//Test data to validate the Price of each item
		String[] priceval= {"$5.00","$6.00","$5.50","$5.00","$5.00","$5.00"};
		int i;
		List<WebElement> rows;
		WebElement Storageoption=null;
		JavascriptExecutor executor=null;
		int qty=0;
		String qtynumber=null;
		WebElement objqtynumber=null;
		int waitTime=200;
		WebDriverWait wait=null;
		int currentprice=0;
		String updateqty="2";
		String Currentpriceval=null;
		String updatepriceval=null;
		
		//Object Properties declaration
				By tbl_Items=By.xpath("//div[@class='List__IceCreamWrapper-gLcbLs foCtUG']");
				By Addtocart=By.xpath("//button[contains(text(),'Add to cart')]");
				By Cancel=By.xpath("//button[contains(text(),'CANCEL')]");
				By Cart=By.xpath("//div[contains(text(),'Cart')]");
				By Checkout=By.xpath("//button[contains(text(),'Checkout')]");
				By Checkputconfirmationmessage=By.xpath("//div[contains(text(),'Enter shipping info')]");
				By Purchase=By.xpath("//button[contains(text(),'Purchase')]");
				By Qtyupdate=By.xpath("//div[contains(text(),'Butter')]/parent::div/input");
				By TotalPrice=By.xpath("//div[contains(text(),'Butter')]/parent::div");
				By Address=By.xpath("//div[contains(@class,'Checkout__InputField-pihZC')]/select");
				By Notes=By.xpath("//div[contains(@class,'Checkout__InputField-pihZC')]/textarea");
				By AddressTxt=By.xpath("//div[contains(text(),'Address')]/parent::div");
				By NotesTxt=By.xpath("//div[contains(text(),'Notes')]/parent::div");
				By Orderdetails=By.xpath("//div[contains(@class,'Confirmation__ItemWrapper')]");
				By Orderconfirmationmessage=By.xpath("//div[contains(text(),'Thank you for your order')]");
				
				/*--validate price using FindbyElements

				       1. Verify the prices of each ice cream flavor.  
						     Starting from the top left:
						   - Butter Pecan is $5.00
						   - Chocolate is $6.00
						   - Cookies and Cream is $5.50   
						   - Pistachio is $5.00
						   - Strawberry is $5.00
						   - Vanilla is $5.00*/
				
				       // Here, the code below will select all rows matching the given XPath.
						rows = driver.findElements(tbl_Items);

						// print the total number of elements
						System.out.println("Total selected rows are " + rows.size());
						
						System.out.println("Total selected prices " + priceval.length);

						if(priceval.length==rows.size())
						{
							// Now using Iterator we will iterate all elements
							Iterator<WebElement> iter = rows.iterator();
			
							// this will check whether list has some element or not
							for(i=1;i<=rows.size();i++)	
							{
								
								By txt_Itemprice=By.xpath("//div[@class='List__IceCreamWrapper-gLcbLs foCtUG']["+i+"]/div");
													
								//display the each item price
			                    System.out.println("Each cell xpth:"+txt_Itemprice);
			                    
								// get the text
								String price = driver.findElement(txt_Itemprice).getText().toString();
			                    
								// print the text
								System.out.println("Item price " + price);
								
								//validating the each item price 
								if(price.equals(priceval[i-1]))
								{
									System.out.println("Item price is matching with the test data " +price);
								}
								else
								{
									System.out.println("Item price is not matching with the test data " +price);
								}
								System.out.println("loop value " + i);
			
							}
					 }
						else
						{
							System.out.println("Test data and Items are not matching. ");
						}
						
						/*--validate   AddtoCart Button is disabled
						2. Verify that the user can only add ice cream to their cart when they pick a type of storage.  
						Note: Sauces and Toppings are optional, while Storage is required.*/
						
						//validate the Addtocart button is disable to without selecting the Storage
						
						driver.findElement(By.xpath("//div[@class='List__IceCreamWrapper-gLcbLs foCtUG'][1]/div")).click();
						
						WebElement button = driver.findElement(Addtocart);
						
					    if(button.isEnabled())
						{
							   System.out.print("\nAddtocartis enabled. Take your action.");
						}
						else
						{
							   System.out.print("\nAddtocart is disabled.");
						}
						
						//Select on Cup option from Storage
						Storageoption = driver.findElement(By.xpath("//div[contains(text(),'Cup')]/parent::div/div/label"));
						executor = (JavascriptExecutor)driver;
						executor.executeScript("arguments[0].click();", Storageoption);
						
						//Validate Addtocart button is enable after selecting the one of the option from storage
						if(button.isEnabled())
						{
							   System.out.println("\nAddtocartis enabled.");
						}
						else
						{
							   System.out.println("\nAddtocart is disabled.Take your action.");
						}
						
						//Click on Cancel button
						WebElement Cancelbutton = driver.findElement(Cancel);
						Cancelbutton.click();
						
						//Wait until Checkout button is displayed
						wait = new WebDriverWait(driver, waitTime);
						wait.until(ExpectedConditions.elementToBeClickable(Checkout));
						
						/*
						 * 3. Verify that the cart is updated whenever the user adds an ice cream to their cart.  
							Note: Add 3 flavors to the cart.
						 */
						for(i=1;i<=3;i++)	
						{
							
							//Select the Cup option deom storage
							driver.findElement(By.xpath("//div[@class='List__IceCreamWrapper-gLcbLs foCtUG']["+i+"]/img")).click();
							Storageoption = driver.findElement(By.xpath("//div[contains(text(),'Cup')]/parent::div/div/label"));
							executor = (JavascriptExecutor)driver;
							executor.executeScript("arguments[0].click();", Storageoption);
							
							
						    //Click  on the Addtocart button
							Storageoption = driver.findElement(Addtocart);
							executor = (JavascriptExecutor)driver;
							executor.executeScript("arguments[0].click();", Storageoption);
							
							objqtynumber=driver.findElement(Cart);
							qtynumber=objqtynumber.getText().split("-")[1].trim();
							if(qty<Integer.parseInt(qtynumber))
							{
								System.out.println("Cart updated with item");
							}
							else
							{
								System.out.println("Cart is not updated with item");
							}
							
							//Wait until Checkout button is displayed
							wait = new WebDriverWait(driver, waitTime);
							//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Checkout')]")));
							wait.until(ExpectedConditions.elementToBeClickable(Checkout));
						}
		                
						//Click on Checkout button
						driver.findElement(Checkout).click();
						
						
					   /*
					    * 4. On the Checkout page, change the quantity of the ice cream and verify that the price is updated correctly.  
							 Note: Add at least 2 ice cream flavors with various toppings to the cart.
							 
							 Not Completed
					   */
						
						//Need to work on the Checkoput page
						  
						 
						//Wait until order confirmation page
						/*wait = new WebDriverWait(driver, waitTime);
						wait.until(ExpectedConditions.textToBe(Checkputconfirmationmessage,"Enter shipping info"));
						
						
						//element is WebElement   
						WebElement Shippinginfor=driver.findElement(Checkputconfirmationmessage);
						executor = (JavascriptExecutor)driver;
						executor.executeScript("arguments[0].scrollIntoView(true);", Shippinginfor); 
						
						
						
						//get the Total price before updatiing qty
						Currentpriceval=driver.findElement(TotalPrice).getText().trim();
						
						//update the qty for at Checkout page
						driver.findElement(Qtyupdate).sendKeys(updateqty);
						
						WebElement updatequantity=driver.findElement(Qtyupdate);
						executor = (JavascriptExecutor)driver;
						executor.executeScript("arguments[0].value=/", updatequantity); 
		                
						//get the Total Price after updating the price				
						updatepriceval=driver.findElement(TotalPrice).getText().trim();
						
						if((Integer.parseInt(updatepriceval))>(Integer.parseInt(Currentpriceval)))
						{
							System.out.println("Total price is updated.");
						} */
						
						
						/*
						    * 
							5. Purchase an order and verify that the information is correct on the confirmation page.  
							Note: Pick a shipping address and fill out the notes text box.
						    */
						
						//Select Address from dropdown
						Select lstAddress = new Select(driver.findElement(Address));
						lstAddress.selectByVisibleText("29 Cactus St. Kernersville, NC 27284");
						
						//Enter Notes
						driver.findElement(Notes).clear();
						driver.findElement(Notes).sendKeys("Test");
						
						//Click on Purchase button
						driver.findElement(Purchase).click();
						
						//Wait until order confirmation page
						wait = new WebDriverWait(driver, waitTime);
						wait.until(ExpectedConditions.textToBe(Orderconfirmationmessage,"Thank you for your order"));
						
						
						rows = driver.findElements(Orderdetails);

						// print the total number of elements
						System.out.println("Total selected rows are at order confirmation page" + rows.size());
						
						
						
							// Now using Iterator we will iterate all elements
							Iterator<WebElement> iter = rows.iterator();
			
							// this will check whether list has some element or not
							while (iter.hasNext()) {

								// Iterate one by one
								WebElement item = iter.next();

								// get the text
								String label = item.getText();

								// print the text
								System.out.println("Row label is " + label);
							}
							
							//Shipping Address 
							System.out.println("Row label is " + driver.findElement(AddressTxt).getText().trim());
							
							//Entered Notes
							System.out.println("Row label is " + driver.findElement(NotesTxt).getText().trim());
	}
	
	@AfterTest
	@Parameters("browser")
	public void tearDown(String browser) 
               {
		if(browser.equalsIgnoreCase("firefox")){
			if(driver!=null) 
            {
				System.out.println("Closing firefox browser");
				driver.quit();
			}
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome"))
		{
			if(driver!=null) 
            {
				System.out.println("Closing chrome browser");
				driver.quit();
			}			
		}
		else if(browser.equalsIgnoreCase("Edge")){
			if(driver!=null) 
            {
				System.out.println("Closing Edge browser");
				driver.quit();
			}	
		}
		
	 }
}
