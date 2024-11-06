import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class App {
    private static final int PORT = 8080;
  


    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        ProductController productController = new ProductController();
        HomeController homeController = new HomeController();

        server.createContext("/products", productController::handle);
        server.createContext("/home", homeController::handle);

        server.setExecutor(null);
        server.start();
        System.err.println("Server stsrted on port " + PORT);
    }

  
}
