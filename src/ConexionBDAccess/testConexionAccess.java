package ConexionBDAccess;

import java.sql.*;

public class testConexionAccess {

    public static void main(String[] args) {
        Connection c = ConexionBDAccess.getConexion();
        if (c != null) {
            System.out.println("Si se conecto");
        } else {
            System.out.println("No se conecto");
        }
        ConexionBDAccess.desconectar();
    }
}
