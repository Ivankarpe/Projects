
public class Product {

    String name;
    String id;
    int count;
    double price;
    int photo;

    public Product(String id, String name, int count, double price) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.price = price;
        photo = Integer.parseInt(this.id) % 3;
    }
}
