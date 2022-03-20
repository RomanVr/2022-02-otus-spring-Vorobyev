package ru.homework.ioService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreams implements IOService {
    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams(PrintStream outputStream, InputStream inputStream) {
        this.output = outputStream;
        this.input = new Scanner(inputStream);
    }

    @Override
    public String readString() {
        return input.nextLine();
    }

    @Override
    public String readWithPrompt(String prompt) {
        output.print(prompt);
        return readString();
    }

    @Override
    public void outputString(String str) {
        output.println(str);
    }
}
