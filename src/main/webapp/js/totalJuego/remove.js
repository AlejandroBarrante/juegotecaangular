/* 
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
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
 * 
 */

'use strict';

moduloTotalJuego.controller('TotalJuegoRemoveController', ['$scope', '$routeParams', 'serverService', '$rootScope',
    function ($scope, $routeParams, serverService, $rootScope) {
        $scope.result = "";

        $scope.ob = 'totalJuego';
        $scope.id = $routeParams.id;
        $scope.title = "Borrado de un Juego";
        $scope.icon = "/images/J.png";
        serverService.getDataFromPromise(serverService.promise_getOne($scope.ob, $scope.id)).then(function (data) {
            $scope.bean = data.message;
        });



        $scope.remove = function () {
            serverService.getDataFromPromise(serverService.promise_removeOne($scope.ob, $scope.id)).then(function (data) {
                $scope.result = data;
            });
        };

        serverService.getDataFromPromise(serverService.promise_getUserSession("usuario")).then(function (data) {
            $scope.bean2 = data.message;
        });

        $scope.registro = function () {

            $('.botreg').attr("href", "#/juego/plist/1/50?order=titulo&ordervalue=asc");
            $('#myModal').modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();

        };

        $scope.close = function () {
            $('.botreg').attr("href", "#/");
            $('#myModal').modal('hide');
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
        };

    }]);