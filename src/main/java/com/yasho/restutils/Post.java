package com.yasho.restutils;

import io.undertow.server.HttpServerExchange;
import org.xnio.Pooled;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Post {

    public static String parseRequestBody(HttpServerExchange exchange) throws IOException {
        Pooled<ByteBuffer> pooledByteBuffer = exchange.getConnection().getBufferPool().allocate();
        ByteBuffer byteBuffer = pooledByteBuffer.getResource();
        int limit = byteBuffer.limit();
        byteBuffer.clear();
        exchange.getRequestChannel().read(byteBuffer);
        int pos = byteBuffer.position();
        byteBuffer.rewind();
        byte[] bytes = new byte[pos];
        byteBuffer.get(bytes);
        String requestBody = new String(bytes, Charset.forName("UTF-8") );
        byteBuffer.clear();
        pooledByteBuffer.free();
        return requestBody;
    }

}
