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

public class ClienteBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String dni;
    @Expose
    private String nombre;
    @Expose
    private String primerapellido;
    @Expose
    private String segundoapellido;
    @Expose
    private String email;
    @Expose
    private String telefono;

    public ClienteBean() {
    }

    public ClienteBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "dni,";
        strColumns += "nombre,";
        strColumns += "primerapellido,";
        strColumns += "segundoapellido,";
        strColumns += "email,";
        strColumns += "telefono";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(dni) + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += EncodingUtilHelper.quotate(primerapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(segundoapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(email) + ",";
        strColumns += EncodingUtilHelper.quotate(telefono);
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "dni=" + EncodingUtilHelper.quotate(dni) + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "primerapellido=" + EncodingUtilHelper.quotate(primerapellido) + ",";
        strPairs += "segundoapellido=" + EncodingUtilHelper.quotate(segundoapellido) + ",";
        strPairs += "email=" + EncodingUtilHelper.quotate(email) + ",";
        strPairs += "telefono=" + EncodingUtilHelper.quotate(telefono);
        return strPairs;
    }

    @Override
    public ClienteBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDni(oResultSet.getString("dni"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrimerapellido(oResultSet.getString("primerapellido"));
        this.setSegundoapellido(oResultSet.getString("segundoapellido"));
        this.setEmail(oResultSet.getString("email"));
        this.setTelefono(oResultSet.getString("telefono"));

        return this;
    }

}
