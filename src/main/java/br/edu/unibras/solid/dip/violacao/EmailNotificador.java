package br.edu.unibras.solid.dip.violacao;

import java.util.ArrayList;
import java.util.List;

/** Detalhe concreto de baixo nível. */
public class EmailNotificador {

    public static final List<String> ENVIADOS = new ArrayList<>();

    public void enviarEmail(String msg) {
        ENVIADOS.add(msg);
        System.out.println("E-mail: " + msg);
    }
}
