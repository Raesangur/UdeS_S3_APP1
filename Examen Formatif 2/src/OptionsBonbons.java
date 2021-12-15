public class OptionsBonbons extends Options {
    public OptionsBonbons(ICremeGlacee wrappee) {
        super(wrappee);
    }

    @Override
    public double prix() {
        return 0.50 + super.prix();
    }
}
