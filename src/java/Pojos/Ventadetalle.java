package Pojos;
// Generated Jul 7, 2015 3:15:46 AM by Hibernate Tools 3.6.0


import java.math.BigDecimal;

public class Ventadetalle  implements java.io.Serializable {


     private Integer idVentaDetalle;
     private Venta venta;
     private Catproducto catproducto;
     private int codigoBarrasProducto;
     private String nombreProducto;
     private BigDecimal precioVentaUnitarioProducto;
     private int cantidad;
     private BigDecimal totalPrecioVenta;

    public Ventadetalle() {
    }

    public Ventadetalle(Venta venta, Catproducto catproducto, int codigoBarrasProducto, String nombreProducto, BigDecimal precioVentaUnitarioProducto, int cantidad, BigDecimal totalPrecioVenta) {
       this.venta = venta;
       this.catproducto = catproducto;
       this.codigoBarrasProducto = codigoBarrasProducto;
       this.nombreProducto = nombreProducto;
       this.precioVentaUnitarioProducto = precioVentaUnitarioProducto;
       this.cantidad = cantidad=1;
       this.totalPrecioVenta = totalPrecioVenta;
    }
   
    public Integer getIdVentaDetalle() {
        return this.idVentaDetalle;
    }
    
    public void setIdVentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }
    public Venta getVenta() {
        return this.venta;
    }
    
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    public Catproducto getCatproducto() {
        return this.catproducto;
    }
    
    public void setCatproducto(Catproducto catproducto) {
        this.catproducto = catproducto;
    }
    public int getCodigoBarrasProducto() {
        return this.codigoBarrasProducto;
    }
    
    public void setCodigoBarrasProducto(int codigoBarrasProducto) {
        this.codigoBarrasProducto = codigoBarrasProducto;
    }
    public String getNombreProducto() {
        return this.nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public BigDecimal getPrecioVentaUnitarioProducto() {
        return this.precioVentaUnitarioProducto;
    }
    
    public void setPrecioVentaUnitarioProducto(BigDecimal precioVentaUnitarioProducto) {
        this.precioVentaUnitarioProducto = precioVentaUnitarioProducto;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getTotalPrecioVenta() {
        return this.totalPrecioVenta;
    }
    
    public void setTotalPrecioVenta(BigDecimal totalPrecioVenta) {
        this.totalPrecioVenta = totalPrecioVenta;
    }




}


