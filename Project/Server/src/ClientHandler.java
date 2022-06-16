import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String type;
    private String clientUsername;
    private String clientPassword;

    private static File clientFile;
    private static FileWriter myWriter;

    public static void initUserPassFile(){
        clientFile = new File("ClientList.txt");
        try{
            myWriter = new FileWriter("ClientList.txt", true);
        } catch(IOException e){
            System.out.println("Unable to create file!");
            e.printStackTrace();
        }
    }

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.type = bufferedReader.readLine(); // Login or Register
            this.clientUsername = bufferedReader.readLine(); // read username
            this.clientPassword = bufferedReader.readLine(); // read password
        } catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public boolean setUserPass() throws IOException{
        if(type.equals("Login")){
            if(CheckUP()){
                bufferedWriter.write("scs");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                clientHandlers.add(this);
                return true;
            }
            else{
                bufferedWriter.write("nxst");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                closeEverything(socket, bufferedReader, bufferedWriter);
                return false;
            }
        } else if(type.equals("Register")){
            if(CheckUP()){
                bufferedWriter.write("axst");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                closeEverything(socket, bufferedReader, bufferedWriter);
                return false;
            }
            else{
                myWriter.append(clientUsername + "\r\n" + clientPassword + "\r\n");
                myWriter.close();
                bufferedWriter.write("scs");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                clientHandlers.add(this);
                return true;
            }            
        } else{
            System.out.println("Unknown type provided!");
            return false;
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        System.out.println("Client " + clientUsername + " connected!");
        while(socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                // System.out.println(clientUsername + ": " + clientPassword + ": " + type);
                // System.out.println("Message from client: " + messageFromClient);
                broadcastMessage(messageFromClient);
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }

    }

    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler : clientHandlers){
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removerClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER : " + clientUsername + " has left the chat !");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removerClientHandler();
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

    public boolean CheckUP(){
        try{
            if(type.equals("Login")){ // checking login information
                Scanner fileReader = new Scanner(clientFile);
                if(!fileReader.hasNextLine()){ // file is empty
                    fileReader.close();
                    return false;  
                }
                else
                    while(fileReader.hasNextLine()){ // check if user and pass exist in file
                        String User = fileReader.nextLine();
                        String Pass = fileReader.nextLine();
                        if((User.equals(clientUsername)) && (Pass.equals(clientPassword))) {
                            fileReader.close();
                            return true;
                        }
                    }              
            }
            else if(type.equals("Register")){
                Scanner fileReader = new Scanner(clientFile);
                if(!fileReader.hasNextLine()){ // file is empty
                    fileReader.close();
                    return true;  
                }
                else{
                    while(fileReader.hasNextLine()){ // check if user and pass exist in file
                        String User = fileReader.nextLine();
                        String Pass = fileReader.nextLine();
                        if(User.equals(clientUsername))
                            fileReader.close();
                            return false; // this user already exists
                        }
                    fileReader.close();
                    return true;
                }
            } 
            else{
                System.out.println("Unknown type provided!");
                return false;
            }
        } catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND!");
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
