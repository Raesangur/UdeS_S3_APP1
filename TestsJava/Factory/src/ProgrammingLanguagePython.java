package testsjava.factory;

public class ProgrammingLanguagePython implements IProgrammingLanguage{

    @Override
    public ProgramPython helloWorld() {
        return new ProgramPython("print(\"Bon matin\")");
    }

    @Override
    public ProgramPython addition() {
        return new ProgramPython();
    }

    @Override
    public ProgramPython quadratic() {
        return new ProgramPython();
    }
}
