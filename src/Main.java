import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cep cep = new Cep();

        String menu = """
                \n1- Consultar Cep
                2- Sair
                """;


        int resposta = 0;
        while (resposta != 2){
            System.out.println(menu);

            System.out.print("\nDigite a opção: ");
            resposta = scanner.nextInt();
            scanner.nextLine();

            if (resposta == 1){
                try {
                    cep.Consultar();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }

            } else if (resposta < 1 || resposta > 2) {
                System.out.println("Opção Inválida");

            }
        }
        System.out.println("Saindo...");
    }
}
