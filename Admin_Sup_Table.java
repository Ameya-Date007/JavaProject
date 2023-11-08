import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin_Sup_Table implements ActionListener {
        protected JFrame frmUpdate;
        private JTable table;
        private JButton DisplayTableButton;
        static int Ship_ID;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Admin_Sup_Table window = new Admin_Sup_Table();
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
        public Admin_Sup_Table() {
            initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
            frmUpdate = new JFrame("Supervisor Table");
            frmUpdate.setTitle("Update");
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
                    final String url ="jdbc:mysql://localhost:3306/OOP_CP";
                    final String user = "root";
                    final String password = "Ad@19ENTC";
                    Ship_ID = Supervisor.getShip_Id();
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection(url,user,password);
                        Statement st = con.createStatement();
                        String retrieve_info = "SELECT SUPERVISOR_ID , NAME FROM SUPERVISORS";
                        ResultSet rs = st.executeQuery(retrieve_info);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        DefaultTableModel model = (DefaultTableModel) table.getModel();

                        int cols = rsmd.getColumnCount();
                        String[] colname = new String[cols];
                        for (int i = 0; i < cols; i++)
                            colname[i] = rsmd.getColumnName(i+1);
                        model.setColumnIdentifiers(colname);
                        String SupervisorID ,SupervisorName;
                        while (rs.next()){
                            SupervisorID = String.valueOf(rs.getInt(1));
                            SupervisorName = rs.getString(2);
                            String[] row = {SupervisorID,SupervisorName};
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
        Admin_GUI a = new Admin_GUI();
        a.frame.setVisible(true);
        frmUpdate.dispose();
    }
}
