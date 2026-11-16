package br.edu.unibras.solid.lsp.violacao;

/**
 * VIOLAÇÃO do LSP: ContaInvestimento "é uma" ContaBancaria, mas não cumpre o contrato da classe base,
 * pois lança exceção em {@link #sacar(double)}. Qualquer código que receba uma ContaBancaria
 * e chame sacar() quebra quando recebe esta subclasse.
 */
public class ContaInvestimento extends ContaBancaria {

    @Override
    public void depositar(double v) { saldo += v * 1.02; }

    @Override
    public void sacar(double v) {
        throw new UnsupportedOperationException("Conta investimento não permite saque direto");
    }
}
