public class Client {

    public static void main(String[] args) {

        ICremeGlacee myIceCream = new CremeGlaceeChocolat();

        myIceCream = new OptionsEnrobage(myIceCream);
        myIceCream = new OptionsSiropErable(myIceCream);

        System.out.println(myIceCream.prix());
    }
}
