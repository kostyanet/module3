package dao;

import entity.Sms;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class SmsDao {
    public static List<Sms> findByText(String pattern) {
        Transaction transaction = null;
        List<Sms> result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Sms WHERE LOWER(text) like LOWER('%" + pattern + "%')";
            Query query = session.createQuery(hql);
            result = query.getResultList();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
}
