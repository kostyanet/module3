import service.AppService;
import service.CLIService;

public class App {

    public static void main(String[] args) {
        AppService appService = new AppService();
        CLIService cliService = new CLIService(appService);
        cliService.run();
    }
}
