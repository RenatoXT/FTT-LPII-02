import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class DAO {

    /**
     * Renomear variáveis
     */

    public static void AddRecord() throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter("desafio_db.txt", true));
        final Scanner strInput = new Scanner(System.in);

        // To-Do
        /*
         * Data de Entrega -> Converter para Date
         * Tamanho -> Converter para Enum
         * Cor -> Converter para Enum
         */

        System.out.print("Código do item: ");
        final int COD = Integer.parseInt(strInput.nextLine());

        System.out.print("Data de Entrada: ");
        final String DTEntr = strInput.nextLine();

        System.out.print("Local da Compra: ");
        final String LocComp = strInput.nextLine();

        System.out.print("Tipo: ");
        final String Tipo = strInput.nextLine();

        System.out.print("Marca: ");
        final String Marca = strInput.nextLine();

        System.out.print("Características: ");
        final String Caract = strInput.nextLine();

        System.out.print("Tamanho: ");
        final String Size = strInput.nextLine();

        System.out.print("Cor: ");
        final String Cor = strInput.nextLine();

        System.out.print("Valor de etiqueta na compra: ");
        final float ValEtiq = Float.parseFloat(strInput.nextLine());

        System.out.print("Valor pago na compra: ");
        final float ValComp = Float.parseFloat(strInput.nextLine());

        final float Val100 = 2 * ValComp;

        System.out.print("Preço sugerido: ");
        final float ValSug = Float.parseFloat(strInput.nextLine());

        bw.write(COD + ";" + DTEntr + ";" + LocComp + ";" + Tipo + ";" + Marca + ";" + Caract + ";"
                + Size + ";" + ValEtiq + ";" + ValComp + ";" + Val100 + ";" + ValSug);
        bw.flush();
        bw.newLine();
        bw.close();
    }

    /**
     * Refatorar -> Substituir token por split
     * Inserir Try-catchs
     * Terminar de inserir as variáveis no UpdateRecord
     * Modificar o SearchRecord para ser similar ao update (Validação do código)
    */

    public static void ViewAllRecord() throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader("desafio_db.txt"));
        String record;

        while ((record = br.readLine()) != null) {
            final StringTokenizer st = new StringTokenizer(record, ";");
            final List<String> elements = new ArrayList<String>();

            while (st.hasMoreTokens()) {
                elements.add(st.nextToken());
            }

            for (final String campos : elements) {
                System.out.print(String.valueOf(campos) + " | ");
            }

            System.out.println();
        }
        br.close();
    }

    public static void DeleteRecordByID() throws IOException {
        Scanner strInput = new Scanner(System.in);
        String COD, record;

        File tempDB = new File("desafio_db_temp.txt");
        File db = new File("desafio_db.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        System.out.println("Deletar produto");

        System.out.println("Insira o código do produto: ");
        COD = strInput.nextLine();

        while ((record = br.readLine()) != null) {

            if (record.contains(COD))
                continue;

            bw.write(record);
            bw.flush();
            bw.newLine();

        }

        br.close();
        bw.close();

        db.delete();

        tempDB.renameTo(db);
    }

    public static void SearchRecordbyID() throws IOException {
        String COD, record;
        Scanner strInput = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new FileReader("desafio_db.txt"));

        System.out.println("Procurar produto");

        System.out.println("Insira o código do produto: ");
        COD = strInput.nextLine();

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, ";");
            final List<String> elements = new ArrayList<String>();

            if (record.contains(COD)) {
                while (st.hasMoreTokens()) {
                    elements.add(st.nextToken());
                }

                for (final String campos : elements) {
                    System.out.print(String.valueOf(campos) + " | ");
                }
            }

            System.out.println();

        }

        br.close();

    }

    public static void updateRecordbyID() throws IOException {
        String newName, newAge, newAddr, record, record2;
        int COD;

        File db = new File("desafio_db.txt");
        File tempDB = new File("desafio_db_temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        Scanner strInput = new Scanner(System.in);

        System.out.println("Atualizar produto");
        System.out.println("Insira o código do produto: ");
        COD = Integer.parseInt(strInput.nextLine());

        while ((record = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(record, ";");
            int firstToken;

            try{
                firstToken = Integer.valueOf(record.split(";")[0]);
            } catch (Exception error){
                firstToken = 0;
            }

            if (firstToken == COD) {
                final List<String> elements = new ArrayList<String>();

            if (firstToken == COD) {
                while (st.hasMoreTokens()) {
                    elements.add(st.nextToken());
                }

                for (final String campos : elements) {
                    System.out.print(String.valueOf(campos) + " | ");
                }
            }

            System.out.println();
            }

        }

        br.close();

        System.out.print("Insira a nova Data de Entrada: ");
        newName = strInput.nextLine();

        System.out.print("Insira o novo Local da Compra: ");
        newAge = strInput.nextLine();

        System.out.print("Insira o novo Tipo: ");
        newAddr = strInput.nextLine();


        BufferedReader br2 = new BufferedReader(new FileReader(db));

        while ((record2 = br2.readLine()) != null) {
            int firstToken;
            try{
                firstToken = Integer.valueOf(record2.split(";")[0]);
            } catch (Exception error){
                firstToken = 0;
            }

            if (firstToken == COD) {
                bw.write(COD + ";" + newName + ";" + newAge + ";" + newAddr);
            } else {

                bw.write(record2);
            }
            bw.flush();
            bw.newLine();
        }

        bw.close();
        br2.close();
        db.delete();
        boolean success = tempDB.renameTo(db);
        System.out.println(success);

    }
}