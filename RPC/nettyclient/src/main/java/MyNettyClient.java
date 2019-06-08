
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.util.UUID;

public class MyNettyClient {

    private MsgHandler handler = new MsgHandler();

    public MyNettyClient() throws Exception
    {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ClientInitializer(handler));
        b.connect("127.0.0.1", 8088).sync();
    }

    public ChannelPromise sendMessaqe(Object msg, UUID id) throws IOException {
        return handler.sendMsg(msg, id);
    }

    public Object getMessage() {
        return handler.getRcv();
    }
}
