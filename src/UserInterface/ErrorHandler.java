package UserInterface;

import java.util.ArrayList;

/**
 * Class responsable for handling errors that can happen in the execution of the
 * software
 *
 * @author Henrique Linhares ; Raphael Quintanilha, Pablo Curty, Felipe Coimbra
 */
public class ErrorHandler {

    /**
     * Call this method when you try to read a coded block that is smaller than
     * 3 bytes. All of the coded blocks must have at least 3 bytes : 2 for
     * parity bits and at least 1 for the block itself
     *
     * This method will display an error message and finish the execution
     *
     */
    public static void readingBlockSmallerThan3Bytes() {
        System.out.println("Fatal error : Trying to read a block smaller than 3 byes.");
        System.out.println("Aborting execution...");
        System.exit(0);
    }

    /**
     * Method to be used when you have a block with parity bits, and the parity
     * bits dont check correclty and the algorith cant fix the problem
     *
     * This method will display an error message and finish the execution
     */
    public static void couldNotFixParityBitsError() {
        System.out.println("Fatal error : Could not fix a parity error");
        System.out.println("Aborting execution...");
        System.exit(0);
    }


}
