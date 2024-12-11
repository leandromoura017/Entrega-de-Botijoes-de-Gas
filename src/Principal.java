import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o \n
            switch (opcao) {
                case 1:
                    fazerPedido();
                    break;
                case 2:
                    confirmarEntrega();
                    break;
                case 3:
                    verPedidos("Confirmado");
                    break;
                case 4:
                    verPedidos("Entregue");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Confirmar entrega");
        System.out.println("3. Ver pedidos confirmados");
        System.out.println("4. Ver pedidos entregues");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void fazerPedido() {
        System.out.print("Digite seu endereço de entrega: ");
        String endereco = scanner.nextLine();
        Pedido pedido = new Pedido(endereco);
        System.out.print("Quer alterar o endereço de entrega? (s/n): ");

        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o novo endereço: ");
            pedido.setEnderecoEntrega(scanner.nextLine());
            System.out.println("Endereço alterado.");
        }

        System.out.print("Quer confirma o pedido? (s/n): ");

        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite a quantidade de botijões: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Coloque o número do cartão de crédito: ");
            String numeroCartao = scanner.nextLine();
            pedido.confirmarPedido(quantidade, numeroCartao);
            System.out.println("Pedido confirmado.");
            System.out.println(pedido.exibirDados());
            pedidos.add(pedido);
        }
    }

    private static void confirmarEntrega() {
        System.out.print("Digite o código do pedido para confirmar entrega: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (pedido != null && pedido.getStatus().equals("Confirmado")) {
            pedido.marcarComoEntregue();
            System.out.println("Entrega confirmada.");
        } else {
            System.out.println("Pedido não localizado.");
        }
    }

    private static void verPedidos(String status) {
        System.out.println("\nPedidos com status: " + status);
        pedidos.stream()
                .filter(p -> p.getStatus().equals(status))
                .forEach(p -> System.out.println(p.exibirDados() + "\n"));
    }
}
