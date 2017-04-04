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
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.TipoempleadoDao;
import net.daw.helper.statics.EncodingUtilHelper;

public class EmpleadoBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String nombre;
    @Expose
    private String primerapellido;
    @Expose
    private String segundoapellido;
    @Expose
    private String login;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String email;
    @Expose
    private String telefono;

    @Expose(serialize = false)
    private Integer id_tipoempleado = 0;
    @Expose(deserialize = false)
    private TipoempleadoBean obj_tipoempleado  = null;
    
    
    public EmpleadoBean() {
    }

    public EmpleadoBean(Integer id) {
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

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getId_tipoempleado() {
        return id_tipoempleado;
    }

    public void setId_tipoempleado(Integer id_tipoempleado) {
        this.id_tipoempleado = id_tipoempleado;
    }

    public TipoempleadoBean getObj_tipoemppleado() {
        return obj_tipoempleado;
    }

    public void setObj_tipoempleado(TipoempleadoBean obj_tipoempleado) {
        this.obj_tipoempleado = obj_tipoempleado;
    }
    
     
    
    
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "primerapellido,";
        strColumns += "segundoapellido,";
        strColumns += "login,";
        strColumns += "password,";        
        strColumns += "email,";
        strColumns += "telefono,";
        strColumns += "id_tipoempleado";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += EncodingUtilHelper.quotate(primerapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(segundoapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(login) + ",";
        strColumns += EncodingUtilHelper.quotate(password) + ",";
        strColumns += EncodingUtilHelper.quotate(email) + ",";
        strColumns += EncodingUtilHelper.quotate(telefono) + ",";
        strColumns += id_tipoempleado;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "primerapellido=" + EncodingUtilHelper.quotate(primerapellido) + ",";
        strPairs += "segundoapellido=" + EncodingUtilHelper.quotate(segundoapellido) + ",";
        strPairs += "login=" + EncodingUtilHelper.quotate(login) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(password) + ",";        
        strPairs += "email=" + EncodingUtilHelper.quotate(email) + ",";
        strPairs += "telefono=" + EncodingUtilHelper.quotate(telefono) + ",";
        strPairs += "id_tipoempleado=" + id_tipoempleado;
        return strPairs;
    }

    @Override
    public EmpleadoBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrimerapellido(oResultSet.getString("primerapellido"));
        this.setSegundoapellido(oResultSet.getString("segundoapellido"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        this.setEmail(oResultSet.getString("email"));
        this.setTelefono(oResultSet.getString("telefono"));

        if (expand > 0) {
            TipoempleadoBean oTipoempleadoBean = new TipoempleadoBean();
            TipoempleadoDao oTipoempleadoDao = new TipoempleadoDao(pooledConnection, oPempleadoBean_security, null);
            oTipoempleadoBean.setId(oResultSet.getInt("id_tipoempleado"));
            oTipoempleadoBean = oTipoempleadoDao.get(oTipoempleadoBean, expand - 1);
            this.setObj_tipoempleado(oTipoempleadoBean);
        } else {
            this.setId_tipoempleado(oResultSet.getInt("id_tipoempleado"));
        }

        return this;
    }

}

