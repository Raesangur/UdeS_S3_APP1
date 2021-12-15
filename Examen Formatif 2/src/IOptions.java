abstract class Options implements ICremeGlacee {
    private ICremeGlacee wrappee;

    public Options(ICremeGlacee wrappee) {
        this.wrappee = wrappee;
    }

    public double prix() {
        return wrappee != null ? wrappee.prix() : 0.0;
    }
}
