public class NumberHandler extends Handler{
    @Override
    public void execute(String password) {
        if (password.matches(".*[0-9].*")) {
            pass(password);
        }
        else {
            System.out.println("Your password needs at least 1 number");
        }
    }
}
