import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class App {
    private static final int PORT = 8088;
    private static String filePath = ".\\src\\log.log";


    public static void main(String[] args) throws Exception {

        Db.db.add(new Data("Phone", 100));
        Db.db.add(new Data("Tablet", 200));
        Db.db.add(new Data("laptop", 300));
        Db.db.add(new Data("headphones", 500));
        Db.db.add(new Data("iphone", 700));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("socket on port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClientRequest(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            Request request = new Request(in);
            request.HandleRequest();
            SaveRequest(request);

            
            Responce responce = new Responce(out);
            responce.SendResponce(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void SaveRequest(Request request) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("Method: " + request.getMethod() + ", path: " + request.getPath() + ", User-Agent: "
                    + request.getHeaders().get("User-Agent:"));
            bw.newLine();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
