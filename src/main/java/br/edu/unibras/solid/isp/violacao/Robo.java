package br.edu.unibras.solid.isp.violacao;

/** O robô é forçado a depender de um método que não faz sentido para ele. */
public class Robo implements Trabalhador {

    @Override
    public void trabalhar() { System.out.println("Robô operando..."); }

    @Override
    public void comer() {
        throw new UnsupportedOperationException("Robô não come");
    }
}
