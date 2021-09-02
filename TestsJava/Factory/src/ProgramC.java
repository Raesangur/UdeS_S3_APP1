package testsjava.factory;

public class ProgramC extends Program {
    public ProgramC() {
        this.comment = "/**/";  // ANSI-C:89 only supports /* */-style comments and not // comments
    }
    public ProgramC(String code) {
        this.code = code;
    }
}
