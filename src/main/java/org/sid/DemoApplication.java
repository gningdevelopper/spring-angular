package org.sid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.sid.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Resource
	StorageService storageService;
	
	
	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact("Gning","Ousseynou",df.parse("10/02/1995"),"ousseynou@gmail.com", 0661345432,"ouuddd.jpeg"));
		contactRepository.save(new Contact("Gning","Ousseynou",df.parse("10/02/1995"),"ousseynou@gmail.com", 0661345432,"ouuddd.jpeg"));
		contactRepository.findAll().forEach(c->{
			System.out.println(c.getNom());
		});*/
		storageService.deleteAll();
		storageService.init();
	}

}

