import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class HomeController implements HttpHandler {

    private final ProductService productService = new ProductService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        switch (method) {
            case "GET":
                getHomePage(exchange);
                break;
            default:
                exchange.sendResponseHeaders(405, -1);
                break;
        }
    }
    private void getHomePage(HttpExchange exchange) throws IOException {
        List<Product> products = productService.getAllProducts();
        StringBuilder productsString = new StringBuilder("Products:<br>");
        for (Product product : products) {
            productsString.append(product.toString()).append("<br>");
        }

        String responce= "<!DOCTYPE html>" +
        "<html lang=\"en\">" +
        "<head>" +
        "    <meta charset=\"UTF-8\">" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
        "    <title>Home</title>" +
        "</head>" +
        "<body>" +
        "    <p>"+ productsString.toString()+
        "</p>" +
        "    <form action=\"/products\" method=\"post\">" +
        "        Name: <input type=\"text\" name=\"name\"><br>" +
        "        Price: <input type=\"text\" name=\"price\"><br>" +
        "        <button type=\"submit\">Add New Product</button>" +
        "    </form>" +
        "</body>" +
        "</html>";



        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, responce.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responce.getBytes());
        }

    }

   
}
