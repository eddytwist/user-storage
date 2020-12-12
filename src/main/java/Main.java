import api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static UserApi api;

    public static void main(String[] args) {
        api = new UserApi();
        api.start();
    }
}
