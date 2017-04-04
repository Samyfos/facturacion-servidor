/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * sisane-server: Helps you to develop easily AJAX web applications
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/sisane-server
 *
 * sisane-server is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.ClienteDao;
import net.daw.dao.implementation.EmpleadoDao;
import net.daw.dao.implementation.FacturaDao;
import net.daw.dao.implementation.ProductoDao;
import net.daw.dao.implementation.TipoempleadoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class LineapedidoBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private Integer cantidad;

    @Expose(serialize = false)
    private Integer factura_id = 0;
    @Expose(deserialize = false)
    private FacturaBean obj_factura = null;

    @Expose(serialize = false)
    private Integer empleado_id = 0;
    @Expose(deserialize = false)
    private EmpleadoBean obj_empleado = null;

    @Expose(serialize = false)
    private Integer id_tipoempleado = 0;
    @Expose(deserialize = false)
    private TipoempleadoBean obj_tipoempleado = null;

    @Expose(serialize = false)
    private Integer cliente_id = 0;
    @Expose(deserialize = false)
    private ClienteBean obj_cliente = null;

    @Expose(serialize = false)
    private Integer producto_id = 0;
    @Expose(deserialize = false)
    private ProductoBean obj_producto = null;

    public LineapedidoBean() {
    }

    public LineapedidoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(Integer factura_id) {
        this.factura_id = factura_id;
    }

    public FacturaBean getObj_factura() {
        return obj_factura;
    }

    public void setObj_factura(FacturaBean obj_factura) {
        this.obj_factura = obj_factura;
    }

    public Integer getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(Integer empleado_id) {
        this.empleado_id = empleado_id;
    }

    public EmpleadoBean getObj_empleado() {
        return obj_empleado;
    }

    public void setObj_empleado(EmpleadoBean obj_empleado) {
        this.obj_empleado = obj_empleado;
    }

    public Integer getId_tipoempleado() {
        return id_tipoempleado;
    }

    public void setId_tipoempleado(Integer id_tipoempleado) {
        this.id_tipoempleado = id_tipoempleado;
    }

    public TipoempleadoBean getObj_tipoempleado() {
        return obj_tipoempleado;
    }

    public void setObj_tipoempleado(TipoempleadoBean obj_tipoempleado) {
        this.obj_tipoempleado = obj_tipoempleado;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public ClienteBean getObj_cliente() {
        return obj_cliente;
    }

    public void setObj_cliente(ClienteBean obj_cliente) {
        this.obj_cliente = obj_cliente;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public ProductoBean getObj_producto() {
        return obj_producto;
    }

    public void setObj_producto(ProductoBean obj_producto) {
        this.obj_producto = obj_producto;
    }

    
    
    
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "cantidad,";
        strColumns += "factura_id,";
        strColumns += "empleado_id,";
        strColumns += "id_tipoempleado,";
        strColumns += "producto_id,";
        strColumns += "cliente_id";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += cantidad + ",";
        strColumns += factura_id + ",";
        strColumns += empleado_id + ",";
        strColumns += id_tipoempleado + ",";
        strColumns += producto_id + ",";
        strColumns += cliente_id;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "cantidad=" + cantidad + ",";
        strPairs += "factura_id=" + factura_id + ",";
        strPairs += "empleado_id=" + empleado_id + ",";
        strPairs += "id_tipoempleado=" + id_tipoempleado + ",";
        strPairs += "producto_id=" + producto_id + ",";
        strPairs += "cliente_id=" + cliente_id;
        return strPairs;
    }

    @Override
    public LineapedidoBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setCantidad(oResultSet.getInt("cantidad"));

        
        if (expand > 0) {
            FacturaBean oFacturaBean = new FacturaBean();
            FacturaDao oFacturaDao = new FacturaDao(pooledConnection, oPempleadoBean_security, null);
            oFacturaBean.setId(oResultSet.getInt("factura_id"));
            oFacturaBean = oFacturaDao.get(oFacturaBean, expand - 1);
            this.setObj_factura(oFacturaBean);
        } else {
            this.setFactura_id(oResultSet.getInt("factura_id"));
        }
        if (expand > 0) {
            EmpleadoBean oEmpleadoBean = new EmpleadoBean();
            EmpleadoDao oEmpleadoDao = new EmpleadoDao(pooledConnection, oPempleadoBean_security, null);
            oEmpleadoBean.setId(oResultSet.getInt("empleado_id"));
            oEmpleadoBean = oEmpleadoDao.get(oEmpleadoBean, expand - 1);
            this.setObj_empleado(oEmpleadoBean);
        } else {
            this.setEmpleado_id(oResultSet.getInt("empleado_id"));
        }
        if (expand > 0) {
            TipoempleadoBean oTipoempleadoBean = new TipoempleadoBean();
            TipoempleadoDao oTipoempleadoDao = new TipoempleadoDao(pooledConnection, oPempleadoBean_security, null);
            oTipoempleadoBean.setId(oResultSet.getInt("id_tipoempleado"));
            oTipoempleadoBean = oTipoempleadoDao.get(oTipoempleadoBean, expand - 1);
            this.setObj_tipoempleado(oTipoempleadoBean);
        } else {
            this.setId_tipoempleado(oResultSet.getInt("id_tipoempleado"));
        }
        if (expand > 0) {
            ClienteBean oClienteBean = new ClienteBean();
            ClienteDao oClienteDao = new ClienteDao(pooledConnection, oPempleadoBean_security, null);
            oClienteBean.setId(oResultSet.getInt("cliente_id"));
            oClienteBean = oClienteDao.get(oClienteBean, expand - 1);
            this.setObj_cliente(oClienteBean);
        } else {
            this.setCliente_id(oResultSet.getInt("cliente_id"));
        }
        if (expand > 0) {
            ProductoBean oProductoBean = new ProductoBean();
            ProductoDao oProductoDao = new ProductoDao(pooledConnection, oPempleadoBean_security, null);
            oProductoBean.setId(oResultSet.getInt("producto_id"));
            oProductoBean = oProductoDao.get(oProductoBean, expand - 1);
            this.setObj_producto(oProductoBean);
        } else {
            this.setProducto_id(oResultSet.getInt("producto_id"));
        }
        return this;
    }

}
