package Tentative2;

import Tentative2.CremeGlacee.*;
import Tentative2.Options.*;
import Tentative2.Produit;


public class Client {
    public static void main(String args[]) {
        Produit chocolat = new Chocolat();
        System.out.println(chocolat);
        Produit enrobage = new Enrobage(chocolat);
        System.out.println(enrobage);
        Produit siropErable = new SiropErable(enrobage);
        System.out.println(siropErable);

        /*CremeGlacee vanille = new Vanille();
        System.out.println(vanille);
        Option siropErable = new SiropErable();
        vanille.addOption(siropErable);
        System.out.println(vanille);*/
    }
}
