import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;

    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getHamletData(){
        return hamletData;
    }

    public String replace(String originalName, String replacementName, String text){
        Pattern secondPattern;
        String replacementName2;
        String replacementNameCamelCase = Character.toUpperCase(replacementName.charAt(0)) + replacementName.substring(1).toLowerCase();
        if (originalName.charAt(1) == Character.toUpperCase(originalName.charAt(1))) {
            secondPattern = Pattern.compile(originalName.charAt(0) + originalName.substring(1).toLowerCase());
            replacementName2 = replacementNameCamelCase;
            replacementName = replacementName.toUpperCase();
        } else {
            secondPattern = Pattern.compile(originalName.toUpperCase());
            replacementName2 = replacementName.toUpperCase();
            replacementName = replacementNameCamelCase;
        }
        //Sets secondPattern to look for all uppercase or camel cased as well as the replacement names
        Pattern pattern = Pattern.compile(originalName);
        Matcher matcher = pattern.matcher(text);
        text = matcher.replaceAll(replacementName);
        matcher = secondPattern.matcher(text);
        text = matcher.replaceAll(replacementName2);
        return text;
    }

    public boolean find(String stringToFind, String text){
        Pattern pattern = Pattern.compile(stringToFind,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
