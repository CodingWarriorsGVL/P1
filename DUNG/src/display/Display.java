package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import characters.Entity;
import characters.Player;
import item.Item;
import navigation.Decor;
import navigation.Door;
import navigation.Room;
import navigation.RoomFeature;
import navigation.Staircase;
import navigation.Wall;
import static display.WordProcessing.*;
import main.*;

public class Display {

	static Scanner scan;

	public static JFrame frame;
	public static JPanel panel;
	public static JScrollPane outputScroll;
	public static JTextPane output;
	public static JScrollPane debugScroll;
	public static JTextArea debug;
	public static JTextField input;

	private static String lastOut;
	private static boolean wasRead;
	private static boolean debugOn = true;
	private static String log;

	// Appearance Settings:
	private static final String WINDOWNAME = "DUNG"; // Jar window name.
	private static final Color BACKCOLOR = Color.black; // Background color
	private static final String FONTSIZE = "4"; // Font sizes larger than 4 are only going to work if we word wrap.
	private static final String BASECOLOR = "009920"; // Color: A Green
	private static final String FONTDEFAULT = "size = "+FONTSIZE+" color = "+BASECOLOR; // Default font settings.
	private static final String HELPCOLOR = "f442ce"; // Color: Hot Pink
	private static final String INPUTMESSAGECOLOR = "1662e5"; // Color: a Blue
	private static final String USERINPUTCOLOR = "00edff"; // Color: Cyan
	private static final String DEBUGCOLOR = "ffff00"; // Color: Yellow
	
	private static final int PRINTSPEED = 20;
	private static final boolean TEXTSCROLL = false;

	public static void initialize() {
		log = "<html> <pre> <font "+FONTDEFAULT+">"; 
		frame = new JFrame(WINDOWNAME);
		panel = new JPanel();
		outputScroll = new JScrollPane();
		output = new JTextPane();
		debugScroll = new JScrollPane();
		input = new JTextField(100);

		wasRead = true;
		//debugOn = false;
		input.addActionListener(
				ae -> {
					lastOut = input.getText();
					input.setText("");

					if (lastOut.equalsIgnoreCase("debug")) {
						debugOn = !debugOn;
						wasRead = true;
					} else {
						wasRead = false;
					}
				}
				);

		//output.setFont(new Font("Monospaced",Font.PLAIN, 12));
		output.setEditable(false);
		output.setContentType("text/html");
		output.setBackground(BACKCOLOR);
		//output.setText("<html>");

		outputScroll.setViewportView(output);
		outputScroll.setPreferredSize(new Dimension(1280,620));
		// outputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Disables the Horizontal scroll bar. Might be needed for word wrap.
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel.add(outputScroll);
		panel.add(input);
		panel.setPreferredSize(new Dimension(1280,720));

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}


	public static void print(String str) {
		console(str);
		if (TEXTSCROLL) { // Time for a super quick and sloppy, lets make a textprint.
			int index = 0;
			String htmlOpen, htmlClose;
			while (index < str.length()) {
				char currentChar = str.charAt(index);
				
				if (currentChar == '<') {
					//for (int j=index; str.charAt(j) != '>'; j++)
					//	htmlOpen = str.substring(index, j) + '>';
					int endIndex = str.indexOf(">", index);
					htmlOpen = str.substring(index, endIndex);
					index = endIndex+1;
					currentChar = str.charAt(index);
				}
				
				printUpdate(""+currentChar);
				if (currentChar != ' ')
					MainGame.gameSleep(1000/PRINTSPEED);
				
				index++;
			}
		}
		else
			printUpdate(str);
	}
	
	private static void printUpdate(String str) {
		log += str;
		output.setText(log);
	}

	public static void println(String str) {
		print(str + "<br>");
	}

