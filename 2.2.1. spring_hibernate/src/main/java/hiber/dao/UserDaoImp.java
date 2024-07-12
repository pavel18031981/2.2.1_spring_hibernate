package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUserWithCar(String modelCar, int seriesCar) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("select car from Car car where car.model = :model " +
                      "and car.series = :series");
      query.setParameter("model", modelCar);
      query.setParameter("series", seriesCar);

      Car car = (Car) query.getSingleResult();

      return car.getUser();
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
