package CodeInitial;

import CodeInitial.CremeGlacee.*;
import CodeInitial.Options.*;


public class Client {
    public static void main(String args[]) {
        CremeGlacee chocolat = new Chocolat();
        System.out.println(chocolat);
        Options enrobage = new Enrobage();

        chocolat.addOption(enrobage);
        System.out.println(chocolat);

        CremeGlacee vanille = new Vanille();
        System.out.println(vanille);
        Options siropErable = new SiropErable();
        vanille.addOption(siropErable);
        System.out.println(vanille);
    }
}
