package ManageBeanView;

import DAO.DaoCatProveedores;
import HibernateUtil.HibernateUtil;
import Pojos.Catproveedor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eduardo
 */
@ManagedBean
@RequestScoped
public class MBCatProveedores {

    private Catproveedor proveedores;
    private List<Catproveedor> listaProveedores;
    private Session session;
    private Transaction transaction;
    private List<Catproveedor> listaProveedoresFiltrado;
    
    public MBCatProveedores() {
        this.proveedores = new Catproveedor();
    }
    
    public void registrar() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoCatProveedores daoProv = new DaoCatProveedores();
            if(daoProv.getByRfcProveedor(this.session, this.proveedores.getRfcProveedores()) != null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El Proveedor se Encuentra Registrado"));
                return ;
            }
            daoProv.registrar(this.session, this.proveedores);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El Registro del Proveedor se Realizó Exitosamente"));
            
            this.transaction.commit();         
            this.proveedores = new Catproveedor();
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
    
    public List<Catproveedor> getAll(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoCatProveedores daoProv = new DaoCatProveedores();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaProveedores = daoProv.getAllProveedores(this.session);
            
            this.transaction.commit();
            
            return this.listaProveedores;
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
            DaoCatProveedores daoProv = new DaoCatProveedores();
//            if(daoProv.getByRfcProveedor(this.session, this.proveedores.getRfcProveedores()) != null)
//            {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El proveedor se encuentra registrado"));
//                return ;
//            } 
            daoProv.update(this.session, this.proveedores);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Actualizó Exitosamente"));
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
    
    public void deleteProveedor() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoCatProveedores daoProv = new DaoCatProveedores();
            daoProv.deleteProveedor(this.session, this.proveedores);
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se Eliminó Exitosamente"));
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

    public Catproveedor getProveedores() {
        return proveedores;
    }

    public void setProveedores(Catproveedor proveedores) {
        this.proveedores = proveedores;
    }

    public List<Catproveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Catproveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<Catproveedor> getListaProveedoresFiltrado() {
        return listaProveedoresFiltrado;
    }

    public void setListaProveedoresFiltrado(List<Catproveedor> listaProveedoresFiltrado) {
        this.listaProveedoresFiltrado = listaProveedoresFiltrado;
    }
}
