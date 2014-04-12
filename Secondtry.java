// Calculator.java

/****
* This program implements a basic calculator
*
* @author	Anna Ntenta
* @version	1.0 Last Modified 4/11/2014
*/

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  
import javax.swing.border.*;

public class Secondtry extends JFrame implements ActionListener
{
	// number of digits (inclusive of decimal point) allowed in the display
	public static final int CAPACITY = 15;

	// private variables
	private JFrame frame;
	private JTextArea display;
	// boolean set to true if number already has a decimal point
	private Boolean decPoint = false;
	// boolean set to true if an operator has been clicked
	private Boolean operators;
	// string representing addend, subtractor, multiplicator or numerator
	private StringBuilder num1 = new StringBuilder();
	// string representing addend, subtractor, multiplicator or denominator
	private StringBuilder num2 = new StringBuilder();
	// Double representing addend, subtractor, multiplicator or numerator
	private Double firstNum = null;
	// Double representing addend, subtractor, multiplicator or denominator
	private Double secondNum = null;
	// String representing an operator (+, -, *, /, or square root)
	private String operator = null;

	private Boolean oldOp = false;
	

	// indicates whether the display should be cleared
	private Boolean doClear;


	// array of number buttons
	private JButton [] jb = new JButton[10];
	//operator buttons
	private JButton clear, root, plus, minus, div, mult, equals, point;


