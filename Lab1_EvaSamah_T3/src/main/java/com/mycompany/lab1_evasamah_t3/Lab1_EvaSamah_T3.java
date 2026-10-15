package com.mycompany.lab1_evasamah_t3;

import java.util.ArrayList;


/**
 *
 * @author EvaSamah
 */
public class Lab1_EvaSamah_T3 {
    
        public static void main(String[] args) {
       EscuadronDAO dao = new EscuadronImperialDAO();

        //inicia la tabla
        dao.inicializarTabla();

        //registra los escuadrones de prueba
        EscuadronDTO e1 = new EscuadronDTO("E-020", "Chafiqy", 
            "Sector Twinkle", 12);
        EscuadronDTO e2 = new EscuadronDTO("E-019", "Medinas",
            "Sector Moon", 8);

        dao.insertar(e1);
        dao.insertar(e2);

        //consulta todos los escuadrones
        System.out.println("Consultar escuadrones");

        ArrayList<EscuadronDTO> escuadrones = dao.obtenerTodos();

        for (EscuadronDTO e : escuadrones) {
            System.out.println(e);
        }
    }
}
