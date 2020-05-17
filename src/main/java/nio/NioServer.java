package nio;

import com.google.common.base.Charsets;
import io.netty.channel.local.LocalAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author linckye
 */
@Data
@Accessors(chain = true)
public class NioServer {


    Integer port;

    public void start() throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress("127.0.0.1", port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel client = channel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
//                    key.interestOps(SelectionKey.OP_ACCEPT);
                }

                if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    StringBuilder builder = new StringBuilder();
                    while (client.read(buffer) > 0) {
                        buffer.flip();
                        builder.append(StandardCharsets.UTF_8.decode(buffer));
                    }
                    System.out.println(builder.toString());
                }

                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NioServer().setPort(8888)
                .start();
    }

}
