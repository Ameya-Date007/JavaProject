import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin_Login extends JFrame implements ActionListener {
    protected JFrame frame;
    private JTextField IDField;
    private JTextField NameField;
    private JPasswordField PasswordField;
    String ID , NAME , PASSWORD;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_Login window = new Admin_Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Admin_Login() {
        initialize();
    }

    public void initialize(){
        frame = new JFrame("Admin Login");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel AdminLoginLabel = new JLabel("Admin Login");
        AdminLoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        AdminLoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AdminLoginLabel.setBounds(146, 10, 148, 26);
        frame.getContentPane().add(AdminLoginLabel);

        JLabel IDLabel = new JLabel("Enter ID :");
        IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        IDLabel.setBounds(35, 60, 70, 26);
        frame.getContentPane().add(IDLabel);

        IDField = new JTextField();
        IDField.setBounds(196, 63, 148, 26);
        frame.getContentPane().add(IDField);
        IDField.setColumns(10);

        JLabel NameLabel = new JLabel("Enter Name :");
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        NameLabel.setBounds(35, 96, 90, 26);
        frame.getContentPane().add(NameLabel);

        NameField = new JTextField();
        NameField.setBounds(196, 99, 148, 26);
        frame.getContentPane().add(NameField);
        NameField.setColumns(10);

        JLabel PasswordLabel = new JLabel("Enter Password :");
        PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        PasswordLabel.setBounds(35, 134, 112, 26);
        frame.getContentPane().add(PasswordLabel);

        PasswordField = new JPasswordField();
        PasswordField.setBounds(196, 135, 148, 26);
        frame.getContentPane().add(PasswordField);

        JButton LoginButton = new JButton("Login");
        LoginButton.addActionListener(this);
        LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LoginButton.setBounds(174, 196, 85, 26);
        frame.getContentPane().add(LoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String url ="jdbc:mysql://localhost:3306/OOP_CP";
        final String user = "root";
        final String password = "Ad@19ENTC";
        ID = IDField.getText();
        NAME = NameField.getText();
        PASSWORD = String.valueOf(PasswordField.getPassword());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            String Login = "SELECT * FROM ADMIN WHERE ID = '"+ID+"' AND NAME = '"+NAME+"' AND PASSWORD = '"+PASSWORD+"'";
            ResultSet rs = st.executeQuery(Login);
            Admin_GUI G = new Admin_GUI();
            if (rs.next()) {
                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Welcome Admin!");
                con.close();
                G.frame.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Id or Username or password wrong");
                IDField.setText("");
                NameField.setText("");
                PasswordField.setText("");
            }
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }
}
