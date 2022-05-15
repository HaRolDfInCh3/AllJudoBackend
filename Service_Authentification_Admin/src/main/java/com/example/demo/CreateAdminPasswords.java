package com.example.demo;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.demo.entities.Admin;
import com.example.demo.repositories.*;
@Component
public class CreateAdminPasswords {
	private AdminRepository repo;
	private PasswordEncoder pe;
	public CreateAdminPasswords(AdminRepository s) {
		this.repo=s;
		this.pe=new BCryptPasswordEncoder();
		
		
	}
	Font font, font1,font2,font3;
	Document document;
	
	PdfPTable table;
	int numero;
 public void regenerer() throws Exception{
	 try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("./Rapports/MotsdePasse.pdf"));
			document.open();
			document.addTitle("Rapport");
		    document.addSubject("attribuer des mots de passe");
		    document.addKeywords("Java, PDF, iText, Spring");
		    document.addAuthor("GONSALLO Ayrton");
		    document.addCreator("GONSALLO Ayrton");
		    table = new PdfPTable(5);
		    table.setWidths(new int[]{1,1, 2,2, 2});
		    PdfPCell c1 = new PdfPCell(new Phrase("n°"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
		    c1 = new PdfPCell(new Phrase("Id Admin"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("Login"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("Permission"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("Password"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        table.setHeaderRows(1);
			font = FontFactory.getFont(FontFactory.COURIER, 24, Font.BOLD);
			font3 = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.GREEN);
			
			font1 = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.RED);
			font2 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLUE);
			document.add(new Chunk("Mots de passe générés:", font).setUnderline(0.1f, -2f));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 System.out.println("Creation de mots de passe...");
	 List<Admin> tous=repo.findAll();
	 for(Admin element:tous) {
			 numero++;
			 table.addCell(String.valueOf(numero));
			 table.addCell(String.valueOf(element.getAdminId()));
			 table.addCell(element.getLogin()); 
			 table.addCell(element.getPermission().name()); 
			 String motdepasse=RandomStringUtils.randomAlphabetic(5)+element.getLogin();
			 table.addCell(motdepasse);
			 element.setPassword(pe.encode(motdepasse));
			 repo.save(element);
	}
	 document.add(table);
	document.add(new Paragraph(StringUtils.repeat(" ", 5)+StringUtils.repeat("-", 30)+StringUtils.repeat(" ", 5), font));
	document.close();
	 System.out.println("traitement fini et document exporté!");
 }
}
