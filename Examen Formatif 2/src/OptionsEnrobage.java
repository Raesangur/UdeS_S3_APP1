public class OptionsEnrobage extends Options {
    public OptionsEnrobage(ICremeGlacee wrappee) {
        super(wrappee);
    }

    @Override
    public double prix() {
        return 0.30 + super.prix();
    }
}
