package com.nurashi.algos.util;

import org.junit.jupiter.api.Test;

import com.nurashi.algos.util.metrics.CsvWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvWriterTest {

    @Test
    void testCsvWriter() throws IOException {
        Path tempFile = Files.createTempFile("metrics", ".csv");

        List<String[]> rows = List.of(
                new String[]{"n", "comparisons", "allocations", "depth"},
                new String[]{"10", "15", "2", "3"}
        );

        CsvWriter.write(tempFile.toString(), rows);

        String content = Files.readString(tempFile);
        assertTrue(content.contains("n,comparisons,allocations,depth"));
        assertTrue(content.contains("10,15,2,3"));
    }
}
