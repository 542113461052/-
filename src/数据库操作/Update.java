package 数据库操作;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public boolean updateTeacher(long tid, String tname, String tsubject, String position, String password) {
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String updateSQL = "update teacher set " + "tname = " + "'" + tname + "'" + "," + "tsubject = " + "'" + tsubject + "'" + "," + "position = " + "'" + position + "'" + "," + "password = " + "'" + password + "'" + " where tid = " + tid;
//            System.out.println(updateSQL);
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
    public boolean updateStudent(long sid, String sname, String position, String password){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        String updateSQL = null;
        try {
            if (!position.equals("")) {
                updateSQL = "update student set " + "sname = " + "'" + sname + "'" + "," + "position = " + "'" + position + "'" + "," + "password = " + "'" + password + "'" + " where sid = " + sid;
            } else {
                updateSQL = "update student set " + "sname = " + "'" + sname + "'" + "," + "password = " + "'" + password + "'" + " where sid = " + sid;
            }
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
    public boolean updateAssessor(long aid, String aname, String position, String password) {
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String updateSQL = "update assessor set " + "aname = " + "'" + aname + "'" + "," + "position = " + "'" + position + "'" + "," + "password = " + "'" + password + "'" + " where aid = " + aid;
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
    public boolean updateFraction(long fid, int basics, int investigate, int additional,int penalty) {
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String updateSQL;
            int totalscore = basics+investigate+additional-penalty;
            if(totalscore>=100) {
                updateSQL = "update fraction set " + "basics = " +  basics + "," + "investigate = " +  investigate  + "," + "additional = " +  additional  +","+ "penalty = " +  penalty + ","+ "totalscore = " +100+" where fid = " + fid;
            }else {
                updateSQL = "update fraction set " + "basics = " +  basics + "," + "investigate = " +  investigate  + "," + "additional = " +  additional  +","+ "penalty = " +  penalty + ","+ "totalscore = " +totalscore+" where fid = " + fid;
            }
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
    public boolean updateProblem(long pid, String problem1,String problem2) {
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String updateSQL = "update problem set " + " problem = " +"'"+  problem2 + "'  where pid = " + pid+" and problem = "+"'"+problem1+"'";
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            System.out.println(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
    public boolean updatereply(long pid, String problem1,String reply) {
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String updateSQL = "update problem set " + " reply = " +"'"+  reply + "'  where pid = " + pid+" and problem = "+"'"+problem1+"'";
            stmt = connection.createStatement();
            stmt.executeUpdate(updateSQL);
            System.out.println(updateSQL);
            result = true;
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }
}
