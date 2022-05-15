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
import com.example.demo.entities.User;
import com.example.demo.repositories.*;
@Component
public class CreateUserPasswords {
	private UserRepository repo;
	private PasswordEncoder pe;
	public CreateUserPasswords(UserRepository s) {
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
		    c1 = new PdfPCell(new Phrase("Id User"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("Nom"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("Username"));
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
	 List<User> tous=repo.findAll();
	 for(User element:tous) {
			 numero++;
			 table.addCell(String.valueOf(numero));
			 table.addCell(String.valueOf(element.getId()));
			 table.addCell(element.getNom()); 
			 table.addCell(element.getUsername()); 
			 String motdepasse=element.getUsername()+RandomStringUtils.randomAlphabetic(5)+element.getNom();
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
