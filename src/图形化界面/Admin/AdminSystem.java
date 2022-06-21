package 图形化界面.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSystem {
    public static void main(String[] args) {
        new AdminSystem();
    }
    public AdminSystem(){
        JFrame frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(400, 200);
        frame.setSize(400,400);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        标题
        JLabel jl1 = new JLabel("请选择管理系统");
        JPanel jp1 = new JPanel();
        jp1.add(jl1);
        frame.add(jp1, BorderLayout.NORTH);
//        选择
        JButton jb1 = new JButton("学生信息管理");
        JButton jb2 = new JButton("教师信息管理");
        JPanel jp2 = new JPanel();
        jp2.add(jb1);
        jp2.add(jb2);
        frame.add(jp2,BorderLayout.CENTER);
        frame.setVisible(true);

//        事件监听
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //把本窗口销毁
                new StudentManagementSystem();
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //把本窗口销毁
                new TeacherManagementSystem();
            }
        });
    }
}
