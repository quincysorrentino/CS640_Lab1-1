import java.io.*;
import java.net.*;

public class Client {
        
    public static void run(String hostname, int port, int time) {
        Socket socket = null;
        OutputStream out = null;
        
        try {
            // Establish connection
            socket = new Socket(hostname, port);
            out = socket.getOutputStream();
            
            // Prepare data chunk of 1000 bytes (all zeros)
            byte[] data = new byte[1000];
            
            long totalBytesSent = 0;
            long startTime = System.currentTimeMillis();

            // Convert seconds to milliseconds
            long endTime = startTime + (time * 1000L); 
            
            // Send data as quickly as possible for 'time' seconds
            while (System.currentTimeMillis() < endTime) {
                out.write(data);
                totalBytesSent += 1000;
            }
            
            // // Calculate statistics
            // long actualDuration = System.currentTimeMillis() - startTime;
            // double durationInSeconds = actualDuration / 1000.0;
            
            // // Convert to kilobytes (1 KB = 1000 bytes)
            // double kilobytesSent = totalBytesSent / 1000.0;
            
            // // Convert to megabits per second (1 B = 8 bits, 1 MB = 1000 KB)
            // double megabitsSent = (totalBytesSent * 8.0) / 1000000.0;
            // double rate = megabitsSent / durationInSeconds;
            
            // // Print summary
            // System.out.printf("sent=%.0f KB rate=%.3f Mbps\n", kilobytesSent, rate);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            // Close resources
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }
}
