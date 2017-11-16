package MatrixRotate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket wfc = new ServerSocket(9999);
            Socket socket;
            Scanner soIn;
            PrintWriter soOut;
            int r;
            int c;

            while (true){
                socket = wfc.accept();

                soIn = new Scanner(socket.getInputStream());
                soOut = new PrintWriter(socket.getOutputStream());


                r = soIn.nextInt();
                soIn.nextLine();

                c = soIn.nextInt();
                soIn.nextLine();

                double[][] a = new double[r][c];

                for(int i = 0; i < r; ++i){
                    for (int j = 0; j < c; ++j) {
                            a[i][j] = soIn.nextDouble();
                            soIn.nextLine();
                    }
                }
                System.out.println("tengo todo");

                a = matrixRotate(a);

                for(int i = 0; i < r; ++i){
                    for (int j = 0; j < c; ++j) {
                        soOut.println(a[i][j]);
                    }
                }

                soOut.flush();


            }


        }catch (IOException e){
            e.printStackTrace();
        }



    }


    public static double[][] matrixRotate(double[][] m){
        double aux = m[m.length-1][m[0].length-1];
        for (int i = m.length-1; i >= 0 ; --i){
            for (int j = m[0].length-2; j >= 0; --j){
                m[i][j+1] = m[i][j];
            }
            if (i >= 1){
                m[i][0] = m[i-1][m.length-1];
            }
        }
        m[0][0] = aux;
        return m;
    }

}
