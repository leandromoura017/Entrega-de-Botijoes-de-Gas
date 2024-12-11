import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private static int contador = 1;
    private int codigo;
    private String enderecoEntrega;
    private LocalDateTime horaCompra;
    private LocalDateTime horaEntrega;
    private int quantidadeBotijoes;
    private double totalCompra;
    private String numeroCartao;
    private String status; //
    private static final double PRECO_UNITARIO = 100.00;

    public Pedido(String enderecoEntrega) {
        this.codigo = contador++;
        this.enderecoEntrega = enderecoEntrega;
        this.horaCompra = LocalDateTime.now();
        this.status = "Novo";
    }

    public int getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public void confirmarPedido(int quantidadeBotijoes, String numeroCartao) {
        this.quantidadeBotijoes = quantidadeBotijoes;
        this.numeroCartao = numeroCartao;
        this.totalCompra = quantidadeBotijoes * PRECO_UNITARIO;
        this.horaEntrega = horaCompra.plusHours(6);
        this.status = "Confirmado";
    }

    public void marcarComoEntregue() {
        this.status = "Entregue";
    }

    public String exibirDados() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Código: %d\nEndereço: %s\nHora da Compra: %s\nHora de Entrega: %s\nQuantidade: %d\nTotal: R$ %.2f\nStatus: %s",
                codigo, enderecoEntrega, horaCompra.format(formatter),
                (horaEntrega != null ? horaEntrega.format(formatter) : "N/A"),
                quantidadeBotijoes, totalCompra, status);
    }
}
