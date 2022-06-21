

import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// 登陆界面
public class Denglu extends Thread {
    public Denglu(){}

    public static void main(String[] args) {
        Denglu denglu = new Denglu();
        denglu.run();
    }
    public void run() {
        JFrame jf1 = new JFrame("高校党务管理系统");
        JPanel jp = new JPanel();
        JButton jb1 = new JButton("用户");
        JButton jb2 = new JButton("管理员");
        Font font = new Font("微软雅黑", Font.PLAIN, 25);
        JLabel jl = new JLabel("欢迎来到高校党务管理系统,请选择你要进入的身份。");
        jf1.setFont(font);
        jf1.add(jl, BorderLayout.NORTH);
        jp.add(jb1);
        jp.add(jb2);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf2 = new JFrame("用户登录");
                JPanel jp2 = new JPanel(new GridLayout(2, 2));
                JLabel jlb1 = new JLabel("请输入账号：");
                JLabel jlb2 = new JLabel("请输入密码：");
                JTextField jt = new JTextField();
                JPasswordField jPw = new JPasswordField();
                jp2.add(jlb1);
                jp2.add(jt);
                jp2.add(jlb2);
                jp2.add(jPw);
                JLabel jLabel = new JLabel("", JLabel.CENTER);
                JPanel jp3 = new JPanel();
                JButton jb1 = new JButton("账号登陆");
                JButton jb2 = new JButton("取消登录");
                jb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String st1 = jt.getText();
                        String st2 = new String(jPw.getPassword());
                        if (st1.equals("root") && st2.equals("123456789")) {

                            jLabel.setText("登陆成功!");
                            Font font = new Font("微软雅黑", Font.PLAIN, 25);
                            jLabel.setFont(font);
                            Query q = new Query();
                        } else {
                            jLabel.setText("登陆失败，请重新输入账号和密码");
                            jt.setText(null);
                            jPw.setText(null);
                            Font font = new Font("微软雅黑", Font.PLAIN, 25);
                            jLabel.setFont(font);
                        }
                    }
                });
                jb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf2.setVisible(false);
                    }
                });
                jp3.add(jb1);
                jp3.add(jb2);
                jf2.add(jp3);
                jf2.add(jLabel, BorderLayout.SOUTH);
                jf2.add(jp2, BorderLayout.NORTH);
                jf2.setSize(400, 300);
                jf2.setVisible(true);

            }
        });
        //管理员事件处理
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf2 = new JFrame("管理员登录");
                JPanel jp2 = new JPanel(new GridLayout(2, 2));
                JLabel jlb1 = new JLabel("请输入账号：");
                JLabel jlb2 = new JLabel("请输入密码：");
                JTextField jt = new JTextField();
                JPasswordField jPw = new JPasswordField();
                jp2.add(jlb1);
                jp2.add(jt);
                jp2.add(jlb2);
                jp2.add(jPw);
                JLabel jLabel = new JLabel("", JLabel.CENTER);
                JPanel jp3 = new JPanel();
                JButton jb1 = new JButton("账号登陆");
                JButton jb2 = new JButton("取消登录");

                jb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf2.setVisible(false);
                    }
                });
                jp3.add(jb1);
                jp3.add(jb2);
                jf2.add(jp3);
                jf2.add(jLabel, BorderLayout.SOUTH);
                jf2.add(jp2, BorderLayout.NORTH);
                jf2.setSize(400, 300);
                jf2.setVisible(true);
                //  GuanLi g=new GuanLi();

            }
        });
        jf1.add(jp, BorderLayout.CENTER);
        jf1.setSize(400, 300);
        jf1.setVisible(true);

        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    };
}

