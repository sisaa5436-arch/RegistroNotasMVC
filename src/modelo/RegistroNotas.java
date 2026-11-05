package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroNotas {
    private final File archivo;
    private final List<NotaEstudiante> registros = new ArrayList<>();

    public RegistroNotas(File archivo) throws IOException {
        this.archivo = archivo;
        int numero = 0;
        for (String linea : Files.readAllLines(archivo.toPath(), StandardCharsets.UTF_8)) {
            numero++;
            if (linea.trim().isEmpty()) { continue; }
            String[] p = linea.split(";", -1);
            try {
                if (p.length != 4) { throw new IllegalArgumentException(); }
                NotaEstudiante nota = validar(p[0], p[1], p[2], p[3]);
                registros.add(nota);
            } catch (IllegalArgumentException e) {
                throw new IOException("Registro inválido o repetido en la línea " + numero + ".", e);
            }
        }
    }

    public List<NotaEstudiante> getRegistros() { return Collections.unmodifiableList(registros); }

    private NotaEstudiante validar(String entrada, String n1, String n2, String n3) {
        String nombre = entrada.trim();
        if (nombre.isEmpty() || nombre.contains(";") || nombre.contains("\n") || nombre.contains("\r")) {
            throw new IllegalArgumentException("Ingrese un nombre sin punto y coma ni saltos de línea.");
        }
        for (NotaEstudiante n : registros) {
            if (n.getNombre().equalsIgnoreCase(nombre)) {
                throw new IllegalArgumentException("El estudiante ya tiene un registro.");
            }
        }
        return new NotaEstudiante(nombre, leerNota(n1), leerNota(n2), leerNota(n3));
    }

    private double leerNota(String entrada) {
        String texto = entrada.trim().replace(',', '.');
        if (!texto.matches("[0-9]+(\\.[0-9]{1,2})?")) {
            throw new IllegalArgumentException("Ingrese notas de 0 a 10, con máximo dos decimales.");
        }
        double nota = Double.parseDouble(texto);
        if (!Double.isFinite(nota) || nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Cada nota debe estar entre 0 y 10.");
        }
        return nota;
    }

    public NotaEstudiante agregar(String nombre, String n1, String n2, String n3) throws IOException {
        NotaEstudiante nueva = validar(nombre, n1, n2, n3);
        List<String> lineas = new ArrayList<>();
        for (NotaEstudiante registro : registros) { lineas.add(registro.toLinea()); }
        lineas.add(nueva.toLinea());
        Files.write(archivo.toPath(), lineas, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        registros.add(nueva);
        return nueva;
    }
}
