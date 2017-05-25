
package EntitiesClasses;

public class Pass {

    private int id_pass;
    private int id_appointment;
    private int id_doctor;
    private int id_service;
    private String time;

    public Pass(int id, int apid, int did, int sid, String time) {
        this.id_pass = id;
        this.id_appointment = apid;
        this.id_doctor = did;
        this.id_service = sid;
        this.time = time;
    }

    public int getId_pass() {
        return id_pass;
    }

    public void setId_pass(int id_pass) {
        this.id_pass = id_pass;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    

}
