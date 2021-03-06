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
package net.daw.service.implementation;

import static net.daw.helper.statics.AppConfigurationHelper.getSourceConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.daw.bean.implementation.CategoriaJuegoBean;
import net.daw.bean.implementation.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.dao.implementation.CategoriaJuegoDao;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.JsonMessage;
import net.daw.helper.statics.ParameterCook;
import net.daw.service.publicinterface.TableServiceInterface;
import net.daw.service.publicinterface.ViewServiceInterface;

/**
 *
 * @author Alejandro Barrante Cano
 */
public class CategoriaJuegoService implements TableServiceInterface, ViewServiceInterface {

    /**
     *
     */
    protected HttpServletRequest oRequest = null;

    /**
     *
     * @param request
     */
    public CategoriaJuegoService(HttpServletRequest request) {
        oRequest = request;
    }

    /**
     * MÉTODO PARA CHEQUEAR QUE EL USUARIO ESTÉ LOGUEADO
     *
     * @return
     * @throws Exception
     */
    private Boolean checkpermission(String strMethodName) throws Exception {
        UsuarioBean oUserBean = (UsuarioBean) oRequest.getSession().getAttribute("userBean");
        if (oUserBean != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return data
     * @throws Exception
     */
    @Override
    public String getall() throws Exception {

        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
        String data = null;
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;

        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            ArrayList<CategoriaJuegoBean> arrBeans = oCategoriaJuegoDao.getAll(alFilter, hmOrder, AppConfigurationHelper.getJsonDepth());
            data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(arrBeans));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAll ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    // MÉTODOS PARA SACAR JUEGOS FILTRADOS POR CATEGORÍA
    /**
     *
     * @return data
     * @throws Exception
     */
    public String getaggregateviewsomecategoria() throws Exception {

        String data = null;
        try {

            String pageCategoria = this.getpagecategoria();
            String pagesCategoria = this.getpagescategoria();
            String registersCategoria = this.getcountcategoria();
            data = "{"
                    + "\"page\":" + pageCategoria
                    + ",\"pages\":" + pagesCategoria
                    + ",\"registers\":" + registersCategoria
                    + "}";
            data = JsonMessage.getJson("200", data);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewSome ERROR: " + ex.getMessage()));
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getpagecategoria() throws Exception {

        int intRegsPerPag = ParameterCook.prepareRpp(oRequest);;
        int intPage = ParameterCook.preparePage(oRequest);
        int id_categoria = ParameterCook.prepareInt("id_categoria", oRequest);
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
        String data = null;
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            List<CategoriaJuegoBean> arrBeans = oCategoriaJuegoDao.getPageCategoria(id_categoria, intRegsPerPag, intPage, alFilter, hmOrder, AppConfigurationHelper.getJsonDepth());
            data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(arrBeans));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getpagescategoria() throws Exception {

        int intRegsPerPag = ParameterCook.prepareRpp(oRequest);
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        int id_categoria = ParameterCook.prepareInt("id_categoria", oRequest);
        String data = null;
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            data = JsonMessage.getJson("200", Integer.toString(oCategoriaJuegoDao.getPagesCategoria(id_categoria, intRegsPerPag, alFilter)));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getcountcategoria() throws Exception {

        String data = null;
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        int id_categoria = ParameterCook.prepareInt("id_categoria", oRequest);
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            data = JsonMessage.getJson("200", Integer.toString(oCategoriaJuegoDao.getCountCategoria(id_categoria, alFilter)));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    // MÉTODOS PARA SACAR CATEGORÍAS FILTRADAS POR JUEGO
    /**
     *
     * @return data
     * @throws Exception
     */
    public String getaggregateviewsomejuego() throws Exception {

        String data = null;
        try {

            String pageJuego = this.getpagejuego();
            String pagesJuego = this.getpagesjuego();
            String registersJuego = this.getcountjuego();
            data = "{"
                    + "\"page\":" + pageJuego
                    + ",\"pages\":" + pagesJuego
                    + ",\"registers\":" + registersJuego
                    + "}";
            data = JsonMessage.getJson("200", data);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewSome ERROR: " + ex.getMessage()));
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getpagejuego() throws Exception {

        int intRegsPerPag = ParameterCook.prepareRpp(oRequest);;
        int intPage = ParameterCook.preparePage(oRequest);
        int id_juego = ParameterCook.prepareInt("id_juego", oRequest);
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
        String data = null;
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            List<CategoriaJuegoBean> arrBeans = oCategoriaJuegoDao.getPageJuego(id_juego, intRegsPerPag, intPage, alFilter, hmOrder, AppConfigurationHelper.getJsonDepth());
            data = JsonMessage.getJson("200", AppConfigurationHelper.getGson().toJson(arrBeans));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getpagesjuego() throws Exception {

        int intRegsPerPag = ParameterCook.prepareRpp(oRequest);
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        int id_juego = ParameterCook.prepareInt("id_juego", oRequest);
        String data = null;
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            data = JsonMessage.getJson("200", Integer.toString(oCategoriaJuegoDao.getPagesJuego(id_juego, intRegsPerPag, alFilter)));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    /**
     *
     * @return data
     * @throws Exception
     */
    public String getcountjuego() throws Exception {

        String data = null;
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.prepareFilter(oRequest);
        int id_juego = ParameterCook.prepareInt("id_juego", oRequest);
        Connection oConnection = null;
        ConnectionInterface oDataConnectionSource = null;
        try {
            oDataConnectionSource = getSourceConnection();
            oConnection = oDataConnectionSource.newConnection();
            CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
            data = JsonMessage.getJson("200", Integer.toString(oCategoriaJuegoDao.getCountJuego(id_juego, alFilter)));
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getCount ERROR: " + ex.getMessage()));
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oDataConnectionSource != null) {
                oDataConnectionSource.disposeConnection();
            }
        }
        return data;

    }

    /**
     * Método para el SET
     *
     * @return data
     * @throws Exception
     */
    @Override
    public String set() throws Exception {

        if (this.checkpermission("set")) {
            String jason = ParameterCook.prepareJson(oRequest);
            String resultado = null;
            Connection oConnection = null;
            ConnectionInterface oDataConnectionSource = null;
            try {
                oDataConnectionSource = getSourceConnection();
                oConnection = oDataConnectionSource.newConnection();
                oConnection.setAutoCommit(false);
                CategoriaJuegoDao oCategoriaJuegoDao = new CategoriaJuegoDao(oConnection);
                CategoriaJuegoBean oCategoriaJuegoBean = new CategoriaJuegoBean();
                oCategoriaJuegoBean = AppConfigurationHelper.getGson().fromJson(jason, oCategoriaJuegoBean.getClass());
                if (oCategoriaJuegoBean != null) {
                    Integer iResult = oCategoriaJuegoDao.set(oCategoriaJuegoBean);
                    if (iResult >= 1) {
                        resultado = JsonMessage.getJson("200", iResult.toString());
                    } else {
                        resultado = JsonMessage.getJson("500", "Error during registry set");
                    }
                } else {
                    resultado = JsonMessage.getJson("500", "Error during registry set");
                }
                oConnection.commit();
            } catch (Exception ex) {
                oConnection.rollback();
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oDataConnectionSource != null) {
                    oDataConnectionSource.disposeConnection();
                }
            }
            return resultado;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }

    }

    // --------------------
    // MÉTODOS NO IMPLEMENTADOS
    /**
     *
     * @return @throws Exception
     */
    @Override
    public String remove() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String get() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getpage() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getpages() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getcount() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public String getaggregateviewsome() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
