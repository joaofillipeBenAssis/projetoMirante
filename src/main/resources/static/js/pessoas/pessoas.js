angular.module('pessoa', [])

    .controller('pessoa', function ($scope, $http)
    {
        $scope.tiposPessoa = [];
        $scope.tiposTelefone = [];
        $scope.pessoas = [];
        $scope.telefones = [];

        $scope.pessoa = {};
        $scope.cadastro = {};
        $scope.telefone = {};
        $scope.operador = {};

        $scope.mostrarDetalhe = false;
        $scope.mostrarCadastrado = false;
        $scope.mostrarSalvarTelefone = false;
        $scope.mostrarEditarTelefone = false;

        $scope.buscarUsuario = function ()
        {
            $http.get("operador/exibirUsuario")
                .then(function (data)
                {
                    $scope.operador = data.data;
                })
        };


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
                })
        };

        $scope.selecionarPessoa = function (pessoa)
        {
            $scope.pessoa = angular.copy(pessoa);
            $scope.buscarTelefones();
            $scope.mostrarDetalhe = true;
            $scope.mostrarSalvarTelefone = false;
            $scope.mostrarEditarTelefone = false;
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

            $http
                    ({
                        method: "POST",
                        url: "/pessoa/salvar",
                        data: novaPessoa
                    })

                    .success(function ()
                    {
                        $scope.cadastro = angular.copy(pessoa);
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

        $scope.editarTelefone = function (telefone)
        {
            var novoTelefone = angular.copy($scope.telefone);
            novoTelefone.ddd =  angular.copy(telefone.ddd);
            novoTelefone.numero = angular.copy(telefone.numero);
            novoTelefone.tipoTelefone = angular.copy(telefone.tipoTelefone);

            $http
                ({
                    method: "PUT",
                    url: "/pessoa/editarTelefone",
                    data: novoTelefone
                })

                .success(function ()
                {
                    $scope.buscarTelefones();
                    $scope.mostrarEditarTelefone = false;
                })
        };

        $scope.formatDate = function(date)
        {
            var dateOut = new Date(date);
            return dateOut;
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
        $scope.buscarUsuario();
    });