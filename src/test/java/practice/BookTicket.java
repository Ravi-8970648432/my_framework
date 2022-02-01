package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vitiger.comcast.genericUtility.ExcelUtility;

public class BookTicket {

	@Test(dataProvider="dataProviderBookTicketTest")
	public void bookTicketTest(String source, String Destination)
	{
		System.out.println("Book Ticket from " +source+ " to " +Destination);
	}
	@DataProvider
	public Object[][] dataProviderBookTicketTest() throws Throwable
	{
		ExcelUtility elib=new ExcelUtility();
		int rowcount=elib.getRowCount("Sheet1");
		Object[][] data=new Object[rowcount][2];
		
		for(int i=0; i<rowcount;i++)
		{
			data[i][0]=elib.getDataFromExcel("Sheet1", i, 0);
			data[i][1]=elib.getDataFromExcel("Sheet1", i, 1);
			
		}
		
		
		return data;
	}
}
