abstract class State {
    protected SomeClass object;

    public State(SomeClass o) {
        object = o;
    }

    public abstract void start();
    public abstract void stop();
}
