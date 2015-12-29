/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeanSession;

import DAO.DaoUsuarios;
import HibernateUtil.HibernateUtil;
import Pojos.Usuarios;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edgaaar65
 */
@ManagedBean
@SessionScoped
public class MBSLogin implements Serializable {

    /**
     * Creates a new instance of MBSLogin
     */
    private String usuario;
    private String contrasena;
    private String usuarioT;
    
    private Session session;
    private Transaction transaction;
    
    public MBSLogin() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        miSession.setMaxInactiveInterval(240);
    }
    
    public String login(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoUsuarios daoUsuarios = new DaoUsuarios();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            Usuarios tUsuario = daoUsuarios.getByUsuario(this.session, this.usuario);
            if(tUsuario != null)
            {
                if(tUsuario.getContrasena().equals(this.contrasena) && tUsuario.getTipoUsuarioIdTipoUsuario()==2)
                {
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("usuario", this.usuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido: Empleado", "Al SCVI "));
                    
                    return "/venta/ventaEmpleado";
                }
                if(tUsuario.getContrasena().equals(this.contrasena) && tUsuario.getTipoUsuarioIdTipoUsuario()==1)
                {
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("usuario", this.usuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido: Admin", "Al SCVI "));
                    
                    return "/venta/venta";
                }
            }
            
            this.transaction.commit();
            this.usuario = null;
            this.contrasena = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o Contrasena incorrecta "));
            return "/index";
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
    
    public String cerrarSesion(){
        this.usuario = null;
        this.contrasena = null;
        
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        return "/index";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
     public String getTipoUsuario() {
        return usuarioT;
    }

    public void setTipoUsuario(String usuarioT) {
        this.usuarioT = usuarioT;

    }
}