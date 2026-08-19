package org.mclods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CRISILInterviewTests {
    @Test
    @DisplayName("Find the second max number from a list of numbers")
    void findSecondMaxNumber() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        Integer secondMaxNumber = list1
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);

        assertThat(secondMaxNumber).isEqualTo(4);
    }

    @Test
    @DisplayName("Find non repeating numbers from a list of numbers")
    void findNonRepeatingNumbers() {
        List<Integer> list2 = List.of(1, 1, 2, 3, 4, 5, 6, 6, 6);
        List<Integer> nonRepeatingNumbers = list2
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(mapEntry -> mapEntry.getValue() == 1)
                .map(Map.Entry::getKey)
                .toList();

        assertThat(nonRepeatingNumbers).containsExactly(2, 3, 4, 5);
    }
}
