public class SomeClass {
    private State state = new WaitingState(this);

    public void change_state(State s) {
        state = s;
    }

    public void start() {
        state.start();
    }
    public void stop() {
        state.stop();
    }

    public boolean is_error() {
        return state instanceof ErrorState;
    }
}
