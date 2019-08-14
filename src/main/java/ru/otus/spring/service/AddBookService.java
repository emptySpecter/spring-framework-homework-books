package ru.otus.spring.service;

import java.io.PrintStream;
import java.util.Scanner;

public interface AddBookService {
    void newBook(Scanner in, PrintStream out);
}
