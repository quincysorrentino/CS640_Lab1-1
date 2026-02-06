import java.io.*;
import java.net.*;

public class Client {
        
    public static void run(String hostname, int port, int time) {
        Socket socket = null;
        OutputStream out = null;
        
        try {
            socket = new Socket(hostname, port);
            out = socket.getOutputStream();
            System.out.println("Sending traffic to " + hostname + " on port " + port);
            
            // Prepare data chunk of 1000 bytes
            byte[] data = new byte[1000];
            
            long totalBytesSent = 0;
            long startTime = System.currentTimeMillis();

            // Convert seconds to milliseconds
            long endTime = startTime + (time * 1000L); 
            
            while (System.currentTimeMillis() < endTime) {
                out.write(data);
                totalBytesSent += 1000;
            }

            // Close resources
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }

            // Calculate statistics

            // Convert to kilobytes
            double total_kb = totalBytesSent / 1000.0;

            // Convert to Megabits then calculate rate
            double rate_Mbps = ((totalBytesSent * 8.0) / (1000000.0) / time);

            System.out.printf("sent=%.0f KB rate=%.3f Mbps\n", total_kb, rate_Mbps);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
