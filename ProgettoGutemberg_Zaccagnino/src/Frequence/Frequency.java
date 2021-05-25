package Frequence;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Frequency {
	//metodo che calcola la frequenza delle parole
	public static String frequenza(String s, TreeMap <String, Integer> dictionary) { 
		//trasformo tutte le parole in minuscolo e tolgo la punteggiatura e i numeri
		String fin = s.toLowerCase().replaceAll("[0-9]", " ").replaceAll("[^\\w]", " ").replaceAll("[_]", " ");
		StringTokenizer st = new StringTokenizer(fin);
		while (st.hasMoreTokens()) {
			String token = st.nextToken(); //salvo il token
			if (dictionary.containsKey(token)) {  //controllo di non averlo già aggiunto
				dictionary.put(token, dictionary.get(token) +1);
			} else {
				dictionary.put(token, 1);
			}
		}
		return dictionary.toString();
	}
	
	public static void frequenzaDiFrequenze (TreeMap <String, Integer> dictionary, TreeMap <Integer, Integer> frequency) {
		try {
			int i = 0;
			for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
				if (frequency.containsKey(i)) {  //controllo di non averlo già aggiunto
					frequency.put(i, frequency.get(i) +1);
				} else {
					frequency.put(i, 1);
				}
			}
			//mi creo una copia delle frequenze delle parole
			File myFile = new File("C:\\Users\\crist\\Desktop\\Dizionario\\"+"frequenze.txt" );
			if (myFile.createNewFile()) {
				System.out.println("File creato: " + myFile.getName());
			} else {
				System.out.println("File già esistente.");
			}
			FileWriter myWriter = new FileWriter("C:\\Users\\crist\\Desktop\\Dizionario\\" + "frequenze.txt");
			myWriter.write(frequency.toString());
			myWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
