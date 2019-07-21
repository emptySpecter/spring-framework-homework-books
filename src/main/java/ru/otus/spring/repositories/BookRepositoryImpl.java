package ru.otus.spring.repositories;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private static final String BOOKS_SELECT = "select bookId, books.name as name, authors.name as authorName, authors.surname as authorSurname, " +
            "pagecount, points, books.authorId as authorId, books.typeId as typeId, types.name as genre " +
            "from books " +
            "inner join authors on books.authorId = authors.authorId " +
            "inner join types on books.typeId = types.typeId ";

    private final NamedParameterJdbcOperations jdbc;


    @Override
    public void save(Book book) {
        final HashMap<String, Object> params = new HashMap<>(8);
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("pagecount", book.getPagecount());
        params.put("points", book.getPoints());
        params.put("authorId", book.getAuthor().getId());
        params.put("typeId", book.getGenre().getId());
        jdbc.update("insert into books (name, pagecount, points, authorId, typeId) " +
                "values (:name, :pagecount, :points, :authorId, :typeId)", params);
    }

    @Override
    public Optional<Book> getById(long id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        Book book = null;
        try {
            book = jdbc.queryForObject(BOOKS_SELECT + " where bookId = :id", params, new BookMapper());
        } catch (DataAccessException e) {
            System.out.println("Book with id = " + id + " doesn't exist!");
        }
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", author.getId());
        return jdbc.query(BOOKS_SELECT + " where books.authorId = :id", params, new BookMapper());
    }


    @Override
    public List<Book> getAll() {
        return jdbc.query(BOOKS_SELECT, new BookRepositoryImpl.BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("bookId");
            String name = resultSet.getString("name");
            long pagecount = resultSet.getLong("pagecount");
            long points = resultSet.getLong("points");
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            String authorSurname = resultSet.getString("authorSurname");
            long typeId = resultSet.getLong("typeId");
            String genre = resultSet.getString("genre");
            return new Book(id, name, pagecount, points, new Genre(typeId, genre), new Author(authorId, authorName, authorSurname));
        }
    }

}
