public class ReadyState extends State {
    public ReadyState(SomeClass o) {
        super(o);
    }

    @Override
    public void start() {
        // Already started
        object.change_state(new ErrorState(object));
    }

    @Override
    public void stop() {
        object.change_state(new WaitingState(object));
    }
}
