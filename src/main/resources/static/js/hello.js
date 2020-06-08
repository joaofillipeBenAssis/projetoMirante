angular.module('hello',
        [
            'ngRoute',
            'operador',
            'pessoa',
            'inicio',
            'auth',
            'navigation'
            
            
           
        ])

        .config
        (
                function ($routeProvider, $httpProvider, $locationProvider)
                {

                        $locationProvider.html5Mode(true);

                        $routeProvider
                    
                                .when('/operadores',
                                    {
                                        templateUrl: 'js/operadores/operadores.html',
                                        controller: 'operador',
                                        controllerAs: 'controller'

                                    })

                                .when('/novo-operador',
                                    {
                                        templateUrl: 'js/operadores/novo-operador.html',
                                        controller: 'operador',
                                        controllerAs: 'controller'

                                    })

                                .when('/pessoas',
                                    {
                                        templateUrl: 'js/pessoas/pessoas.html',
                                        controller: 'pessoa',
                                        controllerAs: 'controller'

                                    })

                                .when('/nova-pessoa',
                                    {
                                        templateUrl: 'js/pessoas/nova-pessoa.html',
                                        controller: 'pessoa',
                                        controllerAs: 'controller'

                                    })

                                    /*

                                .when('/login',
                                    {
                                        templateUrl: 'js/navigation/login.html',
                                        controller: 'navigation',
                                        controllerAs: 'controller'

                                    })

                                    */
                                    
                                .when('/',
                                    {
                                        templateUrl: 'js/inicio/inicio.html',
                                        controller: 'navigation',
                                        controllerAs: 'controller'

                                    })
                                    
                                .otherwise('/');

                        $httpProvider.defaults.headers
                                .common['X-Requested-With'] = 'XMLHttpRequest';

                })

                .run(function (auth)
                {
                    auth.init('/', '/login', '/logout');
                });