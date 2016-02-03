package ApplicationModel;

/** Class used to represent a pair of RSA keys
 *
 * @author Henrique Linhares ; Raphael Quintanilha ; Pablo Curty ; Filipe Coimbra
 */
public class RSAKeys {

    private int NKey;
    private int EorDKey;

    public RSAKeys(int publicKey, int privateKey) {
        this.NKey = publicKey;
        this.EorDKey = privateKey;
    }

    public int getNKey() {
        return NKey;
    }

    public int getEorDKey() {
        return EorDKey;
    }

    /** A N-Key could be any number between 255 and 65536
     *  This method checks if the number could be a N-Key
     * 
     * @param pKey The number to be validated
     * @return True if it can be a N-Key ; False if it cant
     */
    public static boolean nKeyValidator(int pKey) {
        if (pKey > 255 && pKey < 65536) {
            return true;
        }
        return false;
    }

}
