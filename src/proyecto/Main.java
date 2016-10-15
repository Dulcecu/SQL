package proyecto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {

        UserDao ud= new UserDao(0,"Masmaso","Calle El pie donde el brazo");
        ud.insert();
        //ud.update();
        //ud.delete();
        //ud.select();

    }
}
