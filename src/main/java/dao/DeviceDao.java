package dao;

import entity.Device;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class DeviceDao {

    public Device getById(int id) {
        Transaction transaction = null;
        Device device = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Device d WHERE d.id = :id");
            query.setParameter("id", id);
            List<Device> results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                device = results.get(0);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return device;
    }
}
