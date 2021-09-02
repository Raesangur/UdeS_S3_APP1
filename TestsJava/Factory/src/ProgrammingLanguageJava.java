package testsjava.factory;

public class ProgrammingLanguageJava implements IProgrammingLanguage {

    @Override
    public ProgramJava helloWorld() {
        String helloWorldProgram = "/*\n" +
                "This test program is an example usage of a Factory design pattern in Java.\n" +
                "I have a programming_language interface that allows for different language factories, allowing to create basics\n" +
                "programs in C, C++, Java and Python.\n" +
                "*/\n" +
                "package testsjava.factory;\n" +
                "\n" +
                "public class FactoryMain {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Bon matin\");\n" +
                "    }\n" +
                "}\n";
        return new ProgramJava(helloWorldProgram);
    }

    @Override
    public ProgramJava addition() {
        return new ProgramJava();
    }

    @Override
    public ProgramJava quadratic() {
        return new ProgramJava();
    }
}
