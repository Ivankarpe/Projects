import java.io.PrintWriter;


public class Responce {
    PrintWriter out;

    public Responce(PrintWriter out) {
        this.out = out;
    }

    public void SendResponce(Request request) {

        if(request.getMethod().equals("GET") && request.getPath().equals("/item") && request.arguments.containsKey("name") && request.arguments.containsKey("price")){
            Db.db.add(new Data(request.arguments.get("name"),Integer.parseInt(request.arguments.get("price"))));
            out.println("HTTP/1.1 302 Found");
            out.println("Location: /home");
            out.println();
        }else if (request.getMethod().equals("GET") && request.getPath().equals("/home")) {
            sendHomePage(out, request);
        } else if (request.getMethod().equals("GET") && request.getPath().equals("/about")) {
            sendAboutPage(out);
        } else {
            sendNotFoundResponse(out);
        }
    }



    private static void sendAboutPage(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><boby><h1> About </h1></boby></html>");
    }

    private static void sendNotFoundResponse(PrintWriter out) {
        out.println("HTTP/1.1 404 NOT Found");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><boby><h1> 404 </h1></boby></html>");
    }

    private static void sendHomePage(PrintWriter out, Request request) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println();
        out.println("<html><boby><h1> HOME </h1>");
        int limit = 0;
        for (Data dat :  Db.db) {

            if (request.arguments.containsKey("minPrice")) {
                if (dat.price < Integer.parseInt(request.arguments.get("minPrice"))) {
                    continue;
                }
            }
            if (request.arguments.containsKey("topPrice")) {
                if (dat.price > Integer.parseInt(request.arguments.get("topPrice"))) {
                    continue;
                }
            }
            if (request.arguments.containsKey("limit")) {
                if (limit >= Integer.parseInt(request.arguments.get("limit"))) {
                    continue;
                }
            }
            out.println("<h1>" + dat.name + " - " + dat.price + "</h1>");
            limit++;
        }
        out.println("</boby></html>");
    }
}
