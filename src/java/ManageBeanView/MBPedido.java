/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeanView;

import DAO.DaoPedido;
import HibernateUtil.HibernateUtil;
import Pojos.Pedido;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edgaaar65
 */
@ManagedBean
@RequestScoped
public class MBPedido {

    /**
     * Creates a new instance of MBPedido
     */
    
    private Pedido pedido;
    private List<Pedido> listaPedido;
    
    private Session session;
    private Transaction transaction;
    
    public MBPedido() {
        this.pedido = new Pedido();
    }
    
    public void registrar() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoPedido daoPed = new DaoPedido();
            daoPed.registrar(this.session, this.pedido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se realizo exitosamente"));
            
            this.transaction.commit();
                
            this.pedido = new Pedido();
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
    
    public List<Pedido> getAll(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoPedido daoPed = new DaoPedido();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaPedido = daoPed.getAllPedido(this.session);
            
            this.transaction.commit();
            
            return this.listaPedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(List<Pedido> listaPedido) {
        this.listaPedido = listaPedido;
    }
    
}
