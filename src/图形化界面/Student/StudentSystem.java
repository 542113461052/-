package 图形化界面.Student;

import 数据库操作.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentSystem {
    public static void main(String[] args) {
        new StudentSystem(Long.parseLong("2001010"));
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
//        标题
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("欢迎来到软件学院德育学分评分系统");
        jp1.add(jl1);
        jp1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
        frame.add(jp1, BorderLayout.NORTH);
//        左侧面板
        JPanel jp2 = new JPanel();
        JButton jb1 = new JButton("评分细则");
        JButton jb2 = new JButton("开始自评");
        JButton jb3 = new JButton("查看德育学分");
        JButton jb4 = new JButton("咨询");
        JButton jb5 = new JButton("个人信息修改");
        JButton jb6 = new JButton("退出系统");
        Dimension preferredSize = new Dimension(148, 60);    //设置尺寸
        jb1.setPreferredSize(preferredSize);    //设置按钮大小
        jb2.setPreferredSize(preferredSize);
        jb3.setPreferredSize(preferredSize);
        jb4.setPreferredSize(preferredSize);
        jb5.setPreferredSize(preferredSize);
        jb6.setPreferredSize(preferredSize);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        jp2.add(jb5);
        jp2.add(jb6);
        jp2.setPreferredSize(new Dimension(150, 600));
//        jp2.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        frame.add(jp2, BorderLayout.WEST);
        jp2.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));

        JPanel jp3_1 = new JPanel();
        JLabel jl3_1 = new JLabel("自评");
        JPanel jp3_3 = new JPanel();
        JLabel jl3_2 = new JLabel("基础分");
        JTextField jtf3_2 = new JTextField();
        JLabel jl3_3 = new JLabel("考察分");
        JTextField jtf3_3 = new JTextField();
        JLabel jl3_4 = new JLabel("加分项：");
        Dimension dimension = new Dimension(100, 20);
        jtf3_2.setPreferredSize(dimension);
        jtf3_3.setPreferredSize(dimension);
        JComboBox cmb = new JComboBox(); // 创建一个空的下拉列表
        cmb.addItem("-- 请选择 --"); // 向列表里添加内容
        cmb.addItem("无");
        cmb.addItem("参加集体活动"); // 向列表里添加内容
        cmb.addItem("荣誉奖励"); // 向列表里添加内容
        cmb.addItem("社会实践"); // 向列表里添加内容
        cmb.addItem("社会公德"); // 向列表里添加内容
        cmb.addItem("集体表彰"); // 向列表里添加内容
        cmb.addItem("学生干部"); // 向列表里添加内容
        cmb.addItem("其他"); // 向列表里添加内容
        JLabel label = new JLabel("证明材料：");
        JTextField jtf3_1 = new JTextField(25);
        JButton button = new JButton("浏览");
        JPanel jp3_4 = new JPanel();
        JButton jb3_1 = new JButton("提交");
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


        JLabel jl = new JLabel("姓名");
        JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(120, 20));
        JLabel jl2 = new JLabel("密码");
        JPasswordField jpf2 = new JPasswordField();
        jpf2.setPreferredSize(new Dimension(120, 20));
        JPanel jp4 = new JPanel();
        JButton jb = new JButton("提交");


        JFileChooser fc = new JFileChooser();
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jp4_2.removeAll();
                jp4_2.repaint();
                String s = "一、总则：\n" +
                        "  为了德育学分评定工作更加规范，特制定本考评体系。本考评体系细则共分四部分，包括基础分、考察分、加分和扣分，学期考评分=基础分(60)+考察分(10)+加分-扣分，学生思想品德综合考评采用百分制：最高分为100分，超过的按100分计算。\n" +
                        "二、加分项\n" +
                        "  1、参加集体活动\n" +
                        "    （1）参加校、院联赛性质的活动（如院篮球赛(参赛者每人加2分，观众加1分)，校篮球赛，校足球赛，“华语杯”辩论赛……）参赛者参加2场及以上者加4分，观众参加2场及以上者加2分。对于参加具体活动并获得国家、省、市、校、院级奖励的团体或个人，参照“荣誉奖励”部分给予加分。\n" +
                        "    （2）参加班级活动的，需在活动举办前交活动计划，举办后交总结，由辅导员老师签字方可加分，1分/人次，上限为6分。\n" +
                        "    （3）对于以上活动积极配合的同学（如观众、拉拉队员）针对每次活动加1分。\n" +
                        "    （4）迎新晚会：加4分/人（参与演出的同学）；校运动会：加4分/人（参加比赛的同学）；走方块队的同学每人加2分。一二·九大合唱：加4分/人（参与训练的同学）；\n" +
                        "   对于以上三项活动获奖者，每人追加2分。(可累计加分，一个证书加2分)三好学生证书加分，奖学金证书不加分。\n" +
                        "     注：\n" +
                        "     1、以上活动多参加者可以累计加分。\n" +
                        "     2、对于学生干部参与各项校级、院级活动，职责范围内不予加分。\t\n" +
                        "     3、集体活动分的上限为10分\n" +
                        "  2、荣誉奖励(必须有复印件，没证书的必须有对应单位证明方可加分。)\n" +
                        "     凡获得各类各级荣誉称号和有关奖励者，加分标准如下：\n" +
                        "    （1）国家级荣誉称号和奖励：加10分/次；\n" +
                        "    （2）省级荣誉称号和奖励：加8分/次；\n" +
                        "    （3）市级荣誉称号和奖励：加6分/次；\n" +
                        "    （4）校级荣誉称号和奖励：标兵、十佳：加4分/次，其他称号如优秀团员、优秀团干加3分/次；\n" +
                        "    （5）院级荣誉称号和奖励：加3分/次。\n" +
                        "    （6）因表现突出受到各级通报表扬的，每次加2-10分。\n" +
                        "  3、社会实践(暑期活动表及报告有一样不交，扣2分，交了不加分)\n" +
                        "    积极参加社会实践，获得各级各类奖励者，加分标准如下：\n" +
                        "    （1）国家级奖励：加10分/次；\n" +
                        "    （2）省级奖励：加8分/次；\n" +
                        "    （3）市级奖励：加6分/次；\n" +
                        "    （4）校级奖励：加4分/次。\n" +
                        "  4、社会公德\t\n" +
                        "    大一的基础分为5分、大二的基础分为7分，大三8分、大四的基础分为10分，对于违反学校规章制度，若损害公物，有不文明表现视情节严重，若发现一次，给予扣3-5分处理。若有见义勇为，与坏人坏事作斗争，自觉维护社会（校园）公共秩序，揭发违法、违纪行为，拾金不昧，助人为乐，受到有关部门表扬、表彰者，每次加5分。\n" +
                        "  5、集体表彰\n" +
                        "    集体（班集体、团支部）受到国家、省级、市级、校级、院级表彰的，该集体成员每人加6、5、4、3、2分。三次被评为院级优秀宿舍的，该宿舍中的成员加5分/人，两次被评为院级差扣5分。优秀宿舍和集体表彰的加分上限为6分。检查寝室按照软件学院生活部检查标准为主。\n" +
                        "  6、学生干部加分\n" +
                        "    学生干部能够起到模范带头作用，切实履行好本职工作，每学期结束时按情况加分（按本学期所任职务为准）。\n" +
                        "    注：\n" +
                        "    校学生会、艺术团、记者团、广播站、轻院青年，国旗班的成员加分参照学生会干部标准。\n" +
                        "    对于身兼多职的学生干部，以所任职务中最高分为标准进行加分，不得累加。\n" +
                        "    期末评定时，班级全体成员对班级干部进行投票，超过半数的才可加分。\n" +
                        "  7、其他加分项\n" +
                        "    （1）四级英语通过者加4分，六级加6分（只加分一次）。\n" +
                        "    （2）考取国家认证取得证书者加4分。不包括学校统一报名考试（如河南省计算机等级考试）。\n" +
                        "    （3）跟老师做项目，表现良好且出具老师证明者加4分。\n" +
                        "    （4）在公开发行的学术刊物国家级、省市级、校级、院级刊物上发表论文及文章者分别加10分、8分、6分、4分。\n" +
                        "    （5）通过计算机三级及三级以上考试者，一次性加4分；\n" +
                        "    （6）有关于微博、微信、观看视频等相关截图加减德育学分规定如下：每次活动加1分/人（算入集体活动分），全程参与者额外加2分（不算入集体活动分）。\n" +
                        "三、扣分部分\n" +
                        "  1、凡违反校规校纪，受通报批评者，每人每次扣5分；集体受到通报批评的，扣当事人相应分数，集体成员每人扣１分/次，集体负责人扣２分／次；找不到当事人的，所有成员扣相应分数；（严重警告扣15分，警告扣10分）。\n" +
                        "  2、院级或校级活动，需要各班抽部分同学进行参加，报名人数不足或参与同学旷到，班集体全体成员基础分扣1分/次（院级），2分/次（校级）。\n" +
                        "  3、凡违反《学生公寓管理办法》中规定的，受到学生公寓管理部门查处，除在“宿舍表现考察分”中扣分外，宿舍卫生受校、院通报批评者（或集体），每人扣2分；影响他人休息、打游戏、上网浏览不健康信息，据查证属实者，每次扣2分。对于寝室检查卫生，优秀寝室（三次及三次以上优秀），宿舍每人加5分，对于脏乱差寝室（两次脏乱差），每人每次扣5分。检查寝室按照软件学院生活部检查标准为主。\n" +
                        "  4、在课堂教育中，集体活动或各类会议中，凡无正当理由缺勤或迟到早退者，分别扣3-5分。注：依据任课老师记录和相关部门记录为准。\n" +
                        "  5、本人因故不能正常参加集体活动者，可以请假。请假者须持请假条（写明请假原因，请假时间），经辅导员老师签字批准后方可请假。如有紧急情况，事前确实不能例行请假手续者，需履行口头请假手续，事后及时补假，并向辅导员老师说明原因。本人将请假情况报所在班级考勤人员处记录。请假者必须按时到辅导员老师和班级考勤人员处消假，否则按细则有关规定处理；参加学校、院、班级的集体活动无故缺勤者，分别扣除5，4，3分。\n" +
                        "  6、（1）受到社会舆论批评者扣5分；\n" +
                        "     （2）受到警告处分以上者，本学期德育学分为0分；\n" +
                        "     （3）受到记过以上处分者德育学分0分；\n" +
                        "     （4）受到法律法规，司法部门处分者0分；\n" +
                        "四、附则\n" +
                        "  1.每学期末，依据学生考评成绩，确定当学期应得的总德育学分：\n" +
                        "    （1）德育学分达60~70分以上者，折合2个总德育学分；\n" +
                        "    （2）德育学分达70~80分以上者，折合4个总德育学分；\n" +
                        "    （3）德育学分达80~90分以上者，折合5个总德育学分；\n" +
                        "    （4）德育学分达90分以上者，折合6个总德育学分；\n" +
                        "  2.德育学分达90分以上者，方可参与一等奖学金的评比；德育学分达80分以下者，不能参与奖学金的评定。\n" +
                        "  3.加分部分除集体表彰以外，其它各项加分由本人申请并出示相关证明；班级团支部和相关部门对每次活动的参与情况作好记录；扣分部分（可以累计扣分）在期末总评时由班级团支部负责扣分。\n" +
                        "  4.评德育学分前后，必须开班会提前通知，全班公示。其中，每个月的总结加分也必须公示，还需要全班同学签名确定，确保全体同学对自己这个月的德育分的情况有必要的了解，保证公平公正。\n";
