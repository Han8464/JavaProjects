import MyDubbo.DubboService;

@DubboService(interfaceName = "MySubject")
public class RealSubject implements MySubject {
    @Override
    public String rent() {
        return "at server I want to rent my house ";
    }

    @Override
    public String hello(String str) {
        return "Hello " + str;
    }
}
