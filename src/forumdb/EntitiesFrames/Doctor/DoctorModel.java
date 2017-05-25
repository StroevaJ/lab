
package forumdb.EntitiesFrames.Doctor;

import EntitiesClasses.Doctor;
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

public class DoctorModel extends AbstractTableModel {

    private List<Doctor> list = new ArrayList<>();
    private Connection c;

    public DoctorModel(Connection c) {
        super();
        this.c = c;
        updateData();
    }

    public void updateData() {
        list = getList();
        rowsCount = list.size();
    }
    int rowsCount = 0;
    int colCount = 4;

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
        String s = "";
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getFamilyD();
            case 1:
                return list.get(rowIndex).getNameD();
            case 2:
                return list.get(rowIndex).getMiddlenameD();
            case 3:
                return list.get(rowIndex).getProfilD();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Family";
            case 1:
                return "Name";
            case 2:
                return "Patronymic";
            case 3:
                return "Profil";
        }
        return null;
    }

    public Doctor getSelectesItem(int row) {
        return list.get(row);
    }
    
    public Doctor getRowById(int id) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM \"Doctor\" where \"id_doctor\"=" + id + ";");
        rs.next();    
        return new Doctor(rs.getInt("id_doctor"), rs.getString("familyD"), rs.getString("nameD"), rs.getString("middlenameD"), rs.getString("profilD"));
    }
    
    public List<Doctor> getList() {
        List<Doctor> tmp = new ArrayList<>();
         try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"Doctor\"");
            while (rs.next()) {
                Doctor item = new Doctor(rs.getInt("id_doctor"), rs.getString("familyD"), rs.getString("nameD"), rs.getString("middlenameD"), rs.getString("profilD"));
                tmp.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
         return tmp;
    }
    
    public  void insert(Doctor doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("insert into \"Doctor\" (\"familyD\", \"nameD\", \"middlenameD\", \"profilD\") values ('" + 
                        doc.getFamilyD()+ "','" + doc.getNameD()+
                        "','" + doc.getMiddlenameD()+ "' ,'" + doc.getProfilD()+ "');");
    }
    
    public void update(Doctor doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("update \"Doctor\" set \"familyD\"='" + doc.getFamilyD() + 
                        "', \"nameD\"='" + doc.getNameD() + 
                        "', \"middlenameD\"='" + doc.getMiddlenameD() + 
                        "', \"profilD\"='" + doc.getProfilD() + "' where \"id_doctor\"=" + doc.getId_doctor()+ ";");
    }
    
    public void delete(int id) throws SQLException {
        try {
                // TODO add your handling code here:
                Statement statement = c.createStatement();
                statement.executeUpdate("delete from \"Doctor\" where \"id_doctor\" = " + id + ";");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                return;
            }
    }
}
