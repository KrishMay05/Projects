import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * SearchServer.java
 *
 * Server side of H11 that reads a file and does all the backend calculations and sends the diffrent results back to the client 
 *
 * @author Krish Patel, 119
 *
 *@version 09/13
 *
 */
public class SearchServer {

    public static void main(String[] args) {
        int portNumber = 2020; 

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("searchDatabase.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                String searchText = (String) in.readObject();
                List<String> searchResults = new ArrayList<>();
                for ( int x = 0; x < lines.size(); x ++) {
                    String[] parts = lines.get(x).split(";");
                    if (lines.get(x).contains(searchText)) {
                        searchResults.add(parts[1]);
                    }
                }
                out.writeObject(searchResults.toArray(new String[0]));
                out.flush();
                String selection = (String) in.readObject();
                String pageDescription = "";
                for (String a : lines) {
                    String[] parts = a.split(";");
                    String pageTitle = parts[1];
                    String description = parts[2];
                    if (pageTitle.equals(selection)) {
                        pageDescription = description;
                        break;
                    }
                }

                out.writeObject(pageDescription);
                out.flush();
                clientSocket.close();
                serverSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            // JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
