import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// ClientHandler class
class ClientHandler extends Thread
{   
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
	{
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run()
	{
		String received;
		while (true)
		{
			try {
				
				// receive the answer from client
				received = dis.readUTF();
                System.out.println(received);
				
				if(received.equals("Over"))
				{
					System.out.println("Client " + this.s + " sends exit...");
					System.out.println("Closing this connection.");
					this.s.close();
					System.out.println("Connection closed");
					break;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
                System.out.println("err3");
			}
		}
		
		try
		{
			// closing resources
			this.dis.close();
			this.dos.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}