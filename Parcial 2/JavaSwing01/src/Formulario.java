import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formulario extends JFrame {
    private JPanel FormularioPanel;
    private JLabel nombreLBL;
    private JLabel apellidoLBL;
    private JTextField NombreTXT;
    private JTextField ApellidoTXT;
    private JTextField EdadTXT;
    private JRadioButton MasculinoBTN;
    private JRadioButton FemeninoBTN;
    private JButton VerificarEdadBTN;
    private JButton LimpiarBTN;
    private JLabel DatosLBL;
    private ButtonGroup grupoGenero;
    public Formulario() {

        setTitle("Formulario de Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setContentPane(FormularioPanel);
        setVisible(true);

        grupoGenero = new ButtonGroup();
        grupoGenero.add(MasculinoBTN);
        grupoGenero.add(FemeninoBTN);

        VerificarEdadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = NombreTXT.getText();
                String apellido = ApellidoTXT.getText();
                String edadStr = EdadTXT.getText();
                String genero = MasculinoBTN.isSelected() ? "Masculino" : "Femenino";

                if (edadStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su edad.");
                    return;
                }

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }

                if (!MasculinoBTN.isSelected() && !FemeninoBTN.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione su género.");
                    return;
                }

                DatosLBL.setText("Usted es " + nombre +" "+ apellido + " con " + edadStr + " años y género " + genero);
                DatosLBL.setVisible(true);

                try {
                    int edad = Integer.parseInt(edadStr);
                    if (0 <= edad && edad < 18) {
                        JOptionPane.showMessageDialog(null, "Usted es menor de edad.");
                    } else if(edad >= 18 && edad <= 120) {
                        JOptionPane.showMessageDialog(null, "Usted es mayor de edad.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Edad no válida. Por favor, ingrese una edad entre 0 y 120.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una edad válida.");
                }

            }
        });
        LimpiarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NombreTXT.setText("");
                ApellidoTXT.setText("");
                EdadTXT.setText("");
                DatosLBL.setText("");
                grupoGenero.clearSelection();
            }
        });
    }
}
