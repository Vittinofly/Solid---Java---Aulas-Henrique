package br.edu.unibras.solid.srp.violacao;

import java.util.ArrayList;
import java.util.List;

/**
 * VIOLAÇÃO do SRP: esta classe tem TRÊS motivos para mudar.
 * <ul>
 *   <li>dados do funcionário (nome, salário);</li>
 *   <li>regra de negócio (cálculo do salário líquido);</li>
 *   <li>persistência (salvar no "banco de dados").</li>
 * </ul>
 * Se a alíquota mudar ou o banco trocar de MySQL para PostgreSQL, a mesma classe é alterada.
 */
public class Funcionario {

    /** Simula uma tabela de banco de dados. */
    public static final List<String> BANCO_DE_DADOS = new ArrayList<>();

    private final String nome;
    private final double salarioBruto;

    public Funcionario(String nome, double salarioBruto) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
    }

    public String getNome() { return nome; }
    public double getSalarioBruto() { return salarioBruto; }

    // Responsabilidade 2: regra de negócio
    public double calcularLiquido() {
        return salarioBruto * 0.8;
    }

    // Responsabilidade 3: persistência
    public void salvar() {
        BANCO_DE_DADOS.add("INSERT INTO funcionario VALUES ('" + nome + "', " + salarioBruto + ")");
    }
}
