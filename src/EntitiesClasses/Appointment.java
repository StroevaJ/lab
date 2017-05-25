
package EntitiesClasses;

public class Appointment {

    private int id_appointment;
    private int id_user;
    private String date;

    public Appointment(int id, int uid, String date) {
        this.id_appointment = id;
        this.id_user = uid;
        this.date = date;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return getDate() +" " + getId_user();
    }
}
