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
package net.daw.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.implementation.ColeccionBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

/**
 *
 * @author Alejandro Barrante Cano
 */
public class ColeccionDao implements ViewDaoInterface<ColeccionBean>, TableDaoInterface<ColeccionBean> {

    private String strSQL = "SELECT * FROM coleccion c, juego j, usuario u WHERE j.id = c.id_juego AND u.id = c.id_usuario AND 1=1 ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;
    private String strTable = "coleccion";

    /**
     *
     * @param oPooledConnection
     * @throws Exception
     */
    public ColeccionDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_usuario
     * @param intRegsPerPag
     * @param intPage
     * @param hmFilter
     * @param hmOrder
     * @param expand
     * @return arrColeccionBean
     * @throws Exception
     */
    public ArrayList<ColeccionBean> getPageUsuario(int id_usuario, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += " AND c.id_usuario=" + id_usuario;
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<ColeccionBean> arrColeccionBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    ColeccionBean oColeccionBean = new ColeccionBean();
                    arrColeccionBean.add(oColeccionBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrColeccionBean;
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_usuario
     * @param intRegsPerPag
     * @param hmFilter
     * @return pages
     * @throws Exception
     */
    public int getPagesUsuario(int id_usuario, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND c.id_usuario=" + id_usuario;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_usuario
     * @param hmFilter
     * @return pages
     * @throws Exception
     */
    public int getCountUsuario(int id_usuario, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND c.id_usuario=" + id_usuario;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_juego
     * @param intRegsPerPag
     * @param intPage
     * @param hmFilter
     * @param hmOrder
     * @param expand
     * @return arrColeccionBean
     * @throws Exception
     */
    public ArrayList<ColeccionBean> getPageJuego(int id_juego, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL += "AND c.id_juego=" + id_juego;
        strSQL += SqlBuilder.buildSqlLimit(oMysql.getCount(strSQL), intRegsPerPag, intPage);
        ArrayList<ColeccionBean> arrColeccionBean = new ArrayList<>();
        try {
            ResultSet oResultSet = oMysql.getAllSql(strSQL);
            if (oResultSet != null) {
                while (oResultSet.next()) {
                    ColeccionBean oColeccionBean = new ColeccionBean();
                    arrColeccionBean.add(oColeccionBean.fill(oResultSet, oConnection, expand));
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrColeccionBean;
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_juego
     * @param intRegsPerPag
     * @param hmFilter
     * @return pages
     * @throws Exception
     */
    public int getPagesJuego(int id_juego, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND c.id_juego=" + id_juego;
        int pages = 0;
        try {
            pages = oMysql.getPages(strSQL, intRegsPerPag);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    /**
     * MÉTODOS PARA HACER CONSULTAS DE COLECCIÓN
     *
     * @param id_juego
     * @param hmFilter
     * @return pages
     * @throws Exception
     */
    public int getCountJuego(int id_juego, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        strSQL += SqlBuilder.buildSqlWhere(hmFilter);
        strSQL += "AND c.id_juego=" + id_juego;
        int pages = 0;
        try {
            pages = oMysql.getCount(strSQL);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        }
        return pages;
    }

    /**
     * Método SET Colección
     *
     * @param oBean
     * @param id_usuario
     * @param id_juego
     * @return iResult
     * @throws Exception
     */
    public Integer setColeccion(ColeccionBean oBean, int id_usuario, int id_juego) throws Exception {

        Integer iResult = null;
        try {

            String strSQLValida = "SELECT * FROM coleccion WHERE id_usuario = " + id_usuario + " AND id_juego = " + id_juego;
            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQLValida);

                if (!oResultSet.next()) {

                    strSQL = "INSERT INTO " + strTable + " ";
                    strSQL += "(" + oBean.getColumns() + ")";
                    strSQL += "VALUES(" + oBean.getValues() + ")";
                    iResult = oMysql.executeInsertSQL(strSQL);

                } else {
                    System.out.println("Ya tienes este juego.");
                    iResult = -1;
                }

            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return iResult;
    }

    /**
     * Método REMOVE Colección
     *
     * @param id_usuario
     * @param id_juego
     * @return result
     * @throws Exception
     */
    public Integer removeColeccion(Integer id_usuario, Integer id_juego) throws Exception {

        int result = 0;
        try {
            String strSQLValida = "SELECT * FROM coleccion WHERE id_usuario = " + id_usuario + " AND id_juego = " + id_juego;

            try {
                ResultSet oResultSet = oMysql.getAllSql(strSQLValida);
                if (oResultSet.next()) {
                    result = oMysql.removeOneColeccion(id_usuario, id_juego, strTable);

                } else {
                    System.out.println("No tienes este juego.");
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
            }

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return result;

    }

// MÉTODOS NO IMPLEMENTADOS
    /**
     *
     * @param oBean
     * @param expand
     * @return
     * @throws Exception
     */
    @Override
    public ColeccionBean get(ColeccionBean oBean, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param oBean
     * @return
     * @throws Exception
     */
    @Override
    public Integer set(ColeccionBean oBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Integer remove(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param intRegsPerPag
     * @param alFilter
     * @return
     * @throws Exception
     */
    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param alFilter
     * @return
     * @throws Exception
     */
    @Override
    public int getCount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param intRegsPerPag
     * @param intPage
     * @param alFilter
     * @param hmOrder
     * @param expand
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<ColeccionBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param alFilter
     * @param hmOrder
     * @param expand
     * @return
     * @throws Exception
     */
    @Override
    public ArrayList<ColeccionBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
