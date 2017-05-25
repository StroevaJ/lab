
package EntitiesClasses;

public class Doctor {

    private int id_doctor;
    private String familyD;
    private String nameD;
    private String middlenameD;
    private String profilD;

    public Doctor(int id, String family, String name, String middlename, String profil) {
        this.id_doctor = id;
        this.familyD = family;
        this.nameD = name;
        this.middlenameD = middlename;
        this.profilD = profil;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getFamilyD() {
        return familyD;
    }

    public void setFamilyD(String familyD) {
        this.familyD = familyD;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getMiddlenameD() {
        return middlenameD;
    }

    public void setMiddlenameD(String middlenameD) {
        this.middlenameD = middlenameD;
    }

    public String getProfilD() {
        return profilD;
    }

    public void setProfilD(String profilD) {
        this.profilD = profilD;
    }
    
    @Override
    public String toString() {
        return getNameD()+ " " + getFamilyD();
    }
    
    
}
