public class UpperCaseHandler extends Handler {
    @Override
    public void execute(String password) {
        if (password.matches(".*[A-Z].*")) {
            pass(password);
        }
        else {
            System.out.println("Your password needs at least 1 uppercase letter");
        }
    }
}
