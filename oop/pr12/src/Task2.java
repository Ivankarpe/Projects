import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

abstract class UseAble {
    String name; 
    int hungerLevel;
    int energyLevel;

    public UseAble(String name, int hungerLevel, int energyLevel){
        this.energyLevel = energyLevel;
        this.hungerLevel = hungerLevel;
        this.name = name;
    }
    int GetHunger(){
        return hungerLevel;
    }
    int GetEnergy(){
        return energyLevel;
    }

    public void PrintInfo(){
        System.out.println("Name: " + name + ", energy cost: " + energyLevel + ", hunger cost: " + hungerLevel);
    }
}


class Consumable extends UseAble {

    public Consumable(String name, int hungerLevel, int energyLevel) {
        super(name, hungerLevel, energyLevel);
    }

    @Override
    public void PrintInfo(){
        System.out.println("Name of a consumable: " + name + ", energy get: " + energyLevel + ", hunger get: " + hungerLevel);
    }
}

class Move extends UseAble{

    public Move(String name, int hungerLevel, int energyLevel) {
        super(name, hungerLevel, energyLevel);
    }
    @Override
    int GetHunger(){
        return -hungerLevel;
    }
    @Override
    int GetEnergy(){
        return -energyLevel;
    }
    public void PrintInfo(){
        System.out.println("Name of a move: " + name + ", energy cost: " + energyLevel + ", hunger cost: " + hungerLevel);
    }
    
}

class Character implements Serializable {
    String name;
    private int levelCaps = 100;
    private int energyLevel  = levelCaps;
    private int hungerLevel   = levelCaps;
    private transient String status;


    public Character(String name) {
        this.name = name;
        UpdateStatus();
    }

    public void Use(UseAble useAble){
        if(!CanUse(useAble)){
            System.out.print(name + " cant use: \n\t");
            useAble.PrintInfo();
            return;
        }

        int newHunger = this.hungerLevel + useAble.GetHunger();
        int newEnergy = this.energyLevel + useAble.GetEnergy();

        this.energyLevel = (newEnergy > levelCaps) ? levelCaps : newEnergy;

        this.hungerLevel = (newHunger > levelCaps) ? levelCaps : newHunger;

        System.out.print(this.name + " used: \n\t");
        useAble.PrintInfo();
        UpdateStatus();
        System.out.print(this.name + " new stats: \n\t");
        printPersonInfo();


    }

    private boolean CanUse(UseAble useAble){
        int newHunger = this.hungerLevel + useAble.GetHunger();
        int newEnergy = this.energyLevel + useAble.GetEnergy();

        if( newEnergy < 0 ){
            return false;
        }
        if( newHunger < 0 ){
            return false;
        }
        return true;
    }

    private void UpdateStatus(){
        int level = energyLevel + hungerLevel;
        if(level > levelCaps * 1.5){
            this.status =  "Nice";
        }
        else if(level> levelCaps * 0.66){
            this.status = "NotBad";
        }
        else{
            this.status =  "whompWhomp";
        }
        
    }

    public void Sleap(int hours){
        if(hours <=0){
            System.out.println(name + " cant sleap les that 1 hour");
        }

        int maxHours = hungerLevel / 3;
        if(hours > maxHours){
            hours = maxHours;
            System.out.println(name + " cant sleap that long they sleap only " + maxHours + " hours");
        }
        

        int deltaEnergy = hours * 15;
        int deltaHunger = hours * -3;

        energyLevel += deltaEnergy;
        hungerLevel += deltaHunger;

        energyLevel = (energyLevel > levelCaps) ? levelCaps : energyLevel;

        hungerLevel = (hungerLevel > levelCaps) ? levelCaps : hungerLevel;
        UpdateStatus();
        System.out.println(name + " sleapt for " + hours + " hours his new stats are: ");
        System.out.print('\t');
        printPersonInfo();
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
            Character deserPerson = (Character) ois.readObject();

            this.name = deserPerson.name;
            this.energyLevel = deserPerson.energyLevel;
            this.hungerLevel = deserPerson.hungerLevel;
            this.UpdateStatus();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void printPersonInfo() {
        System.out.println("Name: " + name + ", energyLevel: " + energyLevel + ", hungerLevel: " + hungerLevel + ", status: " + status);
    }
}

public class Task2 {
    public static void main(String[] args) throws Exception {
        File initialFile = new File(".\\oop\\pr12\\src\\Task2.java\\characterSave.ser");
        Character Luffy;

        if (initialFile.exists()) {
            Luffy = null;
            try (InputStream fis = new FileInputStream(initialFile);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                Luffy = (Character) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            Luffy = new Character("Luffy");
        }
        
        Move pistol = new Move("Gomu Gomy No Pistol", 20, 10);
        Move whip = new Move("Gomu Gomy No Whip", 15, 15);

        Consumable staminaPotion = new Consumable("Stamina potion", -10, 50);
        Consumable meat = new Consumable("Grilled meat", 35, -2); 


        Luffy.printPersonInfo();
        Luffy.Use(pistol);
        Luffy.Use(meat);
        Luffy.Use(whip);
        Luffy.Use(staminaPotion);
        Luffy.Sleap(10);
        Luffy.Use(whip);
        Luffy.Use(whip);
        Luffy.Use(whip);
        Luffy.Use(whip);
        Luffy.printPersonInfo();
    }
}
