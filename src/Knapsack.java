import java.util.Scanner;

public class Knapsack {

    static int findMax(int a, int b){
        if(a > b){
            return a;
        }else
            return b;
    }

    static void tabelKnapsack(int kapasitas, int[] berat, int[] nilai, int in){
        int i, w;
        int tas[][] = new int[in+1][kapasitas+1];

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("           Tabel Knapsack");
        System.out.println();
        System.out.println("-----------------------------------------");

        //Menyimpan data barang yang diambil dan tidak dalam bentuk tabel/matriks
        for(i=0; i<=in; i++){
            for(w=0; w<=kapasitas; w++){
                if(i == 0 || w == 0){
                    //case 1 : kondisi tas masih kosong dan tidak ada barang
                    //return 0; i=0 atau w=0
                    tas[i][w] = 0;
                }else if(berat[i-1] <= w){
                    //case 2 : kondisi kapasitas tas dapat memuat barang saat ini
                    //ketika berat barang saat ini (berat[i-1]) <= kapasitas tas (w)
                    tas[i][w] = findMax((nilai[i-1]+tas[i-1][w - berat[i-1]]), tas[i-1][w]);
                }else{
                    //case 3 : kondisi kapasitas tas tidak dapat memuat barang
                    //ketika berat barang saat ini (berat[i-1]) > kapasitas tas (w)
                    tas[i][w] = tas[i-1][w];
                }
                System.out.print(tas[i][w] +"\t" +"|\t");
            }
            System.out.println();
        }

        int hasilAkhir = tas[in][kapasitas];
        System.out.println("-----------------------------------------");
        System.out.println("Hasil Knapsack : " +hasilAkhir);

        System.out.println("-----------------------------------------");
        System.out.println("Barang yang diambil : ");
        w = kapasitas;
        //Menulis kesimpulan barang yang diambil
        for(i=in; i>0 && hasilAkhir>0; i--){
            if(hasilAkhir == tas[i-1][w]){
                continue;
            }else{
                System.out.println("Barang ke-" +i +" berat : " +berat[i-1] +" nilai : " +nilai[i-1]);

                hasilAkhir = hasilAkhir-nilai[i-1];
                w = w - berat[i-1];
            }
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("Masukkan Jumlah Barang : ");
        int in = s.nextInt();
        int nilai[] = new int[in];
        int berat[] = new int[in];

        for(int i=0; i<in; i++){
            System.out.println("\nBarang ke-" +(i+1));
            System.out.print("Berat Barang : ");
            berat[i] = s.nextInt();
            System.out.print("Nilai Barang : ");
            nilai[i] = s.nextInt();
        }

        System.out.print("\nMasukkan kapasitas yang akan dibawa : ");
        int kapasitas = s.nextInt();

        tabelKnapsack(kapasitas, berat, nilai, in);

    }
}

