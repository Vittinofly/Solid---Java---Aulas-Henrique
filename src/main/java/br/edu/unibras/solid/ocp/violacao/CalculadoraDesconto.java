package br.edu.unibras.solid.ocp.violacao;

/**
 * VIOLAÇÃO do OCP: para criar um novo tipo de desconto é preciso MODIFICAR esta classe,
 * acrescentando mais um "if". Cada alteração pode quebrar os descontos que já funcionavam.
 */
public class CalculadoraDesconto {

    public double calcular(String tipoCliente, double valor) {
        if ("VIP".equals(tipoCliente)) {
            return valor * 0.85;
        } else if ("ESTUDANTE".equals(tipoCliente)) {
            return valor * 0.90;
        }
        // Novo tipo "FUNCIONARIO"? É preciso editar este método de novo...
        return valor;
    }
}
