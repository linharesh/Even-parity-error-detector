/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationModel;

import UserInterface.ConsoleInterface;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HenriqueLinhares
 */
public class OutputFileWriter {
    
    private File file;
    private FileOutputStream fileOutputStream;
    
    public void writeEncriptedFile(String outputFilePath, ArrayList data) throws IOException{
        file = new File(outputFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }else{
        	ConsoleInterface.displayMessage("File already exists");
        	return;
        }
        fileOutputStream = new FileOutputStream(file); 
    
        DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFilePath));
      
        for (Object d : data){
            int i = (Integer)d;
            System.out.println(i);
            out.writeInt(i);
        }
        out.close();
        fileOutputStream.close();
    
    }
    
    
    
}
