package ru.otus.spring.repositories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@NoArgsConstructor
@Transactional
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    private static final String GENRE_SELECT = "select typeId, name from types";
    //private final NamedParameterJdbcOperations jdbc;

/*
    @Override
    public Optional<Genre> getById(long id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        Genre genre = null;
        try {
            genre = jdbc.queryForObject(GENRE_SELECT + " where typeId = :id", params, new GenreRepositoryImpl.GenreMapper());
        } catch (DataAccessException e) {
            System.out.println("Genre with id = " + id + " doesn't exist!");
        }
        return Optional.ofNullable(genre);
    }
*/

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g",
                Genre.class);
        return query.getResultList();
    }
/*
    @Override
    public List<Genre> getAll() {
        return jdbc.query(GENRE_SELECT, new GenreRepositoryImpl.GenreMapper());
    }
*/

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("typeId");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
