/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Inteface.InterfaceVenta;
import Pojos.Venta;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public class DaoVenta implements InterfaceVenta {

    @Override
    public boolean registrar(Session session, Venta venta) throws Exception {
        session.save(venta);
        return true;
    }

    @Override
    public Venta getUltimoRegistro(Session session) throws Exception {
        String hql = "from Venta order by numeroVenta desc";
        Query query = session.createQuery(hql).setMaxResults(1);
        
        return (Venta) query.uniqueResult();
    }
    
}
