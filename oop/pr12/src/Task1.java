import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class Person implements Serializable {
    String name;
    int age;
    String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    void savePerson(String filePath) {
        try (OutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void loadPerson(String filePath) {
        try (InputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Person deserPerson = (Person) ois.readObject();
            this.name = deserPerson.name;
            this.age = deserPerson.age;
            this.city = deserPerson.city;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void printPersonInfo() {
        System.out.println("Name: " + name + ", age: " + age + "years, city: " + city);
    }
}

public class Task1 {
    public static void main(String[] args) throws Exception {
        final String path = ".\\oop\\pr12\\src\\person.ser";
        Person tip = new Person("Tip", 10, "Cherkasy");
        tip.printPersonInfo();
        tip.savePerson(path);
        tip.loadPerson(path);
        tip.printPersonInfo();
    }
}
