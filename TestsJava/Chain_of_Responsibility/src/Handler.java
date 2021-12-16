abstract class Handler {
    private Handler next;

    public void set_next(Handler h) {
        next = h;
    }

    protected void pass(String password) {
        if (next == null) {
            System.out.println("Your password is secure!");
        } else {
            next.execute(password);
        }
    }

    public abstract void execute(String password);
}
