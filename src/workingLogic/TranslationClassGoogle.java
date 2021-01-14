package workingLogic;
import java.util.Scanner;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslationClassGoogle {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static String translateText() {
		
		String sourceLanguage = getSourceLanguage();
		String targetLanguage = getTargetLanguage();
		String userInput = getInput();
		String translatedInput = translateInputString(userInput, sourceLanguage, targetLanguage);
		
		System.out.println("Translated text is : " + translatedInput);
		
		return translatedInput;
	}
	
	public static String getSourceLanguage() {
		
		System.out.println("Enter the source language  (type \"help\" for a list of language often used ISO-639-1-Codes):");
		String sourceLanguage = "";
		sourceLanguage = scanner.nextLine();
		
		if (sourceLanguage.equals("help")) {
			System.out.println(
					"German: "+"de"+"\n"+
					"Spanish: "+"es"+"\n"+
					"French: "+"fr"+"\n"+
					"English: "+"en"+"\n"+
					"Russian: "+"ru"+"\n"+
					"Norwegian: "+"no"+"\n"+
					"Chinese: "+"zh"+"\n"+
					"Japanese: "+"ja"+"\n"+
					"Korean: "+"ko"+"\n" +
					"Turkish: " +"tr"
			);
			
			System.out.println("Enter the source language:");
			sourceLanguage = scanner.nextLine();
			
			while (sourceLanguage.equals("")) {
				System.out.println("Please enter something!");
				sourceLanguage = scanner.nextLine();
			}
			return sourceLanguage;
		} else {
			
			System.out.println();
			while (sourceLanguage.equals("")) {
				System.out.println("Please enter something!");
				sourceLanguage = scanner.nextLine();
			}
			return sourceLanguage;
		}
	}
	
	public static String getTargetLanguage() {
		
		System.out.println("Enter the target language (type \"help\" for a list of language often used ISO-639-1-Codes):");
		String targetLanguage = "";
		targetLanguage = scanner.nextLine();
		
		if (targetLanguage.equals("help")) {
			System.out.println(
					"German: "+"de"+"\n"+
					"Spanish: "+"es"+"\n"+
					"French: "+"fr"+"\n"+
					"English: "+"en"+"\n"+
					"Russian: "+"ru"+"\n"+
					"Norwegian: "+"no"+"\n"+
					"Chinese: "+"zh"+"\n"+
					"Japanese: "+"ja"+"\n"+
					"Korean: "+"ko"+"\n" +
					"Turkish: " +"tr"
			);
			
			System.out.println("Enter the target language:");
			targetLanguage = scanner.nextLine();
			
			while (targetLanguage.equals("")) {
				System.out.println("Please enter something!");
				targetLanguage = scanner.nextLine();
			}
			main.setTargetLang(targetLanguage);
			return targetLanguage;
		} else {
			
			System.out.println();
			while (targetLanguage.equals("")) {
				
				System.out.println("Please enter something!");
				targetLanguage = scanner.nextLine();
			}
			main.setTargetLang(targetLanguage);
			return targetLanguage;
		}
	}
	
	public static String getInput() {
		
		System.out.println("Enter your text and end your input with '/end/' to submit your input:");
		String userInput = "";
		String partOfInput;
		
		while(scanner.hasNextLine()) {
			partOfInput=scanner.nextLine();
			if(partOfInput.equals("/end/")) {
				break;
			}
			
			userInput += " " + "\n" + partOfInput;	
		}
		
		System.out.println("Your input was: " + userInput);
		System.out.println();
		return userInput;
	}
	
	public static String translateInputString(String inputString, String sourceLanguage, String targetLanguage) {
		
		Translate translate = TranslateOptions.getDefaultInstance().getService();
		Translation translation = translate.translate(
				
			        inputString,
			        Translate.TranslateOption.sourceLanguage(sourceLanguage),
			        Translate.TranslateOption.targetLanguage(targetLanguage),
			        // Use "base" for standard edition, "nmt" for the premium model.
			        Translate.TranslateOption.model("base")
		);
			
		return translation.getTranslatedText();
	}
	
}
