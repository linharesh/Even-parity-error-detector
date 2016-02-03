package redes2.trabalho2;


import UserInterface.ConsoleInterface;
import java.io.IOException;

/** Class that launch the application
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class Launcher {

   /** Launch the application
    * 
    * @param args
    * @throws IOException 
    */
    public static void main(String[] args) throws IOException {
        ConsoleInterface console = new ConsoleInterface();
        console.launchConsoleInterface();
    }
    
}
