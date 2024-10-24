import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class Task1 {
    public static void main(String[] args) {
        File initialFile = new File(".\\text.txt");

        if (!initialFile.exists()) {
            System.out.println("File doesn't exist");
            System.exit(0);
        }

        if (!initialFile.canRead()) {
            System.out.println("File is not readable");
            System.exit(0);
        }

        System.out.println("File is readable");
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(initialFile), "UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8")) {

            char[] buf = new char[256];  
            StringBuilder buffer = new StringBuilder(); // StringBuilder is more efficient than String for concatenation
            String finalString;

            int charsRead;

            int charCounter = 0;
            int wordsCounter = 0;
            int linesCounter = 0;
            boolean inWord = false;

            while ((charsRead = isr.read(buf)) != -1) {
                buffer.append(buf, 0, charsRead);  // Append only the valid characters read
            }

            finalString = buffer.toString();

            charCounter = finalString.length();

            for (int i = 0; i < charCounter; i++) {
                char currentChar = finalString.charAt(i);

                if (currentChar == '\n') {
                    linesCounter++;
                    if (inWord) {
                        wordsCounter++;
                        inWord = false;
                    }
                } else if (currentChar == ' ') {
                    if (inWord) {
                        wordsCounter++;
                        inWord = false;
                    }
                } else {
                    inWord = true;
                }
            }

            if (inWord) {
                wordsCounter++;
            }

            osw.write(finalString);

            System.out.println("Chars: " + charCounter);
            System.out.println("Words: " + wordsCounter);
            System.out.println("Lines: " + linesCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
