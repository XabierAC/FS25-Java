import java.util.*;
import java.io.*;
public class FS25 {
    static int idField;
    static int statusId;
    static String fieldCrop;
    static PrintStream consolePS = new PrintStream(System.out);
    
    /* Program to manage the fields and vehicles easy for FS25 */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);
        String selection = mainMenu(inputScanner);
        if (selection.startsWith("C")) {
            fieldMenu(inputScanner);
        } else if (selection.startsWith("V")) {
            vehicleMenu(inputScanner);
        }else {
            consolePS.println("Fin");
        }
    }

    /* shows the main menu and takes the input from the keyboard */
    public static String mainMenu(Scanner inputScanner) {
        boolean correctOption = false;
        String selectedOption = "";
        do {
            consolePS.println("Que quieres hacer?");
            consolePS.println("Editar los (C)ampos");
            //consolePS.println("Editar los (V)ehículos");
            consolePS.println("(S)alir");
            selectedOption = inputScanner.nextLine().toUpperCase();
            if (selectedOption.startsWith("C") || selectedOption.startsWith("V") || selectedOption.startsWith("S")){
                correctOption = true;
            } else {
                consolePS.println("No has elegido una opcion valida, vuelve a intentarlo.");
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
            consolePS.println("****CAMPOS****");
            consolePS.println("Que quieres hacer?");
            consolePS.println("(A)gregar un nuevo campo)");
            consolePS.println("(E)ditar un campo)");
            consolePS.println("(B)orrar un campo)");
            consolePS.println("(M)ostrar los campos");
            consolePS.println("(V)olver atras");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addField(inpuScanner, fileInputField, fileCopyField);
            } else if (selection.startsWith("E")) {
                modifyField(inpuScanner, fileInputField, fileCopyField);
            } else if (selection.startsWith("B")) {
                deleteField(inpuScanner, fileInputField, fileCopyField);
            } else if (selection.startsWith("M")) {
                
            } else if (selection.startsWith("V")) {
                mainMenu(inpuScanner);
            } else {
                consolePS.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                consolePS.println();
            }
        }
    }

    /* Adds a new field to the list */
    public static void addField(Scanner inputScanner, File fileInputField, File fileCopyField) throws FileNotFoundException {
        Scanner inputFileScanner = new Scanner(fileInputField);
        PrintStream filePS = new PrintStream(fileCopyField);
        String statusString;
        int cropId = 0;
        boolean copied = false;
        consolePS.println("Vamos a agregar un nuevo campo a la lista.");
        consolePS.println("Cual es el numero del campo que quieres agregar?");
        idField = Integer.parseInt(inputScanner.nextLine());
        consolePS.println("Cual es el estado actual del campo?");
        statusString = inputScanner.nextLine();
        statusId = fieldStatusToInt(statusString.toUpperCase());
        if (statusId > 5 && statusId < 10) {
            consolePS.println("Que producto hay plantado?");
            fieldCrop = inputScanner.nextLine().toUpperCase();
            cropId = cropToInt(fieldCrop);
 
        } else {
            consolePS.println("Que producto vas a plantar?");
            fieldCrop = inputScanner.nextLine().toUpperCase();
            cropId = cropToInt(fieldCrop);
        }
        String newField = idField + " " + statusId + " " + cropId;
        while (inputFileScanner.hasNextLine()) {
            String oldField = inputFileScanner.nextLine();
            int oldFieldInt = stringToInt(oldField);
            if (!copied && idField < oldFieldInt) {
                filePS.println(newField);
                copied = true;
            }
            filePS.println(oldField);
        }
        if (!copied) {
            filePS.println(newField);
        }
        filePS.close();
        inputFileScanner.close();
        inputFileScanner = new Scanner(fileCopyField);
        filePS = new PrintStream(fileInputField);
        while (inputFileScanner.hasNextLine()) {
            String line = inputFileScanner.nextLine();
            filePS.println(line);
        }
        inputFileScanner.close();
        filePS.close();
        fileCopyField.delete();
    }

    /* Modifies the info of one of the fields in the list */
    public static void modifyField(Scanner inputScanner, File fileInputField, File fileCopyField) {
        String input;
        consolePS.println("La información de qué campo quieres modificar?");
        idField = Integer.parseInt(inputScanner.nextLine());
        consolePS.println("Que quieres modificar?");
        if (inputScanner.nextLine().equals("Cosecha")) {
            consolePS.println("Cual es el nuevo dato?");
            input = inputScanner.nextLine();
        } else if (inputScanner.nextLine().equals("Estado del campo")) {
            consolePS.println("Cual es el nuevo dato?");
            input = inputScanner.nextLine();
        }
        
    }

    /* Deletes a field from the list */
    public static void deleteField(Scanner inputScanner, File fileInputField, File fileCopyField) {

    }

    /* Shows on console all the fields in property and their status and crop info */
    public static void showAllFields(Scanner inputScanner, File fileInputField, File fileCopyField) {

    }

    /* Transforms String from fieldStatus input to a number for status identification */
    public static int fieldStatusToInt(String fieldStatus) {
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
    
    /* Converts from String to int */
    public static int stringToInt(String frase) {
        Scanner fraseScanner = new Scanner(frase);
        int number = Integer.parseInt(fraseScanner.next());
        fraseScanner.close();
        return number;
    }
    
    /* Transforms from the crop input to a number for easy save */
    public static int cropToInt(String crop) {
        int number = 0;
        if (crop.equals("ACEITUNAS") || crop.equals("ACEITUNA")) {
            number = 1;
        } else if (crop.equals("ALGODON")) {
            number = 2;
        } else if (crop.equals("ARROZ")) {
            number = 3;
        } else if (crop.equals("ARROZ DE GRANO LARGO") || crop.equals("ARROZ CON COSECHADORA")) {
            number = 4;
        } else if (crop.equals("AVENA")) {
            number = 5;
        } else if (crop.equals("COLZA") || crop.equals("CANOLA")) {
            number = 6;
        } else if (crop.equals("CAÑA DE AZUCAR") || crop.equals("CAÑA")) {
            number = 7;
        } else if (crop.equals("CEBADA")) {
            number = 8;
        } else if (crop.equals("CHICAROS")) {
            number = 9;
        } else if (crop.equals("ESPINACA") || crop.equals("ESPINACAS")) {
            number = 10;
        } else if (crop.equals("FRIJOL DE SOJA") || crop.equals("HABAS DE SOJA")) {
            number = 11;
        } else if (crop.equals("GIRASOL") || crop.equals("GIRASOLES")) {
            number = 12;
        } else if (crop.equals("HABICHUELAS")) {
            number = 13;
        } else if (crop.equals("HIERBA")) {
            number = 14;
        } else if (crop.equals("MAIZ")) {
            number = 15;
        } else if (crop.equals("NABO")) {
            number = 16;
        } else if (crop.equals("PATATAS") || crop.equals("PAPA") || crop.equals("PAPAS") || crop.equals("PATATA")) {
            number = 17;
        } else if (crop.equals("REMOLACHA AZUCARERA") || crop.equals("REMOLACHA AZ")) {
            number = 18;
        } else if (crop.equals("REMOLACHA ROJA")) {
            number = 19;
        } else if (crop.equals("SORGO") || crop.equals("SORGHUM")) {
            number = 20;
        } else if (crop.equals("TRIGO")) {
            number = 21;
        } else if (crop.equals("UVAS") || crop.equals("UVA")) {
            number = 22;
        } else if (crop.equals("ZANAHORIAS") || crop.equals("ZANAHORIA")) {
            number = 23;
        }
        return number;
    }

    /* Shows the selecable menu to work with vehicle info  */
    public static void vehicleMenu(Scanner inpuScanner) {
        boolean option = false;
        while (!option) {
            consolePS.println("****VEHICULOS****");
            consolePS.println("Que quieres hacer?");
            consolePS.println("(A)gregar un nuevo vehiculo)");
            consolePS.println("(M)odificar un vehiculo)");
            consolePS.println("(E)liminar un vehiculo)");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addVehicle(inpuScanner);
            } else if (selection.startsWith("M")) {
                modifyVehicle(inpuScanner);
            } else if (selection.startsWith("E")) {
                deleteVehicle(inpuScanner);
            } else {
                consolePS.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                consolePS.println();
            } 
        }
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