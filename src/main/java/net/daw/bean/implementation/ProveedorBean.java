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

public class ProveedorBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String nombreempresa;
    @Expose
    private String direccion;
    @Expose
    private String ciudad;
    @Expose
    private Integer cp;
    @Expose
    private String provincia;
    @Expose
    private String pais;


    

    public ProveedorBean() {
    }

    public ProveedorBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombreempresa,";
        strColumns += "direccion,";
        strColumns += "ciudad,";
        strColumns += "cp,";
        strColumns += "provincia,";
        strColumns += "pais";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombreempresa) + ",";
        strColumns += EncodingUtilHelper.quotate(direccion) + ",";
        strColumns += EncodingUtilHelper.quotate(ciudad) + ",";
        strColumns += cp + ",";
        strColumns += EncodingUtilHelper.quotate(provincia) + ",";
        strColumns += EncodingUtilHelper.quotate(pais);
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombreempresa) + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(direccion) + ",";
        strPairs += "primerapellido=" + EncodingUtilHelper.quotate(ciudad) + ",";
        strPairs += "segundoapellido=" + cp + ",";
        strPairs += "email=" + EncodingUtilHelper.quotate(provincia) + ",";
        strPairs += "telefono=" + EncodingUtilHelper.quotate(pais);
        return strPairs;
    }

    @Override
    public ProveedorBean fill(ResultSet oResultSet, Connection pooledConnection, EmpleadoBean oPempleadoBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombreempresa(oResultSet.getString("nombreempresa"));
        this.setDireccion(oResultSet.getString("direccion"));
        this.setCiudad(oResultSet.getString("ciudad"));
        this.setCp(oResultSet.getInt("cp"));
        this.setProvincia(oResultSet.getString("provincia"));
        this.setPais(oResultSet.getString("pais"));

        return this;
    }

}
