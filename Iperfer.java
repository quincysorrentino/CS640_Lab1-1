import java.io.*;
import java.net.*;

public class Iperfer {

    public static void main(String[] args) {
        // Check if there are arguments
        if (args.length == 0) {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }

        // Parse mode
        String mode = args[0];

        if (mode.equals("-c")) {
            // Client mode
            System.out.println("Client Mode Selected");
            // Client.run(args);

        } else if (mode.equals("-s")) {
            // Server mode
            System.out.println("Server Mode Selected");
            // Server.run(args);

        } else {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }
    }
}
