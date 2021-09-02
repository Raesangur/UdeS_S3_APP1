package testsjava.factory;

public class ProgrammingLanguageC implements IProgrammingLanguage{

    @Override
    public ProgramC helloWorld() {
        String helloWorldProgram = "#include <stdio.h>\n" +
                "\n" +
                "int main(int argc, char** argv)\n" +
                "{\n" +
                "    printf(\" Bon matin \");\n" +
                "    return 0;\n" +
                "}\n";
        return new ProgramC(helloWorldProgram);
    }

    @Override
    public ProgramC addition() {
        return new ProgramC();
    }

    @Override
    public ProgramC quadratic() {
        return new ProgramC();
    }
}
