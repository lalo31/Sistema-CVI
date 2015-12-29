/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Ventadetalle;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public interface InterfaceVentaDetalle {
    public boolean registrar(Session session, Ventadetalle detalle) throws Exception;
}
