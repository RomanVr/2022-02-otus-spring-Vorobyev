package ru.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Person {
    private final Map<Integer, String> answers;
    private String name;
}
