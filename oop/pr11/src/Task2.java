import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class LogInfo {
    LocalDateTime logTime;
    String logStatus;
    String logDesc;

    public LogInfo(LocalDateTime logTime, String logStatus, String logDesc) {
        this.logTime = logTime;
        this.logStatus = logStatus;
        this.logDesc = logDesc;
    }
}

class User {
    String username;
    ArrayList<LogInfo> userLogs = new ArrayList<>();
    private int logCount = 0;
    private int successfulLogCount = 0;

    public User(String username) {
        this.username = username;
    }

    void addLog(LocalDateTime logTime, String logStatus, String logDesc) {
        this.userLogs.add(new LogInfo(logTime, logStatus, logDesc));
        this.logCount++;
        if (logStatus.equals("INFO")) {
            this.successfulLogCount++;
        }
    }

    public double GetSuccsessPercent() {
        if (logCount > 0) {
            return (successfulLogCount / (double) logCount) * 100;
        }
        return 0;
    }

    void printUserInfo(BufferedWriter bw) throws IOException {
        String userInfo = "Username: " + username + 
                          ", Total amount of user logs: " + logCount + 
                          ", Successful logs: " + successfulLogCount + 
                          " times, Negative logs: " + (logCount - successfulLogCount) + 
                          " times, Success percentage: " + String.format("%.2f", GetSuccsessPercent()) + "%\n";

        bw.write(userInfo);
    }
    public int getLogCount(){
        return logCount;
    }
    public int getSuccessfulLogCount() {
        return successfulLogCount;
    }

}

public class Task2{
    public static Map<String, User> userList = new HashMap<>();

    public static void main(String[] args) {
        String path = ".\\oop\\pr11\\src\\user_request_logs_1000.log";
        String formattedPath = ".\\oop\\pr11\\src\\formatted.log";

        try(BufferedReader br = new BufferedReader(new FileReader(path));
            BufferedWriter bw = new BufferedWriter(new FileWriter(formattedPath)))
            {

            String line;

            int charCounter = 0;
            int wordsCounter = 0;
            int linesCounter = 0;

            while ((line = br.readLine()) != null) {
                linesCounter++;
                charCounter += line.length();
                wordsCounter += countWords(line);
                addUserInfo(line);
            }

            System.out.println("Chars: " + charCounter);
            System.out.println("Words: " + wordsCounter);
            System.out.println("Lines: " + linesCounter);

           
            double countOfLogs = 0;
            double countOfSuccLogs = 0;


            for (User element : userList.values()) {
                element.printUserInfo(bw);
                countOfLogs += element.getLogCount();
                countOfSuccLogs += element.getSuccessfulLogCount();
                
            }
            
            
            
            String overallInfo = "Total amount of user logs: " + countOfLogs + 
            ", Successful logs: " + countOfSuccLogs + 
            " times, Negative logs: " + (countOfLogs - countOfSuccLogs) + 
            " times, Success percentage: " + countOfSuccLogs/countOfLogs*100 + "%";

            System.out.println(overallInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int countWords(String line) {
        boolean inWord = false;
        int wordsCounter = 0;
        int charCounter = line.length();

        for (int i = 0; i < charCounter; i++) {
            char currentChar = line.charAt(i);

            if (currentChar == ' ') {
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
        return wordsCounter;
    }

    static void addUserInfo(String infoStr) {
        String[] info = infoStr.trim().split(" - ");
        String date = info[0];    
        String state = info[1];   
        String username = info[2];
        String msg = info[3];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime logTime = LocalDateTime.parse(date, formatter);
        
        if (!userList.containsKey(username)) {
            userList.put(username, new User(username)); 
        } 
        userList.get(username).addLog(logTime, state, msg);
    }

}
