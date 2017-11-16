
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 9999)) {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), false);

            System.out.print("Ingrese el numero a buscar: ");
            int query = stdin.nextInt();

            out.println(query);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}