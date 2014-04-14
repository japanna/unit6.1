// Calculator.java

/**
* Implements a basic calculator with  a +/- button that negates the current 
* display. Clicking this button repeatedly will toggle the value’s sign.
*
* @author	Anna Ntenta
* @version	1.0 Last Modified 4/13/2014
*/

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  
import javax.swing.border.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;


public class Calculator extends JFrame implements ActionListener
{
	// number of digits (inclusive of decimal point) allowed in the display
	public static final int CAPACITY = 15;
	// set maximum digits to the right of decimal to 10
	public static final int MAX_DECIMALS = 10;
	// set min digits to the right of decimal to 0
	public static final int MIN_DECIMALS = 0;

	private JFrame frame;

	private JTextArea display;

	// boolean set to true if  a number already has a decimal point
	private Boolean decPoint;

	// boolean set to true if an operator has already been clicked
	private Boolean operators;

	// Double representing addend, subtractor, multiplicator or numerator
	private Double firstNum;

	// Double representing addend, subtractor, multiplicator or denominator
	private Double secondNum;

	// String representing an operator (+, -, *, /, or square root)
	private String operator;

	// boolean set to true if a second operator is clicked immediately after an operator
	private Boolean secondOp;

	// boolean set to true if a second equal sign is clicked immediately after equal sign
	private Boolean secondEquals;
	
	// indicates whether the display should be cleared
	private Boolean doClear;

	// array of number buttons
	private JButton [] jb = new JButton[10];

	//operator buttons
	private JButton clear, root, plus, minus, div, mult, equals, point, negate;


	/****
     *  Constructor. Sets up user interface and initializations
     */
	public Calculator()
	{
		decPoint = false;
		firstNum = 0.0;
		secondNum = 0.0;
		operator = null;
		secondOp = false;
		secondEquals = false;
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

		// create the negate button
		negate = new JButton("\u002B\u002F\u002D");
		negate.addActionListener(this);
		negate.setFont (new Font ("SansSerif", Font.BOLD, 14));

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
		buttons.add(negate);

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
	    // if decimal point is clicked for the first time in a number
	    if((ae.getSource() == point) && (decPoint == false)) {
	    	display.append(".");
	    	// disallow leading zeros
			if (display.getText().equals(".")) {
				display.setText("0.");
			}
			// store 0. as a Double
		    secondNum = Double.parseDouble(display.getText());
		    // disallow additional decimal points
	    	decPoint = true;
	    	// do not clear display the next time a button is clicked
	    	doClear = false;
	    	// this is a number, so a subsequent operator will be the one applied
	    	secondOp = false;
	    	// this is a number, so a subsequent equal sign will be allowed
	    	secondEquals = false;
	    }
	    else {
			// check if a digit was clicked
		    for (int i = 0; i < 10; i++) {

		    	// append digits to number while number is less than 15 digits long
		    	if((ae.getSource() == jb[i]) && (display.getText().length() < 15)) {
		    		// display digit
					display.append(((JButton)ae.getSource()).getText());

					if (display.getText().equals("0")) {
						display.setText("0.");
						// disallow additional decimal points in this number
						decPoint = true;
					}

					// store as a Double
		    		secondNum = Double.parseDouble(display.getText());
		    		// this is a number, so a subsequent equal sign will be allowed
		    		secondEquals = false;
		    		doClear = false;
		    		// this is a number, so subsequent operator will be the one applied
		    		secondOp = false;
		    	}
			}
		}	

		// if an operator was clicked
		if((ae.getSource() == plus) || (ae.getSource() == minus) ||
    		(ae.getSource() == div) || (ae.getSource() == mult)) {
			// decimal point allowed again
			decPoint = false;
			// if operator was clicked immediately after a number 
			if(secondOp == false) {
				// if this is the first operator in the calculation
				if (operators) {
					// the number last entered becomes the first number
					firstNum = secondNum;
				}
				// if this is the second operator in a calculation, calculate
				else {
					calc();
				}

				// store the operator that was clicked
				JButton temp=(JButton)ae.getSource();
				operator = temp.getText();
				operators = false;
				doClear = true;
				secondOp = true;
			}
			// if an operator was clicked immediately after another operator
			else {
				// store the new operator that was clicked
				JButton temp=(JButton)ae.getSource();
				// make it the operator to be applied
				operator = temp.getText();
				// keep displaying the first operand
				display.setText(String.valueOf(format(firstNum)));
				// decimal point allowed again
				decPoint = false;
			}
		}

		// calculate square root
		if(ae.getSource() == root) {
			squareRoot();
		}

		// negate number
		if(ae.getSource() == negate) {
			negate();
		}

		// if clear button is clicked
		if(ae.getSource() == clear) {
			clear();
		}

		// if equal sign is clicked (once), calculate 
		if((ae.getSource() == equals) && (secondEquals == false)) {
			calc();
			decPoint = true;
		}
	}	

	/**
     *  Negates number
     */
	public void negate() {
		if (secondNum > 0) {
			secondNum -= (2 * secondNum);
		}
		else if (secondNum < 0) {
			secondNum += -(2 * secondNum);
		}
		display.setText(String.valueOf(format(secondNum)));
	}


	/**
     *  Calculates square root
     *  Yields an ERROR if applied to zero
     */
	public void squareRoot() {
		// square root of zero yields an ERROR
		if (secondNum == 0.0) {
			display.setText("ERROR");
			// reset
			firstNum = 0.0;
			secondNum = 0.0;
			operators = true;
			doClear = true;
		}
		// square root of non-zero
		else {
			secondNum = Math.sqrt(secondNum);
			display.setText(String.valueOf(format(secondNum)));
			// indicates that it's OK to enter a decimal point again
			decPoint = false;
		}
	}

	/**
     *  Calculates (+, -, * and /)
     */
	public void calc() {

		if(operator== "+") {
			firstNum += secondNum;
		}
		if(operator == "-"){
			firstNum -= secondNum;
		}
		if(operator == "*") {
			firstNum *= secondNum;
		}
		// division by non-zero
		if((operator == "/") && secondNum != 0.0) {
			firstNum /= secondNum;
		}
		// division by zero
		if((operator == "/") && secondNum == 0.0) {
			display.setText("ERROR");
			// reset
			firstNum = 0.0;
			secondNum = 0.0;
			operators = true;
			doClear = true;
		}
			
		// if the calculation did not yield an ERROR	
		if (!(display.getText().equals("ERROR"))) {
			// set ready for new number
			operators = true;
			// it's OK to enter a decimal point again
			decPoint = false;
			// clicking the equals-button right now does nothing
			secondEquals = true;
			// display formatted result
			display.setText(String.valueOf(format(firstNum)));
			// the result of the calculation is now the first operand in 
			// subsequent calculations
			secondNum = firstNum;
		}
	}

	/**
     *  Clears the display and resets values for new calculation
     */
	public void clear() {
		firstNum = 0.0;
		secondNum = 0.0;
		operators = true;
		display.setText(null);
		decPoint = false;
		secondEquals = false;
	}

	/**
     *  Formats the output that is shown in display
     *
     *  @param  n   The number to be formatted
     *  @return 	Formatted number
     */
	public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(MIN_DECIMALS);
        format.setMaximumFractionDigits(MAX_DECIMALS);
        return format.format(n);
    }
	

    public static void main(String args[]) 
    {
        Calculator calc = new Calculator ();
    }   
}