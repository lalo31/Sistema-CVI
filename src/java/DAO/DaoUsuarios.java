/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Inteface.InterfaceUsuarios;
import Pojos.Tipousuario;
import Pojos.Usuarios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public class DaoUsuarios implements InterfaceUsuarios {

    @Override
    public boolean registrar(Session session, Usuarios usuarios) throws Exception {
        session.save(usuarios);
        
        return true;
    }

    @Override
    public List<Usuarios> getAllUsuarios(Session session) throws Exception {
        String hql = "from Usuarios";
        Query query = session.createQuery(hql);
        
        List<Usuarios> listaUsuarios = (List<Usuarios>) query.list();
        return listaUsuarios;
    }

    @Override
    public Usuarios getByNombre(Session session, String nombre) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuarios getByUsuario(Session session, String nombreUsuario) throws Exception {
        String hql = "from Usuarios where nombreUsuario=:nombreUsuario";
        Query query = session.createQuery(hql);
        query.setParameter("nombreUsuario", nombreUsuario);
        Usuarios usuarios = (Usuarios) query.uniqueResult();
        return usuarios;
    }

    @Override
    public boolean deleteUsuario(Session session, Usuarios usuarios) throws Exception {
        session.delete(usuarios);
        
        return true;
    }
    
    public boolean update(Session session, Usuarios usuarios) throws Exception{
        
    session.update(usuarios);
    
    return true;
    }

    @Override
    public List<Tipousuario> getAllTipoUsuario(Session session) throws Exception {
        String hql = "from Tipousuario";
        Query query = session.createQuery(hql);
        
        List<Tipousuario> listaTipousuario = (List<Tipousuario>) query.list();
        return listaTipousuario;
    }

   
    
}
