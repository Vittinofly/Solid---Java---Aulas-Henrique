package br.edu.unibras.solid.dip.refatorado;

/** Depende apenas da abstração {@link Notificador}, recebida por injeção no construtor. */
public class PedidoService {

    private final Notificador notificador;

    public PedidoService(Notificador n) {
        this.notificador = n;
    }

    public void fecharPedido() {
        notificador.enviar("Pedido Confirmado!");
    }
}
