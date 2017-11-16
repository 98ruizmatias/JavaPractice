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

        boolean cnx = true;
        while (cnx) {

            //  System.out.println("I access to the cnx loop");

           // this.out.println("Que desea hacer: \n 1. Consultar si hay elemntos\n 2. Consultar el ultimo numero\n 3. Ingresar numero en la cola\n 4. Comer Aka");
            // this.out.flush();

            switch (in.nextInt()){
                case 1:
                    try{this.s.acquire();}catch(InterruptedException e){ e.printStackTrace();}
                    this.out.println((this.q.hayAlguito())? "Hay algo en la cola": "No hay nada, esta re free");
                    this.out.flush();
                    this.s.release();
                    break;

                case 2:

                    try{this.s.acquire();}catch(InterruptedException e){e.printStackTrace();}
                    this.out.println("El ultimo elemento es: "+this.q.giveLast());
                    this.out.flush();
                    this.s.release();
                    break;

                case 3:
                    this.out.println("Ingrese su numero");
                    this.out.flush();
                    int num = this.in.nextInt();

                    try {this.s.acquire();}catch(InterruptedException e){e.printStackTrace();}
                    this.q.enQueue(num);
                    this.s.release();

                    this.out.println("Listo!");
                    break;

                case 4:
                    cnx = false;
                    try{this.socket.close();} catch (IOException e){this.socket = null;}
                    break;
            }



        }

    }

}

