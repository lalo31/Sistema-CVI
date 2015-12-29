/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeanView;

import Clases.Encrypt;
import DAO.DaoCatProveedores;
import DAO.DaoUsuarios;
import HibernateUtil.HibernateUtil;
import Pojos.Tipousuario;
import Pojos.Usuarios;
import java.util.ArrayList;
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
public class MBUsuarios {

    /**
     * Creates a new instance of MBUsuarios
     */
    
    private Usuarios usuarios;
    private List<Usuarios> listaUsuarios;
    private List<Tipousuario> listaTipousuario;
    private List<String> lista;
    private Session session;
    private Transaction transaction;
    private List<Usuarios> listaUsuariosFiltrado;
    private String contrasenaRepita;
    
    
    public MBUsuarios() {
        this.usuarios = new Usuarios();
    }
    
    public void registrar() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            /*if(!this.usuarios.getContrasena().equals(this.contrasenaRepita))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "Las contraseñas no coinciden"));
                return ;
            }*/
            DaoUsuarios daoUsu = new DaoUsuarios();
            if(daoUsu.getByUsuario(this.session, this.usuarios.getNombreUsuario()) != null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El usuario se encuentra registrado"));
                return ;
            }
            if(this.usuarios.getTipoUsuarioIdTipoUsuario()<=0 || this.usuarios.getTipoUsuarioIdTipoUsuario() >2 )
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El Tipo de Usuario no Corresponde"));
                return ;
            }
            /*this.usuarios.setContrasena(Encrypt.sha512(this.usuarios.getContrasena()));*/
            daoUsu.registrar(this.session, this.usuarios);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El registro se realizo exitosamente"));
            
            this.transaction.commit();
                
            this.usuarios = new Usuarios();
        }
        catch(Exception ex)
        {
            if(this.transaction != null)
            {
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "" +ex.getMessage()));
        }
        finally
        {
            if(this.session != null)
            {
                this.session.close();
            }
        }
    }
    
    public List<Usuarios> getAll(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoUsuarios daoUsu = new DaoUsuarios();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaUsuarios = daoUsu.getAllUsuarios(this.session);
            
            this.transaction.commit();
            
            return this.listaUsuarios;
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
    
    public List<String> getAllTipoUsario(){
        this.session = null;
        this.transaction = null;
        try
        {
            DaoUsuarios daoUsu = new DaoUsuarios();
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.listaTipousuario = daoUsu.getAllTipoUsuario(this.session);
            for(int i = 0; i <= listaTipousuario.size(); i++)
            {
                Tipousuario objtemp = (Tipousuario)listaTipousuario.get(i);
                //System.out.println(objtemp.getTipoUsuario());
                lista = (List<String>) objtemp;
                System.out.println(lista);
            }
            
            this.transaction.commit();
            
            return lista;
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
    
    public void deleteUsuario() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoUsuarios daoUsu = new DaoUsuarios();
            daoUsu.deleteUsuario(this.session, this.usuarios);
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
    
       public void update() throws Exception{
        this.session = null;
        this.transaction = null;
        
        try
        {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = session.beginTransaction();
            DaoUsuarios daoUsu = new DaoUsuarios();
            if(this.usuarios.getTipoUsuarioIdTipoUsuario()<=0 || this.usuarios.getTipoUsuarioIdTipoUsuario() >2 )
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" , "El Tipo de Usuario no Corresponde"));
                return ;
            }
            
            daoUsu.update(this.session, this.usuarios);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se actualizo exitosamente"));
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuarios> getListaUsuariosFiltrado() {
        return listaUsuariosFiltrado;
    }

    public void setListaUsuariosFiltrado(List<Usuarios> listaUsuariosFiltrado) {
        this.listaUsuariosFiltrado = listaUsuariosFiltrado;
    }

    public String getContrasenaRepita() {
        return contrasenaRepita;
    }

    public void setContrasenaRepita(String contrasenaRepita) {
        this.contrasenaRepita = contrasenaRepita;
    }

    public List<Tipousuario> getListaTipousuario() {
        return listaTipousuario;
    }

    public void setListaTipousuario(List<Tipousuario> listaTipousuario) {
        this.listaTipousuario = listaTipousuario;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
    
}
