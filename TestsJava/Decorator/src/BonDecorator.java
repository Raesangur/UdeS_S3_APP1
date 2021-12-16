public class BonDecorator extends BaseDecorator {
    public BonDecorator(IComponent c) {
        super(c);
    }

    public void execute() {
        System.out.println("Bon");
        super.execute();
    }
}
