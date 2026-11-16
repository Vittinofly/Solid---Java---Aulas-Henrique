package br.edu.unibras.solid.lsp;

import br.edu.unibras.solid.lsp.refatorado.ContaBancaria;
import br.edu.unibras.solid.lsp.refatorado.ContaCorrente;
import br.edu.unibras.solid.lsp.refatorado.ContaInvestimento;
import br.edu.unibras.solid.lsp.refatorado.ContaSacavel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LiskovTest {

    @Test
    void testSubstituicao() {
        ContaBancaria conta = new ContaInvestimento();
        conta.depositar(100.0);
        Assertions.assertEquals(102.0, conta.getSaldo(), 0.001);
    }

    @Test
    void qualquerContaBancariaFuncionaNoMesmoCodigoCliente() {
        List<ContaBancaria> contas = List.of(new ContaCorrente(), new ContaInvestimento());
        for (ContaBancaria c : contas) {
            Assertions.assertDoesNotThrow(() -> c.depositar(50.0));
            Assertions.assertTrue(c.getSaldo() >= 50.0);
        }
    }

    @Test
    void contaCorrentePermiteSaque() {
        ContaSacavel cc = new ContaCorrente();
        cc.depositar(100.0);
        cc.sacar(30.0);
        Assertions.assertEquals(70.0, cc.getSaldo(), 0.001);
    }

    @Test
    void versaoComViolacaoQuebraQuandoSubstituida() {
        br.edu.unibras.solid.lsp.violacao.ContaBancaria conta =
                new br.edu.unibras.solid.lsp.violacao.ContaInvestimento();
        conta.depositar(100.0);
        // O cliente espera poder sacar de qualquer ContaBancaria, mas a subclasse quebra o contrato
        Assertions.assertThrows(UnsupportedOperationException.class, () -> conta.sacar(10.0));
    }
}
