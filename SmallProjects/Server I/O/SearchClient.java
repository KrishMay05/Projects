import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
/**
 * SearchServer.java
 *
 * Client side of H11 that does the front end work and sends user inputs back to the serverside.
 *
 * @author Krish Patel, 119
 *
 *@version 09/13
 *
 */
public class SearchClient {

    public static void main(String[] args) {
        String hostName = JOptionPane.showInputDialog("Enter the host name (localhost):");
        int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the port number: (2020)"));

        try {
            Socket socket = new Socket(hostName, portNumber);
            JOptionPane.showMessageDialog(null, "Connection established successfully.");

            while (true) {
                String searchText = JOptionPane.showInputDialog("Enter your search text:");
                if (searchText == null) {
                    break;
                }

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(searchText);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String[] results = (String[]) in.readObject();

                if (results.length == 0) {
                    JOptionPane.showMessageDialog(null, "No results found.");
                } else {
                    String selectedResult = (String) JOptionPane.showInputDialog(null,
                    "Which page:", "Search Results",
                            JOptionPane.PLAIN_MESSAGE, null, results, results[0]);

                    if (selectedResult == null) {
                        break;
                    }

                    out.writeObject(selectedResult);

                    String pageDescription = (String) in.readObject();
                    JOptionPane.showMessageDialog(null, "Page Description:\n" + pageDescription);

                    int choice = JOptionPane.showConfirmDialog(null,
                    "Do you want to search again?", "Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.NO_OPTION) {
                        break;
                    }
                }
            }
            socket.close();
            JOptionPane.showMessageDialog(null, "Thanks for using this Search Engine!");

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
