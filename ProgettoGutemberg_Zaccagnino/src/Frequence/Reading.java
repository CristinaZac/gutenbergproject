package Frequence;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Reading extends Frequency {

	//metodo che legge i file che rispettano i criteri indicati nel metodo checkTxt(files, dictionary)
	public static void readTxt(String filename, String encoding, TreeMap <String, Integer> dictionary) {
		boolean  checker = false;
		boolean finisher = false;
		Scanner reader;
			try {
				File f = new File (filename);
				reader = new Scanner ((f), encoding);
				StringBuilder sb = new StringBuilder();
				String data;
				while (reader.hasNextLine()) {
					data = reader.nextLine();
					if (data.contains("*** START OF ")) {
						checker = true;
					}
					if (data.contains("*** END OF")) {
						checker = false;  
					}
					if (checker == true) {
						sb.append(data);
						sb.append(System.lineSeparator());
					}
				}
				String tot = sb.toString();  
				frequenza(tot, dictionary);  
				reader.close();  	    
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
		//metodo che controlla che i file txt siano in lingua inglese e l'encoding
	public static void checkTxt (ArrayList <String> files, TreeMap <String, Integer> dictionary) {
		Scanner reader;
		String encoding = "";
		String filename;
		try {
			for (String file : files) {
				boolean language = false;
				boolean startfile = false;
				boolean badencoding = false;
				if (file.endsWith(".txt"));
					filename = file; 			
					File obj = new File(filename);
					reader = new Scanner(obj);
					while (reader.hasNextLine() || startfile == false) {
						String data = reader.nextLine();
						if (data.equalsIgnoreCase("Language: English")) {
						 language = true;
						}
						//smetto di leggere il file quando raggiungo "*** START OF"
						if (data.contains("*** START OF")) {
							startfile = true;
						}
						if (language == true && data.contains("Character set encoding:")) {
							encoding = data.substring(24);
							//nome con variante non riconosciuta da Java quindi specifico
							if (encoding.contains("ISO Latin-1")) {
								encoding = "ISO-8859-1";
							} else if (encoding.contains("ISO-646-US")) {
								encoding = "US-ASCII";
							// MAC e MP3 risultavano problematiche nella lettura dei file
							} else if (encoding.contains("MP3") || encoding.contains("MAC")) {
								badencoding = true;
							//specifico anche eventuali errori nello dichiarare il tipo di encoding
							} else if (encoding.contains("ISO-8858-1") ||
								 		encoding.contains("IDO-8859-1") || 
								 		encoding.contains("ISO 8859-1")) {
								encoding =  "ISO-8859-1";
							} else if (encoding.contains("Unicode UTF-8")) {
								encoding = "UTF-8";
							} else if (encoding.contains("CP-1252")) {
								encoding = "windows-1252";
							}
						}
				   } 
				  if (language == true && badencoding == false) {
					readTxt(filename, encoding, dictionary);
				  }
				 reader.close();
				 continue;
			 } 
			//mi scrivo una copia delle frequenze delle parole
			File myFile = new File("C:\\Users\\crist\\Desktop\\Dizionario\\"+"dictionary.txt" );
			if (myFile.createNewFile()) {
				System.out.println("File creato: " + myFile.getName());
			} else {
				System.out.println("File già esistente.");
			}
			FileWriter myWriter = new FileWriter("C:\\Users\\crist\\Desktop\\Dizionario\\" + "dictionary.txt");
			myWriter.write(dictionary.toString());
			myWriter.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
