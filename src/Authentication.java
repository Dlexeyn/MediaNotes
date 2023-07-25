package src;

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

            if(login.equals(LOGIN) && pass.equals(PASSWORD)){
                isLoginSuccess = true;
            }
        }

        if(!isLoginSuccess){
            throw new RuntimeException("Login failed!");
        }
    }
}
