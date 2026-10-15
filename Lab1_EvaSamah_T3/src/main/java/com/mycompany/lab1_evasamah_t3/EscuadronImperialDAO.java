package com.mycompany.lab1_evasamah_t3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author EvaSamah
 */
public class EscuadronImperialDAO implements EscuadronDAO{
    
    private static final String URL = "jdbc:postgresql://localhost:5432/HoloNet";
    private static final String USER = "openpg";
    private static final String PASS = "openpgpwd";

    @Override
    public void inicializarTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS escuadrones_imperiales ("
                   + "idEscuadron VARCHAR(10) PRIMARY KEY, "
                   + "nombre VARCHAR(100) NOT NULL, "
                   + "sectorBase VARCHAR(50) NOT NULL, "
                   + "numeroCazas INT NOT NULL"
                   + ");";
        
          try (Connection con =
                     DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabla de escuadrones ");

        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
        
    }

    @Override
    public boolean insertar(EscuadronDTO escuadron) {
          String sql = "INSERT INTO escuadrones_imperiales " + "(idEscuadron, nombre, sectorBase, numeroCazas) " 
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, escuadron.getIdEscuadron());
            pstmt.setString(2, escuadron.getNombre());
            pstmt.setString(3, escuadron.getSectorBase());
            pstmt.setInt(4, escuadron.getNumeroCazas());

            int filas = pstmt.executeUpdate();

            System.out.println("Filas insertadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar el escuadrón: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EscuadronDTO> obtenerTodos() {
        ArrayList<EscuadronDTO> lista = new ArrayList<>(); 

        String sql = "SELECT idEscuadron, nombre, sectorBase, numeroCazas "
            + "FROM escuadrones_imperiales ORDER BY codigo";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                String codigo = rs.getString("idEscuadron");
                String nombre = rs.getString("nombre");
                String sector = rs.getString("sectorBase");
                int numeroNaves = rs.getInt("numeroCazas");

                EscuadronDTO escuadron = new EscuadronDTO(codigo, nombre, sector, numeroNaves);
                lista.add(escuadron);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar escuadrones: " + e.getMessage());
        }
        return lista;
    }    
    
    
}
