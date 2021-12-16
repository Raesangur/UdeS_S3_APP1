public class Client {

    public static void main(String[] args) {
        SomeClass object = new SomeClass();

        System.out.println(object.is_error());
        object.start();
        object.start();
        System.out.println(object.is_error());
        object.stop();
        System.out.println(object.is_error());
    }
}
