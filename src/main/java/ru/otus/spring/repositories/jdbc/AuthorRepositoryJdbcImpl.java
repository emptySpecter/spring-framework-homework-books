package ru.otus.spring.repositories.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.jdbc.Author;
import ru.otus.spring.domain.jdbc.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AuthorRepositoryJdbcImpl implements  AuthorRepositoryJdbc {

    public static final String AUTHORS_SELECT = "select authorId, name, surname from authors";
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<Author> getById(long id) {
        final HashMap<String,Object> params = new HashMap<>(1);
        params.put("id",id);
        Author author = null;
        try {
            author = jdbc.queryForObject(AUTHORS_SELECT + " where authorId = :id",params,new AuthorRepositoryJdbcImpl.AuthorMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(author);
    }


    @Override
    public List<Author> getAll() {
        return jdbc.query(AUTHORS_SELECT, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("authorId");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            return new Author(id, name, surname);
        }
    }
}