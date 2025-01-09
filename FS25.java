import java.util.*;
import java.io.*;
public class FS25 {
    static int idField;
    static int statusId;
    static String fieldCrop;
    /* Program to manage the fields and vehicles easy for FS25 */
    public static void main(String[] args) throws FileNotFoundException {
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
    public static void fieldMenu(Scanner inpuScanner) throws FileNotFoundException {
        File fileInputField = new File("/Users/xabierac/Developer/Visual Studio Code/FS25 Java/field.txt");
        File fileCopyField = new File("/Users/xabierac/Developer/Visual Studio Code/FS25 Java/fieldCopy.txt");
        boolean option = false;
        while (!option) {
            System.out.println("****CAMPOS****");
            System.out.println("Que quieres hacer?");
            System.out.println("(A)gregar un nuevo campo)");
            System.out.println("(M)odificar un campo)");
            System.out.println("(E)liminar un campo)");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addField(inpuScanner, fileInputField, fileCopyField);
            } else if (selection.startsWith("M")) {
                modifyField(inpuScanner, fileInputField, fileCopyField);
            } else if (selection.startsWith("E")) {
                deleteField(inpuScanner, fileInputField, fileCopyField);
            } else {
                System.out.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                System.out.println();
            }
        }
        fileCopyField.delete();
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
    public static void addField(Scanner inputScanner, File fileinputField, File fileCopyField) throws FileNotFoundException {
        Scanner inputFileScanner = new Scanner(fileinputField);
        PrintStream writePS = new PrintStream(fileCopyField);
        String statusString;
        System.out.println("Entendido.\nVamos a agregar un nuevo campo a la lista.");
        System.out.println("Cual es el numero del campo que quieres agregar?");
        idField = Integer.parseInt(inputScanner.nextLine());
        System.out.println("Cual es el estado actual del campo?");
        statusString = inputScanner.nextLine();
        statusId = fieldStatusId(statusString.toUpperCase());
        if (statusId < 6 || statusId == 10) {
            System.out.println("Que producto vas a plantar?");
            fieldCrop = inputScanner.nextLine();
        } else {
            System.out.println("Que producto hay plantado?");
            fieldCrop = inputScanner.nextLine();
        }
        String field = idField + " " + statusId + " " + fieldCrop;
        while (inputFileScanner.hasNextLine()) {
            String copyField = inputFileScanner.nextLine();
            if (copyField.equals(" ") || idField < stringToInt(copyField)) {
                writePS.println(field);
                writePS.println(copyField);
            }
        }
        writePS.close();
        inputFileScanner.close();

    }

    /* Modifies the info of one of the fields in the list */
    public static void modifyField(Scanner inputScanner, File fileinputField, File fileCopyField) {
        String input;
        System.out.println("La información de qué campo quieres modificar?");
        idField = Integer.parseInt(inputScanner.nextLine());
        System.out.println("Que quieres modificar?");
        if (inputScanner.nextLine().equals("Cosecha")) {
            System.out.println("Cual es el nuevo dato?");
            input = inputScanner.nextLine();
        } else if (inputScanner.nextLine().equals("Estado del campo")) {
            System.out.println("Cual es el nuevo dato?");
            input = inputScanner.nextLine();
        }
    }

    /* Deletes a field from the list */
    public static void deleteField(Scanner inputScanner, File fileinputField, File fileCopyField) {

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

    /* Transforms String from fieldStatus input to a number for status identification */
    public static int fieldStatusId(String fieldStatus) {
        int statusId = 0;
        if (fieldStatus.equals("DESMENUZADO")) {
            statusId = 1;
        } else if (fieldStatus.equals("CON CAL")) {
            statusId = 2;
        } else if (fieldStatus.equals("ABONO 1")) {
            statusId = 3;
        } else if (fieldStatus.equals("ARADO") || fieldStatus.equals("CULTIVADO")) {
            statusId = 4;
        } else if (fieldStatus.equals("SEMBRADO")) {
            statusId = 5;
        } else if (fieldStatus.equals("ABONO 2")) {
            statusId = 6;
        } else if (fieldStatus.equals("RODILLO")) {
            statusId = 7;
        } else if (fieldStatus.equals("CON MALAS HIERBAS")) {
            statusId = 8;
        } else if (fieldStatus.equals("LISTO PARA COSECHAR")) {
            statusId = 9;
        } else if (fieldStatus.equals("COSECHADO")) {
            statusId = 10;
        }
        return statusId;
    }

    /* Transforms String from crop input to a number for identification */
    public static int cropId() {
        int result = 0;

        return result;
    }

    /* Converts from String to int */
    public static int stringToInt(String frase) {
        Scanner fraseScanner = new Scanner(frase);
        int number = Integer.parseInt(fraseScanner.next());
        fraseScanner.close();
        return number;
    }
}