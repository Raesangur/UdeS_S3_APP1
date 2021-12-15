public class Client {

    public static void main(String[] args) {
        Composite composite1 = new Composite();
        composite1.add(new Leaf());
        composite1.add(new Leaf());
        composite1.add(new Leaf());
        composite1.add(new Leaf());

        Composite composite2 = new Composite();
        composite2.add(new Leaf());
        composite2.add(new Leaf());
        composite2.add(new Leaf());

        composite1.add(composite2);
        composite1.execute();
    }
}
