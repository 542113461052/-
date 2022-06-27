package 图形化界面.Assessor;

import 数据库操作.GetConnection;
import 数据库操作.Seek;
import 数据库操作.Update;
import 文件操作.FileOperation;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssessorSystem {
    public static void main(String[] args) throws IOException {
        new AssessorSystem(Long.parseLong("2001011"));

    }

    public AssessorSystem(long aid) throws IOException {
        //        页面整体
        JFrame frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(300, 50);
        frame.setSize(800, 800);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        组件
//        标题
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("欢迎来到软件学院德育学分评分系统");
        jp1.add(jl1);
        jp1.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        frame.add(jp1, BorderLayout.NORTH);
//        左侧面板
        JPanel jp2 = new JPanel();
        JButton jb1 = new JButton("审核");
        JButton jb2 = new JButton("统计");
        JButton jb4 = new JButton("答疑");
        JButton jb3 = new JButton("退出系统");
        Dimension preferredSize = new Dimension(148, 60);    //设置尺寸
        jb1.setPreferredSize(preferredSize);    //设置按钮大小
        jb2.setPreferredSize(preferredSize);
        jb3.setPreferredSize(preferredSize);
        jb4.setPreferredSize(preferredSize);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb4);
        jp2.add(jb3);
        jp2.setPreferredSize(new Dimension(150, 600));
//        jp2.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        frame.add(jp2, BorderLayout.WEST);
        jp2.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
        JPanel jp3 = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        jp3.add(scrollPane);
        frame.add(jp3, BorderLayout.CENTER);
        JTable jt = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(jt);


        JPanel jp4 = new JPanel();
        jp4.setPreferredSize(new Dimension(100, 400));
        jp2.add(jp4);
        JLabel jl2 = new JLabel("加分：");
        JTextField jtf = new JTextField();
        JLabel jl2_1 = new JLabel("扣分");
        JTextField jtf2 = new JTextField();
        JButton jb4_1 = new JButton("提交");
        Dimension dimension = new Dimension(100, 20);
        jtf.setPreferredSize(dimension);
        jtf2.setPreferredSize(dimension);
        JLabel jl3 = new JLabel("排序：");    //创建标签
        JComboBox jcb1 = new JComboBox();    //创建JComboBox
        jcb1.addItem("----请选择----");
        jcb1.addItem("     班级     ");
        jcb1.addItem("     年级     ");
        jcb1.addItem("    班级排名   ");
        jcb1.addItem("    年级排名   ");
        jcb1.setSelectedIndex(0);
        JButton jb4_2 = new JButton("查询");
        JButton jb4_3 = new JButton("导出");


        JLabel jl4_1 = new JLabel("回复");
        JTextField jtf4_1 = new JTextField();
        jtf4_1.setPreferredSize(dimension);
        JButton jb = new JButton("提交");


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();    //获得表格模型
                jt.setModel(tableModel);    //应用表格模型
                jb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tableModel.setRowCount(0);    //清空表格中的数据
                        tableModel.setColumnIdentifiers(new Object[]{"工号", "姓名", "加分项", "材料"});    //设置表头
                        Statement stmt = null;
                        long tid;
                        String tname;
                        String Bonusitem;
                        String enclosure;
                        ResultSet rs = null;
                        ResultSet rs2 = null;
                        GetConnection getConnection = new GetConnection();
                        Connection conn = getConnection.GetConnection();
                        // 执行查询
                        try {
                            stmt = conn.createStatement();
                            String sql = "SELECT *  FROM student where sid != " + aid + " and sid like " + "'" + String.valueOf(aid).substring(0, 2) + String.valueOf(aid).substring(2, 4) + "%'";
                            //                    System.out.println(sql);
                            rs = stmt.executeQuery(sql);
                            // 展开结果集数据库
                            while (rs.next()) {
                                // 通过字段检索
                                tid = rs.getLong(1);
                                tname = rs.getString(2);
                                Bonusitem = rs.getString(5);
                                enclosure = rs.getString(6);
                                Seek seek = new Seek();
                                int[] fraction = seek.getFraction(tid);
                                if (Bonusitem.equals("无")||fraction[2]!=0) {
                                    continue;
                                }
                                tableModel.addRow(new Object[]{tid, tname, Bonusitem, enclosure});
                            }
                            jt.setRowHeight(20);
                        } catch (SQLException a) {
                        } finally {
                            // 完成后关闭
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                            } catch (SQLException b) {
                            }
                            try {
                                if (rs2 != null) {
                                    rs2.close();
                                }
                            } catch (SQLException b) {
                            }
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                            } catch (SQLException c) {
                            }
                            try {
                                if (conn != null) {
                                    conn.close();
                                }
                            } catch (SQLException d) {
                            }

                        }
                        jp4.removeAll();
                        jp4.repaint();
                        jp4.add(jl2);
                        jp4.add(jtf);
                        jp4.add(jl2_1);
                        jp4.add(jtf2);
                        jp4.add(jb4_1);
                        jp4.updateUI();
                        frame.setVisible(true);
                    }
                });
                jb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        清空jp4
                        jp4.removeAll();
                        jp4.repaint();
                        tableModel.setRowCount(0);    //清空表格中的数据
                        tableModel.setColumnIdentifiers(new Object[]{"工号", "姓名", "基础分", "考察分", "加分", "扣分", "总分"});    //设置表头
                        Statement stmt = null;
                        long tid;
                        String tname;
                        ResultSet rs = null;
                        GetConnection getConnection = new GetConnection();
                        Connection conn = getConnection.GetConnection();
                        // 执行查询
                        try {
                            stmt = conn.createStatement();
//                            String sql = "SELECT *  FROM student where sid != " + aid + " and sid like " + "'" + String.valueOf(aid).substring(0, 2) + String.valueOf(aid).substring(2, 4) + "%'";
                            String sql = "SELECT *  FROM student ";
                            //                    System.out.println(sql);
                            rs = stmt.executeQuery(sql);
                            // 展开结果集数据库
                            while (rs.next()) {
//                                 通过字段检索
                                tid = rs.getLong(1);
                                tname = rs.getString(2);
                                Seek seek = new Seek();
                                int[] fraction = seek.getFraction(tid);
                                tableModel.addRow(new Object[]{tid, tname, fraction[0], fraction[1], fraction[2], fraction[3], fraction[4]});
                            }
                            jt.setRowHeight(20);
                        } catch (SQLException a) {
                        } finally {
                            // 完成后关闭
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                            } catch (SQLException b) {
                            }
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                            } catch (SQLException c) {
                            }
                            try {
                                if (conn != null) {
                                    conn.close();
                                }
                            } catch (SQLException d) {
                            }
                        }
                        jp4.add(jl3);
                        jp4.add(jcb1);
                        jp4.add(jb4_2);
                        jp4.add(jb4_3);
