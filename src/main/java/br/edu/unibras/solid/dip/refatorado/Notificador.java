package br.edu.unibras.solid.dip.refatorado;

/** Abstração da qual tanto o módulo de alto nível quanto os detalhes dependem. */
public interface Notificador {
    void enviar(String msg);
}
