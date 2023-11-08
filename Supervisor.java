import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Supervisor implements ActionListener{
    protected JFrame frame;
    private JTextField NameField;
    private JTextField IDField;

    static int Ship_Id;

    public static int getShip_Id() {
        return Ship_Id;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Supervisor window = new Supervisor();
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
    public Supervisor() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Supervisor");
        frame.setBounds(100, 100, 600, 309);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel Supervisor_Options = new JPanel();
        Supervisor_Options.setBounds(10, 10, 237, 343);
        frame.getContentPane().add(Supervisor_Options);
        Supervisor_Options.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(257, 10, 319, 343);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton View = new JButton("View Assigned Cargoes");
        View.setFont(new Font("Tahoma", Font.PLAIN, 13));
        View.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Update_Status u = new Update_Status();
                JOptionPane.showMessageDialog(frame , "Your Assigned Ship_Id is : " +Supervisor_Login.getAssigned_Ship_ID());
                Ship_Id = Supervisor_Login.getAssigned_Ship_ID();
                System.out.println(Ship_Id);
                u.frmUpdate.setVisible(true);
                panel.setVisible(false);
            }
        });
        View.setBounds(10, 58, 180, 45);
        Supervisor_Options.add(View);


        JLabel Name = new JLabel("Enter new name :");
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Name.setBounds(18, 58, 147, 39);
        panel.add(Name);

        JLabel ID = new JLabel("Enter your ID to verify:");
        ID.setHorizontalAlignment(SwingConstants.CENTER);
        ID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ID.setBounds(18, 119, 147, 39);
        panel.add(ID);

        JButton UpdateProfileButton = new JButton("Update");
        UpdateProfileButton.addActionListener(this);
        UpdateProfileButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        UpdateProfileButton.setBounds(116, 190, 85, 21);
        panel.add(UpdateProfileButton);

        NameField = new JTextField();
        NameField.setBounds(175, 65, 134, 29);
        panel.add(NameField);
        NameField.setColumns(10);

        IDField = new JTextField();
        IDField.setBounds(175, 126, 134, 29);
        panel.add(IDField);
        IDField.setColumns(10);

        JButton Edit = new JButton("Edit Profile");
        Edit.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(true);
            }
        });
        Edit.setBounds(10, 125, 180, 45);
        Supervisor_Options.add(Edit);
        panel.setVisible(false);

        JButton LogoutButton = new JButton("Logout");
        LogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 7));
        LogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Supervisor_Login s1 = new Supervisor_Login();
                frame.dispose();
                s1.frame.setVisible(true);
            }
        });
        LogoutButton.setBounds(32, 212, 59, 21);
        Supervisor_Options.add(LogoutButton);
        panel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String url ="jdbc:mysql://localhost:3306/OOP_CP";
        final String user = "root";
        final String password = "Ad@19ENTC";
        String update_Name = NameField.getText();
        int ID = Integer.parseInt(IDField.getText());
        System.out.println(ID);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url , user , password);
            Statement st = con.createStatement();
            String Update_Profile = "UPDATE SUPERVISORS SET NAME = '"+update_Name+"' WHERE SUPERVISOR_ID = '"+ID+"'";
            st.executeUpdate(Update_Profile);
            JOptionPane.showMessageDialog(frame,"Profile updated successfully!");
            con.close();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }
}
