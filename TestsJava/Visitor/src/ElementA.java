public class ElementA implements IElement{

    @Override
    public void accept(IVisitor v) {
        v.visit(this);
    }
}
