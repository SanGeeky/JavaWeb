/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColegioSOAP;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author sange
 */
public class Conexion {
    
        
    public Connection pruebaconexion() {
        System.out.println("Prueba de conexion");
        try {
            final String url = "jdbc:mysql://localhost:3306/colegio";
            final Connection Conexion = DriverManager.getConnection(url, "root", "");
            if (Conexion != null) {
                return Conexion;
            } else
                return null;
        } catch (final Exception ex) {
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }
}
