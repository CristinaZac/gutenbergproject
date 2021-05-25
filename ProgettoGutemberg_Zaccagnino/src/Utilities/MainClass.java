package Utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import Frequence.ScatterPlot;

public class MainClass extends Scan {
	public static void main (String[] args) {
		TreeMap <String, Integer> dictionary = new TreeMap <String, Integer> ();
    	TreeMap <Integer, Integer> frequency = new TreeMap <Integer, Integer> ();
    	ArrayList <String> paths = new ArrayList <String> (); //percorsi cartelle da unzippare
    	ArrayList <String> folders = new ArrayList <String> ();  //percorsi cartelle unzippate in ZipFiles
    	ArrayList <String> files = new ArrayList <String> (); //percorsi di tutti i file
        
        extractPaths(new File("C:\\Users\\crist\\Documents\\Progetto Gutenberg\\"), paths);
        unzip (paths, "C:\\Users\\crist\\Documents\\Progetto Gutenberg\\ZipFiles");
        extractFolderPaths(new File("C:\\Users\\crist\\Documents\\Progetto Gutenberg\\ZipFiles"), folders);
        extractFilesPaths(folders, files);
        checkTxt(files, dictionary);
        
        
    	SwingUtilities.invokeLater(() -> {  
	          ScatterPlot scatterchart = new ScatterPlot("Scatter Plot", frequency );  
	          scatterchart.setSize(800, 400);  
	          scatterchart.setLocationRelativeTo(null);  
	          scatterchart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
	          scatterchart.setVisible(true);  
	        }); 
        
	}
}
