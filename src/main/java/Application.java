import api.UserApi;

public class Application {

    public static void main(String[] args) {
        UserApi api = new UserApi();
        api.start();
    }
}
