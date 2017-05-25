
package forumdb.EntitiesFrames.User;

import EntitiesClasses.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class UserModel extends AbstractTableModel {

    private List<User> users = new ArrayList<>();
    private Connection c;

    public UserModel(Connection c) {
        super();
        this.c = c;
        
        updateData();
    }

    public void updateData() {
        users = getList();
        rowsCount = users.size();
    }
    int rowsCount = 5;
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
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getFamilyU();
            case 1:
                return users.get(rowIndex).getNameU();
            case 2:
                return users.get(rowIndex).getMiddlenameU();
            case 3:
                return users.get(rowIndex).getAddressU();
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
                return "Middlename";
            case 3:
                return "Address";
        }
        return null;
    }

    public User getSelectesUser(int row) {
        return users.get(row);
    }
    
    public User getRowById(int id) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM \"User\" where \"id_user\"=" + id + ";");
        rs.next();    
        return new User(rs.getInt("id_user"), rs.getString("familyU"), rs.getString("nameU"),rs.getString("middlenameU"),rs.getString("addressU"));
    }
    
    
    public List<User> getList(){
        List<User> tmp = new ArrayList<>();
         try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"User\"");
            while (rs.next()) {
                User item = new User(rs.getInt("id_user"), rs.getString("familyU"), rs.getString("nameU"),rs.getString("middlenameU"),rs.getString("addressU"));
                tmp.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
         return tmp;
    }
    
    public  void insert(User user) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("insert into \"User\" (\"familyU\", \"nameU\", \"middlenameU\", \"addressU\") values ('" + 
                        user.getFamilyU() + "','" + user.getNameU() + "','" + user.getMiddlenameU() + "','" + user.getAddressU() + "');");
    }
    
    public void update(User user) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("update \"User\" set " + " \"familyU\"='" + user.getFamilyU() + 
                        "', \"nameU\"='" + user.getNameU() + "', \"middlenameU\"=" + ((user.getMiddlenameU() == "") ? "null" : ("'" + (user.getMiddlenameU())) + "'")  + ", \"addressU\"='" + user.getAddressU() + 
                        "' where \"id_user\"=" + user.getId_user()+";");
    }
    
    public void delete(int id) throws SQLException {
        try {
                Statement statement = c.createStatement();
                statement.executeUpdate("delete from \"User\" where \"id_user\" = " + id + ";");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                return;
            }
    }

}
