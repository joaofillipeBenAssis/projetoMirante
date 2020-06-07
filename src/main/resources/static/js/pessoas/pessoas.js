angular.module('pessoa', [])

    .controller('pessoa', function ($scope, $http)
    {
        $scope.tiposPessoa = [];
        $scope.pessoas = [];

        $scope.pessoa = {};

        $scope.mostrarEditar = false;
        $scope.mostrarCadastrado = false;

        $scope.buscarTiposPessoa = function ()
        {
            $http.get("pessoa/exibirTiposPessoa")
                .then(function (data)
                {
                    $scope.tiposPessoa = data.data;
                })
        };

        $scope.buscarPessoas = function ()
        {
            $http.get("pessoa/exibirPessoas")
                .then(function (data)
                {
                    $scope.pessoas = data.data;
                    console.log($scope.pessoas);
                })
        };

        $scope.selecionar = function (operador)
        {
            $scope.operador = angular.copy(operador);
            $scope.mostrarEditar = true;
        }

        $scope.salvarPessoa = function (pessoa)
        {
            var novaPessoa = {};
            novaPessoa.nome = angular.copy(pessoa.nome);
            novaPessoa.tipoPessoa = angular.copy(pessoa.tipoPessoa);
            novaPessoa.documento = angular.copy(pessoa.documento);
            novaPessoa.dataNascimento = angular.copy(pessoa.dataNascimento);

            $scope.pessoa = angular.copy(pessoa);

            $http
                    ({
                        method: "POST",
                        url: "/pessoa/salvar",
                        data: novaPessoa
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

        $scope.buscarTiposPessoa();
        $scope.buscarPessoas();
    });