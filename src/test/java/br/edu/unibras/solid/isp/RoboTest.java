package br.edu.unibras.solid.isp;

import br.edu.unibras.solid.isp.refatorado.Alimentavel;
import br.edu.unibras.solid.isp.refatorado.Humano;
import br.edu.unibras.solid.isp.refatorado.Robo;
import br.edu.unibras.solid.isp.refatorado.Trabalhador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoboTest {

    @Test
    void testRoboTrabalha() {
        Trabalhador r = new Robo();
        Assertions.assertDoesNotThrow(r::trabalhar);
    }

    @Test
    void roboNaoDependeDeAlimentavel() {
        Assertions.assertFalse(Alimentavel.class.isAssignableFrom(Robo.class));
    }

    @Test
    void humanoTrabalhaEComeSemErros() {
        Humano h = new Humano();
        Assertions.assertDoesNotThrow(h::trabalhar);
        Assertions.assertDoesNotThrow(h::comer);
    }

    @Test
    void versaoComViolacaoObrigaRoboAImplementarComer() {
        br.edu.unibras.solid.isp.violacao.Trabalhador r = new br.edu.unibras.solid.isp.violacao.Robo();
        Assertions.assertThrows(UnsupportedOperationException.class, r::comer);
    }
}
