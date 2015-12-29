/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Tipousuario;
import Pojos.Usuarios;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public interface InterfaceUsuarios {
    public boolean registrar(Session session, Usuarios usuarios) throws Exception;
    public List<Usuarios> getAllUsuarios(Session session) throws Exception; 
    public Usuarios getByNombre(Session session, String nombre) throws Exception;
    public Usuarios getByUsuario(Session session, String nombreUsuario)throws Exception;
    public boolean deleteUsuario(Session session, Usuarios usuarios) throws Exception;
    public List<Tipousuario> getAllTipoUsuario (Session session) throws Exception;
}
