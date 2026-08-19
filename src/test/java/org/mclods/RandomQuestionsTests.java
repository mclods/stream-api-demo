package org.mclods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomQuestionsTests {
    private final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;

    @BeforeEach
    void beforeEach() {
        System.setOut(new PrintStream(testOutputStream));
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOutputStream);
    }

    private String getConsoleOutput() {
        return testOutputStream.toString().replaceAll(System.lineSeparator(), "\n");
    }

    @Test
    @DisplayName("From a list of names return a map of name -> length of name string")
    void returnMapOfNamesAndLength() {
        String expectedOutput = """
                {Alice=5, Bob=3, White=5}
                """;

        List<String> namesList = List.of("Alice", "Bob", "White");

        Map<String, Integer> nameLengthMap = namesList
                .stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        NavigableMap<String, Integer> sortedNameLengthMap = new TreeMap<>(nameLengthMap);
        System.out.println(sortedNameLengthMap);

        assertThat(getConsoleOutput()).isEqualTo(expectedOutput);
    }
}
