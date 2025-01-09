import java.util.*;

public class FS25 {
    /* Program to manage the fields and vehicles easy for FS25 */
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String selection = mainMenu(inputScanner);
        if (selection.startsWith("C")) {
            fieldMenu(inputScanner);
        } else if (selection.startsWith("V")) {
            vehicleMenu(inputScanner);
        }else {
            System.out.println("Fin");
        }
    }

    /* shows the main menu and takes the input from the keyboard */
    public static String mainMenu(Scanner inputScanner) {
        boolean correctOption = false;
        String selectedOption = "";
        do {
            System.out.println("Que quieres hacer?");
            System.out.println("Editar los (C)ampos");
            System.out.println("Editar los (V)ehículos");
            System.out.println("(S)alir");
            selectedOption = inputScanner.nextLine().toUpperCase();
            if (selectedOption.startsWith("C") || selectedOption.startsWith("V") || selectedOption.startsWith("S")){
                correctOption = true;
            } else {
                System.out.println("No has elegido una opcion valida, vuelve a intentarlo.");
            }
        } while (!correctOption);
        return selectedOption;
    }

    /* Shows the selectable menu to work with fields info */
    public static void fieldMenu(Scanner inpuScanner) {
        boolean option = false;
        while (!option) {
            System.out.println("****CAMPOS****");
            System.out.println("Que quieres hacer?");
            System.out.println("(A)gregar un nuevo campo)");
            System.out.println("(M)odificar un campo)");
            System.out.println("(E)liminar un campo)");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addField(inpuScanner);
            } else if (selection.startsWith("M")) {
                modifyField(inpuScanner);
            } else if (selection.startsWith("E")) {
                deleteField(inpuScanner);
            } else {
                System.out.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                System.out.println();
            } 
        }

    }

    /* Shows the selecable menu to work with vehicle info  */
    public static void vehicleMenu(Scanner inpuScanner) {
        boolean option = false;
        while (!option) {
            System.out.println("****VEHICULOS****");
            System.out.println("Que quieres hacer?");
            System.out.println("(A)gregar un nuevo vehiculo)");
            System.out.println("(M)odificar un vehiculo)");
            System.out.println("(E)liminar un vehiculo)");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addVehicle(inpuScanner);
            } else if (selection.startsWith("M")) {
                modifyVehicle(inpuScanner);
            } else if (selection.startsWith("E")) {
                deleteVehicle(inpuScanner);
            } else {
                System.out.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                System.out.println();
            } 
        }

    }

    /* Adds a new field to the list */
    public static void addField(Scanner inputScanner) {

    }

    /* Modifies the info of one of the fields in the list */
    public static void modifyField(Scanner inputScanner) {

    }

    /* Deletes a field from the list */
    public static void deleteField(Scanner inputScanner) {

    }

    /* Adds a new vehicle to the list */
    public static void addVehicle(Scanner inputScanner) {

    }

    /* Modifies the info of one of the vehicle in the list */
    public static void modifyVehicle(Scanner inputScanner) {

    }

    /* Deletes one of the vehicle in the list */
    public static void deleteVehicle(Scanner inputScanner) {

    }
}