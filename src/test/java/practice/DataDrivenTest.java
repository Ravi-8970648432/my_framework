package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {
	@Test(dataProvider="dataProviderBookTicketTest")
	public void bookTicketTest(String source, String Destination, int ticket)
	{
		System.out.println("Book Ticket from " +source+ " to " +Destination+ " ticket "+ticket);
	}
	@DataProvider
	public Object[][] dataProviderBookTicketTest()
	{
		Object[][] data=new Object[5][3];

		data[0][0]="Bangalore";
		data[0][1]="Goa";
		data[0][2]=12;

		data[1][0]="Bangalore";
		data[1][1]="Mysore";
		data[1][2]=20;

		data[2][0]="Bangalore";
		data[2][1]="Hassan";
		data[2][2]=15;

		data[3][0]="Bangalore";
		data[3][1]="Chenni";
		data[3][2]=40;

		data[4][0]="Bangalore";
		data[4][1]="Manglore";
		data[4][2]=10;

		return data;
	}
}
