package br.edu.unibras.solid.lsp.refatorado;

public class ContaInvestimento implements ContaBancaria {

    private double saldo;

    @Override
    public void depositar(double v) { saldo += v * 1.02; } // Rendimento aplicado

    @Override
    public double getSaldo() { return saldo; }
}
