import 数据库操作.Add;
import 数据库操作.Delete;
import 数据库操作.GetConnection;
import 数据库操作.Update;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class StudentSystem {
    public static void main(String[] args) {
         new StudentSystem(2001010);
    }
    public StudentSystem(long aid) {
//        页面整体
        JFrame frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(300, 50);
        frame.setSize(800, 800);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        组件
//        左侧面板
        JPanel jp2 = new JPanel();
        JButton jb4 = new JButton("咨询");
        jp2.add(jb4);
        frame.add(jp2, BorderLayout.WEST);
        Dimension dimension = new Dimension(100, 20);
        JPanel jp3_4 = new JPanel();
        JPanel jp3_2 = new JPanel();
        JPanel jp3 = new JPanel(new BorderLayout());
        frame.add(jp3, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        JPanel jp4_1 = new JPanel(new BorderLayout());
        JTable jt = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(jt);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();    //获得表格模型
                tableModel.setRowCount(0);    //清空表格中的数据
                tableModel.setColumnIdentifiers(new Object[]{ "问题","回答"});    //设置表头
                Statement stmt = null;
                String problem;
                String reply;
                ResultSet rs = null;
                GetConnection getConnection = new GetConnection();
                Connection conn = getConnection.GetConnection();
                // 执行查询
                try {
                    stmt = conn.createStatement();
                    String sql = "SELECT problem,reply  FROM problem where pid = "+aid;
                    rs = stmt.executeQuery(sql);
                    // 展开结果集数据库
                    while (rs.next()) {
                        // 通过字段检索
                        problem = rs.getString(1);
                        if(problem.equals("无")){
                            continue;
                        }
                        reply = rs.getString(2);
                        tableModel.addRow(new Object[]{problem,reply});
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
        JPanel jp4_2 = new JPanel();
        jp2.add(jp4_2);
        jp4_2.setPreferredSize(new Dimension(148,200));
        JButton jb4_1 = new JButton("增加问题");
        JButton jb4_2 = new JButton("修改问题");
        JButton jb4_3 = new JButton("删除问题");
        JLabel jl4_1 = new JLabel("修改后的问题");
        JLabel jl4_2 = new JLabel("提出的问题");

        JTextField jtf4_1 = new JTextField();
        jtf4_1.setPreferredSize(dimension);
        JButton jb4_4 = new JButton("提交");
        JButton jb4_5 = new JButton("提交");


        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                清空jp3中的组件
                jp3.removeAll();
                jp3.repaint();
                jp4_2.removeAll();
                jp4_2.repaint();
                jp3.add(scrollPane);
                jp4_2.add(jb4_1);
                jp4_2.add(jb4_2);
                jp4_2.add(jb4_3);
                jp3.updateUI();
                jp4_2.updateUI();
                frame.setVisible(true);
            }
        });
        jb4_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp4_2.remove(jl4_1);
                jp4_2.remove(jb4_5);
                jp4_2.add(jl4_2);
                jp4_2.add(jtf4_1);
                jp4_2.add(jb4_4);
                jp4_2.updateUI();
                frame.setVisible(true);

            }
        });
        jb4_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Add add= new Add();
                String problem = jtf4_1.getText();
//                    System.out.println(problem);
                boolean b = add.addProblem(aid,problem);
                jtf4_1.setText("");
                if (b) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jb4_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp4_2.remove(jl4_2);
                jp4_2.remove(jb4_4);
                jp4_2.add(jl4_1);
                jp4_2.add(jtf4_1);
                jp4_2.add(jb4_5);
                jp4_2.updateUI();
                frame.setVisible(true);
            }
        });
        jb4_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b =false;
                Update update = new Update();
                String problem2 = jtf4_1.getText();
                int row = jt.getSelectedRow(); //这句选择要修改的行
                if(row!=-1){
                    String problem1 = jt.getValueAt(row, 0).toString();
                    b = update.updateProblem(aid,problem1,problem2);
//                            System.out.println(problem1+","+problem2);
                    jtf4_1.setText("");
                }
                if (b) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jb4_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp4_2.remove(jl4_1);
                jp4_2.remove(jl4_2);
                jp4_2.remove(jtf4_1);
                jp4_2.remove(jb4_4);
                jp4_2.remove(jb4_5);
                jp4_2.updateUI();
                boolean b =false;
                Delete delete = new Delete();
                int row = jt.getSelectedRow(); //这句选择要修改的行
                if(row!=-1){
                    String problem = jt.getValueAt(row, 0).toString();
                    b = delete.deleteProblem(aid,problem);
//                            System.out.println(problem);
                    jtf4_1.setText("");
                    if (b) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        frame.setVisible(true);
    }
}
