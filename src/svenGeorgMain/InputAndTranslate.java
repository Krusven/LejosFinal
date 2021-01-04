package svenGeorgMain;

import java.util.Scanner;

import com.sun.java.accessibility.util.Translator;

import javafx.scene.transform.Translate;

public class InputAndTranslate {
	Scanner scanner = new Scanner(System.in);
	
	public String getInput() {
		System.out.println("Enter your text and end your input with 'end':");
		String userInput = "";
		String theInput;
		while(scanner.hasNextLine()) {
			theInput=scanner.nextLine();
			if(theInput.equals("end")) {
				break;
			}
			userInput += " " + "\n" + theInput;	
		}
		System.out.println("Input has Ended and was: " + "\n" + userInput);
		return userInput;
	}

}
