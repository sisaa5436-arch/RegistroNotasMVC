package app;

import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import modelo.RegistroNotas;
import vista.VentanaNotas;
import controlador.ControladorNotas;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaNotas vista = new VentanaNotas();
            try {
                RegistroNotas modelo = new RegistroNotas(new File("doc/notas.txt"));
                new ControladorNotas(modelo, vista);
            } catch (IOException e) {
                vista.btnAgregar.setEnabled(false);
                vista.mensaje.setText("No se pudo cargar doc/notas.txt. Revise que exista y sea válido.");
            }
            vista.setVisible(true);
        });
    }
}
