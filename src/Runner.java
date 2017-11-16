import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Runner extends Thread {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private int[] a;

    private Semaphore s;



    public Runner(Socket socket, int[] a){
        this.socket = socket;
        this.a = a;
        this.s =  new Semaphore(1);
        try {
            this.in = new Scanner(this.socket.getInputStream());
            this.out = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execute(){
        this.start();
    }

    @Override
    public void run(){
        //System.out.println("Im in thread");

        boolean cnx = true;
        while (cnx){
          //  System.out.println("I access to the cnx loop");
            out.println("Introduzca su numero: ");
            out.flush();
            //System.out.println("Im after first flush");

            try {
               // System.out.println("Im before in");

                int n = in.nextInt();

               // System.out.println("Im after in");

                boolean ih = safeIsHere(n);

                out.println("Su numero "+ ((ih)? "": "no ")+"se encuentra en el arreglo.");
                out.flush();
            } catch (NoSuchElementException nse){
                cnx = false;
            }


        }

    }



    private boolean isHere(int num) {
        sortIt();

        int top = this.a.length-1;
        int bot = 0;
        if (num < this.a[bot] || num > this.a[top]){
            return false;
        }
        while (bot <= top){
            int mid = bot + (top - bot)/2;

            if ( this.a[mid] == num ){
                System.out.printf("Ahi vaaa");
                return true;
            }
            if (num < this.a[mid]){
                top = mid -1;
                System.out.println("Por ahora no...");
            }
            if (num > this.a[mid]){
                bot = mid+1;
                System.out.println("No hay caso che...");

            }

        }
        return false;
    }

    private void sortIt() {
        //bubble sort
        int k;
        int aux;
        for (int i = 0; i < this.a.length-1; ++i){
            for (int j = 0; j < this.a.length-1; ++j){
                k = j+1;
                if (this.a[j] > this.a[k]){
                    aux = this.a[j];
                    this.a[j] = this.a[k];
                    this.a[k] = aux;
                }
            }
        }
    }

    private boolean safeIsHere(int num){


        try {
            this.s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean ih = isHere(num);


        this.s.release();

        return ih;
    }

    private void safeSortIt(){
        try{
            this.s.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        this.s.release();
    }

}

