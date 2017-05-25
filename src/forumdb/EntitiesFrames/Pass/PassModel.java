
package forumdb.EntitiesFrames.Pass;


import forumdb.EntitiesFrames.Appointment.AppointmentModel;
import forumdb.EntitiesFrames.Doctor.DoctorModel;
import forumdb.EntitiesFrames.Service.ServiceModel;
import EntitiesClasses.Appointment;
import EntitiesClasses.Pass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class PassModel extends AbstractTableModel {

    private List<Pass> list = new ArrayList<>();
    private Connection c;
    private AppointmentModel modelAppointment;
    private DoctorModel modelDoctor;
    private ServiceModel modelService;

    public AppointmentModel getModelAppointment() {
        return modelAppointment;
    }

    public DoctorModel getModelDoctor() {
        return modelDoctor;
    }

    public ServiceModel getModelService() {
        return modelService;
    }

    public PassModel(Connection c) {
        super();
        this.c = c;
         modelAppointment = new AppointmentModel(c);
         modelDoctor = new DoctorModel(c);
         modelService = new ServiceModel(c);
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
        try {
            switch (columnIndex) {
                case 0:
                    return modelAppointment.getRowById(list.get(rowIndex).getId_appointment()).getDate();
                case 1:
                    return modelDoctor.getRowById(list.get(rowIndex).getId_doctor()).getFamilyD();
                case 2:
                    return modelService.getRowById(list.get(rowIndex).getId_service()).getNameS();
                case 3:
                    return list.get(rowIndex).getTime();
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
                return "Data";
            case 1:
                return "FamilyD";
            case 2:
                return "Service";
            case 3:
                return "Time";
        }
        return null;
    }

    public Pass getSelectesItem(int row) {
        return list.get(row);
    }
    
     public Pass getRowById(int id) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM \"Pass\" where \"id_pass\"=" + id + ";");
        rs.next();    
        return new Pass(rs.getInt("id_pass"), rs.getInt("id_appointment"), rs.getInt("id_doctor"), rs.getInt("id_service"), rs.getString("time"));
    }
    
    public List<Pass> getList() {
        List<Pass> tmp = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"Pass\"");
            while (rs.next()) {
                Pass item = new Pass(rs.getInt("id_pass"), rs.getInt("id_appointment"), rs.getInt("id_doctor"), rs.getInt("id_service"), rs.getString("time"));
                tmp.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
        }
        return tmp;
    }
    
    public  void insert(Pass doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("insert into \"Pass\" "+
                "(\"id_appointment\", \"id_doctor\", \"id_service\", \"time\")"+
                " values (" +  doc.getId_appointment() + ", " + 
                doc.getId_doctor() + ", " + doc.getId_service() + 
                ", '" + doc.getTime() + "');");
    }
    
    public void update(Pass doc) throws SQLException {
        Statement statement = c.createStatement();
        statement.executeUpdate("update \"Pass\" "+
                "set \"id_appointment\" = " + doc.getId_appointment() + 
                ",\"id_doctor\" = " + doc.getId_doctor() + 
                ", \"id_service\" = " + doc.getId_service() + 
                ", \"time\"='" + doc.getTime() + 
                "' where \"id_pass\"=" + doc.getId_pass()+ ";");
    }
    
    public void delete(int id) throws SQLException {
        try {
                // TODO add your handling code here:
                Statement statement = c.createStatement();
                statement.executeUpdate("delete from \"Pass\" where \"id_pass\" = " + id + ";");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                return;
            }
    }
}
