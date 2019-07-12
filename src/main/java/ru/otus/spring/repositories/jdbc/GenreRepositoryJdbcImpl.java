package ru.otus.spring.repositories.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.jdbc.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
@AllArgsConstructor
public class GenreRepositoryJdbcImpl implements GenreRepositoryJdbc {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre getById(long id) {
        final HashMap<String,Object> params = new HashMap<>(1);
        params.put("id",id);
        return jdbc.queryForObject("select typeId, name from types where typeId = :id", params, new GenreRepositoryJdbcImpl.GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select typeId, name from types", new GenreRepositoryJdbcImpl.GenreMapper());
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
