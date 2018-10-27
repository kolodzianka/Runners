package pl.sdacademy.main;

import pl.sdacademy.database.jdbc.utils.JdbcUtils;
import pl.sdacademy.database.jdbc.utils.dao.MemberDao;
import pl.sdacademy.database.jdbc.utils.dao.RunDao;
import pl.sdacademy.database.jdbc.utils.entity.Member;
import pl.sdacademy.database.jdbc.utils.entity.Run;
import pl.sdacademy.database.jdbc.utils.providers.DaoProvider;

import java.sql.*;
import java.util.UUID;

public class Main {


    public static void main(String[] args) throws SQLException {

        RunDao runDao = DaoProvider.getInstance().getRunDao();
        MemberDao memberDao = DaoProvider.getInstance().getMemberDao();

        long memberId = 1;
        for(int i = 1; i< 10; i++){
            Run run = new Run();
            run.setId((long)i);
            run.setName((UUID.randomUUID().toString()));
            run.setPlace(UUID.randomUUID().toString());
            run.setStartDate(new java.util.Date());
            run.setStartTime(new java.util.Date());
            run.setMembersLimit(100);
            runDao.save(run);

            for(int j=0; j<10; j++){
                Member member = new Member();
                member.setId(memberId++);
                member.setName(UUID.randomUUID().toString());
                member.setLastName(UUID.randomUUID().toString());
                member.setStartNumber((int)(Math.random()*100));
                member.setRunId(run.getId());

                memberDao.save(member);
            }


        }





    }

    public static void insertRuns(Integer id, String name, String place, java.util.Date startDate, java.util.Date startTime, Integer membersLimit) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into RUNS (ID, NAME, PLACE, START_DATE, START_TIME, MEMBERS_LIMIT)" +
                " values (?,?,?,?,?,?)");
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setString(3, place);
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlStartTime = new java.sql.Date(startTime.getTime());
        statement.setDate(4, sqlStartDate);
        statement.setDate(5, sqlStartTime);
        statement.setInt(6, membersLimit);
        statement.executeUpdate();
        statement.close();

    }

    public static void deleteRun(Integer id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM RUNS where id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();


    }

    public static void deleteAllRuns() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM RUNS");
        statement.executeUpdate();
        statement.close();
    }

    public static void findRun(Integer id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("select * FROM RUNS where id=?");
        statement.setInt(1, id);
        statement.close();
    }

    public static void insertRandomRun(Integer id) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into RUNS (ID, NAME, PLACE, START_DATE, START_TIME, MEMBERS_LIMIT)" +
                " values (?,?,?,?,?,?)");
        statement.setInt(1, id);
        statement.setString(2, UUID.randomUUID().toString());
        statement.setString(3, UUID.randomUUID().toString());
        java.util.Date startDate = new java.util.Date();
        java.util.Date startTime = new java.util.Date();

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlStartTime = new java.sql.Date(startTime.getTime());
        statement.setDate(4, sqlStartDate);
        statement.setDate(5, sqlStartTime);
        statement.setInt(6, (int) (Math.random() * 100));
        statement.executeUpdate();
        statement.close();
    }

    private static void printAllRuns() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * FROM RUNS");
        while (result.next()){
            String invoiceID = result.getString("id");
            String invoiceMembersLimit = result.getString("members_Limit");
            System.out.println(invoiceID + " " + invoiceMembersLimit);
        }

        statement.close();
    }

    public static void updateRun (Integer id, String name, String place, java.util.Date startDate, java.util.Date startTime, Integer membersLimit) throws SQLException{
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("UPDATE RUNS SET NAME = ?, PLACE = ?, START_DATE = ?, START_TIME = ?, MEMBERS_LIMIT=? " +
                "WHERE ID = ?");

        statement.setString(1, name);
        statement.setString(2, name);
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlStartTime = new java.sql.Date(startTime.getTime());
        statement.setDate(3, sqlStartDate);
        statement.setDate(4, sqlStartTime);
        statement.setInt(5, membersLimit);
        statement.setInt(6, id);
        statement.executeUpdate();
        statement.close();
    }


}
