import models.Passport;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(User.class) /*!!!!!!! register class*/
                        .addAnnotatedClass(Passport.class) /*!!!!!!! register class*/
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        //work space

        session.beginTransaction();

        Passport vk = new Passport("vk", "87656351"); // 0
        System.out.println(vk); // 0
        session.save(vk);
        System.out.println(vk); //1

        session.save(new User("vasya1", "pupkin", vk));
        session.save(new User("vasya2", "pupkin", new Passport("ah", "286352754")));
        session.save(new User("vasya3", "pupkin", new Passport("vs", "0892683741")));
        session.getTransaction().commit();

        List<User> list =
//                session.createNativeQuery("select * from user_table", User.class).list();
                session.createQuery("select u from User u", User.class).list();
//        System.out.println(list);

        User user = session.find(User.class, 2); // User passport - null

        System.out.println(user);
        System.out.println("''''''''''''''''''''''''''''''''''''");
        System.out.println(user.getPassport());




        /*end dont forget*/
        session.close();
        sessionFactory.close();
    }
}
