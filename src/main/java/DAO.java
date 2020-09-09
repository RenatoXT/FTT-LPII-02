import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class DAO {

    public static void AddRecord() throws IOException {
           try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("desafio_db.txt", true));
            final Scanner strInput = new Scanner(System.in);
            Date DTEntr;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Código do item: ");
            final int COD = Integer.parseInt(strInput.nextLine());
            System.out.println("Data de Entrada: ");
            try{
                DTEntr = formato.parse(strInput.nextLine());
            }catch (ParseException e) {
                DTEntr = new Date();
            }
            
            System.out.println("Local da Compra: ");
            final String LocComp = strInput.nextLine();
            System.out.println("Tipo: ");
            final String Tipo = strInput.nextLine();
            System.out.println("Marca: ");
            final String Marca = strInput.nextLine();
            System.out.println("Características: ");
            final String Caract = strInput.nextLine();
            System.out.println("Tamanho [PP, P, M, G, GG]: ");
            final StorageModel.OpcoesTamanho Size = StorageModel.OpcoesTamanho.valueOf(strInput.nextLine().toUpperCase());
            System.out.println("Cor[Roxo, Preto, Branco, Vermelho, Rosa]: ");
            String convert_cor = strInput.nextLine().toLowerCase();
            final StorageModel.OpcoesCor Cor = StorageModel.OpcoesCor.valueOf(convert_cor.substring(0, 1).toUpperCase() + convert_cor.substring(1).toLowerCase());
            System.out.println("Valor de etiqueta na compra: ");
            final float ValEtiq = Float.parseFloat(strInput.nextLine());
            System.out.println("Valor pago na compra: ");
            final float ValComp = Float.parseFloat(strInput.nextLine());
            final float Val100 = 2 * ValComp;
            System.out.println("Preço sugerido: ");
            final float ValSug = Float.parseFloat(strInput.nextLine());
            bw.write(COD + ";" + DTEntr + ";" + LocComp + ";" + Tipo + ";" + Marca + ";" + Caract + ";"
                    + Size + ";" + Cor + ";" + ValEtiq + ";" + ValComp + ";" + Val100 + ";" + ValSug);
            bw.flush();
            bw.newLine();
        
    } catch(Exception error){
            System.out.println();
            System.out.println(error.getMessage());
        }
                
    }


    public static void ViewAllRecord() throws IOException, ParseException {
        try{
            BufferedReader br = new BufferedReader(new FileReader("desafio_db.txt"));
                
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
        }catch(Exception error)
        {
            System.out.println();
            System.out.println(error.getMessage());
        }
        
    }

    public static void DeleteRecordByID() throws IOException {
        Scanner strInput = new Scanner(System.in);
        String record;
        int COD;

        File tempDB = new File("desafio_db_temp.txt");
        File db = new File("desafio_db.txt");
        try{
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

            if (firstToken != COD){
                bw.write(record);
            bw.flush();
            bw.newLine();
                }
            
            
            
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
        }catch(Exception error){
            System.out.println();
            System.out.println(error.getMessage());
        }
    }

    public static void SearchRecordbyID() throws IOException {
        String record;
        int COD;
        Scanner strInput = new Scanner(System.in);

        try{
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
                } catch (NumberFormatException error){
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
        }catch(Exception error){
            System.out.println();
        System.out.println(error.getMessage());
        }

    }

    public static void updateRecordbyID() throws IOException {
        String newDtInput, newLocalCompra, newTipo, newMarca, newCarac, newTamanho, newCor;
        float newEtiqVal, newPagoVal, newVal100, newPrecoSug;
        String oldRecord, newRecord;
        int COD;

        File db = new File("desafio_db.txt");
        File tempDB = new File("desafio_db_temp.txt");

        BufferedWriter bw;
        Scanner strInput;
        strInput = new Scanner(System.in);
        try {
            BufferedReader br = new BufferedReader(new FileReader(db));
            bw = new BufferedWriter(new FileWriter(tempDB));
            
            System.out.println("Atualizar produto");
            System.out.println("Insira o código do produto: ");
            COD = Integer.parseInt(strInput.nextLine());
            while ((oldRecord = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(oldRecord, ";");
                int firstToken;
                
                try{
                    firstToken = Integer.valueOf(oldRecord.split(";")[0]);
                } catch (NumberFormatException error){
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
            bw = new BufferedWriter(new FileWriter(tempDB));
            while ((newRecord = br2.readLine()) != null) {
                int firstToken;
                try{
                    firstToken = Integer.valueOf(newRecord.split(";")[0]);
                } catch (NumberFormatException error){
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
    }catch(Exception error){
            System.out.println();
        System.out.println(error.getMessage());
        }
}
}