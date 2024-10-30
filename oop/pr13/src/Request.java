import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class Request {
    String method;
    String path;
    String args;
    String line;
    Map<String, String> headers = new HashMap<>();
    Map<String, String> arguments = new HashMap<>();
    private BufferedReader in;

    public Request(BufferedReader in) {
        this.in = in;
    }

    public void HandleRequest() {
        try {
            String requestLine = in.readLine();

            if (requestLine == null) {
                return;
            }

            String[] requestParts = requestLine.split(" ");

            method = requestParts[0];
            String pathArgs = requestParts[1];

            String[] splitArgs = pathArgs.split("\\?", 2);

            path = splitArgs[0];

            if (splitArgs.length > 1) {
                args = splitArgs[1];
                arguments = ParseArgs(args);
            }

            String line;

            while (!(line = in.readLine()).isEmpty()) {
                String[] headerParts = line.split(" ", 2);

                if (headerParts.length == 2) {
                    headers.put(headerParts[0], headerParts[1]);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> ParseArgs(String argsStr) {

        Map<String, String> arguments = new HashMap<>();

        String[] argArr = argsStr.split("&");

        for (String arg : argArr) {
            String[] argParts = arg.split("=", 2);

            String key = argParts[0];
            String value = argParts.length > 1 ? argParts[1] : "";

            arguments.put(key, value);
        }

        System.err.println(arguments);
        return arguments;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
