package br.edu.unibras.solid.ocp.refatorado;

/**
 * Não conhece nenhum desconto concreto. Novos descontos são novas classes que implementam
 * {@link DescontoService}; esta classe nunca precisa ser alterada.
 */
public class CalculadoraPreco {

    public double precoFinal(double valor, DescontoService desconto) {
        return desconto.calcular(valor);
    }
}
