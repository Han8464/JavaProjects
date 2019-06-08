
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private MyNettyClient client;
    public DynamicProxy() throws Exception {
    client = new MyNettyClient();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getSimpleName(), method.getName(), args);

        client.sendMessaqe(rpcRequest, rpcRequest.getUuid()).sync();
        RpcResponse rpcResponse = (RpcResponse)client.getMessage();

        return rpcResponse.getObject();
    }
}
