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

    private static void port_check(int port_num) {
        if ((port_num < 1024) || (port_num > 65535)) {
            System.out.println("Error: port number must be in the range 1024 to 65535");
            System.exit(1);
        } 
    }

    public static void main(String[] args) {
        // Check if there are arguments
        if (args.length != 7) {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }

        // Do port check
        port_check(Integer.parseInt(args[4]));

        // Parse mode
        String mode = args[0];

        if (mode.equals("-c")) {
            // Client mode
            System.out.println("Client Mode Selected");
            String hostname = args[2];
            int port = Integer.parseInt(args[4]);
            int time = Integer.parseInt(args[6]);

            //Client.run(hostname=hostname, port=port, time=time);

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
