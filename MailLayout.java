// MailLayout.java

/****
* This program presents a window that looks like an email client.
* When the "Send" button is clicked, the content of the message is
* written to a file named outbox.txt.
*
* @author	Anna Ntenta
* @version	1.0 Last Modified 4/5/2014
*/

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.io.*;                    // File I/O
import java.awt.event.*;             // ActionListener, etc.
import java.util.*;                  // Scanner class
import javax.swing.border.*;

public class MailLayout extends JFrame implements ActionListener, FocusListener
{
	// private variables
	private JButton sendButton;

	private JTextField toField;
	private JTextField ccField;
	private JTextField bccField;
	private JTextField subjectField;

	private JComboBox<String> fromField;

	private JTextArea message;

	private JFrame frame;

	// constructor
	public MailLayout()
	{
		frame = new JFrame ("New Message");
		frame.setLayout( new BorderLayout( 10, 10 ));
        Font f = new Font ("Helvetica", Font.BOLD, 24);

        // labels
        JLabel to = new JLabel ("To:", SwingConstants.RIGHT);
        JLabel cc = new JLabel ("Cc:", SwingConstants.RIGHT);
        JLabel bcc = new JLabel ("Bcc:", SwingConstants.RIGHT);
        JLabel subject = new JLabel ("Subject:", SwingConstants.RIGHT);
        JLabel from = new JLabel ("From:", SwingConstants.RIGHT);
        
        to.setFont (f);
        cc.setFont (f);
        bcc.setFont (f);
        subject.setFont (f);
        from.setFont (f);

       	// text fields
        toField = new JTextField();
        ccField = new JTextField();
        bccField = new JTextField();
        subjectField = new JTextField();
        subjectField.addFocusListener(this);

        // JComboBox
        String[] fromAddr = { "anna@gmail.com", "anna.ntenta@gmail.com", "anna.e.mckelvey@gmail.com" };
        fromField = new JComboBox<>(fromAddr);

        // "send" button
        sendButton = new JButton ("Send");
        sendButton.addActionListener(this);
        sendButton.setFont (f);

        // message area
        message = new JTextArea();
        message.setBorder (BorderFactory.createTitledBorder("Message"));
       
   		// top contains the labels and the "to", "cc" etc text fields
        JPanel top = new JPanel( new BorderLayout());   

        // labels in a grid layout
        JPanel labels = new JPanel( new GridLayout(6, 0, 4, 4));

        // text fields in a grid layout
        JPanel textFields = new JPanel( new GridLayout(6, 0, 4, 4));

        // add label grid and text field grid to top area
        top.add (labels, BorderLayout.WEST);
        top.add (textFields, BorderLayout.CENTER);

        // add labels to label grid
        labels.add (to);
        labels.add (cc);
        labels.add (bcc);
        labels.add (subject);
        labels.add (from);
        labels.add (sendButton);

        // add text fields and JComboBox to grid
        textFields.add(toField);
        textFields.add(ccField);
        textFields.add(bccField);
        textFields.add(subjectField);
        textFields.add(fromField);

        // add top and message area to frame
        frame.add (top, BorderLayout.NORTH);
        frame.add (message, BorderLayout.CENTER);

        frame.setSize (600, 600);
        frame.setVisible (true);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed( ActionEvent ae )
    {
        if( ae.getSource() == sendButton )
        	processMail(message);
    }

    public void focusGained( FocusEvent fe) 
    {
    }

    public void focusLost( FocusEvent fe)
    {
    	if( fe.getSource() == subjectField )
    		frame.setTitle(subjectField.getText());
    }
    

    /** 
     * processMail() writes the contents of message to outbox.txt
     * 
     * @param message -- a JTextArea where the message is typed
     */
    public void processMail(JTextArea message) {
    	// add check for "to"
        try 
          {
          	File fileName = new File("outbox.txt");
	        FileWriter outStream =  new FileWriter (fileName, true);
	        outStream.append ("To: " + toField.getText() + "\nCc: " + ccField.getText() +
	        				  "\nBcc: " + bccField.getText() + "\nSubject: " + subjectField.getText() +
	        				  "\nFrom: " + fromField.getSelectedItem() + "\nMessage: " + message.getText() +"\n\n");
	        outStream.close ();
	        JOptionPane.showMessageDialog (null, "Your email has been sent!");
	        toField.setText(null);
	        ccField.setText(null);
	        bccField.setText(null);
	        subjectField.setText(null);
          	message.setText(null);
          	toField.requestFocusInWindow();
          } 
          catch (IOException e) 
          {
               message.setText("IOERROR: " + e.getMessage() + "\n");
               e.printStackTrace();
          }
    } 
    

	public static void main(String args[])
    {
        MailLayout ml = new MailLayout ();
    }     
}