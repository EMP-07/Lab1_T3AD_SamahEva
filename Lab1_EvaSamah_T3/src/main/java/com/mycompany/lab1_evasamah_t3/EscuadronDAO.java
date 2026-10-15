package com.mycompany.lab1_evasamah_t3;

import java.util.ArrayList;

/**
 *
 * @author EvaSamah
 */
public interface EscuadronDAO {
    
    void inicializarTabla();
    
    boolean insertar(EscuadronDTO escuadron);
    
    ArrayList<EscuadronDTO> obtenerTodos();
    
}
