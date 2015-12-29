/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Venta;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public interface InterfaceVenta {
    public boolean registrar(Session session, Venta venta) throws Exception;
    public Venta getUltimoRegistro(Session session) throws Exception;
}
