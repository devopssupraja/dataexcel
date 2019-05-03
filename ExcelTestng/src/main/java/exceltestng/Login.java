package exceltestng;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.DataProvider;

 
public class Login {
public static WebDriver driver;
	
	@Test(dataProvider = "inputdata")
	public static void testdata(String username ,String password){
	System.setProperty("webdriver.chrome.driver","E:\\supraja\\chromedriver_win32\\chromedriver.exe");
 
         WebDriver driver= new ChromeDriver();
	     driver.get("http://parabank.parasoft.com");
		
		 
		
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//input[@class='button' and @type='submit']")).click();
	    System.out.println("submit button pressed");
	
}
	
   @DataProvider(name="inputdata")
 
	    public Object[][] getcellData() throws IOException {
      	 
		 FileInputStream file = new FileInputStream("C:\\New folder\\Sample.xlsx");
   		
		 XSSFWorkbook wb = new XSSFWorkbook(file);
  		
		 XSSFSheet s = wb.getSheet("sheet1");
		
		 int rowcount = s.getLastRowNum()+1;
		 int cellcount = s.getRow(0).getLastCellNum();
		
		 Object data[][] = new Object[rowcount][cellcount];
		
		      for(int i=0;i<rowcount;i++){
			  Row r =s.getRow(i);
			
			  for(int j = 0;j<cellcount;j++){
				Cell c = r.getCell(j);
				data[i][j] = c.getStringCellValue();
			}
		}
		wb.close();
		return data;
		
	}
 
}