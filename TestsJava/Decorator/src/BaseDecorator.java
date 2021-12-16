abstract class BaseDecorator implements IComponent {
    private IComponent wrapee;

    public BaseDecorator(IComponent c) {
        wrapee = c;
    }

    public void execute() {
        wrapee.execute();
    }
}
