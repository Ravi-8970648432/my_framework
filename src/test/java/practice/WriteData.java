package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteData {

	public static void main(String[] args) throws IOException {

		//create  workbook object for workbook
		XSSFWorkbook wb=new XSSFWorkbook();

		//create dheet object
		XSSFSheet sheet = wb.createSheet("Student Data");


		//prepare data for using mapSet
		Map<String, Object[]>studentData=new TreeMap<String, Object[]>();
		studentData.put("1", new Object[] {"RolNo", "Name", "Year"});
		studentData.put("2", new Object[] {"101", "Ravi", "1990"});
		studentData.put("3", new Object[] {"102", "Rajini", "1993"});
		studentData.put("4", new Object[] {"103", "Varuna", "1990"});

		Set<String> keys = studentData.keySet();
		// use foreach
		//create row
		XSSFRow row;
		int rowId=0;
		for (String string : keys) {
			row=sheet.createRow(rowId++);
			Object[] key = studentData.get(string);
			
			int cellId=0;
			for (Object object : key) {
				XSSFCell cell = row.createCell(cellId++);
				cell.setCellValue((String)object);
			}
		}
		//write excel file
		
		//create  file object
		File file=new File("./Properties/std.xlsx");
		
		//create object for FileOutputStream
		FileOutputStream fos=new FileOutputStream(file);
		
		wb.write(fos);
		fos.close();
	}

}
