import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateGUI implements ActionListener{
    private String s;
    private String f;
    private String Status;
    protected JFrame frame;
    private JTextField ReasonText;
    private JTextField CargoIDField;
    JRadioButton SuccessRadio;
    JRadioButton FailButton;
    private static int Ship_id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateGUI window = new UpdateGUI();
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
    public UpdateGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Update GUI");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        SuccessRadio = new JRadioButton("Successful");
        SuccessRadio.addActionListener(this);
        SuccessRadio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SuccessRadio.setBounds(153, 34, 103, 21);
        frame.getContentPane().add(SuccessRadio);

        JLabel StatusLabel = new JLabel("Status : ");
        StatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        StatusLabel.setBounds(39, 29, 88, 30);
        frame.getContentPane().add(StatusLabel);

        FailButton = new JRadioButton("Failed");
        FailButton.addActionListener(this);
        FailButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        FailButton.setBounds(283, 34, 103, 21);
        frame.getContentPane().add(FailButton);

        ButtonGroup group = new ButtonGroup();
        group.add(SuccessRadio);
        group.add(FailButton);

        JLabel ReasonLabel = new JLabel("Reason :");
        ReasonLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ReasonLabel.setBounds(39, 131, 71, 30);
        frame.getContentPane().add(ReasonLabel);

        ReasonText = new JTextField();
        ReasonText.setBounds(158, 134, 209, 30);
        frame.getContentPane().add(ReasonText);
        ReasonText.setColumns(10);
        ReasonText.setVisible(false);

        JLabel CargoIdLabel = new JLabel("Enter Cargo Id :");
        CargoIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CargoIdLabel.setBounds(39, 80, 110, 30);
        frame.getContentPane().add(CargoIdLabel);

        CargoIDField = new JTextField();
        CargoIDField.setBounds(158, 80, 209, 30);
        frame.getContentPane().add(CargoIDField);
        CargoIDField.setColumns(10);

        JButton SubmitButton = getjButton();
        frame.getContentPane().add(SubmitButton);
    }

    private JButton getjButton() {
        JButton SubmitButton = new JButton("Submit");
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                String Reason = ReasonText.getText();
                int Cargo_id = Integer.parseInt(CargoIDField.getText());
                Ship_id = Supervisor.getShip_Id();
                System.out.println(Ship_id);
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , user , password);
                    Statement st = con.createStatement();
                    String update_with_failure = "UPDATE SHIPCARGO SET STATUS = '"+f+"' , REASON = '"+Reason+"' WHERE SHIPID = '"+Ship_id+"' AND CARGOID = '"+Cargo_id+"'";
                    String update_with_success = "UPDATE SHIPCARGO SET STATUS = '"+s+"' , REASON = '"+null+"' WHERE SHIPID = '"+Ship_id+"' AND CARGOID = '"+Cargo_id+"'";
                    if (SuccessRadio.isSelected()) {
                        st.executeUpdate(update_with_success);
                        JOptionPane.showMessageDialog(frame,"Successfully updated!");
                    }
                    else if (FailButton.isSelected()) {
                        st.executeUpdate(update_with_failure);
                        JOptionPane.showMessageDialog(frame, "Successfully updated!");
                    }
                    con.close();
                }
                catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });
        SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SubmitButton.setBounds(171, 191, 85, 30);
        return SubmitButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SuccessRadio) {
            s = SuccessRadio.getText();
            ReasonText.setVisible(false);
        } else if (e.getSource() == FailButton) {
            f = FailButton.getText();
            ReasonText.setVisible(true);
        }
    }
}
