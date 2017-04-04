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

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fechafactura,";
        strColumns += "empleado_id,";
        strColumns += "id_tipoempleado,";
        strColumns += "cliente_id";

        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fechafactura) + ",";
        strColumns += empleado_id + ",";
        strColumns += id_tipoempleado + ",";
        strColumns += cliente_id;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "fechafactura=" + EncodingUtilHelper.stringifyAndQuotate(fechafactura) + ",";
        strPairs += "empleado_id=" + empleado_id + ",";
        strPairs += "id_tipoempleado=" + id_tipoempleado + ",";
        strPairs += "cliente_id=" + cliente_id;
        return strPairs;
    }

    @Override
    public FacturaBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFechafactura(oResultSet.getTimestamp("fechafactura"));

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
        return this;
    }

}
