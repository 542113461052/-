package 数据库操作;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {
    public static void main(String[] args) {
        Add add = new Add();
//        boolean b = add.addTeacher(456789, "麻子", "c++", "麻子","teacher");
//        if (b){
//            System.out.println("添加成功");
//        }else {
//            System.out.println("添加失败");
//        }
        boolean b = add.addFraction(1,"麻子");
        if (b){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
    public boolean addTeacher(long tid ,String tname ,String tsubject,String position ,String password){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String insertSQL = "insert into teacher (tid,tname,tsubject,position,password) values ("+tid+","+"'"+tname+"'"+","+"'"+tsubject+"'"+","+"'"+position+"'"+","+"'"+password+"'"+")";
            stmt = connection.createStatement();
            stmt.executeUpdate(insertSQL);
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
        }
        return result;
    }
    public boolean addAssessor(long aid ,String aname ,String position ,String password){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String insertSQL = "insert into assessor (aid,aname,position,password) values ("+aid+","+"'"+aname+"'"+","+"'"+position+"'"+","+"'"+password+"'"+")";
            stmt = connection.createStatement();
            stmt.executeUpdate(insertSQL);
            result = true;
        } catch (SQLException e) {
        }
        return result;
    }
    public boolean addStudent(long sid ,String sname ,String position ,String password){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String insertSQL = "insert into student (sid,sname,position,password) values ("+sid+","+"'"+sname+"'"+","+"'"+position+"'"+","+"'"+password+"'"+")";
            stmt = connection.createStatement();
            stmt.executeUpdate(insertSQL);
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
        }
        return result;
    }
    public boolean addFraction(long fid ,String fname ){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String insertSQL = "insert into fraction (fid,name) values ("+fid+","+"'"+fname+"'"+")";
//            System.out.println(insertSQL);
            stmt = connection.createStatement();
            stmt.executeUpdate(insertSQL);
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
        }
        return result;
    }
    public boolean addProblem(long pid ,String problem ){
        boolean result = false;
        Statement stmt = null;
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        try {
            String insertSQL = "insert into problem (pid,problem) values ("+pid+","+"'"+problem+"'"+")";
//            System.out.println(insertSQL);
            stmt = connection.createStatement();
            stmt.executeUpdate(insertSQL);
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
        }
        return result;
    }
}
