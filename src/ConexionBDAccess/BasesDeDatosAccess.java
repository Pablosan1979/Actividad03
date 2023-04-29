package ConexionBDAccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class BasesDeDatosAccess extends JFrame implements ActionListener{
    JTextArea txtSalida;
    
    
    JLabel  lAprendiz,
            //bloque  labels insercion datos
            lTituloInsertar,lNombreLibro, lEditorial, lAutor,lPrecio, 
            //bloque labels borrado datos
            lTituloBorrar, lEliminar,
            //bloque labels actualizacion datos
            lTituloActualizar, lIdActualizar, lActualizarNombreLibro, lActualizarEditorial, lActualizarAutor, lActualizarPrecio;
    JTextField 
            //bloque  txtfields insercion datos 
            tfNombreLibro, tfEditorial,tfAutor, tfPrecio,
            //bloque txtfields borrado datos
            tfIdEliminar, 
            //bloque txtfields actualizacion datos
            tfIdActualizar, tfActualizarNombreLibro, tfActualizarEditorial, tfActualizarAutor, tfActualizarPrecio ;
    //Bloque botones
    JButton btGuardar, btEliminar, btActualizar;
    JPanel panel;
    
    BasesDeDatosAccess(){
        
        panel = new JPanel();
        panel.setLayout(null);
        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtSalida);
        scroll.setBounds(10, 10, 860, 350);
        panel.add(scroll);  
        // datos paorendiz presenta evidencia
        lAprendiz = new JLabel("Pedro pablo Santacruz");
        lAprendiz.setBounds(730, 550, 200, 20);
        panel.add(lAprendiz);
        // area insercion datos.
        lTituloInsertar = new JLabel("Insertar datos");
        lTituloInsertar.setBounds(10, 380, 100, 20);
        panel.add(lTituloInsertar);
        lNombreLibro = new JLabel("Libro: ");
        lNombreLibro.setBounds(10, 410, 100, 20);
        panel.add(lNombreLibro);
        tfNombreLibro = new JTextField();
        tfNombreLibro.setBounds(110, 410, 100, 20);
        panel.add(tfNombreLibro);
        lEditorial = new JLabel("Editorial: ");
        lEditorial.setBounds(10, 440, 100, 20);
        panel.add(lEditorial);
        tfEditorial = new JTextField();
        tfEditorial.setBounds(110, 440, 100, 20);
        panel.add(tfEditorial);
        lAutor = new JLabel("Autor: ");
        lAutor.setBounds(10, 470, 100, 20);
        panel.add(lAutor);
        tfAutor = new JTextField();
        tfAutor.setBounds(110, 470, 100, 20);
        panel.add(tfAutor);
        
        lPrecio = new JLabel("Precio: ");
        lPrecio.setBounds(10, 500, 100, 20);
        panel.add(lPrecio);
        tfPrecio = new JTextField();
        tfPrecio.setBounds(110, 500, 100, 20);
        panel.add(tfPrecio);
        
        btGuardar = new JButton("Guardar");
        btGuardar.setBounds(70, 580, 100, 20);
        btGuardar.addActionListener(this);
        panel.add(btGuardar);
        
        // area eliminacion datos.
        lTituloBorrar = new JLabel("Borrar datos");
        lTituloBorrar.setBounds(310, 380, 100, 20);
        panel.add(lTituloBorrar);
        lEliminar = new JLabel("Id a eliminar: ");
        lEliminar.setBounds(310, 400, 100, 20);
        panel.add(lEliminar);
        tfIdEliminar = new JTextField();
        tfIdEliminar.setBounds(310, 420, 100, 20);
        panel.add(tfIdEliminar);
        btEliminar = new JButton("Eliminar");
        btEliminar.setBounds(310, 580, 100, 20);
        btEliminar.addActionListener(this);
        panel.add(btEliminar);
        
        //Area actualiozacion datos.
        lTituloActualizar = new JLabel("Actualizar datos");
        lTituloActualizar.setBounds(500, 380, 200, 20);
        panel.add(lTituloActualizar);
        lIdActualizar = new JLabel("ID a actualizar: ");
        lIdActualizar.setBounds(500, 410, 100, 20);
        panel.add(lIdActualizar);
        tfIdActualizar = new JTextField();
        tfIdActualizar.setBounds(610, 410, 100, 20);
        panel.add(tfIdActualizar);
        lActualizarNombreLibro = new JLabel("Nuevo Libro: ");
        lActualizarNombreLibro.setBounds(500, 440, 100, 20);
        panel.add(lActualizarNombreLibro);
        tfActualizarNombreLibro = new JTextField();
        tfActualizarNombreLibro.setBounds(610, 440, 100, 20);
        panel.add(tfActualizarNombreLibro);        
        lActualizarEditorial = new JLabel("Nueva Editorial: ");
        lActualizarEditorial.setBounds(500, 470, 100, 20);
        panel.add(lActualizarEditorial);
        tfActualizarEditorial = new JTextField();
        tfActualizarEditorial.setBounds(610, 470, 100, 20);
        panel.add(tfActualizarEditorial);  
        
        lActualizarAutor = new JLabel("Nuevo Autor: ");
        lActualizarAutor.setBounds(500, 500, 100, 20);
        panel.add(lActualizarAutor);
        tfActualizarAutor = new JTextField();
        tfActualizarAutor.setBounds(610, 500, 100, 20);
        panel.add(tfActualizarAutor);  
        
        lActualizarPrecio = new JLabel("Nuevo precio: ");
        lActualizarPrecio.setBounds(500, 530, 100, 20);
        panel.add(lActualizarPrecio);
        tfActualizarPrecio = new JTextField();
        tfActualizarPrecio.setBounds(610, 530, 100, 20);
        panel.add(tfActualizarPrecio);  
        
        btActualizar = new JButton("Actualizar");
        btActualizar.setBounds(570, 580, 100, 20);
        btActualizar.addActionListener(this);
        panel.add(btActualizar);
        
        add(panel);
        setSize(900, 650);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarDatos();
    }
    
    public void mostrarDatos(){
        txtSalida.setText("");
        Connection c = ConexionBDAccess.getConexion();
        Statement comando;
        try{
            comando = c.createStatement();
            ResultSet registro = comando.executeQuery("select id, NombreLibro, Editorial from libros ");
            txtSalida.setText("Salida de datos \n");
            while(registro.next()){
                int id;
                String NombreLibros, Editorial;
                id=registro.getInt("id");
                NombreLibros= registro.getString("NombreLibro");
                Editorial = registro.getString("Editorial");
                txtSalida.append((id+ " - " +NombreLibros+ " - " +Editorial+ "\n")); 
            }
        } catch (SQLException e){
        }
        ConexionBDAccess.desconectar();
    }
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==btGuardar){
            Connection c=ConexionBDAccess.getConexion();
            String sql = "INSERT INTO libros (NombreLibro, Editorial)" + "VALUES (?, ?)";
                try{
                    PreparedStatement st =c.prepareStatement(sql);
                    st.setString(1, tfNombreLibro.getText());
                    st.setString(2, tfEditorial.getText());
                    st.executeUpdate();
                    tfNombreLibro.setText("");
                    tfEditorial.setText("");
                    st.close();
                }catch(SQLException e1){
            }
                mostrarDatos();
                ConexionBDAccess.desconectar();
        }
        if(e.getSource()==btEliminar){
            Connection c=ConexionBDAccess.getConexion();
            String sql = "DELETE FROM libros WHERE id =?";
                try{
                    PreparedStatement st =c.prepareStatement(sql);
                    st.setString(1, tfIdEliminar.getText());                    
                    st.executeUpdate();
                    tfIdEliminar.setText("");
                    st.close();
                }catch(SQLException e1){
            }
                mostrarDatos();
                ConexionBDAccess.desconectar();
        }
        if(e.getSource()==btActualizar){
            Connection c=ConexionBDAccess.getConexion();
            String sql = "UPDATE libros SET NombreLibro = ?, Editorial = ? WHERE id = ?";
                try{
                try (PreparedStatement st = c.prepareStatement(sql)) {
                    st.setString(1, tfActualizarNombreLibro.getText());                    
                    st.setString(2, tfActualizarEditorial.getText());
                    st.setString(3, tfIdActualizar.getText());
                    st.executeUpdate();
                    tfActualizarNombreLibro.setText("");
                    tfActualizarEditorial.setText("");
                    tfIdActualizar.setText("");
                }
                }catch(SQLException e1){
            }
                mostrarDatos();
                ConexionBDAccess.desconectar();
        }
    }
    public static void main(String[] args) {
        BasesDeDatosAccess basesDeDatosAccess = new BasesDeDatosAccess();
    }
    
}

