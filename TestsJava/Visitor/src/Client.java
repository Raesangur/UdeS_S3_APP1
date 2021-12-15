public class Client {

    public static void main(String[] args) {
        IElement[] list = new IElement[]{new ElementA(), new ElementA(), new ElementB(), new ElementC(), new ElementB()};
        VisitorPascal pascal = new VisitorPascal();

        for (IElement elem : list) {
            elem.accept(pascal);
        }
    }
}
