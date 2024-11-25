package com.karpenko.laba8.services.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.karpenko.laba8.models.Product;


@Service
public class ProductServiceDB implements ProductServiceInterface {

    private final String url = "jdbc:sqlite:mdb.db";

    public ProductServiceDB() {
        try (Connection connection = DriverManager.getConnection(url)) {
            System.err.println("Coected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Product product) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Products(UUID,Name,Price) VALUES(?, ?, ?);");) {

            String uuid = UUID.randomUUID().toString();
            statement.setString(1, uuid);
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());

            statement.executeUpdate();
            System.err.println("created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement("Select * from Products;");
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getString("UUID"));
                product.setName(resultSet.getString("Name"));
                product.setPrice(resultSet.getDouble("Price"));
                products.add(product);
            }

            System.err.println("Coected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product read(String uuid) {
        Product product = new Product();
        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("Select * from Products WHERE UUID = ?;");
            ) {
            statement.setString(1, uuid);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            product.setId(resultSet.getString("UUID"));
            product.setName(resultSet.getString("Name"));
            product.setPrice(resultSet.getDouble("Price"));            

            System.err.println("readed from " + uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;

    }

    @Override
    public boolean update(Product product, String uuid) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Products SET Name=?, Price=? WHERE UUID = ?;");) {

            
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, uuid);

            statement.executeUpdate();
            System.err.println("Updated");

            Product newProduct = read(uuid);
            if(newProduct.getName() == product.getName() &&newProduct.getPrice() == product.getPrice()){
                return true;
            }
            
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String uuid) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM Products WHERE UUID = ?;");) {

            
            
            statement.setString(1, uuid);

            statement.executeUpdate();
            System.err.println("Deleted");

            Product newProduct = read(uuid);
            if(newProduct == null){
                return true;
            }
            
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
