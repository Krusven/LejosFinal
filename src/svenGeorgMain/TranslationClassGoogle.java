package svenGeorgMain;
import java.util.Scanner;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslationClassGoogle {
	static Scanner scanner = new Scanner(System.in);
	public static void translateText() {
		String userInput = getInput();
		TranslateTheString(userInput, "de", "es");
	}
	
	public static String getInput() {
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
	public static String getSourceInput() {
		System.out.println("Enter the source Language:");
		String userInput = "";
		String theInput;
		theInput=scanner.nextLine();
		userInput += " " + "\n" + theInput;	
		System.out.println("Input has Ended and was: " + "\n" + userInput);
		return userInput;
	}
	public static void TranslateTheString(String text, String sourceL, String targetL) {
		Translate translate = TranslateOptions.getDefaultInstance().getService();

		Translation translation =
			    translate.translate(
			        text,
			        Translate.TranslateOption.sourceLanguage(sourceL),
			        Translate.TranslateOption.targetLanguage(targetL),
			        // Use "base" for standard edition, "nmt" for the premium model.
			        Translate.TranslateOption.model("base"));

			System.out.printf("TranslatedText:\nText: %s\n", translation.getTranslatedText());
	}
	
}
