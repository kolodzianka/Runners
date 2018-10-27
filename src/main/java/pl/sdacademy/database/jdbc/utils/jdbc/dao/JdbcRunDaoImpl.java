package pl.sdacademy.database.jdbc.utils.jdbc.dao;

import pl.sdacademy.database.jdbc.utils.JdbcUtils;
import pl.sdacademy.database.jdbc.utils.dao.RunDao;
import pl.sdacademy.database.jdbc.utils.entity.Member;
import pl.sdacademy.database.jdbc.utils.entity.Run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunDaoImpl implements RunDao {


    public void save(Run run) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into RUNS (ID, NAME, PLACE, START_DATE, START_TIME, MEMBERS_LIMIT)" +
                " values (?,?,?,?,?,?)");
        statement.setLong(1, run.getId());
        statement.setString(2, run.getName());
        statement.setString(3, run.getPlace());
        java.sql.Date sqlStartDate = new java.sql.Date(run.getStartDate().getTime());
        java.sql.Date sqlStartTime = new java.sql.Date(run.getStartTime().getTime());
        statement.setDate(4, sqlStartDate);
        statement.setDate(5, sqlStartTime);
        statement.setInt(6, run.getMembersLimit());
        statement.executeUpdate();
        statement.close();
    }

    public Run findById(long id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("select * FROM RUNS where id=?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == true) {
            Run run = new Run();
            run.setId(resultSet.getLong("id"));
            run.setName(resultSet.getString("name"));
            run.setPlace(resultSet.getString("place"));
            run.setStartDate(resultSet.getDate("start_date"));
            run.setStartTime(resultSet.getTime("start_time"));
            run.setMembersLimit(resultSet.getInt("members_limit"));

            return run;
        }

        return null;
    }

    public List<Run> findAll() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("select * FROM RUNS");
        List<Run> result = new ArrayList();
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next() == true) {
            Run run = new Run();
            run.setId(resultSet.getLong("id"));
            run.setName(resultSet.getString("name"));
            run.setPlace(resultSet.getString("place"));
            run.setStartDate(resultSet.getDate("start_date"));
            run.setStartTime(resultSet.getTime("start_time"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
            result.add(run);
        }
        return result;
    }

    public void update(Run run) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("UPDATE RUNS SET NAME = ?, PLACE = ?, START_DATE = ?, START_TIME = ?, MEMBERS_LIMIT=? " +
                        "WHERE ID = ?");

        statement.setString(1, run.getName());
        statement.setString(2, run.getPlace());
        java.sql.Date sqlStartDate = new java.sql.Date(run.getStartDate().getTime());
        java.sql.Date sqlStartTime = new java.sql.Date(run.getStartTime().getTime());
        statement.setDate(3, sqlStartDate);
        statement.setDate(4, sqlStartTime);
        statement.setInt(5, run.getMembersLimit());
        statement.setLong(6, run.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(long id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM RUNS where id=?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    private List<Member> getMemberList(Long runId) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        List<Member> result = new ArrayList<Member>();
        PreparedStatement statement = connection.prepareStatement("select * FROM MEMBERS where run_id=?");
        statement.setLong(1, runId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next() == true) {
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
}

