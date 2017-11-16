import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Runner2 extends Thread {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Kiu q;

    private Semaphore s;


    public Runner2(Socket socket, Kiu q) {
        this.socket = socket;
        this.q = q;
        this.s = new Semaphore(1);
        try {
            this.in = new Scanner(this.socket.getInputStream());
            this.out = new PrintWriter(this.socket.getOutputStream());

            int o = this.in.nextInt();

            if ( o == 1){
                try{this.s.acquire();}catch (InterruptedException e){e.printStackTrace();}
                this.q.deQueue();
                System.out.println("dequede");
                this.s.release();
            } else {
                try{this.s.acquire();}catch(InterruptedException e){e.printStackTrace();}
                this.out.println(this.q.giveLast());
                System.out.println("Last");

                this.s.release();
            }

            this.out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        this.start();
    }

    @Override
    public void run() {
        //System.out.println("Im in thread");




    }

}

