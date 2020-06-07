angular.module('operador', [])

    .controller('operador', function ($scope, $http)
    {
        $scope.perfis = [];
        $scope.status = [];
        $scope.operadores = [];

        $scope.operador = {};
        $scope.cadastrado = {};

        $scope.mostrarEditar = false;
        $scope.mostrarCadastrado = false;

        $scope.buscarPerfis = function ()
        {
            $http.get("operador/exibirPerfis")
                .then(function (data)
                {
                    $scope.perfis = data.data;
                })
        };

        $scope.buscarStatus = function ()
        {
            $http.get("operador/exibirStatus")
                .then(function (data)
                {
                    $scope.status = data.data;
                })
        };

        $scope.buscarOperadores = function ()
        {
            $http.get("operador/exibirTodos")
                .then(function (data)
                {
                    $scope.operadores = data.data;
                })
        };

        $scope.selecionar = function (operador)
        {
            $scope.operador = angular.copy(operador);
            $scope.mostrarEditar = true;
        }

        $scope.salvarOperador = function (operador)
        {
            var novoOperador = {};
            novoOperador.nome = angular.copy(operador.nome);
            novoOperador.login = angular.copy(operador.login);
            novoOperador.perfil = angular.copy(operador.perfil);

            $scope.cadastrado = angular.copy(operador);

            $http
                    ({
                        method: "POST",
                        url: "/operador/salvar",
                        data: novoOperador
                    })

                    .success(function ()
                    {
                        $scope.mostrarCadastrado = true;
                    })
        };

        $scope.editarOperador = function (operador)
        {
            var novoOperador = angular.copy($scope.operador);
            novoOperador.nome =  angular.copy(operador.nome);
            novoOperador.login = angular.copy(operador.login);
            novoOperador.ativo = angular.copy(operador.ativo);
            novoOperador.perfil = angular.copy(operador.perfil);
            
            console.log(novoOperador);

            $http
                ({
                    method: "PUT",
                    url: "/operador/editar",
                    data: novoOperador
                })

                .success(function ()
                {
                    $scope.buscarOperadores();
                })

                .error(function (data)
                {
                    console.log("erro ao alterar produto!!");
                });
        };

        $scope.buscarPerfis();
        $scope.buscarStatus();
        $scope.buscarOperadores();
    });