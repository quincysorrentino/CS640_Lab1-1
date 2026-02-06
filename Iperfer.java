public class Iperfer {
    // Sample arguments:  -c -h <server hostname> -p <server port> -t <time>
    /*
        - -c indicates this is the iperf client which should generate data.
        - Server hostname is the hostname or IP address of the iperf server which will consume data.
        - Server port is the port on which the remote host is waiting to consume data; the port should be in the
        range 1024 ≤ server port ≤ 65535.
        - Time is the duration in seconds for which data should be generated.
        - You may assume that parameters are always passed in this order
    */

    private static int parseAndValidatePort(String portStr) {
        try {
            int port = Integer.parseInt(portStr);
            if (port < 1024 || port > 65535) {
                System.out.println("Error: port number must be in the range 1024 to 65535");
                System.exit(1);
            }
            return port;

        } catch (NumberFormatException e) {
            System.out.println("Error: port number must be an integer");
            System.exit(1);
            return -1;
        }
    }

    private static int parseAndValidateTime(String timeStr) {
        try {
            int time = Integer.parseInt(timeStr);
            if (time <= 0) {
                System.out.println("Error: time must be a positive integer");
                System.exit(1);
            }
            return time;

        } catch (NumberFormatException e) {
            System.out.println("Error: time must be an integer");
            System.exit(1);
            return -1;
        }
    }

    public static void main(String[] args) {
        // Arg count
        if (args.length < 1) {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }

        // Parse mode
        String mode = args[0];
        if (!mode.equals("-c") && !mode.equals("-s")) {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }

        if (mode.equals("-c")) {
            // Client mode: -c -h <hostname> -p <port> -t <time>
            if (args.length != 7 || !args[1].equals("-h") || !args[3].equals("-p") || !args[5].equals("-t")) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            
            String hostname = args[2];
            int port = parseAndValidatePort(args[4]);
            int time = parseAndValidateTime(args[6]);

            // Run client
            Client.run(hostname, port, time);

        } else {
            // Server mode: -s -p <port>
            if (args.length != 3 || !args[1].equals("-p")) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            
            int port = parseAndValidatePort(args[2]);

            // Run server
            Server.run(port);
        }
    }
}
