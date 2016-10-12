package proyecto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Turpitude on 10/10/2016.
 */
public abstract class Dao {

    private  String getMethod(String m){

        m =m.substring(0, 1).toUpperCase() +m.substring(1);
        return "get"+m;

    }

public void insert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

    StringBuffer command= new StringBuffer();
    command.append("INSERT INTO ").append(this.getClass().getSimpleName()+" (");

    System.out.println(this.getClass().getSimpleName());
    Field[] fields= this.getClass().getFields();
    for(Field f : fields)
    {
        command.append(f.getName()+",");

    }

    command.replace(command.length()-1,command.length(),") VALUES (");
    for(Field f : fields)
    {
        command.append("?,");

    }
    command.replace(command.length()-1,command.length(),")");

    System.out.println(command.toString());

    Method[] methods= this.getClass().getMethods();
    int i=0;
    for(Field f:fields){

        Method m=this.getClass().getMethod(getMethod((f.getName())),null);
        Object ret=m.invoke(this,null);

        if(ret instanceof String){
            System.out.println("res:"+ret.toString());
        }
        if(ret instanceof Integer){
            int id= (int) ret;
            System.out.println("res:"+id);
        }

    }


}
public  void update(){

}
public  void delete(){

}

public  void select(){

}

}
