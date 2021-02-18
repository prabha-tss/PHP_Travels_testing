package product_validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class main {
	static WebDriver driver;
	static ChromeOptions options;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	@BeforeSuite
	void browserinitialization() throws IOException {
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium Driver\\chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("-start-maximized");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		driver = new ChromeDriver(options);
		driver.get("https://www.phptravels.net");
		//Path of the excel file
		FileInputStream wb = new FileInputStream("F:\\product_validation\\testdata\\test.xlsx");
		//Creating a workbook
		 workbook = new XSSFWorkbook(wb);
		 sheet = workbook.getSheet("login_data");
		
	}
	@Test(priority=1)
	void loginval() {		
		PageFactory.initElements(driver,dataroot.class);	
		dataroot.myac.click(); 
		dataroot.log.click();		  
		  WebDriverWait wait = new WebDriverWait(driver,10);
		  wait.until(ExpectedConditions.elementToBeClickable(dataroot.Eid));		
		 int loginpct= sheet.getLastRowNum();
		
		 for(int i=1; i<=loginpct; i++) {						
				XSSFRow row = sheet.getRow(i);
				String Eid = row.getCell(0).toString();
				dataroot.Eid.sendKeys(Eid);
				String Eidpwd = row.getCell(1).toString();
				dataroot.Eidpwd.sendKeys(Eidpwd); 
				dataroot.Logclk.click();
				try {
					WebDriverWait waitvl = new WebDriverWait(driver,3);
					boolean titver =	waitvl.until(ExpectedConditions.titleIs("My Account"));
					//String Exptit ="My Account";
					Assert.assertEquals(titver, true);
					System.out.println("test is pass");
					
					break;
					
				} catch (Exception e) {
					System.out.println("Fail");
					TakesScreenshot	sc = (TakesScreenshot) driver;
					File sorcePicLoc = sc.getScreenshotAs(OutputType.FILE);
					File saveLoc = new File ("F:\\product_validation\\op\\"+Eid+".png");
					try {
						org.openqa.selenium.io.FileHandler.copy(sorcePicLoc,saveLoc);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					driver.navigate().refresh();
				}
		 }	 
}
	
	@Test(priority=2)
	void acMyprofile() {
		PageFactory.initElements(driver,dataroot.class);
		System.out.println("brake is working");
		 String Actit = "My Account";
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.titleIs("My Account"));		 
		 Assert.assertEquals(driver.getTitle(), Actit);		 
		 System.out.println("Landed SucessFull...!");
		dataroot.home.click();
		WebDriverWait waitvl = new WebDriverWait(driver,3);
		boolean titver =	waitvl.until(ExpectedConditions.titleIs("PHPTRAVELS | Travel Technology Partner"));
		
		Assert.assertEquals(titver, true);	
		System.out.println("Sucess");
	}
	
	@Test(priority=3)
	void hotelsearch() {
		PageFactory.initElements(driver,dataroot.class);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(dataroot.Hoteldiv));
		dataroot.Hoteldiv.click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='select2-drop']/ul/li[2]/ul/li[1]")));
		
	List <WebElement> hotellist =	driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li[2]/ul/li"));
	System.out.println(hotellist.size());
	for (WebElement list : hotellist) {
		System.out.println(list.getText());
		String Hotls = "Dubai, United Arab Emirates";
		if(list.getText().equalsIgnoreCase(Hotls)) {
			list.click();
			break;
		}
	}
	dataroot.datein.clear();
	dataroot.datein.sendKeys("01/01/2021");
	dataroot.dateout.clear(); 
	dataroot.dateout.sendKeys("02/01/2021");
	dataroot.homesubmit.click();
	wait.until(ExpectedConditions.elementToBeClickable(dataroot.hottellist));
	List <WebElement> Hotel_List = driver.findElements(By.xpath("//*[@class='product-long-item']//h5/a"));
	System.out.println(Hotel_List.size());
	String ctwind = driver.getWindowHandle();
	System.out.println(driver.getWindowHandle());
	for (WebElement Hotel_ele : Hotel_List) {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		Hotel_ele.sendKeys(selectLinkOpeninNewTab);
		System.out.println(driver.getTitle());
		System.out.println("Tab Open"+driver.getWindowHandle());
		Set<String> winh = driver.getWindowHandles();
		
		for (String hnd : winh) {
			if(ctwind.equals(hnd)) {
				driver.switchTo().window(ctwind);
			}else {
				driver.switchTo().window(hnd);
				driver.close();
				break;
			}
		}
		
		driver.switchTo().window(ctwind);
	}
	driver.navigate().back();
	
	}
	@Test(priority=4)
	void flight() {
		PageFactory.initElements(driver,dataroot.class);
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(dataroot.homeFlightClk));
		 
		 dataroot.homeFlightClk.click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='s2id_location_from']/a")));
		 boolean a = driver.findElement(By.xpath("//div[@id='flights']/div/div/form/div/div/div/div/div/div[2]/label")).isSelected();
		  if(a==true) {
			  System.out.println("Selected");
		  }else {
			  System.out.println("not selected");
			  driver.findElement(By.xpath("//div[@id='flights']/div/div/form/div/div/div/div/div/div[2]/label")).click();	
		  }
		  try {
			  Select ecnmy = new Select(dataroot.flightclass);
			  ecnmy.selectByVisibleText("Business");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@id='flights']/div/div/form/div/div/div[1]/div[2]/div/div/a")).click();
			driver.findElement(By.xpath("//*[@class='chosen-results']/li[1]")).click();
		}
		/*dataroot.fltFrom.click();
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("New");
		WebDriverWait wit = new WebDriverWait(driver,30);
		List <WebElement>	ela =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='select2-drop']/ul/li")));
		for (WebElement webElement : ela) {
			System.out.println(webElement.getText());
			if(webElement.getText().equals("Newcastle (NCS)")) {
				webElement.click();
				break;
			}
		}*/
		dataroot.fltFromOut.click();
		dataroot.flightOtInp.sendKeys("New");
		List <WebElement>	fltout =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='select2-drop']/ul/li")));
		for (WebElement webElement : fltout) {
			if(webElement.getText().equals("Newport (NPT)")) {
				webElement.click();
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id='FlightsDateStart']")).click();
		WebDriverWait dtwait = new WebDriverWait(driver,30);
		List<WebElement> month =	 dtwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='datepicker--cells datepicker--cells-months']//div")));
			 
			for (WebElement webElement : month) {
				if(webElement.getText().equals("Mar")) {
					webElement.click();
				}
			}
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='datepickers-container']/div[9]/div/div[1]/div[2]//div")));
			List<WebElement> date = driver.findElements(By.xpath("//*[@id='datepickers-container']/div[9]/div/div[1]/div[2]//div"));
			String expectdt = "5";
				for (WebElement webElement : date) {
					System.out.println("try");
					System.out.println(webElement.getText());
					if(webElement.getText().equals(expectdt)) {
						boolean dt=  webElement.isEnabled();
						if(dt=true) {
							webElement.click();
							break;
						}
					}
				}
			
	}
	
	@AfterTest()
	void afttst() {
		System.out.println("Afttst");
	}
	
	
}
	
