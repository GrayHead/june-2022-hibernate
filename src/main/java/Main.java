import models.Card;
import models.Passport;
import models.Sunglass;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
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
                        .addAnnotatedClass(Card.class) /*!!!!!!! register class*/
                        .addAnnotatedClass(Sunglass.class) /*!!!!!!! register class*/
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        //work space

        session.beginTransaction();

        Passport vk = new Passport("vk", "87656351"); // 0

        List<Sunglass> sunglasses = Arrays.asList(new Sunglass("rayban1"), new Sunglass("rayban2"));
        User user1 = new User("vasya", "kokos", sunglasses);
        User user2 = new User("petya", "asidgyt", sunglasses);

        session.save(user1);
        session.save(user2);


        session.getTransaction().commit();







        /*end dont forget*/
        session.close();
        sessionFactory.close();
    }
}
