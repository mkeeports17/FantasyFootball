/*This is the database class, whose main purpose is to deal with the Players, since it holds the Players array,
 * which has all the players available to draft. It contains all of the methods like accessing the players, searching and filtering, 
 * and as well as the autodrafting methods. It also holds the teams array, which holds all of the drafted players, in the row of their team
 * number and the column of the round they were drafted in.*/

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database {
	// I used arrays because thats what I prefer, but for the 'recent' and 'urteam' i used arraylist because it would be much easier to use 
	//.remove() and .add(), which are features necessary for those lists. 
	private static Player[] Players;
	private static ArrayList <Player> recent = new ArrayList <Player>();
	private static ArrayList <Player> urteam = new ArrayList <Player>();
	private Player[][] teams;
	private static Player empty;
	private static int ROUNDS = 14, teamcount = 4;
	public Database(){
		teams = new Player[teamcount][ROUNDS];
		Players = new Player[] {new Player("Christian McCaffrey", "CAR", "RB"), new Player("Dalvin Cook", "MIN", "RB"), 
				new Player("Alvin Kamara", "NO", "RB"), new Player("Davante Adams", "GB", "WR"), new Player("Derrick Henry", "TEN", "RB"), 
				new Player("Tyreek Hill", "KC", "WR"), new Player("Stefon Diggs", "BUF", "WR"), new Player("Nick Chubb", "CLE", "RB"), 
				new Player("Travis Kelce", "KC", "TE"), new Player("DK Metcalf", "SEA", "WR"), new Player("Jonathon Taylor", "IND", "RB"), 
				new Player("JK Dobbins", "BAL", "RB"), new Player("Calvin Ridley", "ATL", "WR"), new Player("DeAndre Hopkins", "ARI", "WR"), 
				new Player("Aaron Jones", "GB", "RB"), new Player("Antonio Gibson", "WSH", "RB"), new Player("D'Andre Swift", "DET", "RB"), 
				new Player("James Robinson", "JAX", "RB"), new Player("Austin Ekeler", "LAC", "RB"), new Player("George Kittle", "SF", "TE"), 
				new Player("Saquon Barkley", "NYG", "RB"), new Player("AJ Brown", "TEN", "WR"), new Player("Josh Jacobs", "LV", "RB"), 
				new Player("Michael Thomas", "NO", "WR"), new Player("Cam Akers", "LAR", "RB"), new Player("Ezekiel Ellott", "DAL", "RB"), 
				new Player("Mike Evans", "TB", "WR"), new Player("Justin Jefferson", "MIN", "WR"), new Player("Allen Robinson", "CHI", "WR"), 
				new Player("Miles Sanders", "PHI", "RB"), new Player("Keenan Allen", "LAC", "WR"), new Player("Joe Mixon", "CIN", "RB"), 
				new Player("David Montgomery", "CHI", "RB"), new Player("Terry McLaurin", "WSH", "WR"), new Player("Robert Woods", "LAR", "WR"), 
				new Player("Ronald Jones", "TB", "RB"), new Player("CeeDee Lamb", "DAL", "WR"), new Player("Diontae Johnson", "PIT", "WR"), 
				new Player("Adam Thielen", "MIN", "WR"), new Player("Kenyan Drake", "ARI", "RB"), new Player("Darren Waller", "LV", "TE"), 
				new Player("Patrick Mahomes", "KC", "QB"), new Player("Julio Jones", "ATL", "WR"), new Player("Will Fuller", "HOU", "WR"), 
				new Player("Chris Godwin", "TB", "WR"), new Player("Corey Davis", "TEN", "WR"), new Player("Amari Cooper", "DAL", "WR"), 
				new Player("Clyde Edwards", "KC", "RB"), new Player("DJ Chark", "JAX", "WR"), new Player("Tee Higgins", "CIN", "WR"), 
				new Player("Raheem Mostert", "SF", "RB"), new Player("Chase Claypool", "PIT", "WR"), new Player("Kareem Hunt", "CLE", "RB"), 
				new Player("DJ Moore", "CAR", "WR"), new Player("Kenny Golladay", "DET", "WR"), new Player("Deebo Samuel", "SF", "WR"), 
				new Player("Josh Allen", "BUF", "QB"), new Player("Courtland Sutton", "DEN", "WR"), new Player("Dak Prescott", "DAL", "QB"), 
				new Player("Tyler Boyd", "CIN", "WR"), new Player("JuJu Smith-Schuster", "PIT", "WR"), new Player("Mark Andrews", "BAL", "TE"), 
				new Player("Cooper Kupp", "LAR", "WR"), new Player("Brandon Aiyuk", "SF", "WR"), new Player("Tyler Lockett", "SEA", "WR"), 
				new Player("Chase Edmonds", "ARI", "RB"), new Player("Chris Carson", "SEA", "RB"), new Player("Kyler Murray", "ARI", "QB"), 
				new Player("Robby Anderson", "CAR", "WR"), new Player("Cole Beasley", "BUF", "WR"), new Player("Melvin Gordon", "DEN", "RB"), 
				new Player("Odell Beckham", "CLE", "WR"), new Player("Deshaun Watson", "HOU", "QB"), new Player("TJ Hockenson", "DET", "TE"), 
				new Player("Lamar Jackson", "BAL", "QB"), new Player("David Johnson", "HOU", "RB"), new Player("Darrell Henderson", "LAR", "RB"), 
				new Player("Rashaad Penny", "SEA", "RB"), new Player("Marquise Brown", "BAL", "WR"), new Player("Jerry Jeudy", "DEN", "WR"), 
				new Player("Myles Gaskin", "MIA", "RB"), new Player("Curtis Samuel", "CAR", "WR"), new Player("Michael Gallup", "DAL", "WR"),
				new Player("DeVante Parker", "MIA", "WR"), new Player("Gus Edwards", "BAL", "RB"), new Player("Nyheim Hines", "IND", "RB"), 
				new Player("Laviska Shenault", "JAX", "WR"), new Player("Russell Wilson", "SEA", "QB"), new Player("Jonnu Smith", "TEN", "TE"), 
				new Player("Justin Herbert", "LAC", "QB"), new Player("Benny Snell", "PIT", "RB"), new Player("Brandin Cooks", "HOU", "RB"), 
				new Player("Aaron Rodgers", "GB", "QB"), new Player("Antonio Brown", "TB", "WR"), new Player("Jalen Hurts", "PHI", "QB"), 
				new Player("Dallas Goedert", "PHI", "TE"), new Player("Devin Singletary", "BUF", "RB"), new Player("Mecole Hardman", "KC", "WR"), 
				new Player("Robert Tonyan", "GB", "TE"), new Player("Denzel Mims", "NYJ", "WR"), new Player("Taysom Hill", "NO", "QB"), 
				new Player("Ke'Shawn Vaughn", "TB", "RB"), new Player("Jalen Reagor", "PHI", "WR"), new Player("Willie Snead", "BAL", "WR"), 
				new Player("Darnell Mooney", "CHI", "WR"), new Player("Zack Moss", "BUF", "RB"), new Player("Keke Coutee", "HOU", "WR"), 
				new Player("Mike Gesicki", "MIA", "TE"), new Player("Noah Fant", "DEN", "TE"), new Player("Damien Harris", "NE", "RB"), 
				new Player("Darius Slayton", "NYG", "WR"), new Player("Christian Kirk", "ARI", "WR"), new Player("Ryan Tannehill", "TEN", "QB"), 
				new Player("Jarvis Landry", "CLE", "WR"), new Player("Michael Pittman", "IND", "WR"), new Player("Jamison Crowder", "NYJ", "WR"), 
				new Player("LaMical Perine", "NYJ", "RB"), new Player("Tyler Higbee", "LAR", "TE"), new Player("James Conner", "PIT", "RB"), 
				new Player("Hunter Henry", "LAC", "TE"), new Player("Mr DuBose", "NYG", "K"), new Player("Rams DST", "LAR", "DST"),
				new Player("Younghoe Koo", "ATL", "K"), new Player("Steelers DST", "PIT", "DST"), new Player("Jason Sanders", "MIA", "K"),
				new Player("Colts DST", "IND", "DST"), new Player("Daniel Carlson", "LV", "K"), new Player("Washington DST", "WSH", "DST"), 
				new Player("Justin Tucker", "BAL", "K"), new Player("Ravens DST", "BAL", "DST"), new Player("Tyler Bass", "BUF", "K"), 
				new Player("Saints DST", "NO", "DST"), new Player("Greg Zuerlein", "DAL", "K"), new Player("Bucs DST", "TB", "DST"), 
				new Player("Ryan Succop", "TB", "K"), new Player("Dolphins DST", "MIA", "DST"), new Player("Harrison Butker", "KC", "K"), 
				new Player("Bills DST", "BUF", "DST"), new Player("Wil Lutz", "NO", "K"), new Player("Cardinals DST", "ARI", "DST"), 
				new Player("Rodrigo Blankenship", "IND", "K"), new Player("Packers DST", "GB", "DST"), new Player("Jason Myers", "SEA", "K"), 
				new Player("Patriots DST", "NE", "DST")};
		empty = new Player("Empty", "", "");
		for (int i = 0; i < teams[0].length; i++) {
			teams[0][i] = empty;
		}
	}
	public static int getNumPlayers() {
		return Players.length;
	}
	//returns the top 5 overall players in a player array
	public static Player[] get5Players() {
		resetPrinted();
		Player[] list = new Player[5];
		int count = 0;
		for (Player p: Players) {
			if (p.getAV() && !p.getPrinted()) {
				list[count] = p;
				count ++;
				p.setPrinted();
				if (count >= 5) {
					break;
				}
			}
		}
		if (count == 5) {
			return list;
		}
		else {
			return elseHelper(count, list);
		}
	}
	// returns a 5 player array, of the next available players with the specified filter which can be a position or a team
	public static Player[] get5Players(String filter) {
		resetPrinted();
		Player[] list = new Player[5];
		int count = 0;
		if (!filter.equals("none")) {
			for (Player p: Players) {
				if (p.getAV() && !p.getPrinted() && (p.getPos().equals(filter) || p.getTeam().equals(filter))) {
					list[count] = p;
					count ++;
					p.setPrinted();
					if (count >= 5) {
						break;
					}
				}
			}
			if (count == 5) {
				return list;
			}
			else {
				return elseHelper(count, list);
			}
		}
		else {
			for (Player p: Players) {
				if (p.getAV() && !p.getPrinted()) {
					list[count] = p;
					count ++;
					p.setPrinted();
					if (count >= 5) {
						break;
					}
				}
			}
			if (count == 5) {
				return list;
			}
			else {
				return elseHelper(count, list);
			}
		}
	}
	//returns a 5 player array of the next available players of this filter. filter can be a team or a position
	public static Player[] getnext5(String filter) {
		Player[] list = new Player[5];
		int count = 0;
		for (Player p: Players) {
			if (p.getAV() && !p.getPrinted() && (p.getPos().equals(filter) || p.getTeam().equals(filter))) {
				list[count] = p;
				count ++;
				p.setPrinted();
				if (count >= 5) {
					break;
				}
			}
		}
		if (count == 5) {
			return list;
		}
		else {
			return elseHelper(count, list);
		}
	}
	/*This method, when used with filtering methods, receives a list of players, with the logical count.
	 * If the logical count is less than 5, this means that, for example, there are less than 5 Giants players. To avoid NullPointerException error, 
	 * This method fills in the other slots of the array with the next overall available player.*/
	private static Player[] elseHelper(int count, Player[] list) {
		for (int i = count; i < list.length; i++) {
			Player p = helperNext();
			p.setPrinted();
			list[i] = p;
		}
		frame.repaintAll();
		return list;
	}
	//copying the arraylist recent to an array, which is easier to work with in my code
	public Player[] getRecentlyDrafted() {	
		Player[] temp = new Player[recent.size()];
			if (temp.length != 0) {
				for (int i = 0; i < recent.size(); i++) {
					temp[i] = recent.get(i);
				}
			}
			return temp;
	}
	//returns true if the int is 2-8, and then sets the amount of teams to that
	public static boolean setTeams(int n) {
		if (n > 1 && n < 9) {
			teamcount = n;
			return true;
		}
		else {
			return false;
		}
	}
	public static int getTeams() {
		return teamcount;
	}
	//resets the printed boolean for every player
	public static void resetPrinted() {
		for (Player p: Players) {
			p.resetPrinted();
		}
	}
	//returns the player with the specified name
	public Player getPlayer(String name) {
		for (Player p: Players) {
			if (p.getName().toLowerCase().equals(name.toLowerCase())) {
				return p;
			}
		}
		return null;
	}
	//checks to see if the parameter name belongs to any of the players.
	public boolean checkName(String name) {
		for (Player p: Players) {
			if (p.getName().toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	//initiates the nextRound, by drafting the parameter, which is the drafted player by the user, and then autodrafts for the other teams.
	public void nextRound(Player p) {
		p.setAV();
		urteam.add(p);
		recent.add(p);
		teams[0][AltPanel.getRound()-1] = p;
		if (recent.size() >= 8) {
			recent.remove(0);
		}
		//puts autodrafted players in an array
		for (int i = 1; i < teamcount; i++) {
			Player n = AIDraft(i, AltPanel.getRound(), teams[i]);
			teams[i][AltPanel.getRound()-1] = n;
			if (recent.size() >= 8) {
				recent.remove(0);
			}
			recent.add(n);
		}
		System.out.println("\nnextround\n");
		AltPanel.incRound();
		AltPanel.resetTimecount();
		printTeams();
	}
	/* my AIDraft program drafts the next available player most rounds. It also checks the 'canDraft' method
	and also makes sure to draft a QB, TE, K, and DST*/
	public Player AIDraft(int teamnum, int round, Player[] team) {
		Player p = null;
		if (round == ROUNDS-4) {
			if (!check(team, "QB")) {
				p = getNextPos("QB");
			}
			else {
				p = getNext();
			}
		}
		else if (round == ROUNDS-3) {
			if (!check(team, "TE")) {
				p = getNextPos("TE");
			}
			else {
				p = getNext();
			}
		}
		else if (round == ROUNDS-1) {
			if (!check(team, "DST")) {
				p = getNextPos("DST");
			}
			else {
				p = getNext();
			}
		}
		else if (round == ROUNDS) {
			if (!check(team, "K")) {
				p = getNextPos("K");
			}
			else {
				p = getNext();
			}
		}
		else {
			p = getNext();
		}
		if (p != null) {
			p.setAV();
			return p;
		}
		else {
			frame.end();
			return null;
		}
	}
	//checks to see if there is a player of the specified position in the team
	public Boolean check(Player[] team, String pos) {
		for (Player p: team) {
			if (p != null) {
				if (p.getPos().equals(pos)) {
					return true;
				}
			}
		}
		return false;
	}
	//returns the next available player
	public static Player getNext() {
		for (Player p: Players) {
			if (p.getAV()) {
				return p;
			}
		}
		return null;
	}
	//returns the next available player that hasnt been printed
	public static Player helperNext() {
		for (Player p: Players) {
			if (p.getAV() && !p.getPrinted()) {
				return p;
			}
		}
		return null;
	}
	//returns the next available player of the specified position
	private Player getNextPos(String pos) {
		for (Player p: Players) {
			if (p.getPos().equals(pos) && p.getAV()) {
				return p;
			}
		}
		return new Player("a " + pos, "", pos);
	}
	/*this method returns the next5 players, unfiltered. There are plenty of players, but because the 'Next 5' button */
	public static Player[] getnext5() {
		Player[] list = new Player[5];
		int count = 0;
		for (Player p: Players) {
			if (p.getAV() && !p.getPrinted()) {
				list[count] = p;
				count ++;
				p.setPrinted();
				if (count >= 5) {
					break;
				}
			}
		}
		if (count == 5) {
			return list;
		}
		else {
			JOptionPane.showMessageDialog(null, "Please stop spamming 'Next 5'. You have reached the end of the Player database,"
					+ "\n so please refilter to go back to the top and draft a real player.");
			return elseHelper(count, list);
		}
	}
	//checks to see if the player with the specified name has been drafted.
	public boolean checkDrafted(String name) {
		for (Player p: Players) {
			if (p.getName().toLowerCase().equals(name.toLowerCase())) {
				if (p.getAV()) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}
	//Gets the next available players, and checks first to see if they can draft that player. 
	//if not it calls itself, until it gets a player that is able to be drafted by the user.
	public Player autoDraft(int n) {
		if (n == 0) {
			resetPrinted();
		}
		Player p = helperNext();
		if (canDraft(p)) {
			p.setAV();
			nextRound(p);
			ColorPanel.next5();
			return p;
		}
		else {
			p.setPrinted();
			System.out.println();
			return autoDraft(1);
		}
	}
	private void formatPrint(Player n) {
		System.out.printf("%-20s", n.getName());
	}
	//prints the teams in a big table
	public void printTeams() {
		for (int row = 0; row < teams.length; row++) {
			for (int i = 0; i < teams[row].length; i++) {
				if (teams[row][i] == null) {
					formatPrint(empty);
				}
				else {
					formatPrint(teams[row][i]);
				}
			}
			System.out.println();
		}
	}
	/* this method puts the players in your team in order, according to the template on the left side.
	 * The team is first filled with the empty player object, and drafted players are put in the right spot.*/
	public static Player[] getYourTeam() {
		Player[] temp = new Player[ROUNDS];
		ArrayList <Player> copy = new ArrayList <Player>();
		for (Player p: urteam) {
			copy.add(p);
		}
		for (int i = 0; i < temp.length; i++) {
			temp[i] = empty;
		}
		for (int i = 0; i < temp.length; i++) {
			if (i == 0) {
				int num = helpGetTeam("QB", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 1 || i == 2) {
				int num = helpGetTeam("RB", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 3 || i == 4) {
				int num = helpGetTeam("WR", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 5) {
				int num = helpGetTeam("TE", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 6) {
				int num = helpGetTeam(copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 7) {
				int num = helpGetTeam("DST", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else if (i == 8) {
				int num = helpGetTeam("K", copy);
				if (num >= 0) {
					temp[i] = copy.get(num);
					copy.remove(num);
				}
			}
			else {
				if (copy.size() > 0) {
					temp[i] = copy.get(0);
					copy.remove(0);
				}
			}
		}
		return temp;
	}
	//returns the index of the first player of that position, in that list. If there are none, returns -1
	private static int helpGetTeam(String pos, ArrayList<Player> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPos().equals(pos)) {
				return i;
			}
		}
		return -1;
	}
	//same as the previous method, except it checks for RB, WR, or TE (aka FLEX)
	private static int helpGetTeam(ArrayList<Player> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPos().equals("RB") || list.get(i).getPos().equals("WR") || list.get(i).getPos().equals("TE")) {
				return i;
			}
		}
		return -1;
	}
	//returns Your Team in a String, to be displayed in a JOptionPane after the draft is over
	public static String getUrString() {
		String str = new String("");
		Player[] list = getYourTeam();
		for (int i = 0; i < list.length; i++) {
			str += list[i].getPos() + " " +  list[i].getName() + "\n";
		}
		return str;
	}
	/*This method checks if the user is allowed to draft this player. Right away, if there already five of 
	 * that position it automatically returns false. Then it checks for availability in the roster, for example if the K spot is full,
	 * and all bench spots are full, you CANT draft a Kicker. You can only draft the position you havent drafted which might be Tight End or Defense*/
	public static boolean canDraft(Player pick) {
		int count = 0;
		String tar = pick.getPos();
		for (Player p: urteam) {
			if (p.getPos().equals(tar)) {
				count ++;
			}
		}
		if (count == 0) {
			return true;
		}
		else if (count >= 5) {
			return false;
		}
		else {
			Player[] list = getYourTeam();
			//checks to see if the starting positions are empty, and then if not checks the flex and bench spots.
			if (tar.equals("QB")) {
				if (list[0].getName().equals(empty.getName())) {
					return true;
				}
			}
			else if (tar.equals("RB")) {
				if (list[1].getName().equals(empty.getName()) || list[2].getName().equals(empty.getName())) {
					return true;
				}
			}
			else if (tar.equals("WR")) {
				if (list[3].getName().equals(empty.getName()) || list[4].getName().equals(empty.getName())) {
					return true;
				}
			}
			else if (tar.equals("TE")) {
				if (list[5].getName().equals(empty.getName())) {
					return true;
				}
			}
			else if (tar.equals("DST")) {
				if (list[7].getName().equals(empty.getName())) {
					return true;
				}
			}
			else if (tar.equals("K")) {
				if (list[8].getName().equals(empty.getName())) {
					return true;
				}
			}
			//intentionally separated, it should check Flex and Bench after checking all starting positions
			if (tar.equals("RB") || tar.equals("WR") || tar.equals("TE")) {
				if (list[6].getName().equals(empty.getName())) {
					return true;
				}
			}
			for (int i = 9; i < list.length; i++) {
				if (list[i].getName().equals(empty.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	//this and the next method check to make sure that a string inputted is a valid position or team, respecitively
	public static boolean checkValPos(String pos) {
		for (Player p: Players) {
			if (p.getPos().toLowerCase().equals(pos.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	public static boolean checkValTeam(String input) {
		for (Player p: Players) {
			if (p.getTeam().toLowerCase().equals(input.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	public static Player getEmpty() {
		return empty;
	}
	public static int getROUNDS() {
		return ROUNDS;
	}
}
