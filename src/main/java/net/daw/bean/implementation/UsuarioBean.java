/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
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
import net.daw.dao.implementation.TipousuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author Alejandro Barrante Cano
 */
public class UsuarioBean implements GenericBean {

    @Expose
    private Integer id;
    @Expose
    private String login = "";
    @Expose
    private String password = "";
    @Expose(serialize = false)
    private Integer id_tipousuario = 0;
    @Expose(deserialize = false)
    private TipousuarioBean obj_tipousuario = null;
    @Expose
    private String ciudad = "";
    @Expose
    private String firma = "";

    /**
     *
     */
    public UsuarioBean() {
        this.id = 0;
    }

    /**
     *
     * @param id
     */
    public UsuarioBean(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Integer getId_tipousuario() {
        return id_tipousuario;
    }

    /**
     *
     * @param id_tipousuario
     */
    public void setId_tipousuario(Integer id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    /**
     *
     * @return
     */
    public TipousuarioBean getObj_tipousuario() {
        return obj_tipousuario;
    }

    /**
     *
     * @param obj_tipousuario
     */
    public void setObj_tipousuario(TipousuarioBean obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }

    /**
     *
     * @return
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     *
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @return
     */
    public String getFirma() {
        return firma;
    }

    /**
     *
     * @param firma
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    // ---------------------------------
    /**
     * Método JSONeador
     *
     * @param expand
     * @return strJson
     */
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id:" + id + ",";
        strJson += "login:" + EncodingUtilHelper.quotate(login) + ",";
        strJson += "password:" + EncodingUtilHelper.quotate(password) + ",";
        strJson += "ciudad:" + EncodingUtilHelper.quotate(ciudad) + ",";
        strJson += "firma:" + EncodingUtilHelper.quotate(firma) + ",";
        if (expand) {
            strJson += "obj_tipousuario:" + obj_tipousuario.toJson(false) + ",";
        } else {
            strJson += "id_tipousuario:" + id_tipousuario + ",";
        }
        strJson += "}";
        return strJson;
    }

    /**
     * Método que obtiene las columnas en caso de NEW
     *
     * @return strColumns
     */
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "login,";
        strColumns += "password,";
        strColumns += "ciudad,";
        strColumns += "firma,";
        strColumns += "id_tipousuario";

        return strColumns;
    }

    /**
     * Método que recupera los valores introducidos en un formulario
     *
     * @return strColumns
     */
    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(login) + ",";
        strColumns += EncodingUtilHelper.quotate(password) + ",";
        strColumns += EncodingUtilHelper.quotate(ciudad) + ",";
        strColumns += EncodingUtilHelper.quotate(firma) + ",";
        strColumns += 2;

        return strColumns;
    }

    /**
     * Método que obtiene las columnas en caso de EDIT
     *
     * @return strPairs
     */
    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "login=" + EncodingUtilHelper.quotate(login) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(password) + ",";
        strPairs += "ciudad=" + EncodingUtilHelper.quotate(ciudad) + ",";
        strPairs += "firma=" + EncodingUtilHelper.quotate(firma) + ",";
        strPairs += "id_tipousuario=" + id_tipousuario;

        return strPairs;
    }

    /**
     * Método que rellena el POJO
     *
     * @param oResultSet
     * @param pooledConnection
     * @param expand
     * @return this
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        this.setCiudad(oResultSet.getString("ciudad"));
        this.setFirma(oResultSet.getString("firma"));
        if (expand > 0) {
            TipousuarioBean oTipousuarioBean = new TipousuarioBean();
            TipousuarioDao oTipousuarioDao = new TipousuarioDao(pooledConnection);
            oTipousuarioBean.setId(oResultSet.getInt("id_tipousuario"));
            oTipousuarioBean = oTipousuarioDao.get(oTipousuarioBean, expand - 1);
            this.setObj_tipousuario(oTipousuarioBean);
        } else {
            this.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
        }
        return this;

    }

}
