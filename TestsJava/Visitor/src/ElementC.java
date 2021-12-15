public class ElementC implements IElement {
    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }
}
