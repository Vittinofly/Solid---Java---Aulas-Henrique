package br.edu.unibras.solid.ocp.refatorado;

public class DescontoEstudante implements DescontoService {
    @Override
    public double calcular(double v) { return v * 0.90; }
}
