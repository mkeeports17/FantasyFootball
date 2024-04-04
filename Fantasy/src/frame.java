/* Michael Keeports
 * 1/15/21
 * This is the class for the frame, which has all the panels, buttons, etc...*/
import javax.swing.*;
import java.awt.*;
public class frame{
    static JPanel[] panels;
	static JFrame theGUI = new JFrame();
	public static void main() {
		//int n = getInputNum("How many teams would you like in your league?", "Invalid input.", "Please enter a 2-8: ");
		System.out.println("Main");
		panels = new JPanel[8];
		theGUI.setTitle("Draft Simulator");
		theGUI.setSize(1800,800);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = theGUI.getContentPane();
		JPanel panel;
		JOptionPane.showMessageDialog(null, "Welcome to Fantasy Football Draft Simulator!\nAfter inputting the number of teams, you will have 5 players to choose from."
				+ "\nThe other teams will be autodrafted by the computer, using a highly efficient autodrafting method."
				+ "\nYou may also use the 'Apply Filter' button, to apply a 'team' or 'position' filter."
				+ "\nYou can use the 'Next 5' button to get the next 5 players of that filter. \nWhen you filter, the board will stay filtered by that until you change it."
				+ "\nNext to that, there is a 'search for player' button, which you can use to "
				+ "search for any player.\nAfter you draft a player, your pick and the players autodrafted "
				+ "will show up the right side, under 'Recently Drafted'.\nYour team can be found on the left side, under 'Your Team'."
				+ "\nWhen the simulator begins, you will be able to see the roster template for your team.\nYou should follow this,"
				+ "because you MUST pick a Kicker and a DST. \nAlso, you may not pick more than 5 of any position, even if the bench is empty."
				+ "\nThere is a clock in the top left corner, so you only have 40 seconds to pick! (60 sec for your first pick)"
				+ "\nIf you run out of time, the computer will autodraft for you. "
				+ "\nLikewise, you can choose to autodraft by clicking the 'Auto Draft' Button. "
				+ "\nAny questions? No? Great, let's get started!");
		Database.setTeams(getInputNum("How many teams would you like?", "Invalid input.", "Please enter a 2-8 for amount of teams: "));
		
		/*I used GridBagLayout for my Layout. This code is NOT copied from the internet. Most of 
		 * the help I received for this was from my older brother who took this class. He helped 
		 * explain to me how to use this, so I could write the code myself. I did, however, use
		 * an article for further guidance. It is only used for
		the layout. There are three panels (top, left, and right), that are AltPanel objects, and
		the panels with the player options are ColorPanel objects.*/
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//top AltPanel with round num
		c.fill = GridBagConstraints.HORIZONTAL;
		panel = new AltPanel(3);
		c.weightx = 1;
		c.ipadx = 10;
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		c.gridheight = 1;
		c.gridwidth = 4;
		panels[0] = panel;
		pane.add(panel, c);

		//left AltPanel with 'Your Team'
		c.fill = GridBagConstraints.VERTICAL;
		panel = new AltPanel(1);
		c.weightx = 0.01;
		c.ipadx = 210;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridheight = 5;
		c.gridwidth = 1;
		panels[1] =  panel;
		pane.add(panel, c);
		
		//5 ColorPanels with player options
		for (int i = 0; i < 5; i++) {
			panel = new ColorPanel(Color.yellow, 4-i);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.ipady = 50;
			c.ipadx = 0;
			c.weightx = 0.08;
			c.gridx = 1;
			c.gridy = 5-i;
			c.insets = new Insets(17, 0, 0, 17);
			c.gridheight = 1;
			c.gridwidth = 1;
			panels[i+2] = panel;
			pane.add(panel, c);
		}
		
		//right AltPanel with 'Recently Drafted'
		c.fill = GridBagConstraints.VERTICAL;
		panel = new AltPanel(2);
		c.weightx = 0.01;
		c.ipadx = 180;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(10, 0, 0, 0);
		c.gridheight = 5;
		c.gridwidth = 1;
		panels[7] = panel;
		pane.add(panel, c);
	    theGUI.setVisible(true);
	    ColorPanel.rep();
	}
	//this method gets valid input for the amount of teams, from the user using JOptionPane messages
	public static int getInputNum(String message, String errormsg, String basic) {
		String t = JOptionPane.showInputDialog(null, message + "\n" + basic);
		Boolean bool = false;
		int num = 2;
		try {
			num = Integer.parseInt(t);
			if (!Database.setTeams(num)) {
				bool = true;
			}
		}
		catch (Exception e){
			bool = true;
		}
		while (bool) {
			try {
				num = Integer.parseInt(JOptionPane.showInputDialog(null, errormsg + "\n" + basic));
				if (num < 9 && num > 1) {
					break;
				}
			}
			catch (Exception e){}
		}
		return num;
	}
	//hides the program when the draft is done.
	public static void end() {
		theGUI.setVisible(false);
	}
	public static JPanel getPanel(int index) {
		return panels[index];
	}
	//array with all the panels, and this method repaints all of them
	public static void repaintAll() {
		for (int i = 0; i < 8; i++) {
			panels[i].repaint();
		}
	}
	//repaints only the top AltPanel, so that only the timer is repainted, since this method is called every second
	public static void repaintTimer() {
		panels[0].repaint();
	}
}

