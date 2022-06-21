package 图形化界面;

import 图形化界面.Admin.AdminSignIn;
import 图形化界面.Assessor.AssessorSignIn;
import 图形化界面.Student.StudentSignIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainpage {
    public static void main(String[] args) {
        new mainpage();
    }
    public mainpage(){

//        页面设置
        JFrame frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(400, 200);
        frame.setSize(400,400);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        标题
        JLabel jl1 = new JLabel("请选择您的身份");
        JPanel jp1 = new JPanel();
        jp1.add(jl1);
        frame.add(jp1, BorderLayout.NORTH);
//        选择
        JButton jb1 = new JButton("我是学生");
        JButton jb2 = new JButton("我是评定员");
        JButton jb3 = new JButton("我是管理员");
        JPanel jp2 = new JPanel();
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        frame.add(jp2,BorderLayout.CENTER);
        frame.setVisible(true);
//        事件监听
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //把本窗口销毁
                new StudentSignIn();
            }
        });

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //把本窗口销毁
                new AssessorSignIn();
            }
        });
        jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); //把本窗口销毁
                new AdminSignIn();
            }
        });

    }
}
