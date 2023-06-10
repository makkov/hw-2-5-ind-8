package org.skypro.hw;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1, 1, 999, 999, 3, 7, 0, 4, 4, 5, 5, 6, 7));
        System.out.println("входной массив " + nums);
        task1(nums);
        task2(nums);
        List<String> words = List.of("hello", "world", "hello", "how are you");
        task3(words);
        task4(words);
    }

    public static void task1(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : nums) {
            if (num % 2 != 0) {
                result.add(num);
            }
        }
        System.out.println(result);
    }

    public static void task2(List<Integer> nums) {
        Set<Integer> result = new TreeSet<>(nums);
        System.out.println(result);
    }

    public static void task3(List<String> words) {
//        обратный порядок:
//        Set<String> uniqWords = new TreeSet<>(
//                (a, b) -> a.compareTo(b) * (-1)
//        );
        Set<String> uniqWords = new TreeSet<>(words);
        System.out.println(uniqWords);
    }

    public static void task4(List<String> words) {
        Map<String, Integer> wordByCount = new HashMap<>();
        for (String word : words) {
            if (wordByCount.containsKey(word)) {
                wordByCount.put(word, wordByCount.get(word) + 1);
            } else {
                wordByCount.put(word, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : wordByCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry);
            }
        }
    }
}
