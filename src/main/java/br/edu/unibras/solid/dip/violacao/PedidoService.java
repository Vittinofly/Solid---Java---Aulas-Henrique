package br.edu.unibras.solid.dip.violacao;

/**
 * VIOLAÇÃO do DIP: o módulo de alto nível (PedidoService) cria e depende diretamente
 * de um detalhe concreto (EmailNotificador). Trocar para SMS/WhatsApp exige alterar esta classe,
 * e não é possível substituir o notificador por um dublê nos testes.
 */
public class PedidoService {

    private final EmailNotificador notificador = new EmailNotificador();

    public void fecharPedido() {
        notificador.enviarEmail("Pedido Confirmado!");
    }
}
