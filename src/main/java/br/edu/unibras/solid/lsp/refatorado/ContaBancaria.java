package br.edu.unibras.solid.lsp.refatorado;

/** Contrato mínimo que TODA conta consegue cumprir. */
public interface ContaBancaria {
    void depositar(double v);
    double getSaldo();
}
