package ru.otus.spring.repositories;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    public static final String AUTHORS_SELECT = "select authorId, name, surname from authors";
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<Author> getById(long id) {
        final HashMap<String,Object> params = new HashMap<>(1);
        params.put("id",id);
        Author author = null;
        try {
            author = jdbc.queryForObject(AUTHORS_SELECT + " where authorId = :id",params,new AuthorRepositoryImpl.AuthorMapper());
        } catch (DataAccessException e) {
            System.out.println("Author with id = " + id + " doesn't exist!");
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