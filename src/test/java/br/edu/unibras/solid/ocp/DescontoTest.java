package br.edu.unibras.solid.ocp;

import br.edu.unibras.solid.ocp.refatorado.CalculadoraPreco;
import br.edu.unibras.solid.ocp.refatorado.DescontoEstudante;
import br.edu.unibras.solid.ocp.refatorado.DescontoService;
import br.edu.unibras.solid.ocp.refatorado.DescontoVip;
import br.edu.unibras.solid.ocp.refatorado.SemDesconto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DescontoTest {

    @Test
    void testDescontoVip() {
        DescontoService d = new DescontoVip();
        Assertions.assertEquals(85.0, d.calcular(100.0), 0.001);
    }

    @Test
    void testDescontoEstudante() {
        Assertions.assertEquals(90.0, new DescontoEstudante().calcular(100.0), 0.001);
    }

    @Test
    void testSemDesconto() {
        Assertions.assertEquals(100.0, new SemDesconto().calcular(100.0), 0.001);
    }

    @Test
    void deveAceitarNovoDescontoSemAlterarCalculadora() {
        // Extensão: um desconto novo criado sem tocar em CalculadoraPreco
        DescontoService descontoFuncionario = v -> v * 0.70;
        Assertions.assertEquals(70.0, new CalculadoraPreco().precoFinal(100.0, descontoFuncionario), 0.001);
    }

    @Test
    void versaoComViolacaoDependeDeIfs() {
        var calc = new br.edu.unibras.solid.ocp.violacao.CalculadoraDesconto();
        Assertions.assertEquals(85.0, calc.calcular("VIP", 100.0), 0.001);
        // "FUNCIONARIO" não é reconhecido: exigiria modificar a classe
        Assertions.assertEquals(100.0, calc.calcular("FUNCIONARIO", 100.0), 0.001);
    }
}
