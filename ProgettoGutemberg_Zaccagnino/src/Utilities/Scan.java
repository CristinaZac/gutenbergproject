package Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Frequence.Reading;

public class Scan extends Reading {

	//medoto per creare arraylist di percorsi di cartelle
	public static void extractFolderPaths (File file, ArrayList <String> folders ) {
		 File file2 = new File("C:\\Users\\crist\\Documents\\Progetto Gutenberg\\ZipFiles");
	     File[] files = file2.listFiles();
	       for (File f: files){
	    	   if (f.isDirectory()) {
	    		   folders.add(f.getPath());
	          }
	       }
	}
	//mostra i percorsi di tutti i file nella cartella principale e nelle subcartelle
	//e li aggiunge nell'arraylist di tutti i percorsi file
	public static void displayDirectoryContents(File dir, ArrayList <String> files ) {
			try {
				File[] files3 = dir.listFiles();
				for (File file : files3) {
					if (file.isDirectory()) {
						System.out.println("directory: " + file.getCanonicalPath());
						displayDirectoryContents(file, files);
					
						
					} else {
						System.out.println("file: " + file.getCanonicalPath());
						files.add(file.getCanonicalPath());
					}
				}
			} catch (Exception e) { 
				e.printStackTrace();
			}
	}
	 // estraggo i path dei files nelle subdirectories passando l'arraylist con i path delle subdirectories e accedendo ai file
	public static void extractFilesPaths (ArrayList <String> folders, ArrayList <String> files ) {
		   File currentDir = new File("C:\\Users\\crist\\Documents\\Progetto Gutenberg\\ZipFiles");
		   displayDirectoryContents(currentDir, files);
		   for (String p : folders) {  
			   File file5 = new File(p);	//passo i path delle directories per accedere ai file e salvare i path  
		       File[] fi = file5.listFiles(); 
		       for(File f: fi){
		          if (f.isFile()) {
		       	  files.add(f.getPath());
		          }
		       }
	       }
	  }
	//estraggo i percorsi delle cartelle
	public static void extractPaths (File file, ArrayList <String> paths) {
	    	if (file.isDirectory()) {
	    		String[] subNote = file.list();
	    		for (String filename : subNote) {
	    			extractPaths(new File(file, filename), paths);
	    			paths.add(file.getPath());
	    		}
	    	}	
	    }
	    
	//scompatto i file zip
	public static void unzip(ArrayList <String> paths, String destDir) {
        File dir = new File(destDir);
        // crea output directory se non esiste
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer che scrive/legge file
        byte[] buffer = new byte[1024];
        try {
        	for (String p : paths) {
        		fis = new FileInputStream(p);
	            ZipInputStream zis = new ZipInputStream(fis);
	            ZipEntry ze = zis.getNextEntry();
	            while(ze != null) {
	                String fileName = ze.getName();
	                if (ze.getName().endsWith("txt")) { 
		                File newFile = new File(destDir + File.separator + fileName);
		                System.out.println("Scompatto in "+ newFile.getAbsolutePath());
		                //crea directories per sub directories in zip
		                new File(newFile.getParent()).mkdirs();
		                FileOutputStream fos = new FileOutputStream(newFile);
		                int len;
		                while ((len = zis.read(buffer)) > 0) {
		                	fos.write(buffer, 0, len);
		                }
		                fos.close();
		                //chiude questa ZipEntry
		                zis.closeEntry();
		                ze = zis.getNextEntry();
	               } else if  (!ze.getName().endsWith("txt")) {	
	            	  zis.closeEntry();
	            	  ze = zis.getNextEntry();
	               } 
	            }
	            //chiude l'ultima ZipEntry
	            zis.closeEntry();
	            zis.close();
	            fis.close();
        	} 
        } catch (IOException e) {
        	e.printStackTrace();
        }  
        
   }
}
