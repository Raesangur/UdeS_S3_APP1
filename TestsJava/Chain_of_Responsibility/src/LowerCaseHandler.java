public class LowerCaseHandler extends Handler{

    @Override
    public void execute(String password) {
        if (password.matches(".*[a-z].*")) {
            pass(password);
        }
        else {
            System.out.println("Your password needs at least 1 lowercase letter");
        }
    }
}
