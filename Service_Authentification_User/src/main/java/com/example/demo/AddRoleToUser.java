package com.example.demo;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.enums.Designation_role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
@Component
public class AddRoleToUser {
	RoleRepository roleRepo;
	UserRepository userRepo;
	Font font, font1,font2,font3;
	Document document;
	public AddRoleToUser(RoleRepository roleRepo, UserRepository userRepo) {
		this.roleRepo = roleRepo;
		this.userRepo = userRepo;
		
	}
	public Role createRole(int index,Designation_role designation) {
		
		Role r=new Role();
		r.setDesignation(designation);
		r.setId(index);
		roleRepo.save(r);
		return r;
	}
	public String addRoleToUser(String username,String designation) throws Exception{
		if(userRepo.existsByUsername(username)) {
			if(roleRepo.existsByDesignation(designation)) {
				Role r=roleRepo.findByDesignation(designation);
				List<User> u=userRepo.findByUsername(username);
				
				List<Role>roles=u.get(0).getRoles();
				roles.add(r);
				u.get(0).setRoles(roles);
				userRepo.save(u.get(0));
				return "Ajout du role "+r+" a l'user "+u;
			}else {
				return "role "+designation+" not found";
			}
			
		}else {
			return "user "+username+" not found";
		}
	}
	public String addRoleToUser(int uid,String designation) throws Exception{
		if(userRepo.existsById(uid)) {
			if(roleRepo.existsByDesignation(designation)) {
				Role r=roleRepo.findByDesignation(designation);
				User u=userRepo.findById(uid);
				List<Role>roles=u.getRoles();
				roles.add(r);
				u.setRoles(roles);
				userRepo.save(u);
				return "Ajout du role "+r+" a l'user "+u;
			}else {
				return "role "+designation+" not found";
			}
			
		}else {
			return "user "+uid+" not found";
		}
	}
	
	public void addRoleToAllUsers(String role) throws Exception {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("./Rapports/Roles.pdf"));
			document.open();
			document.addTitle("Rapport");
		    document.addSubject("attribuer des roles");
		    document.addKeywords("Java, PDF, iText, Spring");
		    document.addAuthor("GONSALLO Ayrton");
		    document.addCreator("GONSALLO Ayrton");
		    
			font = FontFactory.getFont(FontFactory.COURIER, 24, Font.BOLD);
			font3 = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.GREEN);
			
			font1 = FontFactory.getFont(FontFactory.COURIER, 18, BaseColor.RED);
			font2 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLUE);
			document.add(new Chunk("Roles ajoutes:", font).setUnderline(0.1f, -2f));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Ajout du role "+role+" a tous les users");
		List<User>users=userRepo.findAll();
		for(User u:users) {
			String res=addRoleToUser(u.getId(), role);
			document.add(new Paragraph(res));
		}
		document.add(new Paragraph(StringUtils.repeat(" ", 5)+StringUtils.repeat("-", 30)+StringUtils.repeat(" ", 5), font));
		System.out.println("Ajout du role "+role+" a tous les users effectué");
		document.close();
	}
}
