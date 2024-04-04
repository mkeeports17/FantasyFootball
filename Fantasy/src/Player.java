
/*This is the Player class. Since its a Fantasy Football Draft Simulator, each actual player has their own Player object. 
 * Each object has a name, position, team, overall rank (RK), and boolean available (not drafted) and printed (meaning a 
 * panel above it already printed this one). */

import java.awt.Color;
public class Player {
	private static int count = 0;
	private int RK;
	private String name, pos, team;
	private boolean available, printed;
	public Player(String nm, String t, String position) {
		name = nm;
		pos = position;
		team = t;
		count ++;
		available = true;
		printed = false;
		if (name.equals("Christian McCaffrey")) {
			count = 1; 		
			// since each player is constructed multiple times, the last pass sets #1, which is mccaffrey, back to 1 and the rest of the numbers are correct
		}
		RK = count;
	}
	public int getRK() {
		return RK;
	}
	public void setRK(int r) {
		RK = r;
	}
	public boolean getAV() {
		return available;
	}
	public void setName(String nm) {
		name = nm;
	}
	public void setAV() {
		available = false;
	}
	public boolean getPrinted() {
		return printed;
	}
	public void resetPrinted() {
		printed = false;
	}
	public String getName() {
		return name;
	}
	public String getTeam() {
		return team;
	}
	public String getPos() {
		return pos;
	}
	public void setPrinted() {
		printed = true;
	}
	public String toString() {
		if (pos.equals("DST")) {
			return "#" + RK + " " + name; //this avoid printing Ravens DST BAL DST which is repetive and unneccesary
		}
		return "#" + RK + " " + name + " " + team + " " + pos;
	}
	//this method takes an int, 1 for background color, 2 for font color, and returns the color based off the team of the player.
	public Color getColor(int n) {
		// I did not copy code, I only copied RGB colors from the following link
		// link https://teamcolorcodes.com/nfl-team-color-codes/
			if (team.equals("ARI")) {
				if (n == 1) {
					return new Color(151, 35, 63);
				}
				return new Color(0, 0, 0);
			}
			else if (team.equals("ATL")) {
				if (n == 1) {
					return new Color(167,25,48);
				}
				return new Color(0, 0, 0);
			}
			else if (team.equals("BAL")) {
				if (n == 1) {
					return new Color(26, 25, 95);
				}
				return new Color(158,124,12);
			}
			else if (team.equals("BUF")) {
				if (n == 1) {
					return new Color(0,51,141);
				}
				return new Color(198,12,48);
			}
			else if (team.equals("CAR")) {
				if (n == 1) {
					return new Color(0,133,202);
				}
				return new Color(191,192,191);
			}
			else if (team.equals("CHI")) {
				if (n == 1) {
					return new Color(11,22,42);
				}
				return new Color(200,56,3);
			}
			else if (team.equals("CIN")) {
				if (n == 1) {
					return new Color(251,79,20);
				}
				return new Color(0, 0, 0);
			}
			else if (team.equals("CLE")) {
				if (n == 1) {
					return new Color(255,60,0);
				}
				return new Color(49,29,0);
			}
			else if (team.equals("DAL")) {
				if (n == 1) {
					return new Color(0,34,68);
				}
				return new Color(127,150,149);
			}
			else if (team.equals("DEN")) {
				if (n == 1) {
					return new Color(0,34,68);
				}
				return new Color(251,79,20);
			}
			else if (team.equals("DET")) {
				if (n == 1) {
					return new Color(0,118,182);
				}
				return new Color(176,183,188);
			}
			else if (team.equals("GB")) {
				if (n == 1) {
					return new Color(24, 48, 40);
				}
				return new Color(255,184,28);
			}
			else if (team.equals("HOU")) {
				if (n == 1) {
					return new Color(3,32,47);
				}
				return new Color(167,25,48);
			}
			else if (team.equals("IND")) {
				if (n == 1) {
					return new Color(0,44,95);
				}
				return new Color(162,170,173);
			}
			else if (team.equals("JAX")) {
				if (n == 1) {
					return new Color(0,103,120);
				}
				return new Color(159,121,44);
			}
			else if (team.equals("KC")) {
				if (n == 1) {
					return new Color(227,24,55);
				}
				return new Color(255,184,28);
			}
			else if (team.equals("LAC")) {
				if (n == 1) {
					return new Color(0,42,94);
				}
				//return new Color(0,128,198); //light blue
				return new Color(255,194,14);
			}
			else if (team.equals("LAR")) {
				if (n == 1) {
					return new Color(0, 53, 148);
				}
				return new Color(255, 163, 0);
			}
			else if (team.equals("MIA")) {
				if (n == 1) {
					return new Color(0,142,151);
				}
				return new Color(0,87,120); //blue
				//return new Color(252,76,2); //orange
			}
			else if (team.equals("MIN")) {
				if (n == 1) {
					return new Color(79,38,131);
				}
				return new Color(255, 198,47);
			}
			else if (team.equals("NE")) {
				if (n == 1) {
					return new Color(0,34,68);
				}
				return new Color(176,183,188); // silver
				//return new Color(198,12,48); // red
			}
			else if (team.equals("NO")) {
				if (n == 1) {
					return new Color(211,188,141);
				}
				return new Color(16,24,31);
			}
			else if (team.equals("NYG")) {
				if (n == 1) {
					return new Color(1,35,82);
				}
				return new Color(163,13,45); //red
				//return new Color(155,161,162); //gray
			}
			else if (team.equals("NYJ")) {
				if (n == 1) {
					return new Color(18, 87, 64);
				}
				return new Color(0, 0, 0); //black
				//return new Color(255, 255, 255); //white
			}
			else if (team.equals("LV")) {
				if (n == 1) {
					return new Color(165,172,175);
				}
				return new Color(0, 0, 0);
			}
			else if (team.equals("PHI")) {
				if (n == 1) {
					return new Color(0,76,84);
				}
				return new Color(165,172,175);
			}
			else if (team.equals("PIT")) {
				if (n == 1) {
					return new Color(255,182,18);
				}
				return new Color(16,24,32);
			}
			else if (team.equals("SF")) {
				if (n == 1) {
					return new Color(170,0,0);
				}
				return new Color(173,153,93);
			}
			else if (team.equals("SEA")) {
				if (n == 1) {
					return new Color(0,34,68);
				}
				return new Color(165,172,175); //gray
				//return new Color(105,190,40); lime green
			}
			else if (team.equals("TB")) {
				if (n == 1) {
					return new Color(213,10,10);
				}
				return new Color(255, 121, 0);
			}
			else if (team.equals("TEN")) {
				if (n == 1) {
					return new Color(75,146,219);
				}
				return new Color(12,35,64);
			}
			else if (team.equals("WSH")) {
				if (n == 1) {
					return new Color(63,16,16);
				}
				return new Color(255,182,18);
			}
			else if (team.equals("null")) {
				if (n == 1) {
					return Color.yellow;
				}
				return Color.black;
			}
			else if (team.equals("%")) {
				if (n == 1) {
					return Color.yellow;
				}
				return Color.black;
			}
			else {
				if (n == 1) {
					return Color.yellow;
				}
				return Color.black;
			}
	}
}
