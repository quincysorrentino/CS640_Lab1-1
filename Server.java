import java.io.*;
import java.net.*;

public class Server {
	public static void error () {
		System.out.println("Error: missing or additional arguments");
		System.exit(1);
	}

	public static void run (String[] args) {
		int listen_port = 0;

		if (args.length != 3) {
			error();
		}

		if (!args[1].equals("-p")) {
			error();
		}
		
		try {
			listen_port = Integer.parseInt(args[2]);
		}
        catch (Exception e) {
        	error();
        }

		if (listen_port < 1024 || listen_port > 65535) {
			error();
		}

		for (int i = 0;i < args.length;i++) {
			System.out.println(args[i]);
		}
	}
}
