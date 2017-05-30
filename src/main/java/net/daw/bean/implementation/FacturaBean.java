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
import net.daw.dao.implementation.TipoempleadoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class FacturaBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private Date fechafactura;

    @Expose(serialize = false)
    private Integer id_empleado = 0;
    @Expose(deserialize = false)
    private EmpleadoBean obj_empleado = null;

    @Expose(serialize = false)
    private Integer id_cliente = 0;
    @Expose(deserialize = false)
    private ClienteBean obj_cliente = null;

    public FacturaBean() {
    }

    public FacturaBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public Integer getEmpleado_id() {
        return id_empleado;
    }

    public void setEmpleado_id(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public EmpleadoBean getObj_empleado() {
        return obj_empleado;
    }

    public void setObj_empleado(EmpleadoBean obj_empleado) {
        this.obj_empleado = obj_empleado;
    }

    public Integer getCliente_id() {
        return id_cliente;
    }

    public void setCliente_id(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public ClienteBean getObj_cliente() {
        return obj_cliente;
    }

    public void setObj_cliente(ClienteBean obj_cliente) {
        this.obj_cliente = obj_cliente;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fechafactura,";
        strColumns += "id_empleado,";
        strColumns += "id_cliente";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fechafactura) + ",";
        strColumns += id_empleado + ",";
        strColumns += id_cliente;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "fechafactura=" + EncodingUtilHelper.stringifyAndQuotate(fechafactura) + ",";
        strPairs += "id_empleado=" + id_empleado + ",";
        strPairs += "id_cliente=" + id_cliente;
        return strPairs;
    }

    @Override
    public FacturaBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFechafactura(oResultSet.getTimestamp("fechafactura"));

        if (expand > 0) {
            EmpleadoBean oEmpleadoBean = new EmpleadoBean();
            EmpleadoDao oEmpleadoDao = new EmpleadoDao(pooledConnection, oPempleadoBean_security, null);
            oEmpleadoBean.setId(oResultSet.getInt("id_empleado"));
            oEmpleadoBean = oEmpleadoDao.get(oEmpleadoBean, expand - 1);
            this.setObj_empleado(oEmpleadoBean);
        } else {
            this.setEmpleado_id(oResultSet.getInt("id_empleado"));
        }
         
        if (expand > 0) {
            ClienteBean oClienteBean = new ClienteBean();
            ClienteDao oClienteDao = new ClienteDao(pooledConnection, oPempleadoBean_security, null);
            oClienteBean.setId(oResultSet.getInt("id_cliente"));
            oClienteBean = oClienteDao.get(oClienteBean, expand - 1);
            this.setObj_cliente(oClienteBean);
        } else {
            this.setCliente_id(oResultSet.getInt("id_cliente"));
        }
        return this;
    }

}
