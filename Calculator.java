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

public class Calculator extends JFrame implements ActionListener
{
	// number of digits (inclusive of decimal point) allowed in the display
	public static final int CAPACITY = 15;

	// private variables
	private JFrame frame;
	private JTextArea display;
	// boolean set to true if number already has a decimal point
	private Boolean decPoint = false;
	// boolean set to true if an operator has been clicked
	private Boolean operators = false;
	// string representing addend, subtractor, multiplicator or numerator
	private StringBuilder num1 = new StringBuilder();
	// string representing addend, subtractor, multiplicator or denominator
	private StringBuilder num2 = new StringBuilder();
	// Double representing addend, subtractor, multiplicator or numerator
	private Double firstNum;
	// Double representing addend, subtractor, multiplicator or denominator
	private Double secondNum;
	// result of a calculation
	private Double result = null;
	// String representing an operator (+, -, *, /, or square root)
	private String operator;
	// String representing an operator (+, -, *, /, or square root)
	private String operator2;

	// indicates w


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
	public void calc1(Double first, String operand)
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

	// calculates square root
	public void calc2(Double second, String operand)
	{
		if(operand == "\u221A")
		result = Math.sqrt(second);

    	// clear num2
    	num2.setLength(0);
    	display.setText(null);
		num2.append(result);
		//operators = false;
		display.append(result.toString());
		System.out.println("Num 2 now (one number calc): " + num2);
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
		if(blnClear)
			txtCalc.Text="";
		
		Button b3=(Button)obj;
		
		txtCalc.Text+=b3.Text;	
		
		if (txtCalc.Text==".")
			txtCalc.Text="0.";
		dblSec=Convert.ToDouble(txtCalc.Text);
		
		blnClear=false;
	}

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

    	/*
    	// the first number entered after clear or launch
    	if(operators == false) {
    		// allow one zero 
    		if((ae.getSource() == jb[0]) && num1.length() == 0)  {
    			// add to num1 array list
	    		num1.append("0");
	    		System.out.println("Num1: " + num1.toString());
	    		// display the digit
	    		display.append("0");
	    		System.out.println("Num1 Char at 0: " + num1.charAt(0));
    		}
    		// allow any digits as long as the first one isn't 0
    		if((num1.length() > 0) && (num1.charAt(0) != '0')) {

		    	for(int i = 0; i < 10; i++) {
		    		// if number and it is less than 15 digits
		    		if((ae.getSource() == jb[i]) && (num1.length() < 15)){
		    			// add to num1 array list
		    			num1.append(jb[i].getText());
		    			// display the digit
		    			display.append(jb[i].getText());
		    			System.out.println("Num1: " + num1.toString());
	    			}
	    		}
    		}
    		if(ae.getSource() != jb[0])
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
    		
    		// else check if a number other than 0 was clicked
    		
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
    	} */

    	// if user clicked square root after the first number has been entered
    	if(((num1.length() > 0 ) && (num2.length() == 0)) && ((ae.getSource() == root))) {
    		// indicate that first number has been entered
	    		operators = true;
	    		// store first number as a Double
	    		firstNum = Double.parseDouble(num1.toString());
	    		System.out.println(firstNum);
	    		// store source of operator in variable
	    		operator = (((JButton)ae.getSource()).getText());

	    		// send number and operator to calc()
    			calc1(firstNum, operator);
    	}

    	// if user clicked square root after a second number has been entered
    	if((num2.length() > 0) && ((ae.getSource() == root))) {
	    		// store first number as a Double
	    		secondNum = Double.parseDouble(num2.toString());
	    		System.out.println(secondNum);
	    		// store source of operator in variable
	    		operator2 = (((JButton)ae.getSource()).getText());

	    		// send number and operator to calc2()
    			calc2(secondNum, operator2);
    			// store first number as a Double
	    		secondNum = Double.parseDouble(num2.toString());
    			calc(firstNum, secondNum, operator);
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