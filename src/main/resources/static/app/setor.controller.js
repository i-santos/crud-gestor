(function () {
    'use strict';

    angular
            .module('app')
            .controller('SetorController', SetorController);

    SetorController.$inject = ['$http'];

    function SetorController($http) {
        var vm = this;
        vm.setores = [];

        vm.getAll = getAll;
        vm.get = get;

        init();

        function init() {
            getAll();
        }

        function getAll() {
            var url = "/api/setor";

            return $http.get(url).then(response => {
                vm.setores = response.data;
                return vm.setores;
            });
        }
        
        function get(id) {
            return vm.setores.find(s => s.id == id);
        }

    }
})();
