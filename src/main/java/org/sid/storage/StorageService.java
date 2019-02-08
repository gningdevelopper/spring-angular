package org.sid.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	Logger log=LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation=Paths.get("upload-dir");
	
public void store(MultipartFile file,String nomFichier) {
		
		try {
			String nom;
			nom=file.getContentType();
			System.out.println("Chemin courant :"+System.getProperty("user.dir")) ;
			Files.copy(file.getInputStream(),this.rootLocation.resolve(nomFichier));
			System.out.println("Enregistrement du fichier reussie et son nom sur  le poste client est :"+nom);
		} catch (Exception e) {
			System.out.println("Hum Echec Echec !!!!!!!!!!!!!");
			 e.printStackTrace();
		}
	}
	
	public void storeFile(MultipartFile file){
		try{
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		}
		catch(Exception e){
			throw new RuntimeException("Fail");
		}
		
	}
	public Resource loadFile(String fileName){
		try{
			Path file=rootLocation.resolve(fileName);	
			Resource resource=new UrlResource(file.toUri());
			if(resource.exists() ||resource.isReadable()){
				return resource;
			}
			else {
				throw new RuntimeException("Fail");
			}
		}
		catch(MalformedURLException e){
			throw new RuntimeException("Fail");
		}
	}
	public void deleteAll(){
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
	public void init(){
		try{
			Files.createDirectory(rootLocation);
		}
		catch(IOException e){
			throw new RuntimeException("Impossible de créer le repertoire");
		}
	}
}
