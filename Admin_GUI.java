import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin_GUI extends JFrame implements ActionListener {
    protected JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_GUI window = new Admin_GUI();
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
    public Admin_GUI() {
        initialize();
    }

    public void initialize(){
        frame = new JFrame("Admin");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        JButton ViewShipmentButton = new JButton("View Shipment Status");
        ViewShipmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                View_Cargo_Status vShip = new View_Cargo_Status();
                vShip.frmView.setVisible(true);
            }
        });
        ViewShipmentButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ViewShipmentButton.setBounds(151, 91, 159, 30);
        frame.getContentPane().add(ViewShipmentButton);

        JButton AssignSupButton = new JButton("Assign Supervisor");
        AssignSupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignSup s = new AssignSup();
                s.frame.setVisible(true);
            }
        });
        AssignSupButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        AssignSupButton.setBounds(237, 41, 191, 30);
        frame.getContentPane().add(AssignSupButton);

        JButton ViewSupervisors = new JButton("View Supervisors list");
        ViewSupervisors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Sup_Table st1 = new Admin_Sup_Table();
                st1.frmUpdate.setVisible(true);
            }
        });
        ViewSupervisors.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ViewSupervisors.setBounds(36, 41, 191, 30);
        frame.getContentPane().add(ViewSupervisors);

        JButton Logout = new JButton("Logout");
        Logout.addActionListener(this);
        Logout.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Logout.setBounds(176, 192, 85, 21);
        frame.getContentPane().add(Logout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Admin_Login adminLogin = new Admin_Login();
        frame.dispose();
        adminLogin.frame.setVisible(true);
    }
}
