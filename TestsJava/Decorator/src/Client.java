public class Client {

    public static void main(String[] args) {
        IComponent component = new ConcreteComponent();
        component = new BonDecorator(component);
        component = new MatinDecorator(component);
        component = new BonDecorator(component);

        component.execute();
    }
}
