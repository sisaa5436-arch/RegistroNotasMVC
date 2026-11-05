package modelo;

import java.util.Locale;

public class NotaEstudiante {
    private final String nombre;
    private final double nota1, nota2, nota3;

    public NotaEstudiante(String nombre, double nota1, double nota2, double nota3) {
        this.nombre = nombre; this.nota1 = nota1; this.nota2 = nota2; this.nota3 = nota3;
    }
    public String getNombre() { return nombre; }
    public double getNota1() { return nota1; }
    public double getNota2() { return nota2; }
    public double getNota3() { return nota3; }
    public double getPromedio() { return (nota1 + nota2 + nota3) / 3.0; }
    public String getEstado() { return getPromedio() >= 7.0 ? "APROBADO" : "REPROBADO"; }
    public String toLinea() {
        return String.format(Locale.ROOT, "%s;%.2f;%.2f;%.2f", nombre, nota1, nota2, nota3);
    }
}
