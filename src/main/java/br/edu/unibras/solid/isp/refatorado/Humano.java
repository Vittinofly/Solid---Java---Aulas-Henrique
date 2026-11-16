package br.edu.unibras.solid.isp.refatorado;

/** Combina as interfaces pequenas de que realmente precisa. */
public class Humano implements Trabalhador, Alimentavel {
    @Override
    public void trabalhar() { System.out.println("Humano trabalhando..."); }

    @Override
    public void comer() { System.out.println("Humano almoçando..."); }
}
