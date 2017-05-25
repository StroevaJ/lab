
package forumdb.EntitiesFrames.Service;

import EntitiesClasses.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ServiceModel extends AbstractTableModel {

    private List<Service> list = new ArrayList<>();

    private Connection c;

    public ServiceModel(Connection c) {
        super();
        this.c = c;
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
        String s = "";
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getNameS();
            case 1:   
                return list.get(rowIndex).getPriceS();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Price";
            
        }
        return null;
    }

    public Service getSelectesItem(int row) {
        return list.get(row);
    }
    
    public Service getRowById(int id) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM \"Service\" where \"id_service\"=" + id + ";");
        rs.next();    
        return new Service(rs.getInt("id_service"), rs.getInt("priceS"), rs.getString("nameS"));
    }
    
    
    public List<Service> getList() {
        List<Service> tmp = new ArrayList<>();
         try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"Service\"");
            while (rs.next()) {
                Service item = new Service(rs.getInt("id_service"), rs.getInt("priceS"),  rs.getString("nameS"));
                tmp.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
         return tmp;
    }
    
    public  void insert(Service service) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("insert into \"Service\" (\"priceS\", \"nameS\") values (" + 
                        service.getPriceS() + ",'" + service.getNameS()  + "');");
    }
    
    public void update(Service service) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("update \"Service\" set " + 
                        " \"nameS\"='" + service.getNameS() + 
                        "', \"priceS\"=" + service.getPriceS() + "where \"id_service\"=" + service.getId_service()+";");
    }
    
    public void delete(int id) throws SQLException {
        try {
                // TODO add your handling code here:
                Statement statement = c.createStatement();
                statement.executeUpdate("delete from \"Service\" where \"id_service\" = " + id + ";");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                return;
            }
    }
}
