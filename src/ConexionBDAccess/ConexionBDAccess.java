package ConexionBDAccess;

import java.sql.*;

public class ConexionBDAccess {

    static String ubicacion = "D:\\Pablo\\OneDrive\\Documentos\\Cursos\\08. Desarrollo de Applets_Aplicaciones con Uso de Base de Datos Redes Servlets y Multimedia\\03. Actividad 03\\NetBeans8.2\\BibliotecaBDAccess\\Biblioteca.accdb";
    static String driver = "jdbc:ucanaccess://" + ubicacion;
    static Connection conexion = null;

    ConexionBDAccess() {
    }

    public static Connection getConexion() {
        try {
            conexion = DriverManager.getConnection(driver);
            System.out.println("Conexion exitosa a la base de datos");
        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base de datos");
        }
        return conexion;
    }

    public static void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
            }
            System.out.println("Conexion cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
