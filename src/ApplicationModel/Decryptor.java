/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationModel;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HenriqueLinhares
 */
public class Decryptor {
    
    private File file;
    private FileInputStream fileInputStream;
    private DataInputStream dataInputStream;

    public void decript(String inputFilepath, String outputFilepath, int[] keys) throws IOException {
        ArrayList<Integer> encriptedData = this.readInputFile(inputFilepath) ;
        ArrayList decriptedData ;
        decriptedData = new ArrayList<>();
        
        for (Integer data : encriptedData){
            decriptedData.add(BinaryExponent.binExp(data, keys[1], keys[0]));
        }
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeDecryptedFile(outputFilepath, decriptedData);
    }

    private ArrayList<Integer> readInputFile(String inputFilePath) throws IOException {

        file = new File(inputFilePath);
        fileInputStream = new FileInputStream(file);
        int fileSize = fileInputStream.available() ;
        System.out.println("Total file size to decrypt (in bytes) : " + fileSize);
        
        int content;
        ArrayList fileData = new ArrayList<Integer>();

        dataInputStream = new DataInputStream(new FileInputStream(inputFilePath));
        
        while (fileSize > 0) {
            content = dataInputStream.readShort();
            fileSize = fileSize - 2 ;
            System.out.println((int) content);
            fileData.add((int) content);
        }

        fileInputStream.close();
        return fileData;
    }


}
