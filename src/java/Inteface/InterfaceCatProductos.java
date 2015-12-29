/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import Pojos.Catproducto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author edgaaar65
 */
public interface InterfaceCatProductos {
    public boolean registrar(Session session, Catproducto productos) throws Exception;
    public List<Catproducto> getAllProductos(Session session) throws Exception; 
    public Catproducto getByIdProducto(Session session, Integer idProducto) throws Exception;
    public boolean update(Session session, Catproducto productos) throws Exception;
    public Catproducto getByCodigoProducto(Session session, Integer numeroCodigo)throws Exception;
    public boolean deleteProducto(Session session, Catproducto productos) throws Exception;
}