//                        更新jp4
                        jp4.updateUI();
                    }
                });
                jb4_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tableModel.setRowCount(0);    //清空表格中的数据
                        tableModel.setColumnIdentifiers(new Object[]{"工号", "姓名", "基础分", "考察分", "加分", "扣分", "总分"});    //设置表头
                        GetConnection getConnection = new GetConnection();
                        Connection connection = getConnection.GetConnection();
                        Statement statement = null;
                        String sql = null;
                        try {
                            statement = connection.createStatement();

                            String string = jcb1.getSelectedItem().toString();
                            if(string.equals("     班级     ")){
                                sql = "SELECT * FROM fraction  order by fid desc";
                            }else if (string.equals("     年级     ")){
                                sql = "SELECT * FROM fraction order by fid desc";
                            }else if (string.equals("    班级排名   ")){
                                sql = "SELECT * FROM fraction order by totalscore,fid desc";
                            }else if (string.equals("    年级排名   ")){
                                sql = "SELECT * FROM fraction order by totalscore desc,fid desc";
                            }
                            ResultSet resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
//                                 通过字段检索
                                long tid = resultSet.getLong(1);
                                String tname = resultSet.getString(2);
                                tableModel.addRow(new Object[]{tid, tname, resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7)});
                            }
                            jt.setRowHeight(20);

                        } catch (SQLException ex) {

                        }

                    }
                });
                jb4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //                        清空jp4
                        jp4.removeAll();
                        jp4.repaint();
                        tableModel.setRowCount(0);    //清空表格中的数据
                        tableModel.setColumnIdentifiers(new Object[]{"学号", "姓名", "问题"});    //设置表头
                        Statement stmt = null;
                        long id;
                        String tname;
                        ResultSet rs = null;
                        GetConnection getConnection = new GetConnection();
                        Connection conn = getConnection.GetConnection();
                        String problem=null;
                        String reply = "无";
                        // 执行查询
                        try {
                            stmt = conn.createStatement();
                            String sql = "SELECT *  FROM problem where pid like "+"'"+String.valueOf(aid).substring(0,4)+"%'";
//                            System.out.println(sql);
                            rs = stmt.executeQuery(sql);
                            // 展开结果集数据库
                            while (rs.next()) {
//                                 通过字段检索
                                id = rs.getLong(2);
                                problem = rs.getString(3);
                                reply = rs.getString(4);
                                if(reply.equals("无")||id==aid){
                                    continue;
                                }
                                String name = null;
//                                System.out.println(id+","+problem);
                                GetConnection getConnection1 = new GetConnection();
                                Connection connection = getConnection1.GetConnection();
                                Statement statement = connection.createStatement();
                                String sql2 = "SELECT sname from student where sid ="+id;
                                ResultSet resultSet = statement.executeQuery(sql2);
                                while (resultSet.next()){
                                    tableModel.addRow(new Object[]{id, resultSet.getString(1),problem });
                                }
                            }
                            jt.setRowHeight(20);
                        } catch (SQLException a) {
                        } finally {
                            // 完成后关闭
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                            } catch (SQLException b) {
                            }
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                            } catch (SQLException c) {
                            }
                            try {
                                if (conn != null) {
                                    conn.close();
                                }
                            } catch (SQLException d) {
                            }
                        }
                        jp4.removeAll();
                        jp4.repaint();
                        jp4.add(jl4_1);
                        jp4.add(jtf4_1);
                        jp4.add(jb);
                        jp4.updateUI();
                        frame.setVisible(true);
                    }
                });
            }
        });
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowActivated(WindowEvent e) {
//                DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();    //获得表格模型
//                tableModel.setRowCount(0);    //清空表格中的数据
//                tableModel.setColumnIdentifiers(new Object[]{"工号", "姓名","加分项", "材料"});    //设置表头
//                Statement stmt = null;
//                long tid ;
//                String tname;
//                String Bonusitem;
//                String position;
//                ResultSet rs = null;
//                ResultSet rs2 = null;
//                GetConnection getConnection = new GetConnection();
//                Connection conn = getConnection.GetConnection();
//                // 执行查询
//                try {
//                    stmt = conn.createStatement();
//                    String sql = "SELECT *  FROM student where sid != "+aid +" and sid like "+"'"+String.valueOf(aid).substring(0,2)+String.valueOf(aid).substring(2,4)+"%'";
////                    System.out.println(sql);
//                    rs = stmt.executeQuery(sql);
//                    // 展开结果集数据库
//                    while (rs.next()) {
//                        // 通过字段检索
//                        tid = rs.getLong(1);
//                        tname = rs.getString(2);
//                        Bonusitem = rs.getString(5);
//                        position = rs.getString(4);
//                        if(Bonusitem==null){
//                            continue;
//                        }
//                        tableModel.addRow(new Object[]{tid, tname, Bonusitem, position});
//                    }
//                    jt.setRowHeight(20);
//
//                } catch (SQLException a) {
//                } finally {
//                    // 完成后关闭
//                    try {
//                        if (rs != null) {
//                            rs.close();
//                        }
//                    } catch (SQLException b) {
//                    }
//                    try {
//                        if (rs2 != null) {
//                            rs2.close();
//                        }
//                    } catch (SQLException b) {
//                    }
//                    try {
//                        if (stmt != null) {
//                            stmt.close();
//                        }
//                    } catch (SQLException c) {
//                    }
//                    try {
//                        if (conn != null) {
//                            conn.close();
//                        }
//                    } catch (SQLException d) {
//                    }
//                }
//                jt.setModel(tableModel);    //应用表格模型
//            }
//        });
//        jb1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                jp4.removeAll();
//                jp4.repaint();
//                jp4.add(jl2);
//                jp4.add(jtf);
//                jp4.add(jb4_1);
//                jp4.updateUI();
//                frame.setVisible(true);
//            }
//        });
        jb4_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jt.getSelectedRow(); //选择的行
                if (row != -1) {
                    Long gettid = Long.parseLong(jt.getValueAt(row, 0).toString());
                    int additional = (int) Long.parseLong(jtf.getText());
//                    System.out.println(additional+""+gettid);
                    Seek seek = new Seek();
                    int[] fraction = seek.getFraction(gettid);
                    Update update = new Update();
                    boolean b = update.updateFraction(gettid, fraction[0], fraction[1], additional, (int) Long.parseLong(jtf2.getText()));
                    jtf.setText("");
                    jtf2.setText("");
                    if (b) {
                        JOptionPane.showMessageDialog(null, "审核成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "审核失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jb4_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOperation fileOperation = new FileOperation();
                fileOperation.exportTable(jt, frame);
            }
        });
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String relpy = jtf4_1.getText();
                Update update = new Update();
                int row = jt.getSelectedRow();
                if(row!=-1){
                    long id = Long.parseLong(jt.getValueAt(row, 0).toString());
//                    System.out.println(jt.getValueAt(row, 0).toString());
                    String problem1 = jt.getValueAt(row,2).toString();
//                    System.out.println(problem1);
                    boolean b = update.updatereply(id, problem1,relpy );
                    jtf4_1.setText("");
                    if (b) {
                        JOptionPane.showMessageDialog(null, "回复成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "回复失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        frame.setVisible(true);
    }

}
