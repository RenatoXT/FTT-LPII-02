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

            // To-Do
            /*
             * Data de Entrega -> Converter para Date
             * Tamanho -> Converter para Enum
             * Cor -> Converter para Enum
             */

             /**
              * public enum OpcoesTamanho {
                PP(1), P(2), M(3), G(4), GG(5);

                private final int valor;
                OpcoesTamanho(int valorOpcao){
                valor = valorOpcao;
                }
                public int getValor(){
                return valor;
                }
                }
              *
              */

    public static void AddRecord() throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter("desafio_db.txt", true));
        final Scanner strInput = new Scanner(System.in);

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
        String record;
        int COD;

        File tempDB = new File("desafio_db_temp.txt");
        File db = new File("desafio_db.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        System.out.println("Deletar produto");

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

            if (firstToken == COD)
                continue;

            bw.write(record);
            bw.flush();
            bw.newLine();

        }

        br.close();
        bw.close();

        db.delete();

        boolean success = tempDB.renameTo(db);
        if(success) {
            System.out.println();
            System.out.println("Item deletado com sucesso!");

        } else {
            System.out.println();
            System.out.println("ERROR 500");
            System.out.println("Ocorreu um erro, informe o suporte!");
        }
    }

    public static void SearchRecordbyID() throws IOException {
        String record;
        int COD;
        Scanner strInput = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new FileReader("desafio_db.txt"));

        System.out.println("Procurar produto");

        System.out.println("Insira o código do produto: ");
        COD = Integer.parseInt(strInput.nextLine());

        System.out.println();

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

    }

    public static void updateRecordbyID() throws IOException {
        String newDtInput, newLocalCompra, newTipo, newMarca, newCarac, newTamanho, newCor;
        float newEtiqVal, newPagoVal, newVal100, newPrecoSug;
        String oldRecord, newRecord;
        int COD;

        File db = new File("desafio_db.txt");
        File tempDB = new File("desafio_db_temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        Scanner strInput = new Scanner(System.in);


        System.out.println("Atualizar produto");
        System.out.println("Insira o código do produto: ");
        COD = Integer.parseInt(strInput.nextLine());

                while ((oldRecord = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(oldRecord, ";");
            int firstToken;

            try{
                firstToken = Integer.valueOf(oldRecord.split(";")[0]);
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

        System.out.print("nova data de Entrada: ");
        newDtInput = strInput.nextLine();

        System.out.print("Local da Compra: ");
        newLocalCompra = strInput.nextLine();

        System.out.print("Tipo: ");
        newTipo = strInput.nextLine();

        System.out.print("Marca: ");
        newMarca = strInput.nextLine();

        System.out.print("Características: ");
        newCarac = strInput.nextLine();

        System.out.print("Tamanho: ");
        newTamanho = strInput.nextLine();

        System.out.print("Cor: ");
        newCor = strInput.nextLine();

        System.out.print("Valor de etiqueta na compra: ");
        newEtiqVal = Float.parseFloat(strInput.nextLine());

        System.out.print("Valor pago na compra: ");
        newPagoVal = Float.parseFloat(strInput.nextLine());

        newVal100 = 2 * newPagoVal;

        System.out.print("Preço sugerido: ");
        newPrecoSug = Float.parseFloat(strInput.nextLine());




        BufferedReader br2 = new BufferedReader(new FileReader(db));

        while ((newRecord = br2.readLine()) != null) {
            int firstToken;
            try{
                firstToken = Integer.valueOf(newRecord.split(";")[0]);
            } catch (Exception error){
                firstToken = 0;
            }

            if (firstToken == COD) {
                bw.write(COD + ";" + newDtInput + ";" + newLocalCompra + ";" + newTipo + ";" +
                        newMarca + ";" + newCarac + ";" + newTamanho + ";" + newCor + ";" + newEtiqVal +
                        ";" + newVal100 + ";" + newPrecoSug);
            } else {

                bw.write(newRecord);
            }
            bw.flush();
            bw.newLine();
        }

        bw.close();
        br2.close();
        db.delete();
        boolean success = tempDB.renameTo(db);
        if(success) {
            System.out.println();
            System.out.println("Atualização realizada com sucesso!");

        } else {
            System.out.println();
            System.out.println("ERROR 500");
            System.out.println("Ocorreu um erro, informe o suporte!");
        }
    }
}