package com.nurashi.algos;

import com.nurashi.algos.cli.Runner;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: <algorithm> <n> [k]");
            System.exit(1);
        }
        Runner.printHeader();
        Runner.run(args);
    }
}