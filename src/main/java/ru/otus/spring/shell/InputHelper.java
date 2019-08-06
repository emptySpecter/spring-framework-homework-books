package ru.otus.spring.shell;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHelper {

    public static int readIntParameter(Scanner in, PrintStream out, String prompt, String validation) {
        int parameter;
        while (true) {
            out.print(prompt);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                parameter = Integer.valueOf(tmp);
                break;
            }
            out.print(validation);
        }
        return parameter;
    }

    public static String readStringParameter(Scanner in, PrintStream out, String prompt, String validation) {
        String str;
        while (true) {
            out.print(prompt);
            str = in.nextLine();
            if (str.length() <= 255) break;
            out.print(validation);
        }
        return str;
    }

    public static <T> T readObjectParameter(MongoRepository repository, Scanner in, PrintStream out, String prompt, String validation) {
        T object;
        while (true) {
            out.print(prompt);
            String tmp = in.nextLine();
            if (tmp.matches("[\\d]+")) {
                long id = Long.valueOf(tmp);
                try {
                    object = (T) repository.findById(id).get();
                    break;
                } catch (NoSuchElementException e) {
                }
            }
            out.print(validation);
        }
        return object;
    }

}
