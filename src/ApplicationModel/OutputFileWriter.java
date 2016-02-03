package ApplicationModel;

import UserInterface.ConsoleInterface;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/** Class responsable for writing information into files
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class OutputFileWriter {

    private File file;
    private FileOutputStream fileOutputStream;
    private DataOutputStream dataOutputStream;

    /** Method used to create and write encrypted information into a file.
     * 
     * @param outputFilePath The path to the file that will be created
     * @param data The encrypted data that will be written into the file
     * @throws IOException Can throw IOException
     */
    public void writeEncryptedFile(String outputFilePath, ArrayList data) throws IOException {

        file = new File(outputFilePath);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            ConsoleInterface.displayMessage("File already exists");
            return;
        }
        fileOutputStream = new FileOutputStream(file);

        dataOutputStream = new DataOutputStream(fileOutputStream);

        for (Object d : data) {
            int i = (short) d;
            dataOutputStream.writeShort(i);
        }
        dataOutputStream.close();
    }

    /** Method used to create and write decrypted information into a file.
     * 
     * @param outputFilePath The path to the file that will be created
     * @param data The decrypted data that will be written into the file
     * @throws IOException Can throw IOException
     */
    public void writeDecryptedFile(String outputFilePath, ArrayList data) throws IOException {

        file = new File(outputFilePath);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            ConsoleInterface.displayMessage("File already exists");
            return;
        }
        fileOutputStream = new FileOutputStream(file);

        dataOutputStream = new DataOutputStream(fileOutputStream);

        for (Object d : data) {
            dataOutputStream.write((int)d);
        }
        dataOutputStream.close();
    }
}
