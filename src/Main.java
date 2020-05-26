import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

public class Main   {
    static BufferedWriter file;
    static Vector vector, aux;
    private int date;

    public Main(int f) {
        date = f;
    }

    public static void main(String[] args) throws IOException {
        long a[] = new long[1000];
        crearFechas();
        //vector(vector);
        descompose(vector);
        for (int i = 0; i < vector.size(); i++){
            a[i] = Long.valueOf((String) vector.get(i));
        }
        quicksort(a, 0, a.length-1);
        vector.clear();
        for (int i = 0; i < a.length; i++){
            Object b = a[i];
            vector.add(b);
        }
        compose(vector);
        //vector(vector);
        createdFile(vector);
        //vector(vector);
    }

    public static void crearFechas() throws IOException {
        file = new BufferedWriter(new FileWriter("aleatorio.txt"));
        vector = new Vector(999,1);
        Random random = new Random(413);
        Calendar calendar;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            for (int i = 0; i < 1000; i++) {
                calendar = Calendar.getInstance();
                calendar.set(random.nextInt(50) + 1950, random.nextInt(12), random.nextInt(30) + 1);
                file.write(sdf.format(calendar.getTime())+"\n");
                vector.add(sdf.format(calendar.getTime()));
            }
            file.close();
            System.out.println("Archivo ALEATORIO creado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void vector(Vector vector){
        for (int i = 0; i < vector.size(); i++){
            System.out.println(vector.get(i) + "  ");
        }
    }

    public static void descompose(Vector vector){
        aux = new Vector(1000);
        for (int i = 0; i < vector.size(); i++){
            String linea = (String) vector.get(i);
            String fecha = "";
            for (int j = 0; j < linea.length(); j++) {
                char c = linea.charAt(j);
                if (c != '/') {
                    fecha = fecha + (String.valueOf(c));
                }
            }
            aux.add(fecha);
        }
        vector.clear();
        for (int i = 0; i < aux.size(); i++){
            vector.add(aux.elementAt(i));
        }
    }

    public static void compose(Vector vector){
        aux = new Vector(1000);
        for (int i = 0; i < vector.size(); i++){
            String linea = (String.valueOf(vector.get(i)));
            String fecha = "";
            for (int j = 0; j < linea.length(); j++) {
                char c = linea.charAt(j);
                fecha = fecha + (String.valueOf(c));
                if (j == 3 || j == 5 ) {
                    fecha = fecha + "/";
                }
            }
            aux.add(fecha);
        }
        vector.clear();
        for (int i = 0; i < aux.size(); i++){
            vector.add(aux.elementAt(i));
        }
    }

    public static void createdFile(Vector vector) throws IOException {
        file = new BufferedWriter(new FileWriter("ordenado.txt"));
        try {
            for (int i = 0; i < vector.size(); i++) {
                file.write((String) vector.get(i)+"\n");
            }
            file.close();
            System.out.println("Archivo ordenado creado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void quicksort(long a[], int firts, int ultimo) {
        int i, j, central;
        double pivot;
        central = (firts + ultimo) / 2;
        pivot = a[central];
        i = firts;
        j = ultimo;
        do {
            while (a[i] > pivot)
                i++;
            while (a[j] < pivot)
                j--;
            if (i <= j) {
                exchange(a, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (firts < j)
            quicksort(a, firts, j); // mismo proceso con sublista izquierda
        if (i < ultimo)
            quicksort(a, i, ultimo); // mismo proceso con sublista derecha
    }

    public static void exchange(long[] a, int i, int j) {
        long aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }

    public static int[] ordQuicksortAsc(int[] data, int left, int right) {
        int pivote=data[left];
        int i=left;
        int j=right;
        int aux;
        while(i<j){
            while(data[i]<=pivote && i<j){
                i++;
            }
            while(data[j]>pivote){
                j--;
            }
            if (i<j) {
                aux= data[i];
                data[i]=data[j];
                data[j]=aux;
            }
        }
        data[left]=data[j];
        data[j]=pivote;
        if(left<j-1){
            ordQuicksortAsc(data,left,j-1);
        }
        if(j+1 <right){
            ordQuicksortAsc(data,j+1,right);
        }
        return data;
    }


}

