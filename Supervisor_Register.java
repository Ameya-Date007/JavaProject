import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Supervisor_Register {
    protected JFrame frame;
    private JTextField NameField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Supervisor_Register window = new Supervisor_Register();
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
    public Supervisor_Register() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Supervisor Register");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel RegLabel = new JLabel("Register");
        RegLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        RegLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RegLabel.setBounds(140, 10, 168, 34);
        frame.getContentPane().add(RegLabel);

        JLabel NameLabel = new JLabel("Enter Name : ");
        NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        NameLabel.setBounds(32, 71, 112, 34);
        frame.getContentPane().add(NameLabel);

        NameField = new JTextField();
        NameField.setBounds(191, 74, 168, 34);
        frame.getContentPane().add(NameField);
        NameField.setColumns(10);

        JLabel Password = new JLabel("Enter Password :");
        Password.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Password.setHorizontalAlignment(SwingConstants.TRAILING);
        Password.setBounds(32, 115, 127, 37);
        frame.getContentPane().add(Password);

        passwordField = new JPasswordField();
        passwordField.setBounds(191, 118, 168, 34);
        frame.getContentPane().add(passwordField);

        JButton Register = new JButton("Register");
        Register.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                String Name = NameField.getText();
                String Password = String.valueOf(passwordField.getPassword());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , user , password);
                    Statement st = con.createStatement();
                    Statement id = con.createStatement();
                    String Reg = "INSERT INTO SUPERVISORS (NAME , PASSWORD) VALUES('"+Name+"','"+Password+"')";
                    String getID = "SELECT SUPERVISOR_ID FROM SUPERVISORS WHERE NAME = '"+Name+"'";
                    st.executeUpdate(Reg);
                    ResultSet rs = id.executeQuery(getID);
                    String Sup_id = null;
                    while (rs.next()){
                        Sup_id = rs.getString(1);
                    }
                    JOptionPane.showMessageDialog(frame,"You have successfully Registered!");
                    JOptionPane.showMessageDialog(frame,"Your id is : " + Sup_id);
                    frame.dispose();
                    Supervisor_Login s1 = new Supervisor_Login();
                    s1.frame.setVisible(true);
                    con.close();
                }
                catch(Exception exception){
                    System.out.println(exception);
                }
            }
        });
        Register.setBounds(177, 180, 95, 34);
        frame.getContentPane().add(Register);
    }
}
