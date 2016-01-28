/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HenriqueLinhares
 */
public class Encryptor {

    private File file;
    private FileInputStream fileInputStream;

    public void encript(String inputFilepath, String outputFilepath, int[] keys) throws IOException {
        ArrayList<Integer> fileData = this.readInputFile(inputFilepath) ;
        ArrayList encriptedData ;
        encriptedData = new ArrayList<>();
        
        for (Integer data : fileData){
            encriptedData.add(BinaryExponent.binExp(data, keys[1], keys[0]));
        }
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeEncriptedFile(outputFilepath, encriptedData);
    }

    private ArrayList<Integer> readInputFile(String inputFilePath) throws IOException {

        file = new File(inputFilePath);
        fileInputStream = new FileInputStream(file);
        System.out.println("Total file size to encode (in bytes) : " + fileInputStream.available());

        int content;
        ArrayList fileData = new ArrayList<Integer>();

        while ((content = fileInputStream.read()) != -1) {
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
