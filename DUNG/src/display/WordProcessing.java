package display;

public class WordProcessing { // Refactor name if you have something better.
	
	private static final String BAR = "="; // The character used in break bars across the screen.
	private static final int BARWIDTH = 86; //Old was 68 or 70. 86 is logo width

	public static String bar() {
		String output = "";
		//println("----------------------------------------------------------------------"); // 70 long. More efficient.
		for (int i=0; i<BARWIDTH; i++) // Draw Bar
			output += BAR;
		return output;
	}

	public static String bar(String str) {
		if (str.length() == 0) // If this string comes in empty for some reason, it prints a normal full bar, instead of printing one with 2 spaces in the middle.
			return bar();
		else {
			String output = "";
			int dashes = BARWIDTH-(str.length()+2);
			for (int i=0; i<dashes/2; i++) // Draw Bar
				output += BAR;
			output += " " + str + " "; // Puts string in middle of bar with spaces.
			for (int i=0; i<dashes/2; i++) // Draw Bar
				output += BAR;
			if (str.length()%2 == 1) // Correction for odd number of characters in string
				output += BAR;
			return output;
		}
	}
	
	public static String rainbowfy(String str) { // Makes each color in a string a fading color, randomly.
		String output = "";
		int offset = (int)(Math.random()*255);
		// Process each character.
		for (int i=0; i<str.toCharArray().length; i++) {
			char c = str.toCharArray()[i];
			output += "<font color="+ trueRandomColor(i+offset)+">"+c+"</font>";
		}
		return output;
	}
	
	public static String RGBtoHex(int r, int g, int b) { // Coverts 3 ints of RGB to a 6 digit hex string.
		String hexCode ="";
		// Convert color from RGB to hex string.
		String hexR = Integer.toHexString(r);
		if (hexR.length() == 2) 
			hexCode += hexR;
		else if (hexR.length() < 2)
			hexCode += "0" + hexR;
		else 
			hexCode += hexR.substring(0, 1);
		
		String hexG = Integer.toHexString(g);
		if (hexG.length() == 2) 
			hexCode += hexG;
		else if (hexG.length() < 2)
			hexCode += "0" + hexG;
		else 
			hexCode += hexG.substring(0, 1);
		
		String hexB = Integer.toHexString(b);
		if (hexB.length() == 2) 
			hexCode += hexB;
		else if (hexB.length() < 2)
			hexCode += "0" + hexB;
		else 
			hexCode += hexB.substring(0, 1);
		return hexCode;
	}
	
	public static String randomColorfy(String str) { // Turns a string into a random color.
		str = "<font color = " + trueRandomColor() + ">" + str + "</font>";
		return str;
	}
	
	public static String trueRandomColor(int seed) { // Returns a color generated off the seed as a hex string.
		int r, g, b;
		// Use index, + basevalue to get a color.
		r = (int)(64*Math.acos(Math.cos((Math.PI/ 17 ) *seed ))); //80
		g = (int)(128*Math.sin((Math.PI/ 13 ) *seed )+127); //255
		b = (int)(64*Math.acos(Math.cos((Math.PI/ 9 ) *seed ))); //(int)(64*Math.acos(Math.cos((Math.PI/ ((100*Math.random())+155))*(i+offset)))) //(Math.cos((Math.PI*255)*(i+offset)))
		
		return RGBtoHex(r,g,b);
	}
	public static String trueRandomColor() { // Overload, sets a random color seed if not given.
		return trueRandomColor((int)(Math.random()*255));
	}
}
