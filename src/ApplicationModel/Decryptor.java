package ApplicationModel;

import UserInterface.ConsoleInterface;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/** Class responsable for the RSA Decryption process
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class Decryptor {
    
    private File file;
    private FileInputStream fileInputStream;
    private DataInputStream dataInputStream;

    /** Method used to decrypt a RSA-encrypted file
     * 
     * @param inputFilepath Path to the RSA-encrypted file
     * @param outputFilepath Path to the (non-existent) decrypted file that will be created
     * @param keys An instance of RSAKeys containing the keys to decrypt
     * @throws IOException Can throw IOException
     */
    public void decript(String inputFilepath, String outputFilepath, RSAKeys keys) throws IOException {
        ArrayList<Integer> encriptedData = this.readInputFile(inputFilepath) ;
        ArrayList decriptedData ;
        decriptedData = new ArrayList<>();
        
        for (Integer data : encriptedData){
            decriptedData.add((int) BinaryExponent.binExp(data.shortValue(), keys.getEorDKey(), ((Integer)keys.getNKey()).shortValue()));
        }
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeDecryptedFile(outputFilepath, decriptedData);
    }

    /** Method used to read the encrypted file, and load it to the memory
     * 
     * @param inputFilePath Path to the RSA-encrypted file
     * @return An ArrayList with the bytes of the encrypted file
     * @throws IOException Can throw IOException
     */
    private ArrayList<Integer> readInputFile(String inputFilePath) throws IOException {

        file = new File(inputFilePath);
        fileInputStream = new FileInputStream(file);
        int fileSize = fileInputStream.available() ;
        ConsoleInterface.displayMessage("Total file size to decrypt (in bytes) : " + fileSize);
        
        if (fileSize % 2 != 0){
            ConsoleInterface.displayMessage("Error : the number of bytes in the encrypted file is not even");
            ConsoleInterface.displayMessage("Execution aborted");
            System.exit(0);
        }
        
        int content;
        ArrayList fileData = new ArrayList<Integer>();
        dataInputStream = new DataInputStream(new FileInputStream(inputFilePath));
        
        while (fileSize > 0) {
            content = dataInputStream.readShort();
            fileSize = fileSize - 2 ;
            fileData.add((int) content);
        }

        fileInputStream.close();
        return fileData;
    }
    
}
