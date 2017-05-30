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
    private Integer id_factura = 0;
    @Expose(deserialize = false)
    private FacturaBean obj_factura = null;

    @Expose(serialize = false)
    private Integer id_producto = 0;
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
        return id_factura;
    }

    public void setFactura_id(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public FacturaBean getObj_factura() {
        return obj_factura;
    }

    public void setObj_factura(FacturaBean obj_factura) {
        this.obj_factura = obj_factura;
    }

    public Integer getProducto_id() {
        return id_producto;
    }

    public void setProducto_id(Integer id_producto) {
        this.id_producto = id_producto;
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
        strColumns += "id_factura,";
        strColumns += "id_producto";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += cantidad + ",";
        strColumns += id_factura + ",";
        strColumns += id_producto;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "cantidad=" + cantidad + ",";
        strPairs += "id_factura=" + id_factura + ",";
        strPairs += "id_producto=" + id_producto;
        return strPairs;
    }

    @Override
    public LineapedidoBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setCantidad(oResultSet.getInt("cantidad"));

        if (expand > 0) {
            FacturaBean oFacturaBean = new FacturaBean();
            FacturaDao oFacturaDao = new FacturaDao(pooledConnection, oPempleadoBean_security, null);
            oFacturaBean.setId(oResultSet.getInt("id_factura"));
            oFacturaBean = oFacturaDao.get(oFacturaBean, expand - 1);
            this.setObj_factura(oFacturaBean);
        } else {
            this.setFactura_id(oResultSet.getInt("id_factura"));
        }
        if (expand > 0) {
            ProductoBean oProductoBean = new ProductoBean();
            ProductoDao oProductoDao = new ProductoDao(pooledConnection, oPempleadoBean_security, null);
            oProductoBean.setId(oResultSet.getInt("id_producto"));
            oProductoBean = oProductoDao.get(oProductoBean, expand - 1);
            this.setObj_producto(oProductoBean);
        } else {
            this.setProducto_id(oResultSet.getInt("id_producto"));
        }
        return this;
    }

}
