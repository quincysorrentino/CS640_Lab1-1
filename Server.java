import java.io.*;
import java.net.*;

public class Server {

    public static void run(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream in = null;

        long totalBytesReceived = 0;
        long startTime = 0;
        long endTime = 0;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("Listening on port " + port);

            // Wait for a client to connect
            socket = serverSocket.accept();
            in = socket.getInputStream();

            System.out.println("Client connected");

            byte[] buffer = new byte[1000];
            int bytesRead;
            boolean firstByte = true;

            while ((bytesRead = in.read(buffer)) != -1) {
                if (firstByte) {
                    startTime = System.currentTimeMillis();
                    firstByte = false;
                }
                totalBytesReceived += bytesRead;
            }

            endTime = System.currentTimeMillis();

            // Close resources
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }

            // Calculate statistics
            double elapsedSeconds = (endTime - startTime) / 1000.0;
            double total_kb = totalBytesReceived / 1000.0;
            double rate_Mbps = (totalBytesReceived * 8.0) / (1000000.0 * elapsedSeconds);

            System.out.printf("received=%.0f KB rate=%.3f Mbps\n", total_kb, rate_Mbps);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}