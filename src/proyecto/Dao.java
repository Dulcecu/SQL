package proyecto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Turpitude on 10/10/2016.
 */
public abstract class Dao {

    PreparedStatement prst;

    StringBuffer command= new StringBuffer();
    Field[] fields;
    Method m;

    private  String getMethod(String m){

        m =m.substring(0, 1).toUpperCase() +m.substring(1);
        return "get"+m;

    }


public void insert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, SQLException {

    command= new StringBuffer();
    command.append("INSERT INTO ").append(this.getClass().getSimpleName()+" (");

    System.out.println(this.getClass().getSimpleName());
    fields= this.getClass().getFields();

    for(Field f : fields)
    {
        command.append(f.getName()+",");

    }

    command.replace(command.length()-1,command.length(),") VALUES (");
    for(Field f : fields)
    {
        command.append("?,");

    }
    command.replace(command.length()-1,command.length(),");");

    System.out.println(command.toString());

    int i=0;
    for(Field f:fields){

        m=this.getClass().getMethod(getMethod((f.getName())),null);
        Object ret=m.invoke(this,null);

        if(ret instanceof String){
            System.out.println("res:"+ret.toString());
        }
        if(ret instanceof Integer){
            int id= (int) ret;
            System.out.println("res:"+id);
        }

    }
    // CHANGE
    prst.executeQuery(command.toString());




}
public  void update() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {

    command= new StringBuffer();
    command.append("UPDATE ").append(this.getClass().getSimpleName()+" SET ");

    System.out.println(this.getClass().getSimpleName());

    fields= this.getClass().getFields();
    for(Field f : fields)
    {
        if(f.getGenericType().toString().equals("int") ) {
            command.append(f.getName() + "=?, ");
        }
        else{
            command.append(f.getName() + "='?',");
        }

    }

    command.replace(command.length()-1,command.length()," WHERE ");
    for(Field f :fields){
        if(f.getName().toString().equals("id")){
            command.append(f.getName().toString()+"=?;");
        }
    }
    System.out.println(command.toString());

    prst.executeQuery(command.toString());
}
public  void delete() throws SQLException {

    command= new StringBuffer();
    command.append("DELETE FROM ").append(this.getClass().getSimpleName()+" WHERE ");

    System.out.println(this.getClass().getSimpleName());

    fields= this.getClass().getFields();

    for(Field f :fields){
        if(f.getName().toString().equals("id")){
            command.append(f.getName().toString()+"=?;");
        }
    }
    System.out.println(command.toString());

    prst.executeQuery(command.toString());

}

public ResultSet select() throws SQLException {


    command= new StringBuffer();
    command.append("SELECT * FROM ").append(this.getClass().getSimpleName()+" WHERE ");

    System.out.println(this.getClass().getSimpleName());
    fields= this.getClass().getFields();
    for(Field f :fields){
        if(f.getName().toString().equals("id")){
            command.append(f.getName().toString()+"=?;");
        }
    }
    System.out.println(command.toString());

    ResultSet rs= prst.executeQuery(command.toString());
    return rs;


}

}
