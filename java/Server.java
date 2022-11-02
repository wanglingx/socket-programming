
// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server {
    // initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    // constructor with port
    public Server(int port) {
        // starts server and waits for a connection
        System.out.println("Server started");
        System.out.println("Waiting for a client ...");
        try {
            server = new ServerSocket(port);
            while (true) {

                socket = server.accept();
                System.out.println("A new client connected to : " + socket);
                // takes input from the client socket
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
				Thread t = new ClientHandler(socket, dis, dos);

				// Invoking the start() method
				t.start();
            }
            
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    public static void main(String args[]) {
        Server server = new Server(3000);
    }
}
