package com.nurashi.algos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void write(String filePath, List<String[]> rows) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for(String[] row : rows) {
                    writer.write(String.join(",", row));
                    writer.write("\n");
            }
        }
    }
}
