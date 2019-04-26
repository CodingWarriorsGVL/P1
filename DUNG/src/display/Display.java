package display;

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
	public static JTextArea output;
	public static JScrollPane debugScroll;
	public static JTextArea debug;
	public static JTextField input;
	
	private static String lastOut;
	private static boolean wasRead;
	
	private static boolean debugOn;
	
	public static void initialize() {
		frame = new JFrame("DUNG");
		panel = new JPanel();
		outputScroll = new JScrollPane();
		output = new JTextArea();
		debugScroll = new JScrollPane();
		debug = new JTextArea();
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
		
		output.setFont(new Font("Monospaced",Font.PLAIN, 12));
		output.setEditable(false);
		
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
		output.setText(output.getText() + str);

		//System.out.print(str);
	}

	public static void println(String str) {
		print(str + "\n");
		//System.out.println(str);
	}

	public static String input(String str) {
		println(str);
		
		while (wasRead) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Thread.
		//String out = input.getText();
		//input.setText("");
		wasRead = true;
		return lastOut;
		//return scan.nextLine();
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

	public static void print(Player player) {
		println(player.toString());
	}

	public static void print(Item item) {
		println(item.getName());
	}

	public static void print(Room room) {
		println("You are in a room");
		print("The North side is ");
		print(room.getNorth());

		print("The East side is ");
		print(room.getEast());

		print("The South side is ");
		print(room.getSouth());

		print("The West side is ");
		print(room.getWest());

	}

	public static void print(RoomFeature feature) {
		if (feature instanceof Door) {
			print((Door)feature);
		}
		if (feature instanceof Decor) {
			print((Decor)feature);
		}
		if (feature instanceof Wall) {
			print((Wall)feature);
		}
		if (feature instanceof Staircase) {
			print((Staircase)feature);
		}

	}

	public static void print(Door door) {
		println("Is a " + ((door.isLocked())? "locked" : "" ) + door.getMaterial() + " door");
	}
	public static void print(Decor decor) {
		println("Is a " + decor.getDescription());
	}
	public static void print(Wall wall) {
		println("wall");
	}
	public static void print(Staircase stair) {
		println("Staircase to another floor!");
	}
	
	
}
