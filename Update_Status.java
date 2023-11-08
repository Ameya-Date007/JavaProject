import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Update_Status extends JFrame implements ActionListener {
    protected JFrame frmUpdate;
    private JTable table;
    private JButton UpdateStatusButton;
    private JButton DisplayTableButton;
    static int Ship_ID;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Update_Status window = new Update_Status();
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
    public Update_Status() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmUpdate = new JFrame();
        frmUpdate.setTitle("Update Cargo Status");
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

        UpdateStatusButton = new JButton("Update");
        UpdateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               UpdateGUI g = new UpdateGUI();
               g.frame.setVisible(true);
            }
        });
        UpdateStatusButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        UpdateStatusButton.setBounds(731, 162, 85, 27);
        frmUpdate.getContentPane().add(UpdateStatusButton);

        DisplayTableButton = new JButton("Display Table");
        DisplayTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                final String user = "root";
                final String password = "Ad@19ENTC";
                Ship_ID = Supervisor.getShip_Id();
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,user,password);
                    Statement st = con.createStatement();
                    String retrieve_info = "SELECT s.ShipName,s.ShipType,c.CargoID,c.CargoName,c.CargoType,c.Weight_in_kgs FROM Ship s LEFT JOIN ShipCargo sc ON s.ShipID = sc.ShipID LEFT JOIN Cargo c ON sc.CargoID = c.CargoID WHERE s.ShipID = '"+Ship_ID+"'";
                    ResultSet rs = st.executeQuery(retrieve_info);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    int cols = rsmd.getColumnCount();
                    String[] colname = new String[cols];
                    for (int i = 0; i < cols; i++)
                        colname[i] = rsmd.getColumnName(i+1);
                    model.setColumnIdentifiers(colname);
                    String Ship_Name , Ship_Type , Cargo_Name , Cargo_Type;
                    int CargoID , Weight_in_kgs;
                    while (rs.next()){
                        Ship_Name = rs.getString(1);
                        Ship_Type = rs.getString(2);
                        CargoID = rs.getInt(3);
                        Cargo_Name = rs.getString(4);
                        Cargo_Type = rs.getString(5);
                        Weight_in_kgs = rs.getInt(6);
                        String[] row = {Ship_Name,Ship_Type, String.valueOf(CargoID),Cargo_Name,Cargo_Type,String.valueOf(Weight_in_kgs)};
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
        Supervisor s = new Supervisor();
        s.frame.setVisible(true);
        frmUpdate.dispose();
    }
}
