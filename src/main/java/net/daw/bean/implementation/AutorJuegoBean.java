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
import net.daw.dao.implementation.AutorDao;
import net.daw.dao.implementation.JuegoDao;

/**
 *
 * @author Alejandro Barrante Cano
 */
public class AutorJuegoBean implements GenericBean {

    @Expose(serialize = false)
    private Integer id_autor = 0;
    @Expose(deserialize = false)
    private AutorBean obj_autor = null;
    @Expose(serialize = false)
    private Integer id_juego = 0;
    @Expose(deserialize = false)
    private JuegoBean obj_juego = null;

    /**
     *
     * @return
     */
    public Integer getId_autor() {
        return id_autor;
    }

    /**
     *
     * @param id_autor
     */
    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
    }

    /**
     *
     * @return
     */
    public AutorBean getObj_autor() {
        return obj_autor;
    }

    /**
     *
     * @param obj_autor
     */
    public void setObj_autor(AutorBean obj_autor) {
        this.obj_autor = obj_autor;
    }

    /**
     *
     * @return
     */
    public Integer getId_juego() {
        return id_juego;
    }

    /**
     *
     * @param id_juego
     */
    public void setId_juego(Integer id_juego) {
        this.id_juego = id_juego;
    }

    /**
     *
     * @return
     */
    public JuegoBean getObj_juego() {
        return obj_juego;
    }

    /**
     *
     * @param obj_juego
     */
    public void setObj_juego(JuegoBean obj_juego) {
        this.obj_juego = obj_juego;
    }

    // ---------------------------------------------
    /**
     * Método JSONeador
     *
     * @param expand
     * @return strJson
     */
    public String toJson(Boolean expand) {
        String strJson = "{";
        strJson += "id_autor:" + id_autor + ",";
        strJson += "id_juego:" + id_juego + ",";
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
        strColumns += "id_autor,";
        strColumns += "id_juego";

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
        strColumns += id_autor.toString() + ",";
        strColumns += id_juego.toString();

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
        strPairs += "id_autor=" + id_autor + ",";
        strPairs += "id_juego=" + id_juego;

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
    public AutorJuegoBean fill(ResultSet oResultSet, Connection pooledConnection, Integer expand)
            throws SQLException, Exception {

        if (expand > 0) {
            AutorBean oAutorBean = new AutorBean();
            AutorDao oAutorDao = new AutorDao(pooledConnection);
            oAutorBean.setId(oResultSet.getInt("id_autor"));
            oAutorBean = oAutorDao.get(oAutorBean, expand - 1);
            this.setObj_autor(oAutorBean);
        } else {
            this.setId_autor(oResultSet.getInt("id_autor"));
        }
        if (expand > 0) {
            JuegoBean oJuegoBean = new JuegoBean();
            JuegoDao oJuegoDao = new JuegoDao(pooledConnection);
            oJuegoBean.setId(oResultSet.getInt("id_juego"));
            oJuegoBean = oJuegoDao.get(oJuegoBean, expand - 1);
            this.setObj_juego(oJuegoBean);
        } else {
            this.setId_juego(oResultSet.getInt("id_juego"));
        }

        return this;

    }

}
