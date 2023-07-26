package medianotes;

import medianotes.command.CommandReader;

public class Main {
    public static void main(String[] args) {
        authenticate();

        CommandReader.startReadCommand();

    }

    private static void authenticate() {
        Authentication authentication = new Authentication();
        try {
            authentication.authenticate();
        } catch (Exception ex) {

        }
    }
}
