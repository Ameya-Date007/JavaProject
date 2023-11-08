import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Main_Frame {

    private JFrame MainFrame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main_Frame window = new Main_Frame();
                    window.MainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main_Frame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        MainFrame = new JFrame("Main Frame");
        MainFrame.setBounds(100, 100, 450, 300);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.getContentPane().setLayout(null);

        JLabel Main_Label = new JLabel("Ships and Port Management System");
        Main_Label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Main_Label.setBounds(93, 10, 279, 27);
        MainFrame.getContentPane().add(Main_Label);

        JButton AdminButton = new JButton("Admin");
        AdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin_Login a = new Admin_Login();
                a.frame.setVisible(true);
            }
        });
        AdminButton.setBounds(67, 116, 118, 38);
        MainFrame.getContentPane().add(AdminButton);

        JButton SupervisorButton = new JButton("Supervisor");
        SupervisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Supervisor_Login s = new Supervisor_Login();
                s.frame.setVisible(true);
            }
        });
        SupervisorButton.setBounds(248, 116, 118, 38);
        MainFrame.getContentPane().add(SupervisorButton);
    }
}
