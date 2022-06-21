package 数据库操作;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public boolean deleteTeacher(long tid){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String delete;
            delete = "delete from teacher where tid="+tid;
            stmt.executeUpdate(delete);
            result = true;
        } catch (SQLException e) {
        }
        finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    };
    public boolean deleteAssessor(long aid){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String delete;
            delete = "delete from assessor where aid="+aid;
            stmt.executeUpdate(delete);
            result = true;
        } catch (SQLException e) {
        }
        finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    };
    public boolean deleteStudent(long sid){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String delete;
            delete = "delete from student where sid="+sid;
            stmt.executeUpdate(delete);
            result = true;
        } catch (SQLException e) {
        }
        finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    };
    public boolean deleteFraction(long fid){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String delete;
            delete = "delete from fraction where fid="+fid;
            stmt.executeUpdate(delete);
            result = true;
        } catch (SQLException e) {
        }
        finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    };
    public boolean deleteProblem(long pid,String problem){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String delete;
            delete = "delete from problem where pid="+pid+ " and problem = "+"'"+problem+"'";
//            System.out.println(delete);
            stmt.executeUpdate(delete);
            result = true;
        } catch (SQLException e) {
        }
        finally {
            // 完成后关闭
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException e) {
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    };
}
