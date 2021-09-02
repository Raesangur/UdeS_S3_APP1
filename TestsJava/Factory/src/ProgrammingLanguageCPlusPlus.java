package testsjava.factory;

public class ProgrammingLanguageCPlusPlus implements IProgrammingLanguage {

    @Override
    public ProgramCPlusPlus helloWorld() {
        String helloWorldProgram = "#include <iostream>\n" +
                "\n" +
                "int main(int argc, char** argv)\n" +
                "{\n" +
                "    std::cout << \"Bon matin\" << std::endl;\n" +
                "    return 0;\n" +
                "}\n";
        return new ProgramCPlusPlus(helloWorldProgram);
    }

    @Override
    public ProgramCPlusPlus addition() {
        return new ProgramCPlusPlus();
    }

    @Override
    public ProgramCPlusPlus quadratic() {
        return new ProgramCPlusPlus();
    }
}
