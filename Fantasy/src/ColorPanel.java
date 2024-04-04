/* ColorPanel class, which has 5 objects, each printing a player option, and a draft button, 
 * which initiates the drafting of the corresponding player. The filter stays filtered, 
 * until reverted to a different filter. each panel has its own draft button and listener, 
 * but mostly everything else is static. the player array holds 5 players, and each one has the index
 * of the corresponding panel's int num. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class ColorPanel extends JPanel {
	Random generator = new Random();
	static Database board = new Database();
	private static Player[] players;
	private int num;
	private static String filter;
	JButton draft;
	public ColorPanel(Color c, int n) {
		num = n;
		filter = new String("none");
		setPreferredSize(new Dimension(100, 100));
		draft = new JButton("DRAFT");
		draft.addActionListener(new AddListener());
		add(draft);
		add(new JLabel("                                                                              "));
		players = Database.get5Players(filter);
	}
	//after user drafts their 15th player, there is a 2 second delay and then game is ended.
	private static void end() {
		if (AltPanel.getRound() >= 15) {
			TimerTask task2 = new TimerTask() {
				public void run() {
					frame.end();
					JOptionPane.showMessageDialog(null, "Your team: \n" + Database.getUrString());
				}
			};
			Timer timer2 = new Timer();
			timer2.schedule(task2, 2000);
		}
	}
	//gets the next 5 from database and assigns it to the player array.
	public static void next5() {
		if (filter.equals("none")) {
			players = Database.getnext5();
		}
		else {
			players = Database.getnext5(filter);
		}
		end();
	}
	//sets the players array to the top 5 of parameter team
	private static void filterT(String team) {
		filter = team;
		players = Database.get5Players(filter);
		end();
	}
	// sets the players array to the top 5 of parameter pos
	private static void filterP(String pos) {
		filter = pos;
		players = Database.get5Players(filter);
		end();
	}
	//reverts the players array to the top 5 overall
	private static void filter() {
		filter = "none";
		players = Database.get5Players();
		end();
	}
	//this gets valid input from user, and calls itself until valid input is received.
	public static void initiateFil() {
		String str = JOptionPane.showInputDialog("How would you like the Players filtered?\n1) Top 5 Overall\n2) Top 5 by Team\n3) Top 5 by Position\n4) Cancel");
		try {
			getFilter(Integer.parseInt(str));
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
			initiateFil();
		}
	}
	//uses the number from getFilter() to get the second prompt (pos or team), then initiates resorting using that
	public static void getFilter(int num) {
		String str = new String();
		//str = JOptionPane.showInputDialog("How would you like the Players filtered?\n1) Top 5 Overall\n2) Top 5 by Team\n3) Top 5 by Position\n4) Cancel");
		if (num == 1) {
			filter();
		}
		else if (num == 2) {
			str = JOptionPane.showInputDialog(null, "Please enter a Team in this format (BAL): ");
			if (Database.checkValTeam(str)) {
				filterT(str.toUpperCase());
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
				getFilter(num);
			}
		}
		else if (num == 3) {
			str = JOptionPane.showInputDialog(null, "Please enter a Position in this format (QB): ");
			if (Database.checkValPos(str)) {
				filterP(str.toUpperCase());
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
				getFilter(num);
			}
		}
		else if (num != 4){
			JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a 1-4.");
			initiateFil();
		}
	}
	public static void rep() {
		filter();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(players[num].getColor(2));
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		setBackground(players[num].getColor(1));
		g.drawString(players[num].toString(), 370, 25);
	}
	/*first confirms to draft the player, checks to see if they can draft that 
	player, then initiates drafting that player in the database.*/
	public static void draft(Player p) {
		if (!p.getName().equals("Empty")) {
			int b = JOptionPane.showConfirmDialog(null, "Draft " + p.getName() + "?");
			if (b == 0) {
				if (Database.canDraft(p)) {
					board.nextRound(p);
					if (AltPanel.getRound() <= (Database.getROUNDS()+1) && !p.getName().equals("null")) {
						players = Database.get5Players(filter);
						frame.repaintAll();	
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You may not draft too many of one position.\nCheck Your Team on the left to find out\nwhat players you can draft.");
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Dude. You can't draft 'empty'. Since there are no more Players, "
					+ "please stop spamming 'Next 5' and go back to the top and draft a real player.");
		}
	}
	//listens for the draft button.
	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			draft(players[num]);
		}
	}
}