//              清空jp3里面的原有的组件
                jp3.removeAll();
                jp3.repaint();
                JTextArea jt = new JTextArea(s);
                JScrollPane js = new JScrollPane();
                js.setViewportView(jt);
                jp3.add(js);
//              更新jp3里面的的组件
                jp3.updateUI();
                frame.setVisible(true);
//
            }
        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//              清空jp3里面的原有的组件
                jp3.removeAll();
                jp3.repaint();
                jp4_2.removeAll();
                jp4_2.repaint();
                //        加分项选择
                jp3_1.add(jl3_1);
                jp3.add(jp3_1, BorderLayout.NORTH);
                jp3_3.add(jl3_2);
                jp3_3.add(jtf3_2);
                jp3_3.add(jl3_3);
                jp3_3.add(jtf3_3);
                jp3_3.add(jl3_4);
                jp3_3.add(cmb);
                jp3_4.add(label);
                jp3_4.add(jtf3_1);
                jp3_4.add(button);
                jp3_3.add(jp3_4);
                jp3_2.add(jp3_3);
                jp3_2.add(jp3_4);
                jp3_2.add(jb3_1);
                jp3.add(jp3_2, BorderLayout.CENTER);
//              更新jp3里面的的组件
                jp3.updateUI();
                frame.add(jp3, BorderLayout.CENTER);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        int val = fc.showOpenDialog(null);    //文件打开对话框
                        if (val == fc.APPROVE_OPTION) {
                            //正常选择文件
                            jtf3_1.setText(fc.getSelectedFile().toString());
                        } else {
                            //未正常选择文件，如选择取消按钮
                            jtf3_1.setText("未选择文件");
                        }
                    }
                });

                frame.setVisible(true);
            }
        });
        jb3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int basic = 0;
                basic = (int) Long.parseLong(jtf3_2.getText());
                jtf3_2.setText("");
                int investigate = 0;
                investigate = (int) Long.parseLong(jtf3_3.getText());
                jtf3_3.setText("");
                String bonusitem = cmb.getSelectedItem().toString();
                GetConnection getConnection = new GetConnection();
                Connection connection = getConnection.GetConnection();
                Statement statement = null;
                String sql = null;
                ResultSet resultSet = null;
                boolean b = false;
                try {
                    statement = connection.createStatement();
                    sql = "update student set bonusitem ="+"'"+bonusitem+"'"+","+"enclosure = "+"'"+fc.getSelectedFile().toString()+"'"+"   where sid =" + aid;
//                            System.out.println(sql);
                    statement.executeUpdate(sql);
                    Update update = new Update();
                    Seek seek = new Seek();
                    int[] fraction = seek.getFraction(aid);
                    b = update.updateFraction(aid, basic, investigate, fraction[2], fraction[3]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                    }try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                    }try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (SQLException ex) {
                    }
                }
                if (b) {
                    JOptionPane.showMessageDialog(null, "自评成功");
                } else {
                    JOptionPane.showMessageDialog(null, "自评失败", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //              清空jp3里面的原有的组件
                jp3.removeAll();
                jp3.repaint();
                jp4.removeAll();
                jp4.repaint();
                jp4_2.removeAll();
                jp4_2.repaint();
                Seek seek = new Seek();
                int[] fraction = seek.getFraction(aid);
                JLabel jl = new JLabel("基础分为" + fraction[0]);
                JLabel jl1 = new JLabel("考察分为" + fraction[1]);
                JLabel jl2 = new JLabel("加分为" + fraction[2]);
                JLabel jl3 = new JLabel("扣分为" + fraction[3]);
                int sum = fraction[2] + fraction[1] + fraction[0] - fraction[3];
                if (sum > 100) {
                    sum = 100;
                }
                JLabel jl4 = new JLabel("总分为" + sum);
                Dimension preferredSize = new Dimension(100, 60);    //设置尺寸
                jl.setPreferredSize(preferredSize);    //设置按钮大小
                jl1.setPreferredSize(preferredSize);
                jl2.setPreferredSize(preferredSize);
                jl3.setPreferredSize(preferredSize);
                jl4.setPreferredSize(preferredSize);
                jp4.add(jl);
                jp4.add(jl1);
                jp4.add(jl2);
                jp3.add(jl3);
                jp4.add(jl4);
                //              更新jp3里面的的组件
                jp4.updateUI();
                jp3.add(jp4);
                jp3.updateUI();

                frame.setVisible(true);
            }
        });
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


        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp4.removeAll();
                jp4.repaint();
                jp4_2.removeAll();
                jp4_2.repaint();
                jp4.add(jl);
                jp4.add(jtf);
                jp4.add(jl2);
                jp4.add(jpf2);
                jp4.add(jb);
                //              清空jp3里面的原有的组件
                jp3.removeAll();
                jp3.repaint();
                jp3.add(jp4);
                //              更新jp3里面的的组件
                jp3.updateUI();
                jp4.updateUI();
            }
        });
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update update = new Update();
                boolean b = false;
                GetConnection getConnection = new GetConnection();
                Connection connection = getConnection.GetConnection();
                Statement statement = null;
                String sql =null;
                GetConnection getConnection2 = new GetConnection();
                Connection connection2 = getConnection2.GetConnection();
                Statement statement2 = null;
                String sql2 =null;
                try {
                    statement = connection.createStatement();
                    sql = "update assessor set aname ="+"'"+jtf.getText()+"'"+",password ="+"'"+String.valueOf(jpf2.getPassword())+"'"+"  where aid =" + aid;
                            System.out.println(sql);
                    statement.executeUpdate(sql);
                    statement2 = connection2.createStatement();
                    sql2 = "update fraction set name ="+"'"+jtf.getText()+"'"+"  where fid =" + aid;
                    System.out.println(sql2);
                    statement2.executeUpdate(sql2);
                    b = update.updateStudent(aid, jtf.getText(), "", String.valueOf(jpf2.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                jtf.setText("");
                jpf2.setText("");
                if (b) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败", "", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
