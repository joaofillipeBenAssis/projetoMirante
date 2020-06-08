angular.module('navigation', ['ngRoute', 'auth'])

    .controller('navigation', function ($http, $route, $scope, auth)
    {
        $scope.operador = {};

        var self = this;

        self.credentials = {};
 
        $scope.buscarUsuario = function ()
        {
            $http.get("operador/exibirUsuario")
                .then(function (data)
                {
                    $scope.operador = data.data;
                })
        };

        self.login = function ()
        {
            auth.authenticate(self.credentials, function (authenticated)
            {
                $scope.buscarUsuario();
            });

        };

        $scope.logout = function()
        {
            auth.clear();
        };

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        self.authenticated = function ()
        {
            return auth.authenticated;
        };

        self.isGestor = function ()
        {
            return auth.loginGestor;
        };
        
        self.authenticated();
        self.login();

    });
