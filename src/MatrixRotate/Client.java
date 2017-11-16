package MatrixRotate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[]args){
        Scanner syIn = new Scanner(System.in);
        Scanner soIn;
        PrintWriter soOut;
        int r;
        int c;

        System.out.println("Ingrese las filas: ");
        r = syIn.nextInt();


        System.out.println("Ingrese las columnass: ");
        c = syIn.nextInt();



        double[][] a = new double[r][c];

        for (int i = 0; i < r; ++i){
            for (int j = 0; j < c; ++j){
                System.out.println("Ingrese valor en la matrix: ");
                a[i][j] = syIn.nextDouble();
            }
        }

        try{
            Socket socket = new Socket("localhost", 9999);
            soIn = new Scanner(socket.getInputStream());
            soOut = new PrintWriter(socket.getOutputStream());

            //enviar
            soOut.println(r);
            soOut.println(c);
            soOut.flush();


            for (int i = 0; i < r; ++i){
                for (int j = 0; j < c; ++j){
                    soOut.println(a[i][j]);
                }
            }
            soOut.flush();


            //parsear
            for (int i = 0; i < r; ++i){
                for (int j = 0; j < c; ++j){
                    a[i][j] = soIn.nextDouble();
                    soIn.nextLine();
                }
            }

            //imprimir resulta3
            System.out.println("A continuacion su matriz rotada a la derecha: ");
            for (int i = 0; i < r; ++i){
                for (int j = 0; j < c; ++j){
                    System.out.println(a[i][j]+", ");
                }
                System.out.println();
            }



        } catch(IOException e){
            e.printStackTrace();
        }





    }



}
