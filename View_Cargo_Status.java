import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Cargo_Status {
    protected JFrame frmView;
    private JTextField IDCheckLabel;
    private String getID;

    public String getGetID() {
        return getID;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View_Cargo_Status window = new View_Cargo_Status();
                    window.frmView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public View_Cargo_Status() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmView = new JFrame("View Cargo Status");
        frmView.setTitle("View Shipment Status");
        frmView.setBounds(100, 100, 450, 300);
        frmView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmView.getContentPane().setLayout(null);

        JLabel TabLabel = new JLabel("View Shipment Status");
        TabLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TabLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TabLabel.setBounds(120, 21, 189, 33);
        frmView.getContentPane().add(TabLabel);

        JLabel CheckLabel = new JLabel("Check for Ship Id :");
        CheckLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CheckLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CheckLabel.setBounds(75, 93, 145, 24);
        frmView.getContentPane().add(CheckLabel);

        IDCheckLabel = new JTextField();
        IDCheckLabel.setBounds(230, 96, 145, 24);
        frmView.getContentPane().add(IDCheckLabel);
        IDCheckLabel.setColumns(10);

        JButton CheckButton = new JButton("Check");
        CheckButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getID = IDCheckLabel.getText();
                System.out.println(getID);
                Admin_Cargo_Status a = new Admin_Cargo_Status();
                a.setShipID(getID);
                a.frmUpdate.setVisible(true);
                frmView.dispose();
            }
        });
        CheckButton.setBounds(120, 161, 85, 21);
        frmView.getContentPane().add(CheckButton);

        JButton BackButton = new JButton("Back");
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin_GUI back = new Admin_GUI();
                frmView.dispose();
                back.frame.setVisible(true);
            }
        });
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        BackButton.setBounds(246, 161, 85, 21);
        frmView.getContentPane().add(BackButton);
    }
}
