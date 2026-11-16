package br.edu.unibras.solid.ocp.refatorado;

/** Abstração estável: fechada para modificação, aberta para extensão. */
public interface DescontoService {
    double calcular(double valor);
}
