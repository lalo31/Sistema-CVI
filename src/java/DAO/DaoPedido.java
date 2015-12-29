/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Inteface.InterfacePedido;
import Pojos.Pedido;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public class DaoPedido implements InterfacePedido {

    @Override
    public boolean registrar(Session session, Pedido pedido) throws Exception {
        session.save(pedido);
        
        return true;
    }

    @Override
    public List<Pedido> getAllPedido(Session session) throws Exception {
        String hql = "from Pedido";
        Query query = session.createQuery(hql);
        
        List<Pedido> listaPedido = (List<Pedido>) query.list();
        return listaPedido;
    }
    
}
