public class ErrorState extends State{
    public ErrorState(SomeClass o) {
        super(o);
    }

    @Override
    public void start() {
        // Do nothing
    }

    @Override
    public void stop() {
        // Reset
        object.change_state(new WaitingState(object));
    }
}
