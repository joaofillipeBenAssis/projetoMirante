angular.module('navigation', ['ngRoute', 'auth'])

    .controller('navigation', function ($route, $scope, auth)
    {
        $scope.userInfo = {};

        var self = this;

        self.credentials = {};
     
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

        self.login = function ()
        {
            auth.authenticate(self.credentials, function (authenticated)
            {
                if (authenticated)
                {

                    console.log("Login succeeded");
                    self.error = false;
                    
                    buscarUser();
                    
                    $scope.userInfo = auth.usuarioLogin;
                    
                    console.log($scope.userInfo);
                }

                else
                {
                    console.log("Login failed");
                    self.error = true;
                }
                
                console.log($scope.userInfo);

            });

        };

        self.logout = function()
        {
            auth.clear();
        };
        
        self.authenticated();
        self.login();

    });
