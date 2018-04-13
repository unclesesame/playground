package com.abner.playground.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
	public static void main(String[] args) throws IOException {
		RandomAccessFile accessFile = new RandomAccessFile("c:/tmp/123.txt", "rw");
		FileChannel fileChannel = accessFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(2048);//2048 byte的数组缓冲区
		
		int byteLength = fileChannel.read(buffer);
		System.out.println("byte length : " + byteLength);
		
		while(byteLength != -1) {
			buffer.flip();
			while(buffer.hasRemaining()){
				System.out.println((char)buffer.get());
			}
			buffer.compact();
			byteLength = fileChannel.read(buffer);
		}
	}
}
