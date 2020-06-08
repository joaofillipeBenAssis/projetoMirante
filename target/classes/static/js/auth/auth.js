angular.module('auth', [])

    .factory('auth', function ($rootScope, $http, $location, $window)
    {

        var self = this;

        self.userInfo = {};

        buscarUsuario = function ()
        {
            $http.get("operador/exibirUsuario")
                .then(function (data)
                {
                    self.userInfo = data.data;
                    auth.usuarioLogin = self.userInfo;
                })
        };
    
        enter = function ()
        {
            if ($location.path() !== auth.loginPath)
            {
                auth.path = $location.path();

                if (!auth.authenticated)
                {
                    $location.path(auth.loginPath); 
                }

                
            }
        };

        var auth =
            {
                authenticated: false,
                loginGestor: false,
                loginPath: '/login',
                logoutPath: '/logout',
                homePath: '/',
                path: $location.path(),
                
                usuarioLogin: {},
                
                authenticate: function (credentials, callback)
                {

                    var headers = credentials && credentials.username ?
                        {
                            authorization: "Basic "
                                + btoa(credentials.username + ":"
                                    + credentials.password)
                        } : {};

                    $http.get('user', {headers: headers})

                        .success(function (data)
                        {
                            console.log(data.principal);

                            if (data.name)
                            {
                                auth.authenticated = true;

                                if (data.authorities[0].authority === "Gerente")
                                {
                                    auth.loginGestor = true;
                                    auth.homePath = '/userFilter'
                                }
                                
                                console.log(auth);
                            }

                            else
                            {
                                console.log(data);

                                auth.authenticated = false;
                            }
                            callback && callback(auth.authenticated);
                            $location.path(auth.path == auth.loginPath ? auth.homePath : auth.path);
                        })

                        .error(function ()
                        {
                            auth.authenticated = false;
                            callback && callback(false);
                        });

                },
                clear: function ()
                {
                    auth.authenticated = false;
                    $location.path(auth.logoutPath);
                    

                    $http.post(auth.logoutPath)
                        .success(function ()
                        {
                            $window.location.reload();
                            enter();
                        })

                        .error(function (data)
                        {
                            console.log("Logout failed");
                            console.log(data);
                        });
                },
                init: function (homePath, loginPath, logoutPath)
                {

                    auth.homePath = homePath;
                    auth.loginPath = loginPath;
                    auth.logoutPath = logoutPath;

                    auth.authenticate({},
                        function (authenticated)
                        {
                            if (authenticated)
                            {
                                $location.path(auth.path);
                            }
                        })

                    // Guard route changes and switch to login page if unauthenticated
                    $rootScope.$on('$routeChangeStart', function ()
                    {
                        enter();
                    });

                }
            };

        return auth;

    });
