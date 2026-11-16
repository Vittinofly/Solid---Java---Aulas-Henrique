package br.edu.unibras.solid.lsp.refatorado;

public class ContaCorrente implements ContaSacavel {

    private double saldo;

    @Override
    public void depositar(double v) { saldo += v; }

    @Override
    public void sacar(double v) {
        if (v > saldo) throw new IllegalArgumentException("Saldo insuficiente");
        saldo -= v;
    }

    @Override
    public double getSaldo() { return saldo; }
}
