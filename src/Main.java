import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(final String[] args) throws IOException {
        final Scanner strInput = new Scanner(System.in);
        for (String cont = "s"; cont.equalsIgnoreCase("s"); cont = strInput.nextLine()) {

			System.out.println("Controle de Estoque -> Renner do Seu Jão");
			System.out.println();
            System.out.println("1 ===> Adicionar novo produto ");
            System.out.println("2 ===> Visualizar todos os produtos ");
            System.out.println("3 ===> Deletar Produto ");
            System.out.println("4 ===> Procurar produto ");
            System.out.println("5 ===> Atualizar produto ");
            System.out.print("\n\n");
			System.out.println("Digite sua escolha: ");

            final String choice = strInput.nextLine();
            if (choice.equals("1")) {
                DAO.AddRecord();
            }
            else if (choice.equals("2")) {
                DAO.ViewAllRecord();
            }
            else if (choice.equals("3")) {
                DAO.DeleteRecordByID();
            }
            else if (choice.equals("4")) {
                DAO.SearchRecordbyID();
            }
            else if (choice.equals("5")) {
                DAO.updateRecordbyID();
            }
            System.out.println("Deseja continuar? S/N");
        }
    }
}