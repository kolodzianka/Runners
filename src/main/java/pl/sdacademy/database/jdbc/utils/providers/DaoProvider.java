package pl.sdacademy.database.jdbc.utils.providers;

import pl.sdacademy.database.jdbc.utils.dao.MemberDao;
import pl.sdacademy.database.jdbc.utils.dao.RunDao;
import pl.sdacademy.database.jdbc.utils.jdbc.dao.JdbcMemberDaoImpl;
import pl.sdacademy.database.jdbc.utils.jdbc.dao.JdbcRunDaoImpl;

public class DaoProvider {

    private static final DaoProvider instance = new DaoProvider();


    private DaoProvider(){
        runDao = new JdbcRunDaoImpl();
        memberDao = new JdbcMemberDaoImpl();
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public RunDao getRunDao() {
        return runDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    private RunDao runDao;
    private MemberDao memberDao;

}
