package pl.sdacademy.database.jdbc.utils.dao;

import pl.sdacademy.database.jdbc.utils.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    void save (Run run) throws SQLException;
    Run findById (long id) throws SQLException;
    List<Run> findAll() throws SQLException;
    void update (Run run) throws SQLException;
    void delete (long id) throws SQLException;
}
