// Calculator.java

/****
* This program implements a basic calculator
*
* @author	Anna Ntenta
* @version	1.0 Last Modified 4/10/2014
*/

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  
import javax.swing.border.*;

public class Calculator extends JFrame implements ActionListener
{
	// number of digits and dec points allowed in display
	public static final int CAPACITY = 15;

	// private variables
	private JFrame frame;
	private JTextArea display;
	private Boolean decPoint = false;
	private Boolean operators = false;
	private StringBuilder num1 = new StringBuilder();
	private StringBuilder num2 = new StringBuilder();
	private Double firstNum;
	private Double secondNum;
	private Double result = null;
	private String operator;


	// array of number buttons
	private JButton [] jb = new JButton[10];
	//operator buttons
	private JButton clear, root, plus, minus, div, mult, equals, point;


	/****
     *  Constructor. Sets up user interface and initializations
     */
	public Calculator()
	{
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

	// calculates square root
	public void calc(Double first, String operand)
	{
		if(operand == "\u221A")
		result = Math.sqrt(first);

		// clear num1
    	num1.setLength(0);
    	// clear num2
    	num2.setLength(0);
    	display.setText(null);
		num1.append(result);
		//operators = false;
		display.append(result.toString());
		System.out.println("Num 1 now (one number calc): " + num1);
	}

	public void calc(Double first, Double second, String operand)
	{
		if(operand == "+")
		result = first + second;
		if(operand == "-")
		result = first - second;
		if(operand == "*")
		result = first * second;
		if((operand == "/") && second != 0)
		result = first / second;

		//System.out.println(result);
		// clear num1
    	num1.setLength(0);
    	// clear num2
    	num2.setLength(0);
    	display.setText(null);
		num1.append(result);
		//operators = false;
		display.append(result.toString());
		System.out.println("Num 1 now (two number calc): " + num1);


	}

	public void actionPerformed( ActionEvent ae )
    {
    	// the first number entered after clear or launch
    	if(operators == false) {
    		// disallow leading zeros 
    		if((ae.getSource() == jb[0]) && (num1.length() != 0) && 
    			(num1.length() < 15))  {
    			// add to num1 array list
	    		num1.append("0");
	    		// display the digit
	    		display.append("0");
    		}
    		// check if a digit other than 0 was clicked
	    	for(int i = 1; i < 10; i++) {
	    		// if number and it is less than 15 digits
	    		if((ae.getSource() == jb[i]) && (num1.length() < 15)){
	    			// add to num1 array list
	    			num1.append(jb[i].getText());
	    			// display the digit
	    			display.append(jb[i].getText());
	    			System.out.println("Num1: " + num1.toString());
    			}
    		}
    		// check if decimal point was clicked for first time
    		if((ae.getSource() == point) && (decPoint == false) && 
    			(num1.length() < 15)) {
    			// disallow more decimal points
    			decPoint = true;
    			// add to num1 array list
	    		num1.append(".");
	    		// display the digit
	    		display.append(".");
	    		System.out.println("Num1: " + num1.toString());
    		}
    	}

    	// check if user clicked square root after a number has been entered
    	if((num1.length() > 0) && ((ae.getSource() == root))) {
    		// indicate that first number has been entered
	    		operators = true;
	    		// store first number as a Double
	    		firstNum = Double.parseDouble(num1.toString());
	    		System.out.println(firstNum);
	    		// store source of operator in variable
	    		operator = (((JButton)ae.getSource()).getText());

	    		// send number and operator to calc()
    			calc(firstNum, operator);
    	}

    	// check if user clicked an operator other than root after one number has been entered
    	if(((num1.length() > 0) && (num2.length() == 0)) && ((ae.getSource() == plus) || (ae.getSource() == minus) ||
    		(ae.getSource() == div) || (ae.getSource() == mult))) {
    			// set operator click to "true"
	    		operators = true;
	    		// store first number as a Double
	    		firstNum = Double.parseDouble(num1.toString());
	    		System.out.println(firstNum);
	    		// store source of operator in variable
	    		operator = (((JButton)ae.getSource()).getText());
	    		System.out.println(operator);
    	}

    	// second number (only stored after an operator has been typed in)
    	if(operators == true) {
    		// set decimal point indicator to false
    		decPoint = false;
    		// disallow leading zeros 
    		if((ae.getSource() == jb[0]) && (num2.length() != 0) && 
    			(num2.length() < 15))  {
    			// add to num1 array list
	    		num2.append("0");
	    		// display the digit
	    		display.append("0");
    		}
    		// check if a digit other than 0 was clicked
	    	for(int i = 1; i < 10; i++) {
	    		// if number and it is less than 15 digits
	    		if((ae.getSource() == jb[i]) && (num2.length() < 15)){
	    			//if this was the first digit in second number
	    			if(num2.length() == 0) {
	    				// clear display
	    				display.setText(null);
	    			}
	    			// add to num1 array list
	    			num2.append(jb[i].getText());
	    			// display the digit
	    			display.append(jb[i].getText());
	    			System.out.println("Num2: " + num2.toString());
    			}
    		}
    		// check if decimal point was clicked for first time
    		if((ae.getSource() == point) && (decPoint == false) && 
    			(num2.length() < 15)) {
    			// disallow more decimal points
    			decPoint = true;
    			// add to num1 array list
	    		num2.append(".");
	    		// display the digit
	    		display.append(".");
	    		System.out.println("Num2: " + num2.toString());
    		}
    	}

    	// check if user clicked an operator other than square root after 
    	// two numbers were entered
    	if(((num1.length() > 0) && (num2.length() > 0)) && ((ae.getSource() == plus) || (ae.getSource() == minus) ||
    		(ae.getSource() == div) || (ae.getSource() == mult))) {

    		firstNum = Double.parseDouble(num1.toString());
    		System.out.println("Num1: " + num1.toString());

    		// store second number as a Double
    		secondNum = Double.parseDouble(num2.toString());
    		System.out.println("Num2: " + num2.toString());
    		// store source of operator in variable
	    	operator = (((JButton)ae.getSource()).getText());
	    	System.out.println(operator);
    		// send numbers and operator to calc()
    		calc(firstNum, secondNum, operator);
    	}



    	// check if user clicked the equal sign after 
    	// two numbers were entered
    	if((ae.getSource() == equals) && (num1.length() > 0) && 
    		(num2.length() > 0)) {
    		
    		firstNum = Double.parseDouble(num1.toString());
    		System.out.println("Num1: " + num1.toString());

    		// store second number as a Double
    		secondNum = Double.parseDouble(num2.toString());
    		// send numbers and operator to calc()
    		calc(firstNum, secondNum, operator);

    	}

    	

    	
    	
    	// check if clear button was clicked
    		if(ae.getSource() == clear) {
    			// clear display
    			display.setText(null);
    			// clear decimal point indicator
    			decPoint = false;
    			// clear operator indicator
    			operators = false;
    			// clear num1
    			num1.setLength(0);
    			// clear num2
    			num2.setLength(0);
    			System.out.println(num1.length());
    		}	
    }

    public static void main(String args[]) 
    {
        Calculator calc = new Calculator ();
    }   
}