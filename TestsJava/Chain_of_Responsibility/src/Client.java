public class Client {

    public static void main(String[] args) {
        String password1 = "admin";
        String password2 = "adminadmin";
        String password3 = "Password";
        String password4 = "Password1";
        String password5 = "Password1!";

        LengthHandler lengthHandler = new LengthHandler();
        LowerCaseHandler lowerCaseHandler = new LowerCaseHandler();
        UpperCaseHandler upperCaseHandler = new UpperCaseHandler();
        NumberHandler numberHandler = new NumberHandler();
        SymbolHandler symbolHandler = new SymbolHandler();

        lengthHandler.set_next(lowerCaseHandler);
        lowerCaseHandler.set_next(upperCaseHandler);
        upperCaseHandler.set_next(numberHandler);
        numberHandler.set_next(symbolHandler);

        System.out.println(password1);
        lengthHandler.execute(password1);
        System.out.println(password2);
        lengthHandler.execute(password2);
        System.out.println(password3);
        lengthHandler.execute(password3);
        System.out.println(password4);
        lengthHandler.execute(password4);
        System.out.println(password5);
        lengthHandler.execute(password5);
    }
}
