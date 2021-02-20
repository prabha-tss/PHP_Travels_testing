package product_validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class dataroot {
	@FindBy(xpath="//a[text()=' My Account                  ']") 
	public static  WebElement myac;

	@FindBy(xpath="//a[text()='Login']") 
	public static WebElement log;
	@FindBy(xpath="//*[@name='username']") 
	public static WebElement Eid;

	@FindBy(xpath="//*[@name='password']") 
	public static WebElement Eidpwd;
	@FindBy(xpath="//*[@id='loginfrm']/button") 
	public static WebElement Logclk;
	@FindBy(xpath="//a[text()='My Profile']") 
	public static WebElement Myprofil;
	@FindBy(xpath="//*[@id='profilefrm']//div//select")
	public static WebElement ttt;

	@FindBy(xpath="//*[@id='profilefrm']/div/div/div[4]/div[2]/div/div[2]/a") 
	public static WebElement MyprofCont;
	@FindBy(xpath="//*[@class='chosen-drop']//input")
	public static WebElement Continp;
	@FindBy(xpath="//*[@id='profilefrm']/div/div/div[4]/div[2]/div/div[2]/div/ul/li[82]")
	public static WebElement indd;
	@FindBy(xpath="//option[@value='IN']") 
	public static WebElement ind;
	@FindBy(xpath="//*[@class='chosen-results']//li")
	public static By continput;
	@FindBy(xpath="//*[@id='profilefrm']//select") 
	public static WebElement Myprofsrchinp;

	@FindBy(xpath="//a[text()='Newsletter']") 
	public static WebElement Newsletter;
	@FindBy(xpath="//*[@id='newsletter']//label/span") 
	public static WebElement NewsletterOnOff;


	//Home Path
	@FindBy(xpath="//*[@id='s2id_autogen16']")
	public static WebElement Hoteldiv;
	@FindBy(xpath="//*[@id='select2-drop']/div/input")
	public static WebElement Hotelinp;

	@FindBy(xpath="//a[text()='Home']")
	public static WebElement home;
	@FindBy(xpath="//*[@id='checkin']")
	public static WebElement datein;
	@FindBy(xpath="//*[@id='checkout']")
	public static WebElement dateout;
	@FindBy(xpath="//*[@type='submit']")
	public static WebElement homesubmit;
	@FindBy(xpath="//*[@class='product-long-item']//h5/a")
	public static WebElement hottellist;

	//Login Validation
	@FindBy(xpath="//*[@class='alert alert-danger']")
	public static WebElement emailval;
	@FindBy(xpath="//*[@class='text-center']")
	public static WebElement menucmny;

	
	@FindBy(xpath="//*[@class='main-nav-menu main-menu-nav navbar-arrow']//li/a")
	public static WebElement menulist;
	
	
@FindBy(xpath="//*[@href='#flights']")
public static WebElement homeFlightClk;
@FindBy(xpath="//*[@id='flightSearchRadio-2']")
public static WebElement fltOwnWay;
@FindBy(xpath="//*[@id='s2id_location_from']")
public static WebElement fltFrom;
@FindBy(xpath="//*[@id='select2-drop']/ul/li")
public static WebElement fltFromInp;
@FindBy(xpath="//*[@id='s2id_location_to']/a")
public static WebElement fltFromOut;
@FindBy(xpath="//*[@id='select2-drop']/div/input")
public static WebElement flightOtInp;

@FindBy(xpath="//*[@name='cabinclass']")
public static WebElement flightclass;






	

}
