/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeanView;

import DAO.DaoCatProductos;
import DAO.DaoVenta;
import DAO.DaoVentaDetalle;
import HibernateUtil.HibernateUtil;
import Pojos.Catproducto;
import Pojos.Venta;
import Pojos.Ventadetalle;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class MBVenta {

    /**
     * Creates a new instance of MBVenta
     */
    Session session;
    Transaction transaction;

    private Catproducto producto;
    private List<Catproducto> listaProducto;
    private Venta venta;
    private List<Ventadetalle> listaVentaDetalle;

    private int valorCodigoBarras;

    public MBVenta() {
        this.venta = new Venta();
        this.listaVentaDetalle = new ArrayList<>();
    }

    public List<Catproducto> getAllProducto() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCatProductos daoProducto = new DaoCatProductos();

            this.transaction = this.session.beginTransaction();

            this.listaProducto = daoProducto.getAllProductos(this.session);

            this.transaction.commit();

            return this.listaProducto;
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ocurrio un error ", ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void agregarListaVentaDetalle(Integer idProducto) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCatProductos daoProducto = new DaoCatProductos();

            this.transaction = this.session.beginTransaction();

            this.producto = daoProducto.getByIdProducto(this.session, idProducto);

            this.listaVentaDetalle.add(new Ventadetalle(null, null, this.producto.getNumeroCodigo(), this.producto.getNombreProducto(), this.producto.getPrecioVentaUnitario(), 0, new BigDecimal("0")));

            this.transaction.commit();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");

        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "producto no encontrado", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void agregarListaVentaDetallePorCodigoBarras() {
        this.session = null;
        this.transaction = null;

        try {
            if (this.valorCodigoBarras == 0) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCatProductos daoProducto = new DaoCatProductos();

            this.transaction = this.session.beginTransaction();

            this.producto = daoProducto.getByCodigoProducto(this.session, this.valorCodigoBarras);

            if (this.producto != null) {
                this.listaVentaDetalle.add(new Ventadetalle(null, null, this.producto.getNumeroCodigo(), this.producto.getNombreProducto(), this.producto.getPrecioVentaUnitario(), 0, new BigDecimal("0")));

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código de barras invalido", "Producto no encontrado"));
            }

            this.valorCodigoBarras = 0;

            this.transaction.commit();

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:txtAgregarPorCodigoBarras");
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error no se pudo agregar", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public void retirarListaVentaDetalle(int codigoBarras) {
        try {
            int i = 0;

            for (Ventadetalle item : this.listaVentaDetalle) {
                if (item.getCodigoBarrasProducto() == codigoBarras) {
                    this.listaVentaDetalle.remove(i);

                    break;
                }

                i++;
            }

            BigDecimal totalVenta = new BigDecimal("0");

            for (Ventadetalle item : this.listaVentaDetalle) {
                BigDecimal totalVentaPorProducto = item.getPrecioVentaUnitarioProducto().multiply(new BigDecimal(item.getCantidad()));

                item.setTotalPrecioVenta(totalVentaPorProducto);

                totalVenta = totalVenta.add(totalVentaPorProducto);
            }

            this.venta.setPrecioVentaTotal(totalVenta);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Correcto", "Producto retirado de la lista de venta"));

            RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
            RequestContext.getCurrentInstance().update("frmRealizarVentas:mensajeGeneral");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error no se puede realizar la venta", ex.getMessage()));
        }
    }

    public void calcularCostos() {
        try {
            BigDecimal totalVenta = new BigDecimal("0");

            for (Ventadetalle item : this.listaVentaDetalle) {
                if (item.getCantidad() <= this.producto.getCantidad()) {

                    BigDecimal totalVentaPorProducto = item.getPrecioVentaUnitarioProducto().multiply(new BigDecimal(item.getCantidad()));
                    item.setTotalPrecioVenta(totalVentaPorProducto);

                    totalVenta = totalVenta.add(totalVentaPorProducto);

                    this.venta.setPrecioVentaTotal(totalVenta);

                    RequestContext.getCurrentInstance().update("frmRealizarVentas:tablaListaProductosVenta");
                    RequestContext.getCurrentInstance().update("frmRealizarVentas:panelFinalVenta");
                }
                else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Insuficiente en stock"));

                }
//                if (item.getCantidad() > this.producto.getCantidad()) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Insuficiente en stock"));
//                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error no se puede realizar la venta \n no contienes los suficientes productos", ex.getMessage()));
        }
    }

    public void realizarVenta() {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();

            DaoCatProductos daoProducto = new DaoCatProductos();
            DaoVenta daoVenta = new DaoVenta();
            DaoVentaDetalle daoVentaDetalle = new DaoVentaDetalle();

            this.transaction = this.session.beginTransaction();

            daoVenta.registrar(this.session, this.venta);
            this.venta = daoVenta.getUltimoRegistro(this.session);

            for (Ventadetalle item : this.listaVentaDetalle) {
                this.producto = daoProducto.getByCodigoProducto(this.session, item.getCodigoBarrasProducto());
                item.setVenta(this.venta);
                item.setCatproducto(this.producto);

                daoVentaDetalle.registrar(this.session, item);
            }
            

            this.transaction.commit();

            this.listaVentaDetalle = new ArrayList<>();
            this.venta = new Venta();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta realizada correctamente"));
        } catch (Exception ex) {
            if (this.transaction != null) {
                transaction.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error, No se puede realizar la venta ", ex.getMessage()));
        } finally {
            if (this.session != null) {
                this.session.close();
            }

        }

    }
    
    public void removerDelStock(){
        
    }

    public Catproducto getProducto() {
        return producto;
    }

    public void setProducto(Catproducto producto) {
        this.producto = producto;
    }

    public List<Catproducto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Catproducto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<Ventadetalle> getListaVentaDetalle() {
        return listaVentaDetalle;
    }

    public void setListaVentaDetalle(List<Ventadetalle> listaVentaDetalle) {
        this.listaVentaDetalle = listaVentaDetalle;
    }

    public int getValorCodigoBarras() {
        return valorCodigoBarras;
    }

    public void setValorCodigoBarras(int valorCodigoBarras) {
        this.valorCodigoBarras = valorCodigoBarras;
    }

}
