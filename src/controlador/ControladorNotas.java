package controlador;

import java.io.IOException;
import java.util.Locale;
import modelo.NotaEstudiante;
import modelo.RegistroNotas;
import vista.VentanaNotas;

public class ControladorNotas {
    private final RegistroNotas modelo;
    private final VentanaNotas vista;

    public ControladorNotas(RegistroNotas modelo, VentanaNotas vista) {
        this.modelo = modelo; this.vista = vista;
        for (NotaEstudiante n : modelo.getRegistros()) { mostrarFila(n); }
        vista.btnAgregar.addActionListener(e -> agregar());
        vista.btnLimpiar.addActionListener(e -> { limpiar(); vista.mensaje.setText("Formulario limpio."); });
    }

    private void mostrarFila(NotaEstudiante n) {
        vista.datos.addRow(new Object[]{n.getNombre(), n.getNota1(), n.getNota2(), n.getNota3(),
                String.format(Locale.ROOT, "%.2f", n.getPromedio()), n.getEstado()});
    }
    private void limpiar() {
        vista.txtNombre.setText(""); vista.txtNota1.setText("");
        vista.txtNota2.setText(""); vista.txtNota3.setText("");
        vista.txtNombre.requestFocusInWindow();
    }
    private void agregar() {
        try {
            NotaEstudiante n = modelo.agregar(vista.txtNombre.getText(), vista.txtNota1.getText(),
                    vista.txtNota2.getText(), vista.txtNota3.getText());
            mostrarFila(n); limpiar();
            vista.mensaje.setText("Guardado: " + n.getNombre() + " | Promedio: "
                    + String.format(Locale.ROOT, "%.2f", n.getPromedio()) + " | " + n.getEstado());
        } catch (IllegalArgumentException e) { vista.mensaje.setText(e.getMessage()); }
        catch (IOException e) { vista.mensaje.setText("No se pudo guardar en doc/notas.txt."); }
    }
}
