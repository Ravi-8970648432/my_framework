package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//  step 1 create file object for file class
		File file=new File("./Properties/ReadData.xlsx");
		
		//Step 2 create object for FileInputStream class
		FileInputStream fis=new FileInputStream(file);
		
		//step 3 create workbookFactory
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		
		// total no.of rows we use getLastRowNum()
		int rowCount = sheet.getLastRowNum();
		for(int i=1; i<=rowCount; i++)
		{
			Row row = sheet.getRow(i);
			int cellCount=row.getLastCellNum();
			for(int j=0; j<cellCount; j++)
			{
				Cell cell = row.getCell(j);
				DataFormatter df=new DataFormatter();
				String value = df.formatCellValue(cell);
				System.out.println(value);
				
				
			}
		}
		

	}

}
