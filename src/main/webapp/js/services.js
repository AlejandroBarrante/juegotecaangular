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

'use strict';
angular.module('Services', [])
        .factory('serverService', function ($http) {
            function getFilter(filter, filteroperator, filtervalue) {
                var filterParams;
                if (filter) {
                    filterParams = "&filter=" + filter + "&filteroperator=" + filteroperator + "&filtervalue=" + filtervalue;
                } else {
                    filterParams = "";
                }
                return filterParams;
            }
            ;
            function getOrder(order, ordervalue) {
                var orderParams;
                if (order) {
                    orderParams = '&order=' + order + '&ordervalue=' + ordervalue;
                } else {
                    orderParams = "";
                }
                return orderParams;
            }
            ;
            return {
                date_toDate: function (input) {
                    var parts = input.split('/');
                    return new Date(parts[2], parts[1] - 1, parts[0]);
                },
                getAppName: function () {
                    var strPath = window.location.pathname;
                    return strPath.substr(1, strPath.substr(1, strPath.length).indexOf('/'));
                },
                getAppClientUrl: function () {
                    return location.protocol + '//' + location.hostname + ':' + location.port + '/' + this.getAppName();
                },
                getAppUrl: function () {
                    return location.protocol + '//' + location.hostname + ':' + location.port + '/' + this.getAppName() + '/json';
                },
                promise_getAll: function (strClass, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewall' + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                getSessionPromise: function () {
                    return $http.get(this.getAppUrl() + '?ob=usuario&op=getsessionstatus', 'GET', '');
                },
                getLoginPromise: function (username, password) {
                    return $http.get(this.getAppUrl() + '?ob=usuario&op=login&login=' + username + '&password=' + password, 'GET', '');
                },
                getLogoutPromise: function () {
                    return $http.get(this.getAppUrl() + '?ob=usuario&op=logout', 'GET', '');
                },
                promise_getMeta: function (strClass) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getmetainformation', 'GET', '');
                },
                promise_getOne: function (strClass, id) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=get&id=' + id, 'GET', '');
                },
                promise_getPromise: function (strClass, operation, params) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=' + operation + params, 'GET', '');
                },
                promise_getSome: function (strClass, rpp, page, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsome' + '&rpp=' + rpp + '&page=' + page + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeAsignaAutor: function (strClass, rpp, page, id_juego, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeautor' + '&rpp=' + rpp + '&page=' + page + '&id_juego=' + id_juego + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeAsignaCategoria: function (strClass, rpp, page, id_juego, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomecategoria' + '&rpp=' + rpp + '&page=' + page + '&id_juego=' + id_juego + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeAsignaIlustrador: function (strClass, rpp, page, id_juego, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeilustrador' + '&rpp=' + rpp + '&page=' + page + '&id_juego=' + id_juego + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeAutor: function (strClass, rpp, page, id_autor, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeautor' + '&rpp=' + rpp + '&page=' + page + '&id_autor=' + id_autor + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeEditorial: function (strClass, rpp, page, id_editorial, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeeditorial' + '&rpp=' + rpp + '&page=' + page + '&id_editorial=' + id_editorial + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeColeccionUsuario: function (strClass, rpp, page, id_usuario, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeusuario' + '&rpp=' + rpp + '&page=' + page + '&id_usuario=' + id_usuario + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeIlustrador: function (strClass, rpp, page, id_ilustrador, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomeilustrador' + '&rpp=' + rpp + '&page=' + page + '&id_ilustrador=' + id_ilustrador + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeCategoria: function (strClass, rpp, page, id_categoria, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomecategoria' + '&rpp=' + rpp + '&page=' + page + '&id_categoria=' + id_categoria + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getSomeJuego: function (strClass, rpp, page, id_juego, filterParams, orderParams, systemfilterParams) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=getaggregateviewsomejuego' + '&rpp=' + rpp + '&page=' + page + '&id_juego=' + id_juego + filterParams + orderParams + systemfilterParams, 'GET', '');
                },
                promise_getTotalJuego: function (strClass, id_juego) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=get&id=' + id_juego, 'GET', '');
                },
                promise_getUserSession: function () {
                    return $http.get(this.getAppUrl() + '?ob=usuario&op=getuserfromsession', 'GET', '');
                },
                promise_removeOne: function (strClass, id) {
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=remove&id=' + id, 'GET', '');
                },
                promise_removeOneColeccion: function (id_juego) {
                    return $http.get(this.getAppUrl() + '?ob=coleccion' + '&op=removecoleccion&id=' + id_juego, 'GET', '');
                },
                promise_setImage: function (strClass, filename, filename2, jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=setconimagen' + '&filename=' + filename + '&filename2=' + filename2, {params: jsonfile});
                },
                promise_setImageEditorial: function (strClass, filename, jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=setconimagen' + '&filename=' + filename, {params: jsonfile});
                },
                promise_setOne: function (strClass, jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=' + strClass + '&op=set', {params: jsonfile});
                },
                promise_setOneAutor: function (jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=autorJuego' + '&op=set', {params: jsonfile});
                },
                promise_setOneIlustrador: function (jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=ilustradorJuego' + '&op=set', {params: jsonfile});
                },
                promise_setOneCategoria: function (jsonfile) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=categoriaJuego' + '&op=set', {params: jsonfile});
                },
                promise_setOneColeccion: function (id_juego) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
                    return $http.get(this.getAppUrl() + '?ob=coleccion' + '&op=setcoleccion&id_juego=' + id_juego);
                },
                get: function (objeto, numero) {
                    return $http.get('/' + this.appName + '/json' + objeto + '/' + numero + '/get.json').then(function (result) {
                        return result.data;
                    });
                },
                getDataFromPromise: function (promise) {
                    return promise.then(function (result) {
                        return result.data;
                    });
                },
                getFieldNames: function (objeto) {
                    return $http.get('/' + appName + '/' + objeto + '/getcolumns.json').then(function (result) {
                        return result.data;
                    });
                },
                getPage: function (objeto, pagina, order, ordervalue, rpp, filter, filteroperator, filtervalue, systemfilter, systemfilteroperator, systemfiltervalue) {
                    var orderParams = getOrder(order, ordervalue);
                    var filterParams = getFilter(filter, filteroperator, filtervalue);
                    var systemfilterParams = getFilter(systemfilter, systemfilteroperator, systemfiltervalue);
                    //console.log('/' + appName + '/' + objeto + '/' + rpp + '/' + pagina + '/getpage.json?' + filterParams + orderParams + systemfilterParams);
                    return $http.get('/' + appName + '/' + objeto + '/' + rpp + '/' + pagina + '/getpage.json?' + filterParams + orderParams + systemfilterParams).then(function (result) {
                        return result.data;
                    });
                },
                getPages: function (objeto, rpp, filter, filteroperator, filtervalue, systemfilter, systemfilteroperator, systemfiltervalue) {
                    var filterParams = getFilter(filter, filteroperator, filtervalue);
                    var systemfilterParams = getFilter(systemfilter, systemfilteroperator, systemfiltervalue);
                    return $http.get('/' + appName + '/' + objeto + '/' + rpp + '/getpages.json?' + filterParams + systemfilterParams).then(function (result) {
                        return result.data;
                    });
                },
                getPrettyFieldNames: function (objeto) {
                    return $http.get('/' + appName + '/' + objeto + '/getprettycolumns.json').then(function (result) {
                        return result.data;
                    });
                },
                remove: function (objeto, numero) {
                    return $http.get('/' + appName + '/' + objeto + '/' + numero + '/remove.json').then(function (result) {
                        return result.data;
                    });
                },
                save: function (objeto, datos) {
                    $http.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';
//


                    $http({
                        url: this.getAppUrl() + '?ob=' + strClass + '&op=set',
                        method: "GET",
                        params: jsonfile
                    });
                },
                array_identificarArray: function (arr) {
                    var newObj = {};
                    for (var property in arr) {
                        if (arr.hasOwnProperty(property)) {
                            if (property.match("^obj_")) {
                                newObj[property.replace("obj_", "id_")] = arr[property].id;
                            } else {
                                newObj[property] = arr[property];
                            }
                        }
                    }
                    return newObj;
                },
                getPaginationBar: function (objeto, accion, page_number, total_pages, neighborhood, nrpp) {
                    page_number = parseInt(page_number);
                    total_pages = parseInt(total_pages);
                    neighborhood = parseInt(neighborhood);
                    var link = '#/' + objeto + '/' + accion + '/';
                    var vector = "<div class=\"pagination\"><ul>";
                    if (page_number > 1)
                        vector += ("<li><a class=\"pagination_link\" id=\"" + (page_number - 1) + "\" href=\"" + link + (page_number - 1) + "/" + nrpp + "\">prev</a></li>");
                    if (page_number > neighborhood + 1)
                        vector += ("<li><a class=\"pagination_link\" id=\"1\" href=\"" + link + "1/" + nrpp + "\">1</a></li>");
                    if (page_number > neighborhood + 2)
                        vector += ("<li>" + "<a href=\"#\">...</a>" + "</li>");
                    for (var i = (page_number - neighborhood); i <= (page_number + neighborhood); i++) {
                        if (i >= 1 && i <= total_pages) {
                            if (page_number == i) {
                                vector += ("<li class=\"active\"><a class=\"pagination_link\" id=\"" + i + "\" href=\"" + link + i + "/" + nrpp + "\">" + i + "</a></li>");
                            } else
                                vector += ("<li><a class=\"pagination_link\" id=\"" + i + "\" href=\"" + link + i + "/" + nrpp + "\">" + i + "</a></li>");
                        }
                    }
                    if (page_number < total_pages - (neighborhood + 1))
                        vector += ("<li>" + "<a href=\"#\">...</a>" + "</li>");
                    if (page_number < total_pages - (neighborhood))
                        vector += ("<li><a class=\"pagination_link\" id=\"" + total_pages + "\" href=\"" + link + total_pages + "/" + nrpp + "\">" + total_pages + "</a></li>");
                    if (page_number < total_pages)
                        vector += ("<li><a class=\"pagination_link\"  id=\"" + (page_number + 1) + "\" href=\"" + link + (page_number + 1) + "/" + nrpp + "\">next</a></li>");
                    vector += "</ul></div>";
                    return vector;
                },
                getNrppBar: function (objeto, accion, page_number, nrpp) {
                    var link = '#/' + objeto + '/' + accion + '/';
                    var vector = "<div class=\"pagination\"><ul>";
                    if (nrpp == 5)
                        vector += "<li class=\"active\" >";
                    else
                        vector += "<li> ";
                    vector += "<a class=\"nrpp\" id=\"nrrp5\" href=\"" + link + page_number + "/5" + "\">5</a></li>";
                    if (nrpp == 10)
                        vector += "<li class=\"active\" >";
                    else
                        vector += "<li> ";
                    vector += "<a class=\"nrpp\" id=\"nrrp10\" href=\"" + link + page_number + "/10" + "\">10</a></li>";
                    if (nrpp == 20)
                        vector += "<li class=\"active\" >";
                    else
                        vector += "<li> ";
                    vector += "<a class=\"nrpp\" id=\"nrrp20\" href=\"" + link + page_number + "/20" + "\">20</a></li>";
                    if (nrpp == 50)
                        vector += "<li class=\"active\" >";
                    else
                        vector += "<li> ";
                    ;
                    vector += "<a class=\"nrpp\" id=\"nrrp50\" href=\"" + link + page_number + "/50" + "\">50</a></li>";
                    if (nrpp == 100)
                        vector += "<li class=\"active\" >";
                    else
                        vector += "<li> ";
                    vector += "<a class=\"nrpp\" id=\"nrrp100\" href=\"" + link + page_number + "/100" + "\">100</a></li>";
                    // http://localhost:8081/AjaxStockUniDaoSpring/index.jsp#/cliente/4/nrpp
                    vector += "</ul></div>";
                    return vector;
                },
                parameter_printOrderParamsInUrl: function (objParams) {
                    if (objParams)
                        if (objParams.order) {
                            return '&order=' + objParams.order + '&ordervalue=' + objParams.ordervalue;
                        } else {
                            return '';
                        }
                    else
                        return '';
                },
                parameter_printFilterParamsInUrl: function (objParams) {
                    if (objParams)
                        if (objParams.filter) {
                            return "&filter=" + objParams.filter + "&filteroperator=" + objParams.filteroperator + "&filtervalue=" + objParams.filtervalue;
                        } else {
                            return '';
                        }
                    else
                        return '';
                },
                parameter_printSystemFilterParamsInUrl: function (objParams) {
                    if (objParams)
                        if (objParams.systemfilter) {
                            return "&systemfilter=" + objParams.systemfilter + "&systemfilteroperator=" + objParams.systemfilteroperator + "&systemfiltervalue=" + objParams.systemfiltervalue;
                        } else {
                            return '';
                        }
                    else
                        return '';
                }



            };
        })
        .factory('sharedSpaceService', function ($http) {
            var obj = {};
            var link = "";
            var fase = 0;
            return {
                getObject: function () {
                    return obj;
                },
                setObject: function (value) {
                    obj = value;
                },
                getReturnLink: function () {
                    return link;
                },
                setReturnLink: function (value) {
                    link = value;
                },
                getFase: function () {
                    return fase;
                },
                setFase: function (value) {
                    fase = value;
                }

            };
        })

        .factory('sharedSpaceJuego', function ($http) {
            var obj = {};
            var id_juego;
            var link = "";
            var fase = 0;
            return {
                getObject: function () {
                    return obj;
                },
                setObject: function (value) {
                    obj = value;
                },
                get_idJuego: function () {
                    return id_juego;
                },
                set_idJuego: function (value) {
                    id_juego = value;
                },
                getReturnLink: function () {
                    return link;
                },
                setReturnLink: function (value) {
                    link = value;
                },
                getFase: function () {
                    return fase;
                },
                setFase: function (value) {
                    fase = value;
                }

            };
        })
        .factory('sessionService', function ($http) {
            var isSessionActive = false;
            var username = "";
            var id=null;
            return {
                getUsername: function () {
                    return username;
                },
                setUsername: function (value) {
                    username = value;
                },
                isSessionActive: function () {
                    return isSessionActive;
                },
                setSessionInactive: function () {
                    isSessionActive = false;
                },
                setSessionActive: function () {
                    isSessionActive = true;
                },
                getId: function () {
                    return id;
                },
                setId: function (value) {
                    id = value;
                }

            };
        })
        .value('version', '0.1');
