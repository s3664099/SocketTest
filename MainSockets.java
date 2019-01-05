import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainSockets {
	
	InetAddress address; //This is an object where the IP address is placed
	InetAddress localhost;
	int port = 1235; //Setting the port to open on other computer
	Socket connection; //setting a socket object
	ServerSocket server;
	DataOutputStream dos; //Object created to send data
	DataInputStream dis; //Object created to recieve data
	String str = "Goodbye Cruel World";
	
	public void socketConnect() {
		
		//The sockets need to be surrounded by Try/Catch blocks
		try {
			//This gets the IP address of the local computer
			localhost = InetAddress.getLocalHost();
			
			System.out.println(localhost);
			
			//This get the name of the other computer by passing through the IP address
			address = InetAddress.getByName("192.168.2.104");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			
			//A connection is open using the defined port and the IP address
			connection = new Socket(address, port);
			connection = server.accept();
			
			//The output stream object is initiated to allow text to be sent
			//Across the socket
			dos = new DataOutputStream(connection.getOutputStream());

			//Sends the text as defined in String
			dos.writeUTF(str);
			
			//Listens to any response coming from the computer connected to it
	        dis = new DataInputStream(connection.getInputStream());
	        
	        //prints out any message recieved.
	        System.out.println(dis.readUTF());
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void serverSetup()
	{

        String message = "";
		
        try {
        	
        	//Creates a new server and advises the user that it is listening
			server = new ServerSocket(port);
			System.out.println("Listening");
			
			//When a socket attempts to connect, the connection is accepted
			//Advises the user that the socket has been connected
			connection = server.accept();
			System.out.println("Socket connected");
			
			//Listens to any response coming from the computer connected to it
			//Advises user that it is waiting for a message
	        dis = new DataInputStream(connection.getInputStream());
	        System.out.println("Waiting for Message");
	        
	        int x=0;
	        
	        //When the message is recieved, will loop through the stream
	        do {
	        
	        //Will read the numerical value of the character coming through
	        //if in ascii	
	        x=dis.readByte();
	        	
	        //prints out the number recieved
	        System.out.println("message recieved: "+x);
	        
	        //Converts the number into the ASCII character and places it into
	        //a string
	        char letter = (char) x;
	        String ltr = String.valueOf(letter);	        
	        message = message.concat(ltr);
	        
	        }while (x!=13);
	        
	        server.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //Prints the message to the console.
        System.out.println(message);

	}
}
