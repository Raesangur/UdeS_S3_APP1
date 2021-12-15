public class OptionsSiropErable extends Options {

    public OptionsSiropErable(ICremeGlacee wrappee) {
        super(wrappee);
    }

    @Override
    public double prix() {
        return 0.75 + super.prix();
    }
}
