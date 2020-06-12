package com.payno.guides.utils.netty.framework.demos.faststart;

import com.payno.guides.utils.netty.base.demos.base.io.IoServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author payno
 * @date 2020/6/12 09:49
 * @description
 */
@Slf4j
public class ClientFastStart {

    static class LogHandler extends ChannelDuplexHandler{

        protected String format(ChannelHandlerContext ctx, String eventName) {
            String chStr = ctx.channel().toString();
            return new StringBuilder(chStr.length() + 1 + eventName.length())
                    .append(chStr)
                    .append(' ')
                    .append(eventName)
                    .toString();
        }

        protected String format(ChannelHandlerContext ctx, String eventName, Object firstArg, Object secondArg) {
            if (secondArg == null) {
                return formatSimple(ctx, eventName, firstArg);
            }

            String chStr = ctx.channel().toString();
            String arg1Str = String.valueOf(firstArg);
            String arg2Str = secondArg.toString();
            StringBuilder buf = new StringBuilder(
                    chStr.length() + 1 + eventName.length() + 2 + arg1Str.length() + 2 + arg2Str.length());
            buf.append(chStr).append(' ').append(eventName).append(": ").append(arg1Str).append(", ").append(arg2Str);
            return buf.toString();
        }

        private static String formatByteBuf(ChannelHandlerContext ctx, String eventName, ByteBuf msg) {
            String chStr = ctx.channel().toString();
            int length = msg.readableBytes();
            if (length == 0) {
                StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 4);
                buf.append(chStr).append(' ').append(eventName).append(": 0B");
                return buf.toString();
            } else {
                int rows = length / 16 + (length % 15 == 0? 0 : 1) + 4;
                StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + 10 + 1 + 2 + rows * 80);

                buf.append(chStr).append(' ').append(eventName).append(": ").append(length).append('B').append(NEWLINE);
                appendPrettyHexDump(buf, msg);

                return buf.toString();
            }
        }

        private static String formatByteBufHolder(ChannelHandlerContext ctx, String eventName, ByteBufHolder msg) {
            String chStr = ctx.channel().toString();
            String msgStr = msg.toString();
            ByteBuf content = msg.content();
            int length = content.readableBytes();
            if (length == 0) {
                StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + msgStr.length() + 4);
                buf.append(chStr).append(' ').append(eventName).append(", ").append(msgStr).append(", 0B");
                return buf.toString();
            } else {
                int rows = length / 16 + (length % 15 == 0? 0 : 1) + 4;
                StringBuilder buf = new StringBuilder(
                        chStr.length() + 1 + eventName.length() + 2 + msgStr.length() + 2 + 10 + 1 + 2 + rows * 80);

                buf.append(chStr).append(' ').append(eventName).append(": ")
                        .append(msgStr).append(", ").append(length).append('B').append(NEWLINE);
                appendPrettyHexDump(buf, content);

                return buf.toString();
            }
        }

        private static String formatSimple(ChannelHandlerContext ctx, String eventName, Object msg) {
            String chStr = ctx.channel().toString();
            String msgStr = String.valueOf(msg);
            StringBuilder buf = new StringBuilder(chStr.length() + 1 + eventName.length() + 2 + msgStr.length());
            return buf.append(chStr).append(' ').append(eventName).append(": ").append(msgStr).toString();
        }

        protected String format(ChannelHandlerContext ctx, String eventName, Object arg) {
            if (arg instanceof ByteBuf) {
                return formatByteBuf(ctx, eventName, (ByteBuf) arg);
            } else if (arg instanceof ByteBufHolder) {
                return formatByteBufHolder(ctx, eventName, (ByteBufHolder) arg);
            } else {
                return formatSimple(ctx, eventName, arg);
            }
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "REGISTERED"));
            ctx.fireChannelRegistered();
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "UNREGISTERED"));
            ctx.fireChannelUnregistered();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "ACTIVE"));
            ctx.fireChannelActive();
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "INACTIVE"));
            ctx.fireChannelInactive();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info(format(ctx, "EXCEPTION",cause),cause);
            ctx.fireExceptionCaught(cause);
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            log.info(format(ctx, "USER_EVENT",evt));
            ctx.fireUserEventTriggered(evt);
        }

        @Override
        public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "BIND",localAddress));
            ctx.bind(localAddress, promise);
        }

        @Override
        public void connect(
                ChannelHandlerContext ctx,
                SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "CONNECT",remoteAddress,localAddress));
            ctx.connect(remoteAddress, localAddress, promise);
        }

        @Override
        public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "DISCONNECT"));
            ctx.disconnect(promise);
        }

        @Override
        public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "CLOSE"));
            ctx.close(promise);
        }

        @Override
        public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "DEREGISTER"));
            ctx.deregister(promise);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "READ COMPLETE"));
            ctx.fireChannelReadComplete();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info(format(ctx, "READ",msg));
            ctx.fireChannelRead(msg);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info(format(ctx, "WRITE",msg));
            ctx.write(msg, promise);
        }

        @Override
        public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "WRITABILITY CHANGED"));
            ctx.fireChannelWritabilityChanged();
        }

        @Override
        public void flush(ChannelHandlerContext ctx) throws Exception {
            log.info(format(ctx, "FLUSH"));
            ctx.flush();
        }
    }

    public static void main(String[] args) throws Exception{
        IoServer.main(new String[]{});
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup threads = new NioEventLoopGroup();
        Channel channel=bootstrap.group(threads).handler(new LogHandler())
                .channel(NioSocketChannel.class)
                .connect("127.0.0.1",8000)
                .sync().channel();
        ChannelFuture lastWriteFuture =channel.writeAndFlush(Unpooled.wrappedBuffer("hello".getBytes()));
        lastWriteFuture.sync();
        threads.shutdownGracefully();
    }
}
