import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The main driver program for the GUI based conversion program.
 * 
 * @author mdixon
 */
public class Converter {
	 
    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Converter"); //creating JFrame object and instantiate in'Converter'
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/DELL/Desktop/APLab/Converter/src/convert.png"));
        
        MainPanel panel = new MainPanel(); //calling main panel class
        
        frame.setJMenuBar(panel.setupMenu()); //set menu bar to the frame
        frame.setResizable(false);//fixed the dilogue box size
        frame.getContentPane().add(panel); //adding panel to the frame
        frame.setLocation(300,200);
        frame.pack(); //pack all component into frame
        frame.setVisible(true); //display window
    }
} 

