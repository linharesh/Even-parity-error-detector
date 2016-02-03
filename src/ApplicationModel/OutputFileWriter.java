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
    private DataOutputStream dataOutputStream;

    public void writeEncriptedFile(String outputFilePath, ArrayList data) throws IOException {

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
            System.out.println(i);
            dataOutputStream.writeShort(i);
        }
        dataOutputStream.close();
    }

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
            short i = ((Integer) d).shortValue();
            System.out.println(i);
            dataOutputStream.write(i);
        }
        dataOutputStream.close();
    }
}
