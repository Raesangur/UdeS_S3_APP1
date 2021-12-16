public class WaitingState extends State{
    public WaitingState(SomeClass o) {
        super(o);
    }

    @Override
    public void start() {
        object.change_state(new ReadyState(object));
    }

    @Override
    public void stop() {
        // Already stopped
        object.change_state(new ErrorState(object));
    }
}
