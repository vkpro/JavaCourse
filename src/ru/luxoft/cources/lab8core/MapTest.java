package ru.luxoft.cources.lab8core;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class MapTest {
    public static void main(String[] args) {
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        check(map.size(), 0);
        check(map.remove(null), null);
        check(map.remove("key"), null);
        check(map.get("key"), null);
        check(!map.containsKey(null));
        check(map.put("key", 1), null);
        check(map.size(), 1);
        check(map.containsKey("key"));
        check(map.get("key"), 1);
        check(!map.containsKey(null));
        check(map.put("key2", 2), null);
        check(map.size(), 2);
        check(map.put("key", 3), 1);
        check(map.put(null, 0), null);
        check(map.put(null, 0), 0);
        check(map.containsKey(null));
        check(map.size(), 3);
        check(!map.isEmpty());
        check(map.remove(null), 0);
        check(map.remove("key"), 3);
        check(map.remove("key2"), 2);
        check(map.size(), 0);
        check(map.isEmpty());
        IntStream.rangeClosed(1, 1000).forEach(i -> map.put(String.valueOf(i), i));
        check(map.size(), 1000);
        BinaryOperator<Boolean> and = (b1, b2) -> b1 && b2;
        check(IntStream.rangeClosed(1, 1000).mapToObj(i -> map.containsKey(String.valueOf(i))).reduce(and).orElse(false));
        check(IntStream.rangeClosed(1, 1000).mapToObj(map::containsValue).reduce(and).orElse(false));
        check(IntStream.rangeClosed(1, 1000).mapToObj(i -> i == map.get(String.valueOf(i))).reduce(and).orElse(false));
        IntStream.rangeClosed(1, 1000).forEach(i -> map.remove(String.valueOf(i)));
        check(IntStream.rangeClosed(1, 1000).mapToObj(i -> !map.containsKey(String.valueOf(i))).reduce(and).orElse(false));
        check(map.isEmpty());

        System.out.println("All OK!");
    }

    static void check(boolean value) {
        check(value, "Error!");
    }

    static void check(boolean value, String message) {
        check(value, true, message);
    }

    static void check(Object value, Object expected) {
        check(value, expected, "Error!");
    }

    static void check(Object value, Object expected, String message) {
        if (!Objects.equals(value, expected)) {
            System.out.println(message);
            throw new CheckConditionException(message);
        }
    }
}

class CheckConditionException extends RuntimeException {
    public CheckConditionException(String message) {
        super(message);
    }
}
