package br.edu.unibras.solid.srp.refatorado;

/** Responsabilidade única: regra de negócio do cálculo salarial (desconto de 20%). */
public class CalculadoraSalario {

    public double calcularLiquido(Funcionario f) {
        return f.getSalarioBruto() * 0.8;
    }
}
