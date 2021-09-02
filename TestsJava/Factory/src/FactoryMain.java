/*
 This test program is an example usage of a Factory design pattern in Java.
 I have a programming_language interface that allows for different language factories, allowing to create basics
 programs in C, C++, Java and Python.
 */
package testsjava.factory;

public class FactoryMain {

    public static void main(String[] args) {
        IProgrammingLanguage pl = new ProgrammingLanguageJava();
        Program helloWorld = pl.helloWorld();
        System.out.println(helloWorld);
    }
}