/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Catproveedor;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author eduardo
 */
public interface InterfaceCatProveedores {
    public boolean registrar(Session session, Catproveedor proveedores) throws Exception;
    public List<Catproveedor> getAllProveedores(Session session) throws Exception; 
    public Catproveedor getByNombreProveedor(Session session, String nombreProveedor) throws Exception;
    public boolean update(Session session, Catproveedor proveedores) throws Exception;
    public Catproveedor getByRfcProveedor(Session session, String rfcProveedores)throws Exception;
    public boolean deleteProveedor(Session session, Catproveedor proveedores) throws Exception;
    
}
