/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import HibernateUtil.HibernateUtil;
import Inteface.InterfaceCatProductos;
import Pojos.Categoria;
import Pojos.Catproducto;
import Pojos.Catproveedor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edgaaar65
 */
public class DaoCatProductos implements InterfaceCatProductos {

    @Override
    public boolean registrar(Session session, Catproducto productos) throws Exception{
        session.save(productos);
        
        return true;
    }

    @Override
    public List<Catproducto> getAllProductos(Session session) throws Exception {
        String hql = "from Catproducto";
        Query query = session.createQuery(hql);
        
        List<Catproducto> listaProductos = (List<Catproducto>) query.list();
        return listaProductos;
    }

    @Override
    public Catproducto getByIdProducto(Session session, Integer idProducto) throws Exception {
        return (Catproducto) session.load(Catproducto.class, idProducto);
    }

    @Override
    public boolean update(Session session, Catproducto productos) throws Exception {
        session.update(productos);
        
        return true;
    }

    @Override
    public Catproducto getByCodigoProducto(Session session, Integer numeroCodigo) throws Exception {
        String hql = "from Catproducto where numeroCodigo=:numeroCodigo";
        Query query = session.createQuery(hql);
        query.setParameter("numeroCodigo", numeroCodigo);
        Catproducto productos = (Catproducto) query.uniqueResult();
        return productos;
    }

    @Override
    public boolean deleteProducto(Session session, Catproducto productos) throws Exception {
        session.delete(productos);
        
        return true;
    }
    
     public List<Catproducto> getAllProductosByStockMinimo(Session session) throws Exception {
        String hql = "from Catproducto where cantidad<=cantidadMin";
        Query query = session.createQuery(hql);
        
        List<Catproducto> listaProductos = (List<Catproducto>) query.list();
        return listaProductos;
    }
    
}
