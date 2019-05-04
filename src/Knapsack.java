import java.util.Scanner;

public class Knapsack {

    static void tabelKnapsack(int kapasitas, int[] berat, int[] nilai, int in){
        //method untuk menghitung knapsack
        int i, w;
        int tas[][] = new int[in+1][kapasitas+1];

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("           Tabel Knapsack");
        System.out.println();
        System.out.println("-----------------------------------------");

        //Menyimpan data nilai keuntungan yang diperoleh dari barang yang diambil dalam bentuk tabel/matriks
        for(i=0; i<=in; i++){
            for(w=0; w<=kapasitas; w++){
                if(i == 0 || w == 0){
                    //case 1 : kondisi tas masih kosong dan tidak ada barang
                    //return 0; i=0 atau w=0
                    tas[i][w] = 0;
                }else if(berat[i-1] <= w){
                    //case 2 : kondisi kapasitas tas dapat memuat barang saat ini
                    //ketika berat barang saat ini (berat[i-1]) <= kapasitas tas (w)
                    //mencari nilai maksimum dari nilai barang saat ini ditambah dengan nilai keuntungan knapsack dan
                    //nilai kondisi tas sebelum barang saat ini masuk
                    tas[i][w] = Math.max((nilai[i-1]+tas[i-1][w - berat[i-1]]), tas[i-1][w]);
                }else{
                    //case 3 : kondisi kapasitas tas tidak dapat memuat barang
                    //ketika berat barang saat ini (berat[i-1]) > kapasitas tas (w)
                    //maka yang diambil adalah barang yang sebelumnya
                    tas[i][w] = tas[i-1][w];
                }
                System.out.print(tas[i][w] +"\t" +"|\t");
            }
            System.out.println();
        }

        //menyimpan hasil knapsack kedalam variabel baru
        int hasilAkhir = tas[in][kapasitas];
        System.out.println("-----------------------------------------");
        System.out.println("Hasil Knapsack : " +hasilAkhir);

        System.out.println("-----------------------------------------");
        System.out.println("Barang yang diambil : ");
        w = kapasitas;
        //Menulis kesimpulan barang yang diambil
        for(i=in; i>0 && hasilAkhir>0; i--){
            if(hasilAkhir == tas[i-1][w]){
                //jika kondisi hasil akhir knapsack sesuai dengan isi tas saat ini
                continue;
            }else{
                //mengambil nilai barang yang diambil
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

