import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {

        File inputFile = new File("user_request_logs_1000.log");
        File outputFile = new File("OutLog.txt");

        FileInputStream FInputStram = new FileInputStream(inputFile);
        FileOutputStream FOutputStram = new FileOutputStream(outputFile);

        FInputStram.close();
        FOutputStram.close();

    }
}
