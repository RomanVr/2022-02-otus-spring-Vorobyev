package ru.homework.output.utils;

import java.util.Arrays;
import java.util.Objects;

public final class ResultService {
    public static boolean arraysEqual(final String[] dataAnswers, final String[] rightAnswer) {
        if (dataAnswers.length != rightAnswer.length) return false;
        Arrays.sort(dataAnswers);
        Arrays.sort(rightAnswer);
        for (int i = 0; i < dataAnswers.length; i += 1) {
            if (!Objects.equals(dataAnswers[i], rightAnswer[i])) return false;
        }
        return true;
    }
}
