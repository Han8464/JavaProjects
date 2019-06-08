import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MsgHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext context;
    private Object rcv;
    private ChannelPromise promise;
    private Map<UUID, ChannelPromise> idPromiseMap;
    public Object getRcv() {
        return rcv;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
        idPromiseMap = new HashMap<UUID, ChannelPromise>();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        rcv = msg;
        promise.setSuccess();
    }

    public ChannelPromise sendMsg(Object msg, UUID id) throws IOException {
        if (context == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ByteBuf byteBuf = context.alloc().buffer();
        byteBuf.writeZero(4);
        ObjectOutputStream stream = new ObjectOutputStream(new ByteBufOutputStream(byteBuf));
        stream.writeObject(msg);
        byteBuf.setInt(0, byteBuf.readableBytes() - 4);

        promise = context.channel().newPromise();
        idPromiseMap.put(id, promise);
        context.writeAndFlush(byteBuf);
        return promise;
    }
}
