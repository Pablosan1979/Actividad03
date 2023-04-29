package ConexionBDAccess;

import java.sql.*;

public class consultaTablaAccess {
    public static void main(String[] args) {
        Connection  c = ConexionBDAccess.getConexion();
        Statement comando;
        try{
            comando = c.createStatement();
            ResultSet registro = comando.executeQuery("select id, NombreLibro, Editorial from libros");
            while (registro.next()){
                int id;
                String nombres, telefono;
                id = registro.getInt("id");
                nombres = registro.getString("nombres");
                telefono = registro.getString("telefono");
                System.out.println(id + "|" +nombres+ "|" +telefono);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        ConexionBDAccess.desconectar();
    }
}
