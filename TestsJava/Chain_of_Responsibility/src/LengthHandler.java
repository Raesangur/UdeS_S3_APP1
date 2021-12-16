public class LengthHandler extends Handler {
    @Override
    public void execute(String password) {
        if (password.length() >= 8) {
            pass(password);
        }
        else {
            System.out.println("Your password needs to be at least 8 characters long");
        }
    }
}
