/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package losayala.frames.registros;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import losayala.interfaces.CustomInternalFrame;
import losayala.objetos.ControladorJugadores;
import losayala.objetos.Jugador;

/**
 *
 * @author RENT A CENTER
 */
public class RegistroJugador extends CustomInternalFrame {
    private static final ControladorJugadores CONTROLADOR_JUGADORES = new ControladorJugadores();
    private Jugador currentJugador = new Jugador();
    private int jugadorIdToEdit = -1;
    private boolean isEditMode = false;

    /**
     * Creates new form RegistroJugador
     */
    public RegistroJugador() {
        initComponents();
        customInitComponentes();
    }
    
    private void customInitComponentes() {
        DefaultComboBoxModel<String> posiciones = new DefaultComboBoxModel<>(Jugador.POSICIONES);
        posicionBox.setModel(posiciones);
        editBtn.setEnabled(false);
        updateTableModel();
        
        jugadoresTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jugadoresTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                tableRowSelected();
            }
        });
    }
    
    private void tableRowSelected() {
        if (jugadoresTable.getSelectedRow() == -1 ) return;
        jugadorIdToEdit = Integer.parseInt((String) jugadoresTable.getValueAt(jugadoresTable.getSelectedRow(), 0));
        editBtn.setEnabled(true);
        eliminarBtn.setEnabled(true);
    }
    
    public void updateTableModel() {
        DefaultTableModel dataModel = new DefaultTableModel(
                CONTROLADOR_JUGADORES.toBidimensionalStringArray(),
                ControladorJugadores.getColumnNames()
        );
        jugadoresTable.setModel(dataModel);
    }
    
    private void syncJugadorData() {
        // Instancia
        String nombre = nombreTxt.getText();
        String aMaterno = aMaternoTxt.getText();
        String aPaterno = aPaternoTxt.getText();
        String telefono = telefonoTxt.getText();
        String posicion = (String)posicionBox.getSelectedItem();
        int playera = (int)playeraSpinner.getValue();
        // Asigna
        currentJugador.persona.setNombres(nombre);
        currentJugador.persona.setApellidoMaterno(aMaterno);
        currentJugador.persona.setApellidoPaterno(aPaterno);
        currentJugador.persona.setTelefono(telefono);
        currentJugador.setPosicion(posicion);
        currentJugador.setNumeroPlayera(playera);
    }
    
    private void saveJugadorData() {
        syncJugadorData();
        // Guarda
        boolean seGuardoJugador = currentJugador.save();
        
        if (seGuardoJugador) {
            JOptionPane.showMessageDialog(this, "Se guardo con exito el jugador");
        } else {
            Object[] options = { "OK" };
            int res = JOptionPane.showOptionDialog(null, "No se pudo guardar el jugador", "Atención",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
        }
        
        clearForm();
    }
    
    private void updateForm() {
        nombreTxt.setText(currentJugador.persona.getNombres());
        aPaternoTxt.setText(currentJugador.persona.getApellidoPaterno());
        aMaternoTxt.setText(currentJugador.persona.getApellidoMaterno());
        telefonoTxt.setText(currentJugador.persona.getTelefono());
        playeraSpinner.setValue(currentJugador.getNumeroPlayera());
        posicionBox.setSelectedItem(currentJugador.getPosicion());
        updateImage();
    }
    
    private void clearForm() {
        currentJugador = new Jugador();
        nombreTxt.setText(null);
        aPaternoTxt.setText(null);
        aMaternoTxt.setText(null);
        telefonoTxt.setText(null);
        playeraSpinner.setValue(1);
        posicionBox.setSelectedItem(Jugador.NO_POSITION);
        eliminarBtn.setEnabled(false);
        updateImage(0);
        CONTROLADOR_JUGADORES.initJugadores();
        updateTableModel();
    }
    
    private void updateImage() {
        BufferedImage img = currentJugador.getFotografia();
        if (img == null) {
            fotoLabel.setIcon(null);
            return;
        }
        Image resizedImg = currentJugador.getFotografia().getScaledInstance(
                    fotoLabel.getWidth(),
                    fotoLabel.getHeight(),
                    Image.SCALE_SMOOTH);
        fotoLabel.setIcon(new ImageIcon(resizedImg));
    }
    
    private void updateImage(int nullNo) {
        fotoLabel.setIcon(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexoGroup = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        aPaternoTxt = new javax.swing.JTextField();
        aMaternoTxt = new javax.swing.JTextField();
        telefonoTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        posicionBox = new javax.swing.JComboBox<>();
        fotoLabel = new javax.swing.JLabel();
        btnSeleccionarFoto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        buscarTxt = new javax.swing.JTextField();
        btnBuscarJ = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jugadoresTable = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        playeraSpinner = new javax.swing.JSpinner();

        setIconifiable(true);
        setTitle("Registro Jugador");
        setToolTipText("");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                deiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Jugador");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Nombre:");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Telefono:");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Apellido Paterno:");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setText("Apellido Materno:");

        aMaternoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aMaternoTxtActionPerformed(evt);
            }
        });

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setText("Numero de Playera:");

        posicionBox.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        posicionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Portero ", "Defensa", "Medio", "Delantero" }));

        fotoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));

        btnSeleccionarFoto.setText("Seleccionar Foto");
        btnSeleccionarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarFotoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setText("Buscar Jugadores por Nombre");

        btnBuscarJ.setText("Buscar");
        btnBuscarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarJActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        jugadoresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Playera", "Posición"
            }
        ));
        jScrollPane2.setViewportView(jugadoresTable);

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Posición:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(posicionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(btnBuscarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eliminarBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(playeraSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(86, 86, 86))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addGap(34, 34, 34)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(5, 5, 5)
                                                    .addComponent(jLabel9)
                                                    .addGap(33, 33, 33)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(92, 92, 92)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(nombreTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                                .addGap(5, 5, 5))
                                            .addComponent(aPaternoTxt)
                                            .addComponent(aMaternoTxt)
                                            .addComponent(telefonoTxt))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fotoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSeleccionarFoto, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(aPaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(aMaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(playeraSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(posicionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarFoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posicionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarJ)
                    .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn)
                    .addComponent(guardarBtn)
                    .addComponent(eliminarBtn))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        SpinnerNumberModel playerasDisponibles = new SpinnerNumberModel();
        playerasDisponibles.setMinimum(1);
        playerasDisponibles.setMaximum(99);
        playerasDisponibles.setStepSize(1);
        playeraSpinner.setModel(playerasDisponibles);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        // GUARDAR O ACTUALIZAR USUARIO
        saveJugadorData();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void deiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_deiconified
        // TODO add your handling code here:
    }//GEN-LAST:event_deiconified

    private void btnSeleccionarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarFotoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Solo imagenes", "png", "jpg", "jpeg", "gif"));
        int response = fileChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION) {
            currentJugador.setFotografia(fileChooser.getSelectedFile());
            updateImage();
        }
    }//GEN-LAST:event_btnSeleccionarFotoActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (jugadorIdToEdit == -1) return;
        
        if (!isEditMode) {
            isEditMode = true;
            editBtn.setText("Actualizar");
            currentJugador = new Jugador(jugadorIdToEdit);
            guardarBtn.setEnabled(false);
            updateForm();
        } else {
            isEditMode = false;
            guardarBtn.setEnabled(true);
            editBtn.setText("Editar");
            int res = JOptionPane.showConfirmDialog(this, "¿Seguro que desea editar?");
            if (res == JOptionPane.OK_OPTION) {
                syncJugadorData();
                boolean jugadorEditado = currentJugador.edit();
                System.out.println("JUGADOR EDITADO: "+jugadorEditado);
                if (currentJugador.edit()) {
                    JOptionPane.showMessageDialog(this, "Se edito el jugador con exito.");
                }
                clearForm();
            }
            
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        Object[] options = { "SÍ", "CANCELAR" };
        int res = JOptionPane.showOptionDialog(this, "¿Seguro que desea eliminar este jugador?", "Atención",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
        if (res == 0) {
            currentJugador = new Jugador(jugadorIdToEdit);
            if (currentJugador.delete()) {
                JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente.");
                currentJugador = new Jugador();
                clearForm();
            }
        }
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void btnBuscarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarJActionPerformed
        String searchValue = buscarTxt.getText();
        System.out.println("BUSCANDO: "+searchValue);
        if (searchValue.equals("") || searchValue.equals(" ")) {
            CONTROLADOR_JUGADORES.initJugadores();
        } else {
            CONTROLADOR_JUGADORES.search(searchValue);
        }
        updateTableModel();
    }//GEN-LAST:event_btnBuscarJActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aMaternoTxt;
    private javax.swing.JTextField aPaternoTxt;
    private javax.swing.JButton btnBuscarJ;
    private javax.swing.JButton btnSeleccionarFoto;
    private javax.swing.JTextField buscarTxt;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel fotoLabel;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jugadoresTable;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JSpinner playeraSpinner;
    public javax.swing.JComboBox<String> posicionBox;
    private javax.swing.ButtonGroup sexoGroup;
    private javax.swing.JTextField telefonoTxt;
    // End of variables declaration//GEN-END:variables
}