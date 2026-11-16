package br.edu.unibras.solid.dip.refatorado;

public class SmsNotificador implements Notificador {
    @Override
    public void enviar(String msg) { System.out.println("SMS: " + msg); }
}
