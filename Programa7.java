import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.io.File;
import java.text.DecimalFormat;
import java.math.RoundingMode;


/**
 * @author miguelcuellar
 */
//&p-Calculos
//Clase calculos que guarda los datos de acumulacion de las parejas de datos así como las betas
  class Calculos{
    public double sumWi = 0.0;
    public double sumXi = 0.0;
    public double sumYi = 0.0;
    public double sumZi = 0.0;
    public double sumWiSquared = 0.0;
    public double sumWiXi = 0.0;
    public double sumWiYi = 0.0;
    public double sumWiZi = 0.0;
    public double sumXiSquared = 0.0;
    public double sumXiZi = 0.0;
    public double sumXiYi = 0.0;
    public double sumYiSquared = 0.0;
    public double sumYiZi = 0.0;
    public double b0 = 0.0;
    public double b1 = 0.0;
    public double b2 = 0.0;
    public double b3 = 0.0;

//&i
//Inicializador sin parametros
  public Calculos(){
    sumWi = 0.0;
    sumXi = 0.0;
    sumYi = 0.0;
    sumZi = 0.0;
    sumWiSquared = 0.0;
    sumWiXi = 0.0;
    sumWiYi = 0.0;
    sumWiZi = 0.0;
    sumXiSquared = 0.0;
    sumXiZi = 0.0;
    sumXiYi = 0.0;
    sumYiSquared = 0.0;
    sumYiZi = 0.0;
    b0 = 0.0;
    b1 = 0.0;
    b2 = 0.0;
    b3 = 0.0;
  }

//&i
//setB0
  public void setB0(double num){
    this.b0 = num;
  }
//&i
//setB1
  public void setB1(double num){
    this.b1 = num;
  }
//&i
//setB1
  public void setB2(double num){
    this.b2 = num;
  }
  //&i
  //setB3
    public void setB3(double num){
      this.b3 = num;
    }



 }

//&p-DatosArchivo
//Clase en donde se guardan todos los datos del archivo y se lee el mismo
 class DatosArchivo{
   public String sNombre = " ";
   double wk = 0.0, xk = 0.0, yk = 0.0;
   Calculos calc = new Calculos();
   int N = 0;
   double[] x;

//&i
//Inicializador sin parametros

   public DatosArchivo(){
     sNombre = " ";
   }
//&i
//Inicializador con el nombre de parametro
   public DatosArchivo(String n){
     sNombre = n;

   }
//&i
//Set del nombre del archivo
   public void setNombre(String n){
     sNombre = n;
   }
//&i
// get del nombre del archivo
   public String getNombre(){
     return sNombre;
   }
//&i
//Metodo que imprime los valores resultantes
   public void Imprimir(){
     DecimalFormat Redondeo = new DecimalFormat("#.#####");
     Redondeo.setRoundingMode(RoundingMode.HALF_UP);
     DecimalFormat df = new DecimalFormat("#.00000");
     System.out.println("N  = "+N);
     System.out.println("wk  =  "+ df.format(wk));
     System.out.println("xk  =  "+ df.format(xk));
     System.out.println("yk  =  "+ df.format(yk));
     System.out.println("----------------------");
     System.out.println("b0  =  "+Redondeo.format(x[0]));
     System.out.println("b1  =  "+Redondeo.format(x[1]));
     System.out.println("b2  =  "+Redondeo.format(x[2]));
     System.out.println("b3  =  "+Redondeo.format(x[3]));
     System.out.println("----------------------");
     double zk = x[0]+x[1]*wk+x[2]*xk+yk*x[3];
     System.out.printf("zk  =  %.05f \n",zk);

   }
//&i
//Metodo que lee y hace calculos del archivo
   public boolean leer(){
     int icont = 0;
     double wi,xi,yi,zi;
     wi=xi=yi=zi=0;
     try {
          BufferedReader in = new BufferedReader(new FileReader(sNombre));
          String linea;
          if (in.readLine()==null) {
            System.out.println("El archivo esta vacio");
            return false;
          }
          BufferedReader info = new BufferedReader(new FileReader(sNombre));
          while((linea = info.readLine()) != null) {
            if (icont==0) {
              String[] parteSeparada = linea.split("\\,");
              wk = Double.parseDouble(parteSeparada[0]);
              xk = Double.parseDouble(parteSeparada[1]);
              yk = Double.parseDouble(parteSeparada[2]);

            }else{
              String[] parteSeparada = linea.split("\\,");
              wi = Double.parseDouble(parteSeparada[0]);
              xi = Double.parseDouble(parteSeparada[1]);
              yi = Double.parseDouble(parteSeparada[2]);
              zi = Double.parseDouble(parteSeparada[3]);
              calc.sumWi += wi;
              calc.sumXi += xi;
              calc.sumYi += yi;
              calc.sumZi += zi;
              calc.sumWiSquared += wi*wi;
              calc.sumWiXi += wi*xi;
              calc.sumWiYi += wi*yi;
              calc.sumWiZi +=wi*zi;
              calc.sumXiSquared += xi*xi;
              calc.sumXiYi += xi*yi;
              calc.sumXiZi += xi*zi;
              calc.sumYiSquared += yi*yi;
              calc.sumYiZi += yi*zi;
              N++;
            }
            icont++;
          }
          double[][] acums = { { N, calc.sumWi,  calc.sumXi, calc.sumYi, calc.sumZi },
                         { calc.sumWi, calc.sumWiSquared, calc.sumWiXi, calc.sumWiYi, calc.sumWiZi },
                         { calc.sumXi, calc.sumWiXi, calc.sumXiSquared, calc.sumXiYi, calc.sumXiZi },
                         {calc.sumYi, calc.sumWiYi, calc.sumXiYi, calc.sumYiSquared,calc.sumYiZi }
                       };

           x = Betas(acums);
          return true;
        }
        catch(IOException e){
          return false;
        }
   }

   //&i
  //Metodo que hace gauss y calcula las betas
   public double[] Betas(double acums[][]){
       double[] betas = new double[4];
       double div;
       for (int x = 0; x < 4; x++){
           for (int y = 0; y < 4; y++){
               if (y != x){
                   div = acums[y][x]/acums[x][x];
                   for (int z = 0; z < 4 + 1; z++){
                       acums[y][z] = acums[y][z] - div * acums[x][z];
                   }
               }
           }
       }
       for (int j=0; j<4; j++){
           betas[j] = acums[j][4]/acums[j][j];
       }
       return betas;
   }
 }
 //&p-Programa7
 //Clase Programa7 donde se piden los datos
public class Programa7{
  //&b=23
    public static void main(String[] args) {
      ArrayList<DatosArchivo> Archivos = new ArrayList<DatosArchivo>();
//&d=1
      System.out.println("Ingrese el nombre del archivo"); //&m
      Scanner nomArchivos = new Scanner(System.in);
      String  inputNombres = nomArchivos.nextLine();
      String[] nombres = inputNombres.split("\\s+"); //&m
      for (int ix = 0; ix<nombres.length; ix++) {
        DatosArchivo arch = new DatosArchivo();
        arch.setNombre(nombres[ix]);
        Archivos.add(arch);
      }


  for(int iy = 0; iy<Archivos.size(); iy++) {
//&d=7
  if(Archivos.get(iy).leer()){
    Archivos.get(iy).Imprimir();
  }else{
    System.out.println("El archivo con nombre: "+Archivos.get(iy).getNombre()+" no existe.");
  }
    }
  }
}
