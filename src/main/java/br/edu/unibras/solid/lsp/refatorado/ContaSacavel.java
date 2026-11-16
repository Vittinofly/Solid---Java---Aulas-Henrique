package br.edu.unibras.solid.lsp.refatorado;

/** Só as contas que realmente permitem saque assumem este contrato. */
public interface ContaSacavel extends ContaBancaria {
    void sacar(double v);
}
