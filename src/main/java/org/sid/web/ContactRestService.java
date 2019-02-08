package org.sid.web;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.sid.dao.ContactRepository;
import org.sid.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContactRestService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping(value="/contacts", method=RequestMethod.GET)
	public List<Contact> getContacts(){
		return contactRepository.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/chercherContacts", method=RequestMethod.GET)
	public Page<Contact> chercher(
			@RequestParam(name="mc", defaultValue="")String mc,
			@RequestParam(name="page", defaultValue="0")int page, 
			@RequestParam(name="size", defaultValue="5")int size){
		return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));
	}   
	
	@RequestMapping(value="/hello" , method=RequestMethod.POST)
	public String hello(){
		return "hello";
	}
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.GET)
	public Contact getContact(@PathVariable Long id){
		return contactRepository.findById(id).orElse(null);
	}
	@RequestMapping(value="/contacts", method=RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact c){
		return contactRepository.save(c);
	}
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public boolean supprimerContact(@PathVariable Long id){
		 contactRepository.deleteById(id);
		 return true;
	}
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public @ResponseBody boolean supprimer(@PathVariable Long id){
	    contactRepository.deleteById(id);
	    return true;
	}
	@RequestMapping(value="/contacts/{id}", method=RequestMethod.PUT)
	public Contact updateContact(@PathVariable Long id,@RequestBody Contact c){
		c.setId(id);
		return contactRepository.save(c);
	}
	
	
	
}
