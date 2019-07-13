package ru.otus.spring.repositories.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.jdbc.Book;
import ru.otus.spring.domain.jdbc.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenreRepositoryJdbcImpl implements GenreRepositoryJdbc {

    public static final String GENRE_SELECT = "select typeId, name from types";
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<Genre> getById(long id) {
        final HashMap<String,Object> params = new HashMap<>(1);
        params.put("id",id);
        Genre genre = null;
        try {
            genre = jdbc.queryForObject(GENRE_SELECT + " where typeId = :id",params,new GenreRepositoryJdbcImpl.GenreMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(genre);
    }


    @Override
    public List<Genre> getAll() {
        return jdbc.query(GENRE_SELECT, new GenreRepositoryJdbcImpl.GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("typeId");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
