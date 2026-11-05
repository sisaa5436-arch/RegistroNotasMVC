package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaNotas extends JFrame {
    private static final long serialVersionUID = 1L;
    public final JTextField txtNombre = new JTextField(18);
    public final JTextField txtNota1 = new JTextField(5);
    public final JTextField txtNota2 = new JTextField(5);
    public final JTextField txtNota3 = new JTextField(5);
    public final JButton btnAgregar = new JButton("Agregar");
    public final JButton btnLimpiar = new JButton("Limpiar");
    public final JLabel mensaje = new JLabel("Notas de 0 a 10. Aprobación del ejercicio: promedio >= 7.");
    public final DefaultTableModel datos = new DefaultTableModel(
            new Object[]{"Estudiante", "Nota 1", "Nota 2", "Nota 3", "Promedio", "Estado"}, 0) {
        private static final long serialVersionUID = 1L;
        @Override public boolean isCellEditable(int fila, int columna) { return false; }
    };
    public final JTable tabla = new JTable(datos);

    public VentanaNotas() {
        super("Registro de notas");
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));
        JLabel titulo = new JLabel("REGISTRO DE NOTAS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        JPanel arriba = new JPanel(new BorderLayout(8, 8));
        arriba.add(titulo, BorderLayout.NORTH);
        JPanel campos = new JPanel(new GridLayout(2, 4, 8, 5));
        campos.add(new JLabel("Estudiante")); campos.add(new JLabel("Nota 1"));
        campos.add(new JLabel("Nota 2")); campos.add(new JLabel("Nota 3"));
        campos.add(txtNombre); campos.add(txtNota1); campos.add(txtNota2); campos.add(txtNota3);
        arriba.add(campos, BorderLayout.CENTER);
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(btnAgregar); botones.add(btnLimpiar);
        arriba.add(botones, BorderLayout.SOUTH); panel.add(arriba, BorderLayout.NORTH);
        tabla.setRowHeight(24); tabla.getColumnModel().getColumn(0).setPreferredWidth(180);
        tabla.getTableHeader().setReorderingAllowed(false);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(mensaje, BorderLayout.SOUTH);
        setContentPane(panel); setSize(780, 480); setResizable(false);
        setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(btnAgregar);
    }
}
