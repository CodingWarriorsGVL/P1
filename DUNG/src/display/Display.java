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

	private static boolean debugOn;
	
	private static String log;

	public static void initialize() {
		
		log = "<html> <pre> <font size = 5>";
		frame = new JFrame("DUNG");
		panel = new JPanel();
		outputScroll = new JScrollPane();
		output = new JTextPane();
		debugScroll = new JScrollPane();
		input = new JTextField(100);

		wasRead = true;
		debugOn = false;
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
		output.setBackground(Color.BLACK);
		//output.setText("<html>");

		outputScroll.setViewportView(output);
		outputScroll.setPreferredSize(new Dimension(1280,620));
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
		log += str;
		output.setText(log);

	}

	public static void println(String str) {
		print(str + "<br>");
	}

	public static String input(String str, String... options) {
		println(str);
		boolean isValid = false;
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
			for (String option : options) {
				if (lastOut.substring(0, Math.min(lastOut.length(), option.length())).equalsIgnoreCase(option.substring(0, Math.min(lastOut.length(), option.length())))) {
					isValid = true;
				}
			}
		} while (!isValid);
		wasRead = true;
		println(lastOut);
		return lastOut;
	}

	public static int inputInt(String str) {
		String in;
		int out = 0;;

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

	public static void print(Entity ent) {
		String name = ent.getName();
		char firstletter = name.toLowerCase().charAt(0);
		if (firstletter == 'a' || firstletter == 'e' || firstletter == 'i' || firstletter == 'o' || firstletter == 'u') {
			println("There is an " + name);
		} else {
			println("There is a " + name);
		}
	}

	public static void debug(String str) {
		if (debugOn) {
			println("Debug: " + str);
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
