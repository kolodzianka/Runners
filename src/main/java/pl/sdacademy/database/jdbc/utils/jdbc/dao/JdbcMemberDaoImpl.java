package pl.sdacademy.database.jdbc.utils.jdbc.dao;

import pl.sdacademy.database.jdbc.utils.JdbcUtils;
import pl.sdacademy.database.jdbc.utils.dao.MemberDao;
import pl.sdacademy.database.jdbc.utils.entity.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMemberDaoImpl implements MemberDao {
    public void save(Member member) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into MEMBERS (ID, NAME, LAST_NAME, START_NUMBER, RUN_ID )" +
                " values (?,?,?,?,?)");
        statement.setLong(1, member.getId());
        statement.setString(2, member.getName());
        statement.setString(3, member.getLastName());
        statement.setInt(4, member.getStartNumber());
        statement.setLong(5, member.getRunId());

        statement.executeUpdate();
        statement.close();
    }

    public Member findById(long id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("select * FROM MEMBERS where id=?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == true){
            Member member = new Member();
            member.setId(resultSet.getLong("id"));
            member.setName(resultSet.getString("name"));
            member.setLastName(resultSet.getString("Last_Name"));
            member.setStartNumber(resultSet.getInt("start_id"));
            member.setRunId(resultSet.getLong("run_id"));

            return member;
        }
        return null;
    }

    public List<Member> findAll() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("select * FROM MEMBERS");
        List <Member>result = new ArrayList();
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next() == true) {
            Member member = new Member();
            member.setId(resultSet.getLong("id"));
            member.setName(resultSet.getString("name"));
            member.setLastName(resultSet.getString("Last_Name"));
            member.setStartNumber(resultSet.getInt("start_id"));
            member.setRunId(resultSet.getLong("run_id"));
            result.add(member);
        }
        return result;

    }

    public void update(Member member) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("UPDATE MEMBERS SET NAME = ?, LAST_NAME = ?, START_NUMBER = ?, RUN_ID = ? " +
                        "WHERE ID = ?");

        statement.setString(1, member.getName());
        statement.setString(2, member.getLastName());
        statement.setInt(3, member.getStartNumber());
        statement.setLong(4, member.getRunId());
        statement.setLong(5, member.getId());
        statement.executeUpdate();
        statement.close();

    }

    public void delete(long id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM MEMBERS where id=?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }
}
