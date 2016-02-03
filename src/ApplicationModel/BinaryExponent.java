package ApplicationModel;

/** Class responsable for the Binary Exponential calculum.
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class BinaryExponent {

    /**This method implements the Binarty Exponential calculum.
     * 
     * @param b Base
     * @param e Expoent
     * @param n Modulum
     * @return Result
     */
    public static short binExp(short b, int e, short n) {

        int res = b;
        int y = 1;
        if (e == 0) {
            return 1;
        }
        while (e > 1) {
            if (e % 2 != 0) {
                y = (y * res) % (int) n;
                e = e - 1;
            }
            res = (res * res) % ((int) n);
            e = e / 2;
        }
        return ((short) ((res * y) % n));
    }

}
