package dao;

import entity.ServiceConsumer;
import entity.DeviceStatistic;
import entity.ServiceStatistic;
import entity.ServiceType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;


public class StatisticDao {
    private static final String PROFITABILITY_SUBQUERY = " JOIN \"user\" AS u " +
            "ON x.user_id = u.id " +
            "JOIN tariff t " +
            "ON u.tariff_id = t.id";

    private static final String DEVICE_POPULARITY_QUERY = "SELECT d.model, top_device.usage_count " +
            "FROM device AS d " +
            "INNER JOIN (" +
                "SELECT u.device_id, COUNT(*) AS usage_count " +
                "FROM \"user\" u " +
                "GROUP BY u.device_id " +
                "ORDER BY usage_count DESC LIMIT 1" +
            ") as top_device " +
            "ON top_device.device_id = d.id";

    private static final String TOP_CONSUMER_QUERY_TEMPLATE = "SELECT id, first_name, last_name, consumed, '%s' as service " +
            "FROM \"user\" u " +
            "JOIN %s AS units ON u.id = units.user_id " +
            "ORDER BY consumed DESC " +
            "LIMIT 5";

    public static DeviceStatistic findMostPopularDevice() {
        Transaction transaction = null;
        DeviceStatistic device = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(DEVICE_POPULARITY_QUERY)
                    .addEntity(DeviceStatistic.class);

            device = (DeviceStatistic) query.getSingleResult();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return device;
    }

    public static List<ServiceConsumer> findTopConsumersByServiceType(ServiceType type) {
        Transaction transaction = null;
        List result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(buildTopConsumerQuery(type)).addEntity(ServiceConsumer.class);
            result = query.list();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    public static ServiceStatistic findServiceProfitByType(ServiceType type) {
        Transaction transaction = null;
        ServiceStatistic result = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(buildProfitQuery(type)).addEntity(ServiceStatistic.class);
            result = (ServiceStatistic) query.getSingleResult();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    private static String buildProfitQuery(ServiceType type) {
        return "SELECT '" + type + getProfitSubQuery(type) + PROFITABILITY_SUBQUERY;
    }

    private static String getProfitSubQuery(ServiceType type) {
        switch (type) {
            case INTERNET:
                return "' AS name, SUM(x.traffic * t.mb_cost) AS profit FROM traffic AS x ";
            case PHONE_CALL:
                return "' AS name, SUM(x.duration * t.minute_cost) AS profit FROM call AS x ";
            case SMS:
                return "' AS name, SUM(t.sms_cost) AS profit FROM sms AS x ";
            default:
                throw new IllegalArgumentException("Unknown service type: " + type);
        }
    }

    private static String buildTopConsumerQuery(ServiceType type) {
        String subQuery = "(SELECT x.user_id, " + getTopConsumerSubQuery(type) + " x GROUP BY x.user_id)";
        return String.format(TOP_CONSUMER_QUERY_TEMPLATE, type.toString(), subQuery);
    }

    private static String getTopConsumerSubQuery(ServiceType type) {
        switch (type) {
            case INTERNET:
                return "SUM(x.traffic) AS consumed FROM traffic";
            case PHONE_CALL:
                return "SUM(x.duration) AS consumed FROM call";
            case SMS:
                return "SUM(SIGN(x.id)) AS consumed FROM sms";
            default:
                throw new IllegalArgumentException("Unknown service type: " + type);
        }
    }
}
