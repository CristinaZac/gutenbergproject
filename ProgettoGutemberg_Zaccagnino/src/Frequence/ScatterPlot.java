package Frequence;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlot extends JFrame {
	private static final long serialVersionUID = 6294689542092367723L;
	public ScatterPlot (String title, TreeMap <Integer, Integer> frequency) {
		super("Scatterplot");
		// indico il dataset da usare
        XYDataset dataset = createDataset(frequency);  
        // creo il grafico  
        JFreeChart chart = ChartFactory.createScatterPlot(  
            "Calcolo della frequenza delle frequenze delle parole",   
            "Asse X", "Asse Y", dataset);  
      
        //imposto le caratteristiche del grafico  
        XYPlot plot = (XYPlot)chart.getPlot();  
        plot.setBackgroundPaint(new Color(255,228,196));
        LogAxis xAxis = new LogAxis("X");
        xAxis.setBase(10);
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
        xAxis.setMinorTickMarksVisible(true);
        LogAxis yAxis = new LogAxis("Y");
        yAxis.setBase(10);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
        yAxis.setMinorTickMarksVisible(true);
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis); 
        ChartPanel panel = new ChartPanel(chart);  
        setContentPane(panel);  
	}
	//creo la serie di dati passando il treemap con le frequenze
	private XYDataset createDataset(TreeMap <Integer, Integer> frequency) {  
        XYSeriesCollection dataset = new XYSeriesCollection();  
        XYSeries series1 = new XYSeries("Parole/Occorrenze");
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) { 
        	series1.add(entry.getKey(), entry.getValue()); 
        }
        dataset.addSeries(series1);  
        return dataset;  
      }  	

}
