import models.Card;
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
                        .addAnnotatedClass(Card.class) /*!!!!!!! register class*/
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        //work space

        session.beginTransaction();

        Passport vk = new Passport("vk", "87656351"); // 0

        User user = new User("vasya1", "pupkin", vk);
        session.save(user);
        session.save(new User("vasya2", "pupkin", new Passport("ah", "286352754")));
        session.save(new User("vasya3", "pupkin", new Passport("vs", "0892683741")));

        Card card1 = new Card("1234 1234 1234 1234",user);

        session.save(card1);



        session.getTransaction().commit();







        /*end dont forget*/
        session.close();
        sessionFactory.close();
    }
}
