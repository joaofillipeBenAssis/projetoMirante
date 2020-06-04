angular.module('hello',
        [
            'ngRoute',
            'operador',
            'navigation'
            
            
           
        ])

        .config
        (
                function ($routeProvider, $httpProvider, $locationProvider)
                {

                    $locationProvider.html5Mode(true);

                    $routeProvider
                    
                            .when('/',
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

                            .when('/login',
                                    {
                                        templateUrl: 'js/navigation/login.html',
                                        controller: 'navigation',
                                        controllerAs: 'controller'

                                    })
                                    
                            .when('/userFilter',
                                    {
                                        templateUrl: 'js/gerente/filter/usersFilter.html',
                                        controller: 'clientes',
                                        controllerAs: 'controller'

                                    })
                                    
                            .when('/produtoFilter',
                                    {
                                        templateUrl: 'js/gerente/filter/produtoFilter.html',
                                        controller: 'produtosGerente',
                                        controllerAs: 'controller'

                                    })
                                    
                            .when('/warehouse',
                                    {
                                        templateUrl: 'js/warehouse/warehouse.html',
                                        controller: 'warehouse',
                                        controllerAs: 'controller'

                                    })
                                    
                            .otherwise('/');

                    $httpProvider.defaults.headers
                            .common['X-Requested-With'] = 'XMLHttpRequest';

                })