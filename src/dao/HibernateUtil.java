package dao;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
public class HibernateUtil{
private static SessionFactory sessionFactory ;
public static SessionFactory getSessionFactory() {
try {
if ( sessionFactory == null ) {
//mediante un objeto Configuration se leerá el fichero de configuración de 
//hibernate hibernate.cfg.xml  que se encuentra en el directorio raíz de las clases Java.
Configuration configuration = new Configuration().configure(); 
//mediante el método configure() se va a leer el fichero de configuración hibernate.cfg.xml.
ServiceRegistryBuilder registry = new ServiceRegistryBuilder(); 
registry.applySettings(configuration.getProperties());
ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
//Registry contiene la lista de los distintos servicios que usará hibernate
//nota: esas 3 lineas se pueden escribir como una sola siendo serviceRegistry el resultado final:
//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
sessionFactory = configuration.buildSessionFactory(serviceRegistry);

}
} catch (HibernateException he) { System. err .println( "ERROR en la inicialización de la SessionFactory: " + he);
throw new ExceptionInInitializerError(he);
}
return sessionFactory;
}
}