/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationModel;

/**
 *
 * @author HenriqueLinhares
 */
public class BinaryExponent {

    public static int binExp(int b, int e, int n) {

        int res = b;
        int y = 1;

        //Caso base
        if (e == 0) {
            return 1;
        }

        while (e > 1) {

            if (e % 2 != 0) {
                /*Caso especial : expoente é ímpar
                 Acumular uma potência de 'res' em 'y'
                 */
                y = (y * res) % (int) n;
                e = e - 1;
            }

            //Elevamos 'res' ao quadrado, dividios expoente por 2
            res = (res * res) % ((int) n);
            e = e / 2;

        }
        return ((int) ((res * y) % n));
    }

}
