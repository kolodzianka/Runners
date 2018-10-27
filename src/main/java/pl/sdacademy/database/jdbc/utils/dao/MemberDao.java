package pl.sdacademy.database.jdbc.utils.dao;

import pl.sdacademy.database.jdbc.utils.entity.Member;
import pl.sdacademy.database.jdbc.utils.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

    void save (Member member) throws SQLException;
    Member findById (long id) throws SQLException;
    List<Member> findAll() throws SQLException;
    void update (Member member) throws SQLException;
    void delete (long id) throws SQLException;
}
