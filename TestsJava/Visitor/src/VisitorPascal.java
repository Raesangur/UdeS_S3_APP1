public class VisitorPascal implements IVisitor {
    @Override
    public void visit(ElementA a) {
        System.out.println("Bon matin A");
    }

    @Override
    public void visit(ElementB b) {
        System.out.println("Bon matin B");
    }

    @Override
    public void visit(ElementC c) {
        System.out.println("Bon matin C");
    }
}
