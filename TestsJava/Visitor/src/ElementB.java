public class ElementB implements IElement{
    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }
}
