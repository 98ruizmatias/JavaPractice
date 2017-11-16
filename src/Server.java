import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        //int[] a = new int[]{1,24,-3223,11111,1,31,322,1,4,134};
        Socket socket;
        Runner2 does;
        Kiu q = new Kiu();
        Producer produce = new Producer(q);

        produce.exexcute();
       try {
            ServerSocket wfc = new ServerSocket(9999);

           while (true){
              // System.out.println("Im in first step");
               socket = wfc.accept();
               //System.out.println("I have founded a client");

               does = new Runner2(socket, q);
               does.execute();
           }

       } catch(IOException e){
           e.printStackTrace();
        }
    }





}
