public class Client {
    public static void main(String[] args) throws Exception {

        Subject subject = (Subject) MyDubboClient.createInstance(Subject.class);
        System.out.println(subject.rent());
        System.out.println(subject.hello("world"));


    }
}
