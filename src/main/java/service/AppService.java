package service;

import dao.SmsDao;
import dao.StatisticDao;
import entity.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class AppService {

    public void run() {
        printMessagesByText("est");
        printMostPopularDevice();
        printMostProfitableService();
        printTopConsumers();
        System.exit(0);
    }

    public void printMessagesByText(String text) {
        List<Sms> result = SmsDao.findByText(text);
        System.out.println(result.toString());
    }

    public void printMostPopularDevice() {
        DeviceStatistic device = StatisticDao.findMostPopularDevice();
        System.out.println("The most popular device is '" + device.getModel() + "' with " + device.getUsageCount() + " usages!");
    }

    public void printMostProfitableService() {
        Stream.of(ServiceType.values())
                .map(StatisticDao::findServiceProfitByType)
                .max(Comparator.comparingDouble(ServiceStatistic::getProfit))
                .ifPresent((ServiceStatistic service) ->
                        System.out.println(
                                "The most profitable service is '" +
                                        service.getName() +
                                        "' with its profit of " +
                                        String.format("%.2f", service.getProfit())
                        )
                );
    }

    public void printTopConsumers() {
        HashMap<ServiceType, List<ServiceConsumer>> map = new HashMap<>();

        Stream.of(ServiceType.values())
                .forEach((ServiceType type) ->
                        map.put(type, StatisticDao.findTopConsumersByServiceType(type))
                );

        map.forEach((ServiceType type, List<ServiceConsumer> list) -> {
            System.out.println("Top 5 '" + type + "' consumers:\n" + list.toString());
        });
    }

}
