/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * sisane-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
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
package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.EmpleadoBean;
import net.daw.bean.implementation.EmpleadoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlData;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.EncodingUtilHelper;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.Log4j;
import net.daw.helper.statics.SqlBuilder;

public class EmpleadoDao implements ViewDaoInterface<EmpleadoBean>, TableDaoInterface<EmpleadoBean> {

    private String strTable = "empleado";
    private String strSQL = "select * from " + strTable + " where 1=1 ";
    private MysqlData oMysql = null;
    private Connection oConnection = null;
    private EmpleadoBean oPuserSecurity = null;

    public EmpleadoDao(Connection oPooledConnection, EmpleadoBean oPuserBean_security, String strWhere) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlData(oConnection);
            oPuserSecurity = oPuserBean_security;
            if (strWhere != null) {
                strSQL += strWhere;
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
    }

    @Override
    public Long getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        Long pages = 0L;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return pages;
    }

    @Override
    public ArrayList<EmpleadoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(alFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<EmpleadoBean> arrEmpleado = new ArrayList<>();
        ResultSet oResultSet = null;
        try {
            oResultSet = oMysql.getAllSQL(strSQL);
            while (oResultSet.next()) {
                EmpleadoBean oEmpleadoBean = new EmpleadoBean();
                arrEmpleado.add((EmpleadoBean) oEmpleadoBean.fill(oResultSet, oConnection, oPuserSecurity, expand));
            }
            if (oResultSet != null) {
                oResultSet.close();
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
        }
        return arrEmpleado;
    }

    @Override
    public ArrayList<EmpleadoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(alFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<EmpleadoBean> arrEmpleado = new ArrayList<>();
        ResultSet oResultSet = null;
        try {
            oResultSet = oMysql.getAllSQL(strSQL);
            while (oResultSet.next()) {
                EmpleadoBean oEmpleadoBean = new EmpleadoBean();
                arrEmpleado.add((EmpleadoBean) oEmpleadoBean.fill(oResultSet, oConnection, oPuserSecurity, expand));
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
        }
        return arrEmpleado;
    }

    @Override
    public EmpleadoBean get(EmpleadoBean oEmpleadoBean, Integer expand) throws Exception {
        if (oEmpleadoBean.getId() > 0) {
            ResultSet oResultSet = null;
            try {
                oResultSet = oMysql.getAllSQL(strSQL + " And id= " + oEmpleadoBean.getId() + " ");
                Boolean empty = true;
                while (oResultSet.next()) {
                    oEmpleadoBean = (EmpleadoBean) oEmpleadoBean.fill(oResultSet, oConnection, oPuserSecurity, expand);
                    empty = false;
                }
                if (empty) {
                    oEmpleadoBean.setId(0);
                }
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
            }
        } else {
            oEmpleadoBean.setId(0);
        }
        return oEmpleadoBean;
    }

    @Override
    public Integer set(EmpleadoBean oEmpleadoBean) throws Exception {
        Integer iResult = null;
        try {
            if (oEmpleadoBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oEmpleadoBean.getColumns() + ")";
                strSQL += "VALUES(" + oEmpleadoBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oEmpleadoBean.toPairs();
                strSQL += " WHERE id=" + oEmpleadoBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return iResult;
    }

    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(id, strTable);
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return result;
    }

    public EmpleadoBean getFromLogin(EmpleadoBean oPuser) throws Exception {
        try {
            String strId = oMysql.getId(strTable, "login", oPuser.getLogin());
            if (strId == null) {
                oPuser.setId(0);
            } else {
                Integer intId = Integer.parseInt(strId);
                oPuser.setId(intId);
                String pass = oPuser.getPassword();
                oPuser.setPassword(oMysql.getOne(strSQL, "password", oPuser.getId()));
                if (!pass.equals(oPuser.getPassword())) {
                    oPuser.setId(0);
                }
                oPuser = this.getP(oPuser, AppConfigurationHelper.getJsonMsgDepth());
            }
            return oPuser;
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
    }

    public EmpleadoBean getP(EmpleadoBean oPuserBean, Integer expand) throws Exception {
        if (oPuserBean.getId() > 0) {
            ResultSet oResultSet = null;
            try {
                oResultSet = oMysql.getAllSQL(strSQL + " And id= " + oPuserBean.getId() + " ");
                Boolean empty = true;
                while (oResultSet.next()) {
                    oPuserBean = (EmpleadoBean) oPuserBean.fill(oResultSet, oConnection, oPuserSecurity, expand);
                    empty = false;
                }
                if (empty) {
                    oPuserBean.setId(0);
                }
            } catch (Exception ex) {
                Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                throw new Exception();
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
            }
        } else {
            oPuserBean.setId(0);
        }
        return oPuserBean;
    }

    public Integer setP(EmpleadoBean oPuserBean) throws Exception {
        //only 4 fill service
        Integer iResult = null;
        try {
            if (oPuserBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable + " ";
                strSQL += "(" + oPuserBean.getColumns() + ")";
                strSQL += "VALUES(" + oPuserBean.getValues() + ")";
                iResult = oMysql.executeInsertSQL(strSQL);
            } else {
                strSQL = "UPDATE " + strTable + " ";
                strSQL += " SET " + oPuserBean.toPairs();
                strSQL += " WHERE id=" + oPuserBean.getId();
                iResult = oMysql.executeUpdateSQL(strSQL);
            }
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return iResult;
    }

    public Integer passchange(String oldPassword, String newPassword) throws Exception {
        Integer iResult = null;
        try {
            strSQL = "UPDATE " + strTable + " ";
            strSQL += " SET password = " + EncodingUtilHelper.quotate(newPassword);
            strSQL += " WHERE id=" + oPuserSecurity.getId();
            strSQL += " and password=" + EncodingUtilHelper.quotate(oldPassword);
            iResult = oMysql.executeUpdateSQL(strSQL);
        } catch (Exception ex) {
            Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
            throw new Exception();
        }
        return iResult;
    }

}
