import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * The main graphical panel used to display conversion components.
 * 
 * This is the starting point for the assignment.
 * 
 * The variable names have been deliberately made vague and generic, and most comments have been removed.
 * 
 * You may want to start by improving the variable names and commenting what the existing code does.
 * 
 * @author mdixon
 */
@SuppressWarnings("serial")
//creating class which extents JPanel to group a set of component together
public class MainPanel extends JPanel {

	//list of instance array variables
	private final static String[] list = { "inches/cm","Pounds/Kilogramas","Digrees/Radians","Acres/Hectares","Miles/Kilometres","Yards/Metres","Celsius/Fahrenheit" };
	private JTextField textField;//instance variable for taking input data from user
	private JLabel convertresult; //instance variable for displaying output result
	JButton convertButton, clearButton;
	private JComboBox<String> combo;
	JLabel counter; //Instance variable for displaying conversion count
	int count=0; //trace the conversion count
	JCheckBox Reverse; //declaring JCheckBox variable Reverse used to reverse conversion 


	JMenuBar setupMenu() { //it contain menu and display them

		JMenuBar menuBar = new JMenuBar(); //object of menubar

		JMenu file = new JMenu("File"); //creating a menu object file
		JMenu help = new JMenu("Help"); //creating a menu object help
		JMenuItem about = new JMenuItem("About"); //creating an sub menu object about
		
		//adding an icon into the about submenu
		about.setIcon(new ImageIcon("C:/Users/DELL/Desktop/APLab/Converter/src/abouticon.png"));
		
		about.addActionListener(e-> {//lambda expression, it takes parameter and return the following value
			JOptionPane.showMessageDialog(this,"This is a simple Converter \n@Mdixon 2020\nLBU ");
		});

		menuBar.add(file); //adding menu file into menu bar
		menuBar.add(help); //adding menu help into menu bar

		JMenuItem exititem = new JMenuItem("Exit");
		//setting an icon image into the menu item Exit
		exititem.setIcon(new ImageIcon("C:/Users/DELL/Desktop/APLab/Converter/src/exiticon.png"));
		
		//adding shortcut key/ key stroke CTRL+W to exit 
		exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
		exititem.addActionListener(new ExitListener()); //adding actionlistener to call exitListener class from the method
		file.add(exititem);//adding menu exit into menu
		help.add(about); //adding menu item into menu

		return menuBar; // Return the convert from driver class
	}


	MainPanel() {

		ActionListener listener = new ConvertListener(); // creating an object of constructor class

		
		combo = new JComboBox<String>(list);
		combo.addActionListener(listener); //convert values when option changed
		combo.setToolTipText("CLICK HERE FORE MORE CONVERSION"); //adding tool tip
		
       //instantiate the component
		JLabel inputLabel = new JLabel("Enter value:"); //textfield to enter a value

		convertButton = new JButton("Convert");//instantiate convert button
		convertButton.setForeground(Color.BLUE); //setting foreground color into convert button
		convertButton.setToolTipText("CLICK TO CONVERT"); //adding tool tip
		
	    clearButton = new JButton("Clear");//creating button object and instantiate clear
		clearButton.setForeground(Color.RED);//setting foreground color into clear button
		clearButton.setToolTipText("CLICK TO RESET EVERYTHING"); //adding tool tip
		
		//adding actionListioner to call the following block of code
		clearButton.addActionListener(e->
		{
			textField.setText(null); //clear textfield
			convertresult.setText("---");
			counter.setText(null);
			count=0;
		});
		
		convertButton.setMnemonic(KeyEvent.VK_C); //setting Mnemonic 'C' into convert button
		convertButton.addActionListener(listener); // convert values when pressed
		
		convertresult = new JLabel("---"); //creating JLabel object and instantiate'---'
		convertresult.setForeground(Color.BLUE); //adding label color
		
		//creating JLabel object and instantiate also it convert values into string
		counter=new JLabel(String.valueOf("Conversion Count: "+count));
		
		Reverse=new JCheckBox("Reverse Conversion"); //instantiate 
		Reverse.setToolTipText("SELECT FOR REVERSE CONVERSION"); //it gives hint to user
		
		textField = new JTextField(5); //text field where user can write maximun 5 digit number
		textField.addKeyListener(new KeyAdapter() //calling the keyadapter object for receiving keyboard instruction
				{
			public void keyPressed(KeyEvent e) //when key is pressed following code will be executed
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) //select Enter keyword to perform conversion 
					convertButton.doClick(); //when enter keyboard id pressed convert is done automatically
			}
				});
		
		//add component to the panel
		add(combo);
		add(inputLabel);
		add(textField);
		add(convertButton);
		add(clearButton);
		add(convertresult);
		add(counter);
		add(Reverse);
		
		setPreferredSize(new Dimension(800, 200)); //setting panel size
		setBackground(Color.PINK);//adding background color into the panel
	}
