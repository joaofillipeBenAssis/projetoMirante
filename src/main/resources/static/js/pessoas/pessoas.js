angular.module('pessoa', [])

    .controller('pessoa', function ($scope, $http)
    {
        $scope.tiposPessoa = [];
        $scope.tiposTelefone = [];
        $scope.pessoas = [];
        $scope.telefones = [];

        $scope.pessoa = {};
        $scope.telefone = {};

        $scope.mostrarDetalhe = false;
        $scope.mostrarCadastrado = false;
        $scope.mostrarSalvarTelefone = false;
        $scope.mostrarEditarTelefone = false;

        $scope.buscarTiposPessoa = function ()
        {
            $http.get("pessoa/exibirTiposPessoa")
                .then(function (data)
                {
                    $scope.tiposPessoa = data.data;
                })
        };

        $scope.buscarTiposTelefone = function ()
        {
            $http.get("pessoa/exibirTiposTelefone")
                .then(function (data)
                {
                    $scope.tiposTelefone = data.data;
                })
        };

        $scope.buscarPessoas = function ()
        {
            $http.get("pessoa/exibirPessoas")
                .then(function (data)
                {
                    $scope.pessoas = data.data;
                })
        };

        $scope.buscarTelefones = function ()
        {

            var params =
            {
                id: angular.copy($scope.pessoa.id)
            };

            $http
                ({
                    method: "GET",
                    url: "pessoa/exibirTelefones",
                    params: params
                })

                .then(function (data)
                {
                    $scope.telefones = data.data;
                    console.log($scope.telefones)
                })

                .catch(function (data)
                {
                    console.log("Erro buscar Telefones");
                });
        };

        $scope.selecionarPessoa = function (pessoa)
        {
            $scope.pessoa = angular.copy(pessoa);
            $scope.buscarTelefones();
            $scope.mostrarDetalhe = true;
        }

        $scope.selecionarTelefone = function (telefone)
        {
            $scope.telefone = angular.copy(telefone);
            $scope.toggleEditarTelefone();
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

        $scope.salvarTelefone = function (telefone)
        {
            var novoTelefone = {};
            novoTelefone.ddd = angular.copy(telefone.ddd);
            novoTelefone.tipoTelefone = angular.copy(telefone.tipoTelefone);
            novoTelefone.numero = angular.copy(telefone.numero);
            novoTelefone.pessoa = angular.copy($scope.pessoa);

            $scope.telefone = angular.copy(telefone);

            $http
                    ({
                        method: "POST",
                        url: "/pessoa/salvarTelefone",
                        data: novoTelefone
                    })

                    .success(function ()
                    {
                        $scope.buscarTelefones();
                        $scope.mostrarSalvarTelefone = false;
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
                    $scope.buscarTelefones();
                })
        };

        $scope.toggleEditarTelefone = function()
        {
            $scope.mostrarSalvarTelefone = false;
            $scope.mostrarEditarTelefone = !$scope.mostrarEditarTelefone;
        }

        $scope.toggleSalvarTelefone = function()
        {
            $scope.telefone = {};
            $scope.mostrarEditarTelefone = false;
            $scope.mostrarSalvarTelefone = !$scope.mostrarSalvarTelefone;
        }

        $scope.buscarTiposPessoa();
        $scope.buscarTiposTelefone();
        $scope.buscarPessoas();
    });