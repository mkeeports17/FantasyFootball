/* This is the AltPanel class. It takes an int (1-3), so I knew which one it was. AltPanel 1 has 'Your Team, 
 * which is printed in a special Fantasy Football lineup template. AltPanel 2 has 'Recently Drafted', 
 * which include the player you draft and the players the computer autodrafts. It holds only the last 8 players, 
 * no matter the amount of teams, because that fits best. Since the teams max is 8, it always holds at least one round.
 * AltPanel 3 is at the top. It holds a timer, which gives you 40 seconds to draft, and when that is up, autodrafts for you.
 * It also holds the round number, and buttons filter, search for player, next 5, and autodraft. There are four 
 * different AddListeners, each for the corresponding button.*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class AltPanel extends JPanel{
	private int num;
	private static int round, timecount;
	Database board = new Database();
	JButton filter, search, next, auto;
	public AltPanel(int n) {
		super();
		round = 1;
		timecount = 60;
		num = n;
		setSize(100, 1000);
		//this code only applies to AltPanel 3
		if (num == 3) {
			add(new JLabel("                                                   "));
			filter = new JButton("Apply Filter");
			filter.addActionListener(new AddListener());
			add(filter);
			search = new JButton("Search for Player");
			search.addActionListener(new AddListener2());
			add(search);
			next = new JButton("Next 5");
			next.addActionListener(new AddListener3());
			add(next);
			auto = new JButton("Auto Draft");
			auto.addActionListener(new AddListener4());
			add(auto);
			TimerTask task = new TimerTask() {
				public void run() {
					timecount --;
					if (timecount == 0) {
						int b = JOptionPane.showConfirmDialog(null, "You ran out of time. :(\nYou autodrafted " + board.autoDraft(0).getName());
						if (b == 0 || b == 1 || b == 2) {
							resetTimecount();
						}
						frame.repaintAll();
					}
					frame.repaintTimer();
				}
			};
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task, 1000, 1000);
		}
	}
	public static void resetTimecount() {
		timecount = 40;
	}
	public static void incRound() {
		round ++;
	}
	public static int getRound() {
		return round;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		if (num == 1) {
			//prints the position of the template, and then prints the players in the right spots.
			g.drawString("Your Team: ", 40, 20);
			String str = null;
			for (int i = 0; i < Database.getROUNDS(); i++) {
				if (i == 0) {
					str = "QB";
				}
				else if (i == 1 || i == 2) {
					str = "RB";
				}
				else if (i == 3 || i == 4) {
					str = "WR";
				}
				else if (i == 5) {
					str = "TE";
				}
				else if (i == 6) {
					str = "FLX";
				}
				else if (i == 7) {
					str = "DST";
				}
				else if (i == 8) {
					str = "K";
				}
				else {
					str = "BE";
				}
				g.drawString(str, 0, 35*(i+1)+20);
			}
			Player[] team = Database.getYourTeam();
			if (team.length > 0) {
				if (team[0] != null) {
					for (int i = 0; i < team.length; i++) {
						if (team[i].getName().equals("Empty")) {
							g.setFont(new Font("Arial", 2, 15));
						}
						else {
							g.setFont(new Font("Arial", Font.PLAIN, 18));
						}
						g.drawString(team[i].getName(), 50, 35*(i+1)+20);
					}
				}
				else {
					g.drawString("null", 15, 25*(1)+20);
				}
			}
		}
		else if (num == 2){
			//prints the recently drafted array from database class.
			g.drawString("Recently Drafted:", 30, 40);
			Player[] recents = board.getRecentlyDrafted();
			if (recents.length > 0) {
				for (int i = 0; i < recents.length; i++) {
					g.drawString(recents[i].getName(), 25, (50*(i+1)+50));
				}
			}
		}
		else if (num == 3) {
			//reprints the round number and clock seconds
			setBackground(new Color(173,216,230));
			g.drawString("Round: " + round, 480, 25); 
			g.drawString("Clock:  " + timecount, 350, 25);
		}
	}
	//listens for filer button
	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ColorPanel.initiateFil();
			frame.repaintAll();
		}
	}
	//listens for player search button
	private class AddListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String player = JOptionPane.showInputDialog(null, "Enter Player Name Here: ");
			while (!board.checkName(player) || board.checkDrafted(player)) {
				if (!board.checkName(player)) {
					player = JOptionPane.showInputDialog(null, "No player found.\nPlease try a different name here: ");
				}
				else {
					player = JOptionPane.showInputDialog(null, "Player Already Drafted.\nPlease try a different name here: ");
				}
			}
			ColorPanel.draft(board.getPlayer(player));
		}
	}
	//listens for next5 button
	private class AddListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ColorPanel.next5();
			frame.repaintAll();
		}
	}
	//listens for autodraft button
	private class AddListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "You drafted " + board.autoDraft(0).getName() + "!");
			frame.repaintAll();
		}
	}
}
