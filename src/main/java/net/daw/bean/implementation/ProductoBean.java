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
import net.daw.dao.implementation.CategoriaDao;
import net.daw.dao.implementation.ClienteDao;
import net.daw.dao.implementation.EmpleadoDao;
import net.daw.dao.implementation.ProveedorDao;
import net.daw.dao.implementation.TipoempleadoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class ProductoBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String nombre;
    @Expose
    private Integer precio;
    @Expose
    private Integer existencias;

    @Expose(serialize = false)
    private Integer categoria_id = 0;
    @Expose(deserialize = false)
    private CategoriaBean obj_categoria = null;

    @Expose(serialize = false)
    private Integer proveedor_id = 0;
    @Expose(deserialize = false)
    private ProveedorBean obj_proveedor = null;

    public ProductoBean() {
    }

    public ProductoBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    public CategoriaBean getObj_categoria() {
        return obj_categoria;
    }

    public void setObj_categoria(CategoriaBean obj_categoria) {
        this.obj_categoria = obj_categoria;
    }

    public Integer getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(Integer proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public ProveedorBean getObj_proveedor() {
        return obj_proveedor;
    }

    public void setObj_proveedor(ProveedorBean obj_proveedor) {
        this.obj_proveedor = obj_proveedor;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "precio,";
        strColumns += "existencias,";
        strColumns += "categoria_id,";
        strColumns += "proveedor_id";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += precio + ",";
        strColumns += existencias + ",";
        strColumns += categoria_id + ",";
        strColumns += proveedor_id;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "precio=" + precio + ",";
        strPairs += "existencias=" + existencias + ",";
        strPairs += "categoria_id=" + categoria_id + ",";
        strPairs += "proveedor_id=" + proveedor_id;
        return strPairs;
    }

    @Override
    public ProductoBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrecio(oResultSet.getInt("precio"));
        this.setExistencias(oResultSet.getInt("existencias"));

        if (expand > 0) {
            CategoriaBean oCategoriaBean = new CategoriaBean();
            CategoriaDao oCategoriaDao = new CategoriaDao(pooledConnection, oPempleadoBean_security, null);
            oCategoriaBean.setId(oResultSet.getInt("categoria_id"));
            oCategoriaBean = oCategoriaDao.get(oCategoriaBean, expand - 1);
            this.setObj_categoria(oCategoriaBean);
        } else {
            this.setCategoria_id(oResultSet.getInt("categoria_id"));
        }
        if (expand > 0) {
            ProveedorBean oProveedorBean = new ProveedorBean();
            ProveedorDao oProveedorDao = new ProveedorDao(pooledConnection, oPempleadoBean_security, null);
            oProveedorBean.setId(oResultSet.getInt("proveedor_id"));
            oProveedorBean = oProveedorDao.get(oProveedorBean, expand - 1);
            this.setObj_proveedor(oProveedorBean);
        } else {
            this.setProveedor_id(oResultSet.getInt("proveedor_id"));
        }
        
        return this;
    }

}
