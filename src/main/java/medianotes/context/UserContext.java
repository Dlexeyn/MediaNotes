package medianotes.context;

public class UserContext {
    private static String userLogin = null;

    public static void setUserLogin(String userLogin) {
        UserContext.userLogin = userLogin;
    }

    public static String getUserLogin() {
        return userLogin;
    }

}
