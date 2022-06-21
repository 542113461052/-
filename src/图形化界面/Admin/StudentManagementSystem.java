package 图形化界面.Admin;

import 数据库操作.*;
import 文件操作.FileOperation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.*;

public class StudentManagementSystem {
    public static void main(String[] args) {
        new StudentManagementSystem();
    }

    public StudentManagementSystem() {
        JFrame frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(300, 50);
        frame.setSize(800, 800);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("学生信息管理");
        jp1.add(jl1);
        jp1.setBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        frame.add(jp1, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        JPanel jp2 = new JPanel(new BorderLayout());
        jp2.add(scrollPane);
        frame.add(jp2, BorderLayout.CENTER);
        JTable jt = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(jt);
        JButton jb1 = new JButton("添加学生");
        JButton jb2 = new JButton("删除学生");
        JButton jb3 = new JButton("修改信息");
        JButton jb4 = new JButton("导出学生");
        JButton jb5 = new JButton("退出系统");
        Dimension preferredSize = new Dimension(148, 60);    //设置尺寸
        jb1.setPreferredSize(preferredSize);    //设置按钮大小
        jb2.setPreferredSize(preferredSize);
        jb3.setPreferredSize(preferredSize);
        jb4.setPreferredSize(preferredSize);
        jb5.setPreferredSize(preferredSize);
        JPanel jp3 = new JPanel();
        frame.add(jp3, BorderLayout.EAST);

        JPanel jp4 = new JPanel();
        JLabel jl1_1 = new JLabel("学号：");
        JTextField jtf1 = new JTextField();
        JLabel jl2 = new JLabel("姓名：");
        JTextField jtf2 = new JTextField();
        JLabel jl3 = new JLabel("职位：");
        JTextField jtf3 = new JTextField();
        JLabel jl4 = new JLabel("密码：");
        JPasswordField jpf4 = new JPasswordField();
        JButton jb = new JButton("提交");
        JButton jb3_1 = new JButton("提交");
        Dimension dimension = new Dimension(100, 20);
        jtf1.setPreferredSize(dimension);
        jtf2.setPreferredSize(dimension);
        jtf3.setPreferredSize(dimension);
        jpf4.setPreferredSize(dimension);
        jp4.setPreferredSize(new Dimension(100, 400));
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);
        jp3.add(jb4);
        jp3.add(jb5);
        jp3.setPreferredSize(new Dimension(150, 600));
        jp3.add(jp4);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();    //获得表格模型
                tableModel.setRowCount(0);    //清空表格中的数据
                tableModel.setColumnIdentifiers(new Object[]{"学号", "姓名", "密码", "职位"});    //设置表头
                Statement stmt = null;
                long tid;
                String tname;
                String password;
                String position;
                ResultSet rs = null;
                GetConnection getConnection = new GetConnection();
                Connection conn = getConnection.GetConnection();
                // 执行查询
                try {
                    stmt = conn.createStatement();
                    String sql = "SELECT *  FROM student";
                    rs = stmt.executeQuery(sql);
                    // 展开结果集数据库
                    while (rs.next()) {
                        // 通过字段检索
                        tid = rs.getLong(1);
                        tname = rs.getString(2);
                        password = rs.getString(3);
                        position = rs.getString(4);
                        tableModel.addRow(new Object[]{tid, tname, password, position});
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
                jt.setModel(tableModel);    //应用表格模型
            }
        });
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                添加组件
                //              清空jp3里面的原有的组件
                jp4.removeAll();
                jp4.repaint();
                jp4.add(jl1_1);
                jp4.add(jtf1);
                jp4.add(jl2);
                jp4.add(jtf2);
                jp4.add(jl3);
                jp4.add(jtf3);
                jp4.add(jl4);
                jp4.add(jpf4);
                jp4.add(jb);
                //              更新jp3里面的的组件
                jp4.updateUI();

                frame.setVisible(true);
            }
        });
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                        添加
                Add add = new Add();
                Long id = Long.parseLong(jtf1.getText());
                String name = jtf2.getText();
                String position = jtf3.getText();
                String position2 = position.split("班")[1];
                String password = String.valueOf(jpf4.getPassword());
                boolean b = false;
                if (position2.equals("长")) {
                    b = add.addAssessor(id, name, position, password) && add.addStudent(id, name, position, password)&&add.addFraction(id,name);
                } else {
                    b = add.addStudent(id, name, position, password)&&add.addFraction(id,name);
                }
                jtf1.setText("");
                jtf2.setText("");
                jtf3.setText("");
                jpf4.setText("");
                if (b) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) jt.getModel();
                int row = jt.getSelectedRow(); //这句选择要删除的行
                if (row != -1) {
                    Long gettid = Long.parseLong(jt.getValueAt(row, 0).toString());
                    String getposition = jt.getValueAt(row, 3).toString();
                    String getposition2 = getposition.split("班")[1];
                    Delete delete = new Delete();
                    boolean b = false;
                    if (getposition2.equals("长")) { // 原职位是班长
                        b = delete.deleteAssessor(gettid) && delete.deleteStudent(gettid) && delete.deleteFraction(gettid);
                    } else {
                        b = delete.deleteStudent(gettid) && delete.deleteFraction(gettid);
                    }
                    if (b) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                    model.removeRow(row);
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              清空jp4里面的原有的组件
                jp4.removeAll();
                jp4.repaint();
                jp4.add(jl2);
                jp4.add(jtf2);
                jp4.add(jl3);
                jp4.add(jtf3);
                jp4.add(jl4);
                jp4.add(jpf4);
                jp4.add(jb3_1);
//              更新jp4里面的的组件
                jp4.updateUI();
                frame.setVisible(true);
            }
        });
        jb3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                        修改
                int row = jt.getSelectedRow(); //这句选择要修改的行
                if (row != -1) {
                    Long gettid = Long.parseLong(jt.getValueAt(row, 0).toString()); //获取被修改学生学号
                    String getposition = jt.getValueAt(row, 3).toString();
                    String getposition2 = getposition.split("班")[1];
                    Update update = new Update();
                    String name = jtf2.getText();
                    String position = jtf3.getText();
                    String password = String.valueOf(jpf4.getPassword());
                    boolean b = false;
                    GetConnection getConnection = new GetConnection();
                    Connection connection = getConnection.GetConnection();
                    Statement statement = null;
                    try {
                        String sql = "update fraction set name ="+"'"+name+"'"+"  where fid =" + gettid;
                        statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        if (getposition2.equals("长")) { // 原职位是班长
                            if (getposition2.equals(position)) { //职位不变
                                b = update.updateStudent(gettid, name, position, password) || update.updateAssessor(gettid, name, position, password);
                            } else { //班长->学生
                                Delete delete = new Delete();
                                b = delete.deleteAssessor(gettid) && update.updateStudent(gettid, name, position, password);
                            }
                        } else {
                            if (getposition2.equals(position)) { //职位不变
                                b = update.updateStudent(gettid, name, position, password);
                            } else { //学生->班长
                                Add add = new Add();
                                b = add.addAssessor(gettid, name, position, password) && update.updateStudent(gettid, name, position, password);
                            }
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    jtf2.setText("");
                    jtf3.setText("");
                    jpf4.setText("");
                    if (b) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOperation fileOperation = new FileOperation();
                fileOperation.exportTable(jt, frame);
            }
        });
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

}
