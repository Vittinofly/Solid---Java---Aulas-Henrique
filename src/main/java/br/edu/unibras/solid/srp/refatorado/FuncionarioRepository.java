package br.edu.unibras.solid.srp.refatorado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Responsabilidade única: persistência de funcionários (aqui, em memória). */
public class FuncionarioRepository {

    private final List<Funcionario> registros = new ArrayList<>();

    public void salvar(Funcionario f) {
        registros.add(f);
    }

    public List<Funcionario> listarTodos() {
        return Collections.unmodifiableList(registros);
    }
}
