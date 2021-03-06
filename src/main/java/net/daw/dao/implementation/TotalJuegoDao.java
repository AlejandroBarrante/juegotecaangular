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
import net.daw.bean.implementation.JuegoBean;
import net.daw.bean.implementation.TotalJuegoBean;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;

/**
 *
 * @author Alejandro Barrante Cano
 */
public class TotalJuegoDao implements ViewDaoInterface<JuegoBean>, TableDaoInterface<JuegoBean> {

    private String strSQL = "select * from juego WHERE 1=1  ";
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;

    /**
     *
     * @param oPooledConnection
     * @throws Exception
     */
    public TotalJuegoDao(Connection oPooledConnection) throws Exception {
        try {
            oConnection = oPooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":constructor ERROR: " + ex.getMessage()));
        }
    }

    /**
     * Método GET TotalJuego
     *
     * @param oTJuegoBean
     * @param oJuegoBean
     * @param expand
     * @return oTJuegoBean
     * @throws Exception
     */
    public TotalJuegoBean get(TotalJuegoBean oTJuegoBean, JuegoBean oJuegoBean, Integer expand) throws Exception {
        if (oJuegoBean.getId() > 0) {
            try {

                ResultSet oResultSet = oMysql.getAllSql(strSQL + " And id= " + oJuegoBean.getId() + " ");
                if (oResultSet != null) {
                    while (oResultSet.next()) {
                        oTJuegoBean = oTJuegoBean.fillTotal(oResultSet, oConnection, expand);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oTJuegoBean.setId(0);
        }
        return oTJuegoBean;
    }

    /**
     * Método REMOVE Juego
     *
     * @param id
     * @return result
     * @throws Exception
     */
    @Override
    public Integer remove(Integer id) throws Exception {
        int result = 0;
        try {

            String strTable = "juego";
            String strTableAJ = "autorJuego";
            String strTableIJ = "ilustradorJuego";
            String strTableCJ = "categoriaJuego";
            result = oMysql.removeOne(id, strTable);
            result = oMysql.removeOneTotalJuego(id, strTableAJ);
            result = oMysql.removeOneTotalJuego(id, strTableIJ);
            result = oMysql.removeOneTotalJuego(id, strTableCJ);
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
    public JuegoBean get(JuegoBean oBean, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // To

    }

    /**
     *
     * @param oBean
     * @return
     * @throws Exception
     */
    @Override
    public Integer set(JuegoBean oBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // To

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
    public ArrayList<JuegoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
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
    public ArrayList<JuegoBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
