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
        String game = gameSelect(inputScanner);
        String selection = mainMenu(inputScanner);
        if (selection.startsWith("C")) {
            fieldMenu(inputScanner, game);
        } else if (selection.startsWith("V")) {
            vehicleMenu(inputScanner, game);
        }else {
            consolePS.println("Fin");
        }
    }

    public static String gameSelect(Scanner inputScanner) {
        String input = "";
        boolean correctInput = false;
        consolePS.println("En que partida vas a jugar?");
        while (!correctInput) {
            input = inputScanner.nextLine().toUpperCase();
            if (input.startsWith("R")) {
                input = "RiverbendSprings";
                consolePS.println();
                consolePS.println("Partida elegida correctamente. Pasalo bien.");
                consolePS.println();
                correctInput = true;
            } else if (input.startsWith("H")) {
                input = "HutanPantai";
                consolePS.println();
                consolePS.println("Partida elegida correctamente. Pasalo bien.");
                consolePS.println();
                correctInput = true;
            }else if (input.startsWith("Z")) {
                input = "Zielonka";
                consolePS.println();
                consolePS.println("Partida elegida correctamente. Pasalo bien.");
                consolePS.println();
                correctInput = true;
            } else {
                consolePS.println();
                consolePS.println("La partida introducido no es existe. Vuelve a intentarlo:");
            }
        }
        
        return input;
    }

    /* shows the main menu and takes the input from the keyboard */
    public static String mainMenu(Scanner inputScanner) {
        boolean correctOption = false;
        String selectedOption = "";
        do {
            consolePS.println("Que quieres hacer?");
            consolePS.println("Editar los (C)ampos");
            consolePS.println("Editar los (V)ehículos");
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
    public static void fieldMenu(Scanner inpuScanner, String game) throws FileNotFoundException {
        File fileInputField = new File("/Users/xabierac/Developer/Visual Studio Code/FS25 Java/field" + game + ".txt");
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
                showAllFields(inpuScanner, fileInputField);
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
        consolePS.println();
        consolePS.println("Vamos a agregar un nuevo campo a la lista.");
        consolePS.println("Cual es el numero del campo que quieres agregar?");
        idField = Integer.parseInt(inputScanner.nextLine());
        consolePS.println();
        consolePS.println("Cual es el estado actual del campo?");
        statusString = inputScanner.nextLine();
        consolePS.println();
        statusId = fieldStatusToInt(statusString.toUpperCase());
        if (statusId > 4 && statusId < 11) {
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
            int oldFieldInt = stringToInt(oldField, 1);
            if (!copied && idField < oldFieldInt) {
                filePS.println(newField);
                copied = true;
            }
            filePS.println(oldField);
        }
        if (!copied) {
            filePS.println(newField);
        }
        consolePS.println();
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
    public static void modifyField(Scanner inputScanner, File fileInputField, File fileCopyField) throws FileNotFoundException{
        Scanner inputFileScanner = new Scanner(fileInputField);
        PrintStream filePS = new PrintStream(fileCopyField);
        boolean modified = false;
        consolePS.println("Que campo es el que quieres modificar?");
        int fieldToModify = Integer.parseInt(inputScanner.nextLine());
        consolePS.println();
        while (inputFileScanner.hasNextLine()) {
            String inputFieldString = inputFileScanner.nextLine();
            int inputFieldInt = stringToInt(inputFieldString, 1);
            if(inputFieldInt == fieldToModify){
                consolePS.println("Que quieres modificar?");
                String inputString = inputScanner.nextLine().toUpperCase();
                consolePS.println();
                if (inputString.startsWith("P")) {
                    String status = fieldStatusToString(stringToInt(inputFieldString, 2));
                    consolePS.println("El anterior estado era " + status + ". Cual es el nuevo estado?");
                    int newIntStatus = fieldStatusToInt(inputScanner.nextLine().toUpperCase());
                    String newLine = stringToInt(inputFieldString, 1) + " " + newIntStatus + " " + stringToInt(inputFieldString, 3);
                    filePS.println(newLine);
                    modified = true;
                    consolePS.println();
                } else if (inputString.startsWith("C")) {
                    String cropString = cropToString(stringToInt(inputFieldString, 3));
                    consolePS.println("La anterior cosecha era " + cropString + ". Cual es la nueva cosecha?");
                    int newIntCrop = cropToInt(inputScanner.nextLine().toUpperCase());
                    String newLine = stringToInt(inputFieldString, 1) + " " + stringToInt(inputFieldString, 2) + " " + newIntCrop;
                    filePS.println(newLine);
                    modified = true;
                    consolePS.println();
                }
            } else {
                filePS.println(inputFieldString);
            }

        }
        if (modified) {
            consolePS.println("Los datos han sido modificados correctamente.");
            consolePS.println();
        } else {
            consolePS.println("Los datos no han podido ser modificados.");
            consolePS.println();
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

    /* Deletes a field from the list */
    public static void deleteField(Scanner inputScanner, File fileInputField, File fileCopyField) throws FileNotFoundException {
        Scanner inputFileScanner = new Scanner(fileInputField);
        PrintStream filePS = new PrintStream(fileCopyField);
        consolePS.println("Cual es el campo que quieres eliminar?");
        int fieldToDelete = Integer.parseInt(inputScanner.nextLine());
        while (inputFileScanner.hasNextLine()) {
            String inputFieldString = inputFileScanner.nextLine();
            int inputFieldInt = stringToInt(inputFieldString, 1);
            if (inputFieldInt == fieldToDelete) {
                consolePS.println();
                consolePS.println("El campo ha sido eliminado correctamente");
                consolePS.println();
            } else {
                filePS.println(inputFieldString);
            }
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

    /* Shows on console all the fields in property and their status and crop info */
    public static void showAllFields(Scanner inputScanner, File fileInputField) throws FileNotFoundException {
        Scanner inputFileScanner = new Scanner(fileInputField);
        while (inputFileScanner.hasNextLine()) {
            consolePS.println();
            String fieldToShow = inputFileScanner.nextLine();
            consolePS.println("Campo " + stringToInt(fieldToShow, 1) + ":");
            consolePS.println("\tEl estado del campo es: " + fieldStatusToString(stringToInt(fieldToShow, 2)));
            if (stringToInt(fieldToShow, 2) > 5 && stringToInt(fieldToShow, 2) < 11) {
                consolePS.println("\tEl cultivo plantado es: " + cropToString(stringToInt(fieldToShow, 3)));
            } else {
                consolePS.println("\tEl cultivo a plantar es: " + cropToString(stringToInt(fieldToShow, 3)));
            }
        }
        consolePS.println();
        inputFileScanner.close();
    }

    /* Transforms String from fieldStatus input to a number for status identification */
    public static int fieldStatusToInt(String fieldStatus) {
        int statusId = 0;
        if (fieldStatus.equals("DESMENUZADO")) {
            statusId = 1;
        } else if (fieldStatus.equals("ARADO") || fieldStatus.equals("CULTIVADO")) {
            statusId = 2;
        } else if (fieldStatus.equals("CON CAL")) {
            statusId = 3;
        } else if (fieldStatus.equals("ABONO 1")) {
            statusId = 4;
        } else if (fieldStatus.equals("SEMBRADO")) {
            statusId = 5;
        } else if (fieldStatus.equals("ABONO 2")) {
            statusId = 6;
        } else if (fieldStatus.equals("RODILLO")) {
            statusId = 7;
        } else if (fieldStatus.equals("CON MALAS HIERBAS")) {
            statusId = 8;
        } else if (fieldStatus.equals("CULTIVANDO")) {
            statusId = 9;
        } else if (fieldStatus.equals("LISTO PARA COSECHAR")) {
            statusId = 10;
        } else if (fieldStatus.equals("COSECHADO")) {
            statusId = 11;
        }
        return statusId;
    }
    
    /* Transforms int from number input to a String of the satuts of the field */
    public static String fieldStatusToString(int number) {
        String status = "";
        if (number == 1) {
            status = "Desmenuzado";
        } else if (number == 2) {
            status = "Arado o Cultivado";
        } else if (number == 3) {
            status = "Con cal";
        } else if (number == 4) {
            status = "Abono 1";
        } else if (number == 5) {
            status = "Sembrado";
        } else if (number == 6) {
            status = "Abono 2";
        } else if (number == 7) {
            status = "Rodillo";
        } else if (number == 8) {
            status = "Con Malas Hierbas";
        } else if (number == 9) {
            status = "Cultivando";
        } else if (number == 10) {
            status = "Listo para Cosechar";
        } else if (number == 11) {
            status = "Cosechado";
        }
        return status;
    }
    /* Converts from String to int */
    public static int stringToInt(String frase, int repeat) {
        Scanner fraseScanner = new Scanner(frase);
        int count = 0;
        int number = 0;
        while (count < repeat) {
            number = Integer.parseInt(fraseScanner.next());
            count ++;
        }
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

    /* Transforms int number input to a String of the crop */
    public static String cropToString(int number){
        String crop = "";
        if (number == 1) {
            crop = "Aceitunas";
        } else if (number == 2) {
            crop = "Algodon";
        } else if (number == 3) {
            crop = "Arroz";
        } else if (number == 4) {
            crop = "Arroz de Grano Largo";
        } else if (number == 5) {
            crop = "Avena";
        } else if (number == 6) {
            crop = "Colza";
        } else if (number == 7) {
            crop = "Caña de Azucar";
        } else if (number == 8) {
            crop = "Cebada";
        } else if (number == 9) {
            crop = "Chicharos";
        } else if (number == 10) {
            crop = "Espinaca";
        } else if (number == 11) {
            crop = "Frijol de Soja";
        } else if (number == 12) {
            crop = "Girasol";
        } else if (number == 13) {
            crop = "Habichuelas";
        } else if (number == 14) {
            crop = "Hierba";
        } else if (number == 15) {
            crop = "Maiz";
        } else if (number == 16) {
            crop = "Nabo";
        } else if (number == 17) {
            crop = "Patatas";
        } else if (number == 18) {
            crop = "Remolacha Azucarera";
        } else if (number == 19) {
            crop = "Remolacha Roja";
        } else if (number == 20) {
            crop = "Sorgo";
        } else if (number == 21) {
            crop = "Trigo";
        } else if (number == 22) {
            crop = "Uvas";
        } else if (number == 23) {
            crop = "Zanahorias";
        }
        return crop;
    }
    /* Shows the selecable menu to work with vehicle info  */
    public static void vehicleMenu(Scanner inpuScanner, String game) {
        boolean option = false;
        while (!option) {
            consolePS.println("****IMPLEMENTOS Y VEHICULOS****");
            consolePS.println("Que quieres hacer?");
            consolePS.println("(A)gregar un nuevo vehiculo)");
            consolePS.println("(M)odificar un vehiculo)");
            consolePS.println("(E)liminar un vehiculo)");
            String selection = inpuScanner.nextLine().toUpperCase();
            if (selection.startsWith("A")) {
                addVehicle(inpuScanner, game);
                consolePS.println();
                option = true;
            } else if (selection.startsWith("M")) {
                modifyVehicle(inpuScanner, game);
                consolePS.println();
                option = true;
            } else if (selection.startsWith("E")) {
                deleteVehicle(inpuScanner, game);
                consolePS.println();
                option = true;
            } else {
                consolePS.println("El texto introducido no es valido.\nVuelve a intentarlo.");
                consolePS.println();
            } 
        }
    }

    /* Adds a new vehicle to the list */
    public static void addVehicle(Scanner inputScanner, String game) {

    }

    /* Modifies the info of one of the vehicle in the list */
    public static void modifyVehicle(Scanner inputScanner, String game) {

    }

    /* Deletes one of the vehicle in the list */
    public static void deleteVehicle(Scanner inputScanner, String game) {

    }
}