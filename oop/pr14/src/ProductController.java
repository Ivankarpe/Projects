import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProductController implements HttpHandler {
    private final ProductService productService = new ProductService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        switch (method) {
            case "GET":
                getAllProducts(exchange);
                break;
            case "POST":
                addProduct(exchange);
                break;
            case "PUT":
                updateProduct(exchange);
                break;
            case "DELETE":
                deleteProduct(exchange);
                break;
            default:
                exchange.sendResponseHeaders(405, -1);
                break;
        }
    }

    private void addProduct(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        StringBuilder body = new StringBuilder();
        int i;
        while ((i = is.read()) != -1) {
            body.append((char) i);
        }
        String decodedBody = URLDecoder.decode(body.toString(), "UTF-8");
        String[] params = decodedBody.split("&");
        String name = params[0].split("=")[1];
        double price = Double.parseDouble(params[1].split("=")[1]);
        productService.addProduct(name,price);
        String response = "Product added succsesfull";
        exchange.sendResponseHeaders(201, response.getBytes().length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }

    }

    private void getAllProducts(HttpExchange exchange) throws IOException{
        List<Product> products = productService.getAllProducts();
        StringBuilder response = new StringBuilder("Products:\n");
        for (Product product : products) {
            response.append(product.toString()).append("\n");
        }
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200,response.toString().length());
        try (OutputStream os = exchange.getResponseBody()) {
          os.write(response.toString().getBytes());  
        } 

    }
    private void deleteProduct(HttpExchange exchange) throws IOException{
        InputStream is = exchange.getRequestBody();
        StringBuilder body = new StringBuilder();
        int i;
        while ((i = is.read()) != -1) {
            body.append((char) i);
        }
        String decodedBody = URLDecoder.decode(body.toString(), "UTF-8");
        String[] params = decodedBody.split("&");
        String name = params[0].split("=")[1];
        productService.deleteProduct(name);
        String response = "Product deleted succsesfull";
        exchange.sendResponseHeaders(201, response.getBytes().length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private void updateProduct(HttpExchange exchange) throws IOException{
        InputStream is = exchange.getRequestBody();
        StringBuilder body = new StringBuilder();
        int i;
        while ((i = is.read()) != -1) {
            body.append((char) i);
        }
        String decodedBody = URLDecoder.decode(body.toString(), "UTF-8");
        String[] params = decodedBody.split("&");
        String name = params[0].split("=")[1];
        double price = Double.parseDouble(params[1].split("=")[1]);
        productService.updateProduct(name, price);
        String response = "Product updated succsesfull";
        exchange.sendResponseHeaders(201, response.getBytes().length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
