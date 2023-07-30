package medianotes.authentication;

import medianotes.context.UserContext;

import java.util.Scanner;

public class Authentication {

    private final static String LOGIN = "turar";
    private final static String PASSWORD = "1234";

    public void authenticate(){
        boolean isLoginSuccess = false;

        Scanner scanner = new Scanner(System.in);

        for(int curCount = 3; curCount > 0 && !isLoginSuccess; curCount--){
            System.out.print("Login: ");
            var login = scanner.next();
            System.out.print("Password: ");
            var pass = scanner.next();

            if(validate(login, pass)){
                isLoginSuccess = true;
                UserContext.setUserLogin(login);
            }
        }

        if(!isLoginSuccess){
            throw new RuntimeException("Login failed!");
        }
    }

    private boolean validate(String login, String pass){
        return login.equals(LOGIN) && pass.equals(PASSWORD);
    }
}
