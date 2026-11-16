package br.edu.unibras.solid.dip.refatorado;

public class EmailNotificador implements Notificador {
    @Override
    public void enviar(String msg) { System.out.println("E-mail: " + msg); }
}
