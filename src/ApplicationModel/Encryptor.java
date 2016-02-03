package ApplicationModel;

import UserInterface.ConsoleInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/** Class responsable for the RSA encryption process
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class Encryptor {

    private File file;
    private FileInputStream fileInputStream;

    /** Method used to encrypt any file into an RSA-encrypted file
     * 
     * @param inputFilepath The path to the file that will be encrypted
     * @param outputFilepath The path to the encrypted file that will be created
     * @param keys An instance of RSAKeys with the keys to encrypt
     * @throws IOException Can Throw IOException
     */
    public void encript(String inputFilepath, String outputFilepath, RSAKeys keys) throws IOException {
        ArrayList<Integer> fileData = this.readInputFile(inputFilepath) ;
        ArrayList encriptedData ;
        encriptedData = new ArrayList<>();
        
        for (Integer data : fileData){
            encriptedData.add(BinaryExponent.binExp(data.shortValue(),keys.getEorDKey(), ((Integer)keys.getNKey()).shortValue()));
        }
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeEncryptedFile(outputFilepath, encriptedData);
    }

    /** Method used to read a file and load all the bytes to the memory
     * 
     * @param inputFilePath The path to the file that will be read
     * @return An ArrayList with the bytes of the file
     * @throws IOException Can throw IOException
     */
    private ArrayList<Integer> readInputFile(String inputFilePath) throws IOException {

        file = new File(inputFilePath);
        fileInputStream = new FileInputStream(file);
        ConsoleInterface.displayMessage("Total file size to encrypt (in bytes) : " + fileInputStream.available());

        int content;
        ArrayList fileData = new ArrayList<Integer>();

        while ((content = fileInputStream.read()) != -1) {
            if (((int) content) > 255 ){
                ConsoleInterface.displayMessage("Error : Block bigger than 255 in the input file.");
                ConsoleInterface.displayMessage("Execution aborted");
                System.exit(0);
            }
            fileData.add((int) content);
        }

        fileInputStream.close();
        return fileData;
    }
    


}
