
package EntitiesClasses;

public class User {

    private int id_user;
    private String familyU;
    private String nameU;
    private String middlenameU;
    private String addressU;

    public User(int id, String family, String name,  String middlename, String address) {
        this.id_user = id;
        this.familyU = family;
        this.nameU = name;
        this.middlenameU = middlename;
        this.addressU = address;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFamilyU() {
        return familyU;
    }

    public void setFamilyU(String familyU) {
        this.familyU = familyU;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public String getMiddlenameU() {
        return middlenameU;
    }

    public void setMiddlenameU(String middlenameU) {
        this.middlenameU = middlenameU;
    }

    public String getAddressU() {
        return addressU;
    }

    public void setAddressU(String addressU) {
        this.addressU = addressU;
    }

     @Override
    public String toString() {
        return getNameU()+" "+getFamilyU();
    }


}
