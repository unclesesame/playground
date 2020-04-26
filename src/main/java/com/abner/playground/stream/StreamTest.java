package com.abner.playground.stream;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
/*
 try-with-resources
 */
        try (Stream<String> in = Files.lines(Paths.get("StreamTest.java"));
             PrintWriter outfile = new PrintWriter("Results.txt")) {
            in.skip(5).limit(1).map(String::toLowerCase).forEachOrdered(outfile::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