//convertlistener implements the actionlistener to implement interface
	private class ConvertListener implements ActionListener {

		
		@Override //using overriding feature for this method to be called from the parent class
		public void actionPerformed(ActionEvent event) {

			//it handle the number format exception
			try {
			String text = textField.getText().trim(); //get value from the text field and remove whitespace
			

			//if the text field value is empty then the following code will be execute
			if (text.isEmpty() == false) {
				
				double value = Double.parseDouble(text); //convert the string value into double

				// the factor applied during the conversion
				double factor = 0;

				// the offset applied during the conversion.
				double offset = 0;
				
				//if the reverse check box is not selected then the following code will be executed
				if(Reverse.isSelected()==false) {
				// Setup the correct factor/offset values depending on required conversion
				//combo box for select an index for converting the value
				switch (combo.getSelectedIndex()) {

				case 0: // inches/cm
					factor = 2.54;
					break;
					
				case 1: //pounds/kilograms
					factor = 0.45359;
					break;
					
				case 2://degrees/radians
					factor = 0.01745;
					break;
					
				case 3://acres/hectacres
					factor = 0.4046;
					break;
					
				case 4: //miles/km
					factor = 1.60934;
					break;
					
				case 5:// yards/meters
					factor = 0.9144;
					break;	
					
				case 6://celcius/farenheit
					offset =value* 1.8 +32;
					break;
				}
			}
				else {
					switch (combo.getSelectedIndex()) {

					case 0: //cm/inches
						factor = 0.3937;
						break;
						
					case 1: //kilograms/pounds
						factor = 2.2046;
						break;
						
					case 2://radians/degrees
						factor = 57.2958;
						break;
						
					case 3://hectacres/acres
						factor = 2.4710;
						break;
						
					case 4: //km/miles
						factor = 0.6213;
						break;
						
					case 5://meters/yards
						factor = 1.0936;
						break;	
						
					case 6://farenheit/celcius
						offset =(value-32) * 5 / 9;
						break;
					}

				}
				double result = factor * value + offset; // store calculation into result
				String finalresult= new DecimalFormat("0.00").format(result); //it display the result in decimal format
				convertresult.setText(finalresult);//display the decimal format result into label
				counter.setText("Conversion Count: "+Integer.toString(++count));
			}
			else //if the textfield is empty then it display message
			{
				JOptionPane.showMessageDialog(null, "Null Value is not accepted","ERROR",JOptionPane.ERROR_MESSAGE);
				
			}
			} catch (NumberFormatException a)
			{
				JOptionPane.showMessageDialog(null, "Please Enter Numeric Value"); //it display error message to user
			}
			
		}
			}

		//creating a exitlistener class
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			String y=x.getActionCommand();
			if(y.equals("Exit")) //if the condition is true then it execute following code
			{
			
				//show dialog box to user whenever click to exit
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to Exit ?","Confirmation",JOptionPane.YES_NO_OPTION);
			if(answer==JOptionPane.YES_OPTION)
			System.exit(0); //terminate the application
			}
		}
	}
}


