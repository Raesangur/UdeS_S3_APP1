import java.util.ArrayList;

public class Composite implements IComponent{
    private ArrayList<IComponent> components;

    public Composite() {
        components = new ArrayList<IComponent>();
    }

    public void add(IComponent c) {
        components.add(c);
    }

    @Override
    public void execute() {
        for (IComponent component : components) {
            component.execute();
        }
    }
}
