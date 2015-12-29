/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package DAO;

import Inteface.InterfaceCatProveedores;
import Pojos.Catproveedor;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author eduardo
 */
public class DaoCatProveedores implements InterfaceCatProveedores {

    @Override
    public boolean registrar(Session session, Catproveedor proveedores) throws Exception {
        session.save(proveedores);
        
        return true;
    }

    @Override
    public List<Catproveedor> getAllProveedores(Session session) throws Exception {
        String hql = "from Catproveedor";
        Query query = session.createQuery(hql);
        
        List<Catproveedor> listaProveedores = (List<Catproveedor>) query.list();
        return listaProveedores;
    }

    @Override
    public Catproveedor getByNombreProveedor(Session session, String nombreProveedor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Session session, Catproveedor proveedores) throws Exception {
        session.update(proveedores);
        
        return true;
    }

    @Override
    public Catproveedor getByRfcProveedor(Session session, String rfcProveedores) throws Exception {
        String hql = "from Catproveedor where rfcProveedores=:rfcProveedores";
        Query query = session.createQuery(hql);
        query.setParameter("rfcProveedores", rfcProveedores);
        Catproveedor proveedores = (Catproveedor) query.uniqueResult();
        return proveedores;
    }

    @Override
    public boolean deleteProveedor(Session session, Catproveedor proveedores) throws Exception {
        session.delete(proveedores);
        
        return true;
    }
    
}
