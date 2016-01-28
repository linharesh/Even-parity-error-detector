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

    public void decript(String inputFilepath, String outputFilepath, int[] keys) throws IOException {
        ArrayList<Integer> encriptedData = this.readInputFile(inputFilepath) ;
        ArrayList decriptedData ;
        decriptedData = new ArrayList<>();
        
        for (Integer data : encriptedData){
            decriptedData.add(BinaryExponent.binExp(data, keys[1], keys[0]));
        }
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeEncriptedFile(outputFilepath, decriptedData);
    }

    private ArrayList<Integer> readInputFile(String inputFilePath) throws IOException {

        file = new File(inputFilePath);
        fileInputStream = new FileInputStream(file);
        System.out.println("Total file size to encode (in bytes) : " + fileInputStream.available());

           int content;
        ArrayList fileData = new ArrayList<Integer>();

        DataInputStream in = new DataInputStream(new FileInputStream(inputFilePath));
        
        while ((content = in.readInt()) != -1) {
            System.out.println((int) content);
            if (((int) content) > 255 ){
                System.out.println("Leitura maior que 255");
            }
            fileData.add((int) content);
        }

        fileInputStream.close();
        return fileData;
    }


}
