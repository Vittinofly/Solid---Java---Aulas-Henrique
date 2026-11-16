package br.edu.unibras.solid.srp;

import br.edu.unibras.solid.srp.refatorado.CalculadoraSalario;
import br.edu.unibras.solid.srp.refatorado.Funcionario;
import br.edu.unibras.solid.srp.refatorado.FuncionarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraSalarioTest {

    @Test
    void deveCalcularSalarioCorretamente() {
        Funcionario f = new Funcionario("Ana", 5000.0);
        CalculadoraSalario calc = new CalculadoraSalario();
        Assertions.assertEquals(4000.0, calc.calcularLiquido(f));
    }

    @Test
    void repositorioDeveSalvarFuncionarioSemCalcularNada() {
        FuncionarioRepository repo = new FuncionarioRepository();
        repo.salvar(new Funcionario("Bruno", 3000.0));
        Assertions.assertEquals(1, repo.listarTodos().size());
        Assertions.assertEquals("Bruno", repo.listarTodos().get(0).getNome());
    }

    @Test
    void versaoComViolacaoMisturaCalculoEPersistencia() {
        var f = new br.edu.unibras.solid.srp.violacao.Funcionario("Ana", 5000.0);
        Assertions.assertEquals(4000.0, f.calcularLiquido());
        f.salvar(); // a mesma classe também acessa o "banco"
        Assertions.assertFalse(br.edu.unibras.solid.srp.violacao.Funcionario.BANCO_DE_DADOS.isEmpty());
    }
}
