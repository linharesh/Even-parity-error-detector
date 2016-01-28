package UserInterface;

import ApplicationModel.Decryptor;
import ApplicationModel.Encryptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.krb5.internal.EncAPRepPart;

/**
 * Class responsable for all user interaction through the console
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Filipe Coimbra; Pablo Curty
 */
public class ConsoleInterface {

    private BufferedReader inputConsole;

    /**
     * Constructor
     */
    public ConsoleInterface() {
        this.inputConsole = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Launch the console interface. Gets all the information with the user,
     * such as if he wants to encript or decript, and the paths to the files
     *
     */
    public void launchConsoleInterface() throws IOException {
        System.out.println("Hello! Welcome to the RSA Encripter / Decripter");
        int encriptOrDecript = this.chooseEncriptOrDecript();
        int[] keys = this.readKeys();
        String inputFilePath = this.chooseINFile() ; 
        String outputFilePath = this.chooseOUTFile() ;
        
        if (encriptOrDecript == 1){//Encript
            Encryptor encryptor = new Encryptor();
            encryptor.encript(inputFilePath, outputFilePath, keys);
        } else {//Decript
            Decryptor decryptor = new Decryptor();
            decryptor.decript(inputFilePath, outputFilePath, keys);
        }
    }

    /**
     * Write the param message in the console
     *
     * @param S the message that will be displayed in console
     */
    public static void displayMessage(String S) {
        System.out.println(S);
    }

    /**
     * Ask for the user if he wats to encript or decript a file
     *
     * @return Encode -> returns 1 ; Decode -> returns 2
     * @throws IOException Can throw IO exception
     */
    private int chooseEncriptOrDecript() throws IOException {
        int read;
        do {
            System.out.println("1 - Encript File");
            System.out.println("2 - Decript File");
            String readStr = inputConsole.readLine();
            read = Integer.parseInt(readStr);
        } while (read != 1 && read != 2);
        return read;
    }

    /**
     * Asks the user the path to the INPUT file, and check if the path is
     * correct and if the file exists
     *
     * @return the correct path to the input file
     * @throws IOException Can throw IO exception
     */
    private String chooseINFile() throws IOException {
        File file;
        Boolean fileExists = false;
        String readStr;
        do {
            System.out.println("Enter the path to the INPUT file");
            readStr = inputConsole.readLine();
            file = new File(readStr);
            if (file.exists() && !file.isDirectory()) {
                fileExists = true;
            } else if (!file.exists()) {
                ConsoleInterface.displayMessage("File don't exist");
                fileExists = false;
            }
        } while (fileExists == false);
        return readStr;
    }

    /**
     * Asks the user the path to the OUTPUT file, and check if the path is
     * correct and if the file dont exists
     *
     * @return the correct path to the output file
     * @throws IOException Can throw IO exception
     */
    private String chooseOUTFile() throws IOException {
        File file;
        Boolean pathIsCorrect;
        String readStr;
        do {
            System.out.println("Enter the path to the OUTPUT file");
            readStr = inputConsole.readLine();
            file = new File(readStr);
            if (!file.exists()) {
                if (!file.isDirectory()) {
                    pathIsCorrect = true;
                } else {
                    pathIsCorrect = false;
                }
            } else {
                pathIsCorrect = false;
                ConsoleInterface.displayMessage("File already exists");
            }
        } while (!pathIsCorrect);
        return readStr;
    }

    /**
     * Asks the user the keys to the encript / decript process
     *
     * @return An array with both keys
     * @throws IOException Can throw IO exception
     */
    private int[] readKeys() throws IOException {
        int[] keys = new int[2];
        String readStr;

        do {
            System.out.println("Enter the first key (N)");
            readStr = inputConsole.readLine();
        } while (!nKeyValidator(readStr));
        keys[0] = Integer.parseInt(readStr);

        do {
            System.out.println("Enter the second key (D or E)");
            readStr = inputConsole.readLine();
        } while (!isNumeric(readStr));
        keys[1] = Integer.parseInt(readStr);

        return keys;
    }

    private boolean nKeyValidator(String readStr) {
        int key = Integer.parseInt(readStr);
        if (isNumeric(readStr)) {
            if (key > 255 && key < 65536) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
