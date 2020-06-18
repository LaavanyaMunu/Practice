package demoHashMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class demoHashMap {
		
	public static void main(String[] args) throws BiffException, IOException {
			
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("https://money.rediff.com/losers/bse/daily");
			//Retrieving data from Web
			List<WebElement> companyList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]")); 
			List<WebElement> currentPrice = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[4]"));
			HashMap<String,String> dataFromWeb = new HashMap<String,String>();
			for(int i=0;i<10;i++)
			{
				dataFromWeb.put(companyList.get(i).getText(), currentPrice.get(i).getText());
			}
			//Data from web
			Set<String> webData = dataFromWeb.keySet();
			for(String web : webData)
			{
				System.out.println(web +"::"+dataFromWeb.get(web));
			}
			//to read data from excel
			FileInputStream fis = new FileInputStream("C:\\Amazon\\SelfTrainingWorkspace\\demoHashMap\\src\\main\\java\\Rediff_losersdata.xls");
			Workbook wb=Workbook.getWorkbook(fis);
			Sheet sheet=wb.getSheet(0);
			int rows = sheet.getRows();
			HashMap<String,String> xcelMap=new HashMap<String,String>();
			System.out.println("Data from xcel");
			 for(int i=0;i<rows;i++)
			 {
				 Cell cell=sheet.getCell(0,i);
				 String keyvalue = cell.getContents();
				 cell=sheet.getCell(1,i);
				 String value = cell.getContents();
				 xcelMap.put(keyvalue,value);
				 System.out.println(keyvalue+ "::"+ value);
			 }
			 Set<String> keyValuesFromWeb = dataFromWeb.keySet();
				for(String key : keyValuesFromWeb)
				{
					dataFromWeb.get(key).equals(xcelMap.get(key));
					System.out.println("Both datas are same");
					
				}
	}
}
