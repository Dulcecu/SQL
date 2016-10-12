package proyecto;

/**
 * Created by Turpitude on 10/10/2016.
 */
public class UserDao extends Dao {

    public int id;
    public String name;
    public String address;

    public UserDao(int id, String name, String address) {

        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
