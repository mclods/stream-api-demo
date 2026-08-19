package org.mclods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HCAHealthCareInterviewTests {
    @Test
    @DisplayName("Transform a sentence")
    void transformSentence() {
        // Given a sentence covert each word to the first letter of the word + number of letters in that word
        // For ex: I Love India -> I1 L4 I5
        System.out.println("Problem 1:");
        String input = "I Love India";

        String output = Arrays.stream(input.split(" "))
                .map(word -> word.substring(0, 1) + word.length())
                .reduce("", (res, word) -> res + word + " ");
        assertThat(output).isEqualTo("I1 L4 I5 ");
    }
}
