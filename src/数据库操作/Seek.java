package 数据库操作;

import java.sql.*;
public class Seek {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/studentmanagementsystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "ZHAOhaitao123";
    public String getStudentPassword(long sid){
        Statement stmt = null;
        String password = null;
        ResultSet rs = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT password FROM student where sid="+sid;
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                password  = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // 完成后关闭
            try {
                if (rs!=null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
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
        return password;
    }
    public String getAdminPassword(long aid){
        Statement stmt = null;
        String password = null;
        ResultSet rs = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT password FROM admin where aid="+aid;
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                password  = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // 完成后关闭
            try {
                if (rs!=null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
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
        return password;
    }
    public int[] getFraction(long fid){
        GetConnection getConnection = new GetConnection();
        Connection connection = getConnection.GetConnection();
        int[] fraction = new int[5];
        fraction[0]=0;
        fraction[1]=0;
        fraction[2]=0;
        fraction[3]=0;
        fraction[4]=0;
        Statement statement = null;
        ResultSet re = null;
        try {
            statement = connection.createStatement();
            String seeksql = "select basics,investigate,additional,penalty,totalscore from fraction where fid ="+fid;
            re = statement.executeQuery(seeksql);
            while (re.next()){
                fraction[0]=re.getInt(1);
                fraction[1]=re.getInt(2);
                fraction[2]=re.getInt(3);
                fraction[3]=re.getInt(4);
                fraction[4]=re.getInt(5);
            }
        } catch (SQLException e) {
        }finally {
            // 完成后关闭
            try {
                if(re!=null){
                    re.close();
                }

            } catch (SQLException e) {
            }
            try {
                if(statement!=null){
                    statement.close();
                }
            } catch (SQLException e) {
            }
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }

        return fraction;
    }
    public String getAssessorPassword(long aid){
        Statement stmt = null;
        String password = null;
        ResultSet rs = null;
        GetConnection getConnection = new GetConnection();
        Connection conn = getConnection.GetConnection();
        // 执行查询
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT password FROM assessor where aid="+aid;
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                password  = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // 完成后关闭
            try {
                if (rs!=null) {
                    rs.close();
                }
            } catch (SQLException e) {
            }
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
        return password;
    }



//    public String  getAdminPassword(long aid){
//        Connection conn = null;
//        Statement stmt = null;
//        String password = null;
//        try{
//            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);
//            // 打开链接
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//            // 执行查询
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT password FROM admin where aid="+aid;
//            ResultSet rs = stmt.executeQuery(sql);
//            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                password  = rs.getString("password");
//            }
//            // 完成后关闭
//            rs.close();
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        }catch(Exception e){
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }finally{
//            // 关闭资源
//            try{
//                if(stmt!=null) {
//                    stmt.close();
//                }
//            }catch(SQLException se2){
//            }// 什么都不做
//            try{
//                if(conn!=null) {
//                    conn.close();
//                }
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        return password;
//    }
}