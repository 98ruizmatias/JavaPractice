
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner syin = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 9999)) {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), false);

            System.out.print("Uso: GET (devuelve un numeor de la cola), WHO(te dice la cantidad de elemntos)");
            System.out.println("Elija su opcion: ");
            String msg = syin.nextLine();
            int o = 0;
            if(msg  =="GET"){
                o = 1;
            }
            if (msg == "WHO") {
                o = 2;
            }

            out.println(o);
            out.flush();

            System.out.println("Listo!");

            int result = in.nextInt();

            System.out.println(result);




        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}