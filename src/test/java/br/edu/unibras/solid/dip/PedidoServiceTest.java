package br.edu.unibras.solid.dip;

import br.edu.unibras.solid.dip.refatorado.EmailNotificador;
import br.edu.unibras.solid.dip.refatorado.Notificador;
import br.edu.unibras.solid.dip.refatorado.PedidoService;
import br.edu.unibras.solid.dip.refatorado.SmsNotificador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PedidoServiceTest {

    @Test
    void testFechamentoPedido() {
        Notificador mockNotificador = msg -> Assertions.assertEquals("Pedido Confirmado!", msg);
        PedidoService service = new PedidoService(mockNotificador);
        service.fecharPedido();
    }

    @Test
    void deveEnviarExatamenteUmaNotificacao() {
        List<String> enviadas = new ArrayList<>();
        new PedidoService(enviadas::add).fecharPedido();
        Assertions.assertEquals(List.of("Pedido Confirmado!"), enviadas);
    }

    @Test
    void funcionaComQualquerImplementacaoConcreta() {
        Assertions.assertDoesNotThrow(() -> new PedidoService(new EmailNotificador()).fecharPedido());
        Assertions.assertDoesNotThrow(() -> new PedidoService(new SmsNotificador()).fecharPedido());
    }

    @Test
    void versaoComViolacaoSoConsegueUsarEmail() {
        new br.edu.unibras.solid.dip.violacao.PedidoService().fecharPedido();
        Assertions.assertTrue(br.edu.unibras.solid.dip.violacao.EmailNotificador.ENVIADOS
                .contains("Pedido Confirmado!"));
    }
}
