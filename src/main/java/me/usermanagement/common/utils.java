package me.usermanagement.common;

import java.util.function.BiPredicate;

public class utils {
    public static boolean stringIsEmpty(String target) {
        BiPredicate<String, String> isEmpty = String::equals;
        return target == null || isEmpty.test(target, "");
    }
}
