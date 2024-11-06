import java.util.ArrayList;

public class ProductService {
    private static final ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts(){
        return new ArrayList<>(products);
    }

    public void addProduct(String name, double price){
        products.add(new Product(name, price));
    }

    public void deleteProduct(String name) {
        for(Product product: products){
            if(product.getName().equals(name)){
                products.remove(product);
            }
        }
    }

    public void updateProduct(String name, double price) {
        for(Product product: products){
            if(product.getName().equals(name)){
                product.setPrice(price);
            }
        }
    }

}