	/****
     *  Constructor. Sets up user interface and initializations
     */
	public Secondtry()
	{
		operators = true;
		doClear = false; 

		frame = new JFrame ("Calculator");
		frame.setLayout( new BorderLayout( 10, 10 ));

		display = new JTextArea();
		display.setSize(245, 100);
		display.setFont (new Font ("SansSerif", Font.BOLD, 28));
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		// create the numeric buttons for calculator
		for (int i = 0; i < 10; i++ )
		{	
		 	jb[i] = new JButton ("" + (i));
		 	jb[i].addActionListener(this);
		 	jb[i].setFont (new Font ("SansSerif", Font.BOLD, 14));
		}
		// create the square root button
		root = new JButton("\u221A");
		root.addActionListener(this);
		root.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the clear button
		clear = new JButton("C");
		clear.addActionListener(this);
		clear.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the plus button
		plus = new JButton("\u002B");
		plus.addActionListener(this);
		plus.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the minus button
		minus = new JButton("\u002D");
		minus.addActionListener(this);
		minus.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the multiply button
		mult = new JButton("\u002A");
		mult.addActionListener(this);
		mult.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the division button
		div = new JButton("\u002F");
		div.addActionListener(this);
		div.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// create the equals button
		equals = new JButton("\u003D");
		equals.addActionListener(this);
		equals.setFont (new Font ("SansSerif", Font.BOLD, 14));

		equals.setPreferredSize(new Dimension(50, 60));

		// create the point button
		point = new JButton("\u002E");
		point.addActionListener(this);
		point.setFont (new Font ("SansSerif", Font.BOLD, 14));

		// button grid
		JPanel buttons = new JPanel(new GridLayout(5, 4, 4, 4));
		
		// add buttons to grid
		buttons.add(clear);
		buttons.add(root);
		buttons.add(div);
		buttons.add(mult);
		// add numeric buttons to grid
		for (int i = 7; i < 10; i++) 
			buttons.add ( jb[i] );
		buttons.add(minus);
		// add numeric buttons to grid
		for (int i = 4; i < 7; i++) 
			buttons.add ( jb[i] );
		buttons.add(plus);
		// add numeric buttons to grid
		for (int i = 1; i < 4; i++) 
			buttons.add ( jb[i] );
		buttons.add(equals);
		buttons.add(jb[0]);
		buttons.add(point);

		frame.add (display, BorderLayout.CENTER);
		frame.add (buttons, BorderLayout.SOUTH);

		frame.setSize (300, 400);
        frame.setVisible (true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  
	
	}


	public void actionPerformed( ActionEvent ae )
    {
    	// clear display if new number is to be entered
		if(doClear)
	    	display.setText(null);
			
		// if decimal point is entered for the first time in number
    	if((ae.getSource() == point) && decPoint == false) {
    		display.append(".");
    		// disallow more decimal points
			decPoint = true;
    	}

    	// if decimal point is the first in a number, display "0."
			if (display.getText().equals(".")) 
				display.setText("0.");
    	
		// check if a digit was clicked
	    for (int i = 0; i < 10; i++) {

	    	// append digits to number while number is less than 15 digits long
	    	if((ae.getSource() == jb[i])  && (display.getText().length() < 15)) {
		
				display.append(((JButton)ae.getSource()).getText());	

				// disallow leading zeros
				if (display.getText().equals("0")) 
					display.setText("0.");

				// store as a Double
	    		secondNum = Double.parseDouble(display.getText());
	    		System.out.println("SecondNum is: " + secondNum);

	    		doClear = false;
	    		// only if you click another digit
	    		oldOp = false;
	    	}

		}	

		// check if an operator was clicked
		if((ae.getSource() == plus) || (ae.getSource() == minus) ||
    		(ae.getSource() == div) || (ae.getSource() == mult)) {
			decPoint = false;
			if(oldOp == false) {
				// if this is the first time an operator was clicked
				if (operators) {
					// the number last entered becomes the first number
					firstNum = secondNum;
					System.out.println("firstNum is: " + secondNum);
				}
				else {
					
					calc();
				}

				// store the operator that was clicked
				JButton temp=(JButton)ae.getSource();
				operator = temp.getText();
				System.out.println("Operator is: " + operator);
				operators = false;
				doClear = true;
				oldOp = true;
			}
			// if an operator was clicked without a digit having been entered between
			else {
				// store the operator that was clicked
				JButton temp=(JButton)ae.getSource();
				operator = temp.getText();
				System.out.println("Operator is: " + operator);
				display.setText(String.valueOf(firstNum));
			}
		}

		if(ae.getSource() == root) {
			squareRoot();
			decPoint = false;
		}

		if(ae.getSource() == clear) {
			clear();
		}

		if(ae.getSource() == equals) {
			calc();
		}
	}	

	public void squareRoot() {
		if (secondNum == 0.0) {
			display.setText("ERROR");
			firstNum = 0.0;
			secondNum = 0.0;
			operators = true;
			doClear = true;
		}
		else {
			secondNum = Math.sqrt(secondNum);
			display.setText(String.valueOf(secondNum));
		}
	}

		public void calc() {

			if(operator== "+") {
				firstNum += secondNum;
				System.out.println("firstNum plus secondNum is: " + firstNum); }
			if(operator == "-"){
				firstNum -= secondNum;
			System.out.println("firstNum minus secondNum is: " + firstNum);}
			if(operator == "*") {
				firstNum *= secondNum;
			System.out.println("firstNum times secondNum is: " + firstNum);}
			if((operator == "/") && secondNum != 0.0){
				System.out.println("firstNum div by secondNum is: " + firstNum);
				firstNum /= secondNum;}
			if((operator == "/") && secondNum == 0.0){
				display.setText("ERROR");
				System.out.println(display.getText());
				firstNum = 0.0;
				secondNum = 0.0;
				operators = true;
				doClear = true;
			}
				
			if (!(display.getText().equals("ERROR"))) {
			operators = true;
			decPoint = false;

			display.setText(String.valueOf(firstNum));
		
			secondNum = firstNum;

			
			System.out.println("SecondNum is: " + firstNum);
		}

		}

		public void clear() {
			firstNum = 0.0;
			secondNum = 0.0;
			operators = true;
			display.setText(null);

		}
	

    public static void main(String args[]) 
    {
        Secondtry sec = new Secondtry ();
    }   
}