package br.edu.unibras.solid.lsp.violacao;

/** Classe base cujo contrato promete que toda conta permite depósito E saque. */
public class ContaBancaria {

    protected double saldo;

    public void depositar(double v) { saldo += v; }

    public void sacar(double v) {
        if (v > saldo) throw new IllegalArgumentException("Saldo insuficiente");
        saldo -= v;
    }

    public double getSaldo() { return saldo; }
}
