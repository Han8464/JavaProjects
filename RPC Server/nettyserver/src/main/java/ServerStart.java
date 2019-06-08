import MyDubbo.MsgServerDecoder;
import MyDubbo.MsgServerHandler;
import MyDubbo.MyDubboServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

public class ServerStart {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        String pkgName = ServerStart.class.getPackage().getName();
        MyDubboServer myDubboServer = new MyDubboServer(pkgName);
        myDubboServer.StartServer(8088);
    }

}
