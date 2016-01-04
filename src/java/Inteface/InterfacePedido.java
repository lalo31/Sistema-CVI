/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Pedido;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public interface InterfacePedido {
    public boolean registrar(Session session, Pedido pedido) throws Exception;
    public List<Pedido> getAllPedido(Session session) throws Exception;
    public boolean deletePedido(Session session, Pedido pedido) throws Exception;
}
