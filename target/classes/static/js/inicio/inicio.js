angular.module('inicio', [])

    .controller('inicio', function ($scope, $http)
    {
        $scope.operador = {};

        $scope.buscarTiposPessoa = function ()
        {
            $http.get("operador/exibirUsuario")
                .then(function (data)
                {
                    $scope.operador = data.data;
                })
        };
    });