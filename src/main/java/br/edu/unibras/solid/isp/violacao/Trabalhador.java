package br.edu.unibras.solid.isp.violacao;

/** VIOLAÇÃO do ISP: interface "gorda" que obriga todos os trabalhadores a implementarem comer(). */
public interface Trabalhador {
    void trabalhar();
    void comer();
}
