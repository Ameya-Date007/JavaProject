import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Supervisor_Login{
    protected JFrame frame;
    public JTextField textField;
    private JPasswordField passwordField;

    static int Assigned_Ship_ID;


    public static int getAssigned_Ship_ID() {
        return Assigned_Ship_ID;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Supervisor_Login window = new Supervisor_Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Supervisor_Login(){
        frame = new JFrame("Supervisor Login");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel SupervisorLogin = new JLabel("Login");
        SupervisorLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
        SupervisorLogin.setHorizontalAlignment(SwingConstants.CENTER);
        SupervisorLogin.setBounds(203, 32, 192, 32);
        frame.getContentPane().add(SupervisorLogin);

        JLabel SupervisorID = new JLabel("ID :");
        SupervisorID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SupervisorID.setHorizontalAlignment(SwingConstants.TRAILING);
        SupervisorID.setBounds(63, 124, 95, 13);
        frame.getContentPane().add(SupervisorID);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBounds(215, 117, 180, 32);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel Password = new JLabel("Password :");
        Password.setHorizontalAlignment(SwingConstants.TRAILING);
        Password.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Password.setBounds(65, 191, 95, 13);
        frame.getContentPane().add(Password);

        passwordField = new JPasswordField();
        passwordField.setBounds(215, 184, 180, 32);
        frame.getContentPane().add(passwordField);

        JButton Reg = new JButton("Register");
        Reg.setFont(new Font("Tahoma", Font.BOLD, 14));
        Reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Supervisor_Register r1 = new Supervisor_Register();
                frame.dispose();
                r1.frame.setVisible(true);
            }
        });
        Reg.setBounds(257, 309, 95, 32);
        frame.getContentPane().add(Reg);

        JLabel Register = new JLabel("Not a supervisor?");
        Register.setFont(new Font("Tahoma", Font.BOLD, 15));
        Register.setHorizontalAlignment(SwingConstants.CENTER);
        Register.setBounds(215, 280, 180, 19);
        frame.getContentPane().add(Register);

        JButton LoginButton = new JButton("Login");
        LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                String id = textField.getText();
                String password1 = String.valueOf(passwordField.getPassword());
                Supervisor s = new Supervisor();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , user , password);
                    Statement st = con.createStatement();
                    Statement st1 = con.createStatement();
                    String verify = "SELECT*FROM SUPERVISORS WHERE SUPERVISOR_ID = '"+id+"' AND PASSWORD = '"+password1+"'";
                    ResultSet rs = st.executeQuery(verify);
//                    String Assigned_ShipID = "SELECT SHIPID FROM ASSIGNMENT WHERE SUPERVISORID = '"+id+"'";
                    String Assigned_ShipID = "SELECT ASSIGNMENT.ShipID FROM SUPERVISORS, ASSIGNMENT, SHIP WHERE SHIP.ShipID = ASSIGNMENT.ShipID and ASSIGNMENT.SupervisorID = '"+id+"' ";

                    ResultSet rs1 = st1.executeQuery(Assigned_ShipID);
                    if (rs1.next()){
                        //Assigned_Ship_ID = Integer.parseInt(textField.getText());
                        Assigned_Ship_ID = rs1.getInt(1);
                    }
                        if (rs.next()) {
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Welcome, "+rs.getString(2 ));
                        con.close();
                        s.frame.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Username or password wrong");
                        textField.setText("");
                        passwordField.setText("");
                    }
                }
                catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });
        LoginButton.setBounds(257, 238, 95, 32);
        frame.getContentPane().add(LoginButton);
        }

}

