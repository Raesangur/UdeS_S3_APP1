public class MatinDecorator extends BaseDecorator{
    public MatinDecorator(IComponent c) {
        super(c);
    }

    public void execute() {
        System.out.println("matin");
        super.execute();
    }
}
