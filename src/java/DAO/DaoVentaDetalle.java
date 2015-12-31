/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Inteface.InterfaceVentaDetalle;
import Pojos.Ventadetalle;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public class DaoVentaDetalle implements InterfaceVentaDetalle {

    @Override
    public boolean registrar(Session session, Ventadetalle detalle) throws Exception {
        session.save(detalle);
        
        return true;
    }
    
 
    
}
