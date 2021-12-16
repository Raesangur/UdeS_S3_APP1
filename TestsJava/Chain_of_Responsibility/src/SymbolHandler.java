public class SymbolHandler extends Handler{
    @Override
    public void execute(String password) {
        if (password.matches(".*[!@#$%?&*():;.,/_{}].*")) {
            pass(password);
        }
        else {
            System.out.println("Your password needs at least 1 special symbol");
        }
    }
}
