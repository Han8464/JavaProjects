package MyDubbo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.lang.reflect.AnnotatedWildcardType;
import java.util.HashMap;
import java.util.List;

public class MyDubboServer {
    public static ServiceMap serviceMap;

    public MyDubboServer(String pkgname) throws IOException, ClassNotFoundException {
        this.serviceMap = new ServiceMap();
        PkgScanner scanner = new PkgScanner(pkgname);
        List<String> classNames = scanner.scan();
        for(String className: classNames)
        {
            Class impClass = Class.forName(className);
            ServiceAnnoUtil.getObjectInfo(impClass);
        }
    }

    public void StartServer(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //.childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MsgServerDecoder(), new MsgServerHandler());
                        }
                    });
            //.option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
