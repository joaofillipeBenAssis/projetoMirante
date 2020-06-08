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

                    if($scope.operador === null)
                    {
                        var user = {};
                        user.nome = "'Usuário Padrão do Sistema'";
                        user.login = "usuario"
                        user.perfil = "Administrador"
                        $scope.operador = angular.copy(user); 
                    }
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
