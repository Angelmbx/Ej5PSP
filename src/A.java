import java.io.*;
import java.util.Scanner;

public class A {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba algo: ");
        String respuesta = sc.nextLine();

        File ruta = new File(".\\out\\production\\EJ5PSP");
        ProcessBuilder pb = new ProcessBuilder("Java", "B", respuesta);
        pb.directory(ruta);

        Process p = pb.start();

        try {
            InputStream is = p.getErrorStream();
            BufferedReader buffr = new BufferedReader(new InputStreamReader(is));
            String linea;
            while ((linea = buffr.readLine()) != null)
                System.out.println("ERROR: "+linea);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        int exitVal;

        try {
            exitVal = p.waitFor();

            switch (exitVal){
                case 1:
                    System.out.println("Valor de salida "+exitVal);
                    break;

                case 2:
                    System.out.println("Por alguna razón vamos a repetir tu respuesta 5 veces:");
                    for(int i=0; i<5; i++){
                        System.out.println(respuesta);
                    }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }




}
