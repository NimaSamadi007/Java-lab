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

    public Client(Socket socket, String username, String password){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.password = password;
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
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
                    try {
                        messageFromGroupChat = bufferedReader.readLine();
                        System.out.println(messageFromGroupChat);
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

    public static boolean CheckUP(String username, String password, String passConf, File clientFile, String Type){

        if(passConf.equals(password)){
            try {
                Scanner fileReader = new Scanner(clientFile);
                boolean existFlag = false;
                if(!fileReader.hasNextLine()){
                    return true;
                }
                while(fileReader.hasNextLine()){
                    String User = fileReader.nextLine();
                    String Pass = fileReader.nextLine();
                    if(Type.equals("Login")){
                        if((User.equals(username)) && (Pass.equals(password))) {
                            existFlag = true;
                            return true;
                        }
                    } else if(Type.equals("Register")){
                        if(User.equals(username)){
                            existFlag = true;
                            System.out.println("Username already exists");
                            return false;
                        }
                    } else {
                        System.out.println("Type Error !");
                    }
                }
                if(!existFlag){
                    if(Type.equals("Login")) {
                        System.out.println("Username of Password does not exist or Wrong User and Pass given !");
                        return false;
                    } else if(Type.equals("Register")){
                        return true;
                    }
                } else if(existFlag) {
                    return true;
                }

            } catch (FileNotFoundException e){
                System.out.println("FILE NOT FOUND !");
                e.printStackTrace();
            }

        } else {
            System.out.println("Passwords are not matching !");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{

        File clientFile = new File("ClientList.txt");
        FileWriter myWriter = new FileWriter("ClientList.txt", true);
        boolean Flag = false;
        Scanner scanner = new Scanner(System.in);
        while(!Flag) {

            System.out.println("Login / Register : ");
            String Action = scanner.nextLine();

            if (Action.equals("Login")) {

                System.out.println("Enter Your Username for the groupchat : ");
                String username = scanner.nextLine();
                System.out.println("Enter Your Password for the groupchat : ");
                String password = scanner.nextLine();
                if(CheckUP(username, password, password, clientFile, "Login")) {
                    Socket socket = new Socket("localhost", 1234);
                    Client client = new Client(socket, username, password);
                    client.listenForMessage();
                    client.sendMessage();
                    Flag = true;
                }

            } else if (Action.equals("Register")) {
                boolean RFlag = false;
                while(!RFlag) {
                    System.out.println(" -- -- -- -- -- -- -- -- -- \r\n" +
                                       "Registeration Process . . . \r\n" +
                                       " -- -- -- -- -- -- -- -- -- ");
                    System.out.println("Enter Your Username for the groupchat : ");
                    String username = scanner.nextLine();
                    System.out.println("Enter Your Password for the groupchat : ");
                    String password = scanner.nextLine();
                    System.out.println("Confirm Your Password for the groupchat : ");
                    String passConf = scanner.nextLine();

                    if (CheckUP(username, password, passConf, clientFile, "Register")) {
                        myWriter.append(username + "\r\n" + password + "\r\n");
                        myWriter.close();
                        System.out.println("Successfully Registered");
                        RFlag = true;
                    }
                }
            }
        }
    }
}
