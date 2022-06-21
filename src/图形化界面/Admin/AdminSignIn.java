package 图形化界面.Admin;

import 数据库操作.Seek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSignIn {
    JFrame frame;
    JPanel jp1;
    JPanel jp2;
    JLabel jl1;
    JLabel jl2;
    JLabel jl3;
    JTextField jtf;
    JPasswordField jpf;
    JButton jb;

    public static void main(String[] args) {
        new AdminSignIn();
    }
    public AdminSignIn() {
        frame = new JFrame("软件学院德育学分评分系统");
        frame.setLocation(400, 200);
        frame.setSize(400,400);
        frame.setResizable(false);
        //  退出时关闭窗口
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jl1 = new JLabel("管理员登陆");
        jl2 = new JLabel("账号：");
        jl3 = new JLabel("密码：");
        jtf = new JTextField();
        jpf = new JPasswordField();
        jb = new JButton("登陆");

        frame.add(jp1,BorderLayout.NORTH);
        jp1.add(jl1);
        frame.add(jp2,BorderLayout.CENTER);
        jp2.add(jl2);
        jtf.setPreferredSize(new Dimension(150,20));
        jp2.add(jtf);
        jp2.add(jl3);
        jpf.setPreferredSize(new Dimension(150,20));
        jp2.add(jpf);
        jp2.setLayout(new FlowLayout(FlowLayout.LEADING,60,20));
        frame.add(jb,BorderLayout.SOUTH);
        frame.setVisible(true);

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = jtf.getText();
                char[] password = jpf.getPassword();
                Seek seek = new Seek();
                String password1 = seek.getAdminPassword(Long.parseLong(text));
                if(String.valueOf(password).equals(password1)){
//                    System.out.println("登录成功");
                    new AdminSystem();
                    frame.dispose(); //把本窗口销毁
                }
                else {
                    JOptionPane.showMessageDialog(null, "账号或密码错误", "警告", JOptionPane. ERROR_MESSAGE);
                }
            }
        });
    }
}



