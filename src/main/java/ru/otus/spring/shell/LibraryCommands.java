package ru.otus.spring.shell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.otus.spring.shell.BeanTableModelHelper.getTableModel;

@RequiredArgsConstructor
@ShellComponent
public class LibraryCommands {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    @ShellMethod("Display list of authors")
    public void authorsList() {
        List<Author> authors = authorRepository.getAll();

        TableModel model = getTableModel(authors);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        System.out.print(tableBuilder.build().render(80));
    }

    @ShellMethod("Display list of genres")
    public void genresList() {
        List<Genre> genres = genreRepository.getAll();

        TableModel model = getTableModel(genres);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        System.out.print(tableBuilder.build().render(80));
    }
}
