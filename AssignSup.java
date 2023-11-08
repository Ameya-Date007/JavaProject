import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class AssignSup {
    JFrame frame;
    private JTextField SupIDText;
    private JTextField ShipIDText;

    private static int SHIP_ID;
    private static int Super_id;

    public static int getShipId() {
        return SHIP_ID;
    }

    public static int getSuper_id() {
        return Super_id;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AssignSup window = new AssignSup();
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
    public AssignSup() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Assign Supervisor");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel AssignLabel = new JLabel("Assign Supervisor");
        AssignLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AssignLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AssignLabel.setBounds(145, 32, 159, 28);
        frame.getContentPane().add(AssignLabel);

        JLabel EnterSupIDLabel = new JLabel("Enter Supervisor ID :");
        EnterSupIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EnterSupIDLabel.setBounds(80, 102, 133, 28);
        frame.getContentPane().add(EnterSupIDLabel);

        SupIDText = new JTextField();
        SupIDText.setBounds(256, 109, 96, 19);
        frame.getContentPane().add(SupIDText);
        SupIDText.setColumns(10);

        JLabel ShipIDLabel = new JLabel("Enter Ship ID :");
        ShipIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ShipIDLabel.setBounds(80, 140, 133, 28);
        frame.getContentPane().add(ShipIDLabel);

        ShipIDText = new JTextField();
        ShipIDText.setBounds(256, 145, 96, 19);
        frame.getContentPane().add(ShipIDText);
        ShipIDText.setColumns(10);

        JButton AssignButton = new JButton("Assign");
        AssignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Ship_id = ShipIDText.getText();
                String Sup_id = SupIDText.getText();
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                java.util.Date myDate = new Date();
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,user,password);
                    Statement st = con.createStatement();
                    Statement st1 = con.createStatement();
                    String Assign = "INSERT INTO ASSIGNMENT(SUPERVISORID , SHIPID, ASSIGNMENTDATE) VALUES('"+Sup_id+"' ,'"+Ship_id+"' ,'"+sqlDate+"')";
//                    String Retrieve_ShipId = "SELECT SUPERVISORID,SHIPID FROM ASSIGNMENT WHERE ASSIGNMENTID = 2";

                    st.executeUpdate(Assign);

                    JOptionPane.showMessageDialog(frame , "Successful");
                }
                catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });
        AssignButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        AssignButton.setBounds(180, 167, 85, 21);
        frame.getContentPane().add(AssignButton);
    }
}
