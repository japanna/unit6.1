// TrafficLight.java

/**
* Draws a traffic light 
*
* @author   Anna Ntenta
* @version  1.0 Last Modified 4/14/2014
*/

import javax.swing.*;                // Swing components
import java.awt.*;                   // Colors, Fonts, etc.
import java.util.*; 

public class TrafficLight 
{
   // location of jFrame on screen
   public static final int FRAME_X = 300;
   public static final int FRAME_Y = 100;

   // size of jFrame
   public static final int WIDTH = 201;
   public static final int HEIGHT = 630;

   public static void main( String [] args)
   {
      JFrame f = new JFrame ("Traffic Light");
      f.setSize (WIDTH, HEIGHT);
      f.setLocation(FRAME_X, FRAME_Y);
      f.add (new MyTrafficLight());
      f.setVisible (true);
      f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   }
}

class MyTrafficLight extends JPanel 
{
   // position of circles
   public static final int CIRCLE_X = 1;
   public static final int CIRCLE_Y = 1;


   /**
   * Draws the three circles and fills them in
   **/
   public void paintComponent (Graphics g)
   {
      g.setColor (Color.RED);
      g.drawOval (CIRCLE_X, CIRCLE_Y, 199, 199);
      g.fillOval (CIRCLE_X, CIRCLE_Y, 199, 199);

      g.setColor (Color.YELLOW);
      g.drawOval (CIRCLE_X, (CIRCLE_Y + 202), 199, 199);
      g.fillOval (CIRCLE_X, (CIRCLE_Y + 202), 199, 199);

      g.setColor (Color.GREEN);
      g.drawOval (CIRCLE_X, (CIRCLE_Y + 404), 199, 199);
      g.fillOval (CIRCLE_X, (CIRCLE_Y + 404), 199, 199);
   }
}
