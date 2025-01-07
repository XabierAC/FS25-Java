import java.util.*;

public class FS25 {
    public static void main(String[] args) {
        String selection = mainMenu();
        if (selection.startsWith("C")) {
            
        } else if (selection.startsWith("V")) {
            
        }else {
            System.out.println("Fin");
        }
    }

    /* shows the main menu and takes the input from the keyboard */
    public static String mainMenu() {
        boolean correctOption = false;
        String selectedOption = "";
        Scanner inputScanner = new Scanner(System.in);
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
        inputScanner.close();
        return selectedOption;
    }

    /* Shows the selectable menu to work with fields info */
    public static void fieldMenu() {

    }

    /* Shows the selecable menu to work with vehicle info  */
    public static void vehicleMenu() {

    }
}