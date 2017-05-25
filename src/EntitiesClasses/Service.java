
package EntitiesClasses;

public class Service {

    private int id_service;
    private int priceS;
    private String nameS;
    

    public Service(int id, int price, String name) {
        this.id_service = id;
        this.priceS=price;
        this.nameS = name;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public int getPriceS() {
        return priceS;
    }

    public void setPriceS(int priceS) {
        this.priceS = priceS;
    }

    @Override
    public String toString() {
        return getNameS();
    }
}
