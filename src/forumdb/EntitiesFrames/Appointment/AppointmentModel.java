
package forumdb.EntitiesFrames.Appointment;

import EntitiesClasses.Appointment;
import forumdb.EntitiesFrames.User.UserModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class AppointmentModel extends AbstractTableModel {

    private List<Appointment> list = new ArrayList<>();

    private Connection c;
    
    private UserModel modelUser;
    
    public UserModel getModelUser(){
        return modelUser;
    }
    
    

    public AppointmentModel(Connection c) {
        super();
        this.c = c;
        modelUser = new UserModel(c);
        updateData();
    }

    public void updateData() {
        list = getList();
        rowsCount = list.size();
    }
    int rowsCount = 0;
    int colCount = 2;

    @Override
    public int getRowCount() {
        return rowsCount;
    }

    @Override
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            switch (columnIndex) {
                case 0:
                    return modelUser.getRowById(list.get(rowIndex).getId_user()).getFamilyU();
                case 1:
                    return list.get(rowIndex).getDate();
            }
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "User";
            case 1:
                return "Data";
        }
        return null;
    }

    public Appointment getSelectesItem(int row) {
        return list.get(row);
    }
    
    public Appointment getRowById(int id) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM \"Appointment\" where \"id_appointment\"=" + id + ";");
        rs.next();    
        return new Appointment(rs.getInt("id_appointment"), rs.getInt("id_user"), rs.getString("date"));
    }
    
    public List<Appointment> getList() {
        List<Appointment> tmp = new ArrayList<>();
         try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"Appointment\"");
            while (rs.next()) {
                Appointment item = new Appointment(rs.getInt("id_appointment"), rs.getInt("id_user"), rs.getString("date"));
                tmp.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
         return tmp;
    }
    
    public  void insert(Appointment doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("insert into \"Appointment\" (\"id_user\", \"date\") values (" + 
                        doc.getId_user()+ ", '" + doc.getDate()+ "');");
    }
    
    public void update(Appointment doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("update \"Appointment\" set \"id_user\"='" + doc.getId_user() + 
                        "', \"date\"='" + doc.getDate() + "' where \"id_appointment\"=" + doc.getId_appointment()+ ";");
    }
    
    public void delete(int id) throws SQLException {
        try {
                // TODO add your handling code here:
                Statement statement = c.createStatement();
                statement.executeUpdate("delete from \"Appointment\" where \"id_appointment\" = " + id + ";");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                return;
            }
    }
    
}
