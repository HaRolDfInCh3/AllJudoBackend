package com.test.microservices.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader {
	public List<Resultat> readFile(byte[]content) {
		List<Resultat>results=new ArrayList<>();
		File file = new File("file"); 
		FileInputStream fis;
		
		// Finds the workbook instance for XLSX file 
		XSSFWorkbook myWorkBook;
		XSSFSheet mySheet;
		try {
			FileOutputStream os = new FileOutputStream(file);
			 
	        // Starting writing the bytes in it
	        os.write(content);
			fis = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook (fis);
			// Return first sheet from the XLSX workbook 
			mySheet = myWorkBook.getSheetAt(0); 
			// Get iterator to all the rows in current sheet 
			Iterator<Row> rowIterator = mySheet.iterator(); 
			// Traversing over each row of XLSX file 
			int i=0;
			while (rowIterator.hasNext()) { 
				Resultat resultat=new Resultat();
				Row row = rowIterator.next(); 
				// For each row, iterate through each columns 
				
					resultat.setId(i);
					resultat.setClub(row.getCell(5).getStringCellValue());
					resultat.setNom(row.getCell(0).getStringCellValue());
					resultat.setPays(row.getCell(2).getStringCellValue());
					resultat.setPoids(row.getCell(4).getStringCellValue());
					resultat.setRang( row.getCell(3).getStringCellValue());
					resultat.setSexe(row.getCell(1).getStringCellValue());
					
					System.out.println(resultat);
					
					results.add(resultat);
					i++;
				System.out.println("------------     ligne suivante  -----------"); 
				os.close();
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return results;
		}

		
	
}
