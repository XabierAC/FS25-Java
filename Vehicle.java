import java.io.*;
import java.util.*;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int horsepower;
    private int damage;
    private String plate;

    public Vehicle (int id, String brand, String model, int horsepower, int damage, String plate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.horsepower = horsepower;
        this.damage = damage;
        this.plate = plate;
    }
    public static int getId(String inputString, Scanner inputScanner) throws FileNotFoundException {
        File bddVehicle = new File("/Users/xabierac/Developer/Visual Studio Code/FS25 Java/bddVehicle.txt");
        Scanner bddVehicleScanner = new Scanner(bddVehicle);
        String brand = FS25.selectedText(inputString, 1);
        String model = FS25.selectedText(inputString, 2);
        int id = 0;
        boolean found = false;
        while (!found){
            while (bddVehicleScanner.hasNextLine()) {
                String textOfBdd = bddVehicleScanner.nextLine();
                if (brand.equals(FS25.selectedText(textOfBdd, 2)) && model.equals(FS25.selectedText(textOfBdd, 3))) {
                    found = true;
                    id = Integer.parseInt(FS25.selectedText(textOfBdd, 1));
                }
            }
            if (!found) {
                System.out.println("El vehiculo introducido no existe en la base de datos.\nIntroduce una marca y modelo validos.");
                brand = FS25.selectedText(inputString, 1);
                model = FS25.selectedText(inputString, 2);
            }
        }
        bddVehicleScanner.close();
        return id;
    }
}
