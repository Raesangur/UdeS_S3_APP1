package SolutionFormatif;

import CodeInitial.CremeGlacee.CremeGlacee;
import CodeInitial.CremeGlacee.Vanille;
import CodeInitial.Options.SiropErable;
import SolutionFormatif.CremeGlacee.*;
import SolutionFormatif.Options.*;

public class Client {
    public static void main(String args[]) {

        CremeGlacee chocolat = new CodeInitial.CremeGlacee.Chocolat();
        System.out.println(chocolat);
        CodeInitial.Options.Options enrobage = new CodeInitial.Options.Enrobage();

        chocolat.addOption(enrobage);
        System.out.println(chocolat);

        CremeGlacee vanille = new Vanille();
        System.out.println(vanille);
        CodeInitial.Options.Options siropErable = new SiropErable();
        vanille.addOption(siropErable);
        System.out.println(vanille);
    }
}