	public static String input(String str, String... options) {
		println("<font color = "+INPUTMESSAGECOLOR+">"+str+"</font>"); 
		boolean isValid = false;
		String r = "";

		// Clean Options
		/*
		int c = 0, j = 0;
		for (int i=0; i<options.length; i++) {
			if (!options[i].equals(""))
				c++;
		}
		String[] optionsTemp = new String[c];
		for (int i=0; i<c; i++) {
			if (!options[i].equals("")){
				optionsTemp[j] = options[i];
				j++;
			}
		}
		options = optionsTemp;
		 */
		// Read Loop
		do {
			while (wasRead) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (options.length==0) {
				isValid = true;
			}
			//for (int i=0; i<options.length; i++) {
				//String option = options[i];
			for (String option : options) {
				if (option != null) {
					if (lastOut.substring(0, Math.min(lastOut.length(), option.length())).equalsIgnoreCase(option.substring(0, Math.min(lastOut.length(), option.length())))) {
						isValid = true;
						r = option;
					}
				}
			}
			if (lastOut.equalsIgnoreCase("help")) {
				println(lastOut);
				if (options.length==0)
					println("<font color = "+HELPCOLOR+">Any input is valid.</font>"); 
				else {
					print("<font color = "+HELPCOLOR+">Options: ");
					for (String o : options) 
						if (o != null)
							print(o + ", ");
					println("</font>");
				}
				isValid = false;
				wasRead = true;
			}
		} while (!isValid);
		wasRead = true;
		//println("<font color = "+USERINPUTCOLOR+">"+ lastOut + ": " + r +"</font>"); 
		if (r != "") {
			println("<font color = "+USERINPUTCOLOR+">"+ r +"</font>"); 
			return r;
		}
		else {
			println("<font color = "+USERINPUTCOLOR+">"+ lastOut +"</font>"); 
			return lastOut;
		}
	}

	public static int inputInt(String str) {
		String in;
		int out = 0;

		in = input(str);
		try {
			out = Integer.parseInt(in);
		} catch (NumberFormatException e) {

			return inputInt(str);
		}
		return out;
	}

	public static int inputInt(String str, int max) {
		String in;
		int out = 0;;
		do {
			in = input(str);
			try {
				out = Integer.parseInt(in);
			} catch (NumberFormatException e) {

				return inputInt(str, max);
			}
		} while (out > max || out < 0);
		return out;
	}

	public static void debug(String str) {
		if (debugOn) {
			println("<font color = "+DEBUGCOLOR+">Debug: " + str +"</font>");
		}
		console(str);
	}
	
	public static void console(String str) {
		str.replaceAll("<br>", "/n"); //replace line breaks.
	    String strRegEx = "<[^>]*>"; //match HTML tags. Taken from web.
	    str.replaceAll(strRegEx, ""); // Find and remove < > and anything between them. 
		System.out.print(str);
	}
	
	// Fancy print methods.
	public static void printbar() {
		println(bar());
	}
	public static void printbar(String str) {
		println(bar(str));
	}
	
	// Object specific print methods.
	public static void print(Entity ent) {
		String name = ent.getName();
		char firstletter = name.toLowerCase().charAt(0);
		if (firstletter == 'a' || firstletter == 'e' || firstletter == 'i' || firstletter == 'o' || firstletter == 'u') {
			println("There is an " + name);
		} else {
			println("There is a " + name);
		}
	}

	public static void println(Player player) {
		println(player.toString());
	}

	public static void println(Item item) {
		println(item.getName());
	}

	public static void println(Room room) {
		println("You are in a room");

		if (!(room.getNorth() instanceof Wall)) {
			print("The North side is ");
			println(room.getNorth());
		}

		if (!(room.getEast() instanceof Wall)) {
			print("The East side is ");
			println(room.getEast());
		}

		if (!(room.getSouth() instanceof Wall)) {
			print("The South side is ");
			println(room.getSouth());
		}

		if (!(room.getWest() instanceof Wall)) {
			print("The West side is ");
			println(room.getWest());
		}

	}

	public static void println(RoomFeature feature) {
		if (feature instanceof Door) {
			println((Door)feature);
		}
		if (feature instanceof Decor) {
			println((Decor)feature);
		}
		if (feature instanceof Wall) {
			println((Wall)feature);
		}
		if (feature instanceof Staircase) {
			println((Staircase)feature);
		}
	}

	public static void println(Door door) {
		println("is a " + ((door.isLocked())? "locked" : "" ) + door.getMaterial() + " door");
	}
	public static void println(Decor decor) {
		println("is a " + decor.getDescription());
	}
	public static void println(Wall wall) {
		println("wall");
	}
	public static void println(Staircase stair) {
		println("Staircase to another floor!");
	}
}
