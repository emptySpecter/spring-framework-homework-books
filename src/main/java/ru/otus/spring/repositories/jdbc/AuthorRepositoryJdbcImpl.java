package ru.otus.spring.repositories.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.jdbc.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Repository
public class AuthorRepositoryJdbcImpl implements  AuthorRepositoryJdbc {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getById(long id) {
        final HashMap<String,Object> params = new HashMap<>(1);
        params.put("id",id);
        return jdbc.queryForObject("select authorId, name, surname from authors where authorId = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select authorId, name, surname from authors", new AuthorMapper());
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