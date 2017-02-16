import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;

/**
 * Created by aacerete on 16/02/17.
 */
public class Hellodb4o {

    //Declaramos el objectContainer
    private static ObjectContainer database;

    public static void main(String[] args) throws Exception {

        try {
            //ruta del archivo de la BBDD
            File databaseFile = new File("/home/aacerete/IdeaProjects/Database4Objects/persons.data");

            //Si el archivo ya existe, lo borramos
            if (databaseFile.exists()){
                databaseFile.delete();
            }

            //Cargamos la base de datos a partir del archivo
            database = Db4o.openFile("persons.data");

            //Creamos 6 Persons
            Person brian = new Person("Brian", "Goetz", 39);
            Person jason = new Person("Jason", "Hunter", 35);
            Person brians = new Person("Brian", "Sletten", 38);
            Person david = new Person("David", "Geary", 55);
            Person glenn = new Person("Glenn", "Vanderberg", 40);
            Person neal = new Person("Neal", "Ford", 39);

            //Los almacenamos
            database.store(brian);
            database.store(jason);
            database.store(brians);
            database.store(david);
            database.store(glenn);
            database.store(neal);

            //realizamos el commit
            database.commit();

            //probamos una consulta que nos devuelva los registros con nombres Brian
            ObjectSet todosLosBrians = database.queryByExample(new Person("Brian", null, 0));

            while (todosLosBrians.hasNext()){
                System.out.println(todosLosBrians.next());
            }

        }finally{

            if (database != null)
                database.close();
        }
    }
}