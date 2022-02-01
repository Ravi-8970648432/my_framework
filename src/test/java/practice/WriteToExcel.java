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

import java_cup.runtime.Symbol;

public class WriteToExcel {

	public static void main(String[] args) throws IOException {

		//create workbook object
		XSSFWorkbook wb= new XSSFWorkbook();

		//create sheet object
		XSSFSheet sheet = wb.createSheet("Employee");

		//prepare datausing map

		Map<String, Object[]>studentData=new TreeMap<String, Object[]>();
		studentData.put("1", new Object[] {"EMP ID", "Employee Name", "Salary", "Department", "Mobile"});
		studentData.put("2", new Object[] {"101", "Ravi", "35000", "Testing", "9875489658"});
		studentData.put("3", new Object[] {"102", "Rajini", "40000", "Testing","8974586958"});
		studentData.put("4", new Object[] {"103", "Varuna", "45000", "Testing", "7854896586"});

		Set<String> key = studentData.keySet();

		//use foreach for row
		int rowcount=0;
		XSSFRow row;
		
		for (String string : key) {
			//create row
			row = sheet.createRow(rowcount++);
			Object[] obj = studentData.get(string);
			//foreach for cell
			int cellcount=0;
			for (Object object : obj) {
				XSSFCell cell = row.createCell(cellcount++);
				cell.setCellValue((String)object);
			}
		}
		//write data into excel
		
		//create file object
		File file=new File("./Properties/Emp.xlsx");
		
		//create object for FileOutputStream
		FileOutputStream fos=new FileOutputStream(file);
		
		wb.write(fos);
		fos.close();
		System.out.println("File Created");

	}

}
