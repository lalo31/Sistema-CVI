/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeanView;

import DAO.DaoCatProductos;
import HibernateUtil.HibernateUtil;
import Pojos.Catproducto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author edgaaar65
 */
@ManagedBean
@RequestScoped
public class MBCatProductos {

    /**
     * Creates a new instance of MBCatProductos
     */
    
    private Catproducto productos;
    private List<Catproducto> listaProductos;
    private Session session;
    private Transaction transaction;
    private List<Catproducto> listaProductosFiltrado;
    private Catproducto selectedPro;
    
    public MBCatProductos() {
        this.productos = new Catproducto();
        this.listaProductosFiltrado = new ArrayList<Catproducto>();
    }
    
    public void registrar() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoCatProductos daoProd = new DaoCatProductos();
            if(daoProd.getByCodigoProducto(this.session, this.productos.getNumeroCodigo()) != null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El producto se encuentra registrado"));
                return ;
            }
//            if(this.productos.getCategoriaIdCategoria()<=0 || this.productos.getCategoriaIdCategoria() >4 )
//            {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "Categoría Inválida"));
//                return ;
//            }
//            if(this.productos.getCatProveedorNumeroProveedores()<=0 || this.productos.getCatProveedorNumeroProveedores() >4 )
//            {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "Marca Inválida"));
//                return ;
//            }
            daoProd.registrar(this.session, this.productos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se realizo exitosamente"));
            
            this.transaction.commit();
        
            //limpiar formulario
            //RequestContext.getCurrentInstance().execute("limpiarFormulario('frmRegistrarProducto')");
                
            this.productos = new Catproducto();
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", " " +ex.getMessage()));
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }
    
    public void onProdcutsDrop(DragDropEvent ddEvent) {
        Catproducto pro = ((Catproducto) ddEvent.getData());
  
        listaProductosFiltrado.add(pro);
        listaProductos.remove(pro);
    }
    
    public List<Catproducto> getAll(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoCatProductos daoProductos = new DaoCatProductos();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaProductos = daoProductos.getAllProductos(this.session);
            
            this.transaction.commit();
            
            return this.listaProductos;
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", " " +ex.getMessage()));
            return null;
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }

    public void update() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoCatProductos daoProd = new DaoCatProductos();
            /*if(daoProd.getByCodigoProducto(this.session, this.productos.getCodigoProducto()) != null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El producto se encuentra registrado"));
                return ;
            }*/
//            if(this.productos.getCategoriaIdCategoria()<=0 || this.productos.getCategoriaIdCategoria() >4 )
//            {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "Categoría Inválida"));
//                return ;
//            }
//            if(this.productos.getCatProveedorNumeroProveedores()<=0 || this.productos.getCatProveedorNumeroProveedores() >4 )
//            {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "Marca Inválida"));
//                return ;
//            }
            daoProd.update(this.session, this.productos);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se actualizo exitosamente"));
            
            //limpiar formulario
            //RequestContext.getCurrentInstance().execute("limpiarFormulario('frmRegistrarProducto')");
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", " " +ex.getMessage()));
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }
    
    public void deleteProducto() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoCatProductos daoProd = new DaoCatProductos();
            daoProd.deleteProducto(this.session, this.productos);
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se eliminó exitosamente"));
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", " " +ex.getMessage()));
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }
    
     public List<Catproducto> getAllbyStockMinimo(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoCatProductos daoProductos = new DaoCatProductos();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaProductos = daoProductos.getAllProductosByStockMinimo(this.session);
           
            this.transaction.commit();
            
            return this.listaProductos;
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", " " +ex.getMessage()));
            return null;
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }

    public Catproducto getProductos() {
        return productos;
    }

    public void setProductos(Catproducto productos) {
        this.productos = productos;
    }

    public List<Catproducto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Catproducto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Catproducto> getListaProductosFiltrado() {
        return listaProductosFiltrado;
    }

    public void setListaProductosFiltrado(List<Catproducto> listaProductosFiltrado) {
        this.listaProductosFiltrado = listaProductosFiltrado;
    }

    public Catproducto getSelectedPro() {
        return selectedPro;
    }

    public void setSelectedPro(Catproducto selectedPro) {
        this.selectedPro = selectedPro;
    }
    
}