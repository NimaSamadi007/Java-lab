import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String password;
    private String type;

    public Client(Socket socket, String username, String password, String type){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.password = password;
            this.type = type;
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(type);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(password);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + " : " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupChat;

                while(socket.isConnected()){
                    try{
                        messageFromGroupChat = bufferedReader.readLine();
                        if (messageFromGroupChat != null){
                            System.out.println(messageFromGroupChat);
                        } else{
                            closeEverything(socket, bufferedReader, bufferedWriter);
                            return;
                        }
                    } catch (IOException e){
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{

        Scanner scanner = new Scanner(System.in);

        System.out.println("Login / Register : ");
        String Action = scanner.nextLine();

        if (Action.equals("Login")) {
            System.out.println("Enter Your Username for the groupchat : ");
            String username = scanner.nextLine();
            System.out.println("Enter Your Password for the groupchat : ");
            String password = scanner.nextLine();

            Socket socket = new Socket("192.168.1.101", 1234);
            Client client = new Client(socket, username, password, Action);
            client.listenForMessage();
            client.sendMessage();
        } else if (Action.equals("Register")) {
            System.out.println(" -- -- -- -- -- -- -- -- -- \r\n" +
                                "Registeration Process . . . \r\n" +
                                " -- -- -- -- -- -- -- -- -- ");
            System.out.println("Enter Your Username for the groupchat : ");
            String username = scanner.nextLine();
            System.out.println("Enter Your Password for the groupchat : ");
            String password = scanner.nextLine();

            Socket socket = new Socket("192.168.1.101", 1234);
            Client client = new Client(socket, username, password, Action);
            client.listenForMessage();
            client.sendMessage();
        }
        
        scanner.close();
    }
}
