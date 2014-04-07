// Age.java

/****
* This program asks the user for her age and responds with a message
* that she is young if she is under 40 and old if she is 40 or older.
* It has a simple GUI for convenience.
* 
* @author	Anna Ntenta
* @version 	1.0	Last modified 4/3/2014
*/

import javax.swing.*;			// Swing components

public class Age
{
	public static void main (String [] args) 
    {
    	Age a = new Age();
    }

    public Age() 
    {
    	try {
	        String ageText = JOptionPane.showInputDialog (null, "Dear friend, how old are you?", "Young-or-Old O'matic", JOptionPane.QUESTION_MESSAGE);
	        int age = Integer.parseInt(ageText);
	        
	        if (age < 40) {
	        	JOptionPane.showMessageDialog (null, age + "? That's young!", "Young-or-Old O'matic", JOptionPane.QUESTION_MESSAGE);
	    	}
	    	else {
	    		JOptionPane.showMessageDialog (null, age + "? That's way too old!", "Young-or-Old O'matic", JOptionPane.QUESTION_MESSAGE);
	    	}
	    }
	    catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog (null, "Hey! You didn't answer!", "Young-or-Old O'matic Error", JOptionPane.WARNING_MESSAGE);
	    }
    }
}