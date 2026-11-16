package br.edu.unibras.solid.srp.refatorado;

/** Responsabilidade única: representar os dados de um funcionário. */
public class Funcionario {

    private final String nome;
    private final double salarioBruto;

    public Funcionario(String nome, double salarioBruto) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
    }

    public String getNome() { return nome; }
    public double getSalarioBruto() { return salarioBruto; }
}
