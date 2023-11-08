import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin_Cargo_Status implements ActionListener {
    protected JFrame frmUpdate;
    private JTable table;
    private JButton DisplayTableButton;
    private String ShipID;

    public void setShipID(String shipID) {
        ShipID = shipID;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_Cargo_Status window = new Admin_Cargo_Status();
                    window.frmUpdate.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Admin_Cargo_Status() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmUpdate = new JFrame("Cargo Status");
        frmUpdate.setTitle("View");
        frmUpdate.setBounds(100, 100, 900, 400);
        frmUpdate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmUpdate.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 21, 630, 320);
        frmUpdate.getContentPane().add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                }
        ));

        JButton BackButton = new JButton("Back");
        BackButton.addActionListener(this);
        BackButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        BackButton.setBounds(731, 199, 85, 27);
        frmUpdate.getContentPane().add(BackButton);

        DisplayTableButton = new JButton("Display Table");
        DisplayTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(ShipID);
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,user,password);
                    Statement st = con.createStatement();
                    String retrieve_info = "SELECT s.ShipName,s.ShipType,c.CargoID,c.CargoName,c.CargoType,c.Weight_in_kgs, sc.STATUS,sc.REASON FROM Ship s LEFT JOIN ShipCargo sc ON s.ShipID = sc.ShipID LEFT JOIN Cargo c ON sc.CargoID = c.CargoID WHERE s.ShipID = '"+ShipID+"'";
                    ResultSet rs = st.executeQuery(retrieve_info);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    int cols = rsmd.getColumnCount();
                    String[] colname = new String[cols];
                    for (int i = 0; i < cols; i++)
                        colname[i] = rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colname);
                    String Ship_Name , Ship_Type , Cargo_Name , Cargo_Type , Status , Reason;
                    int CargoID , Weight_in_kgs;
                    while (rs.next()){
                        Ship_Name = rs.getString(1);
                        Ship_Type = rs.getString(2);
                        CargoID = rs.getInt(3);
                        Cargo_Name = rs.getString(4);
                        Cargo_Type = rs.getString(5);
                        Weight_in_kgs = rs.getInt(6);
                        Status = rs.getString(7);
                        Reason = rs.getString(8);
                        String[] row = {Ship_Name,Ship_Type, String.valueOf(CargoID),Cargo_Name,Cargo_Type,String.valueOf(Weight_in_kgs),Status,Reason};
                        model.addRow(row);
                    }
                    con.close();
                }
                catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });
        DisplayTableButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        DisplayTableButton.setBounds(718, 125, 109, 27);
        frmUpdate.getContentPane().add(DisplayTableButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View_Cargo_Status v1 = new View_Cargo_Status();
        v1.frmView.setVisible(true);
        frmUpdate.dispose();
    }
}
