package testsjava.factory;

public class Program {
    protected String code;
    protected String comment;

    public Program() {
        // Nothing here
    }

    Program(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
