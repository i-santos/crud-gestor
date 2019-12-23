(function () {
    'use strict';

    angular
            .module('app')
            .controller('GestorController', GestorController);

    GestorController.$inject = ['$http', '$scope'];

    function GestorController($http, $scope) {
        var url = "/api/gestor";
        var vm = this;

        init();

        function init() {
            vm.gestores = [];

            vm.getAll = getAll;
            vm.add = add;
            vm.remove = remove;
            vm.removeAll = removeAll;
            vm.selectGestor = selectGestor;
            vm.toggleGestor = toggleGestor;
            vm.toggleAll = toggleAll;
            vm.cancelEdit = cancelEdit;
            vm.update = update;

            $scope.selectedGestores = [];

            getAll();
        }

        function getAll() {
            return $http.get(url).then(response => {
                vm.gestores = response.data;
                return vm.gestores;
            });
        }

        function add() {
            return $http.post(url, $scope.gestor).then(response => {
                if (response.data && response.data.id) {
                    vm.gestores.push(response.data);
                    $scope.gestor = {};
                    $('#addEmployeeModal').modal('hide');
                }
            });
        }

        function remove() {
            var gestor = $scope.selectedGestor;
            return $http.delete(url + '/' + gestor.id).then(response => {
                if (response.data) {
                    var i = vm.gestores.findIndex(g => g.id === gestor.id);
                    vm.gestores.splice(i, 1);
                    $('#deleteEmployeeModal').modal('hide');
                }
            }
            );
        }

        function removeAll() {
            return $http.post(url + "/delete", vm.gestores.filter(g => g.active)).then(response => {
                if (response.data) {
                    vm.gestores = vm.gestores.filter(g => !g.active);
                    $('#deleteAllEmployeeModal').modal('hide');
                }
            });
        }

        function update() {
            return $http.put(url + '/' + $scope.selectedGestor.id, $scope.selectedGestor).then(response => {
                if (response.data) {
                    Object.assign($scope.selectedGestorRef, $scope.selectedGestor);
                    $('#editEmployeeModal').modal('hide');
                }
            });
        }

        function selectGestor(g) {
            $scope.selectedGestorRef = g;
            $scope.selectedGestor = {};
            Object.assign($scope.selectedGestor, g);
            if ( ! (g.dataNascimento instanceof Date) ) {
                $scope.selectedGestor.dataNascimento = new Date(g.dataNascimento.replace(/-/g, '\/'));
            }
        }

        function toggleGestor() {
            $scope.activeAll = vm.gestores.every(g => g.active);
        }
        function toggleAll(active) {
            if (active) {
                vm.gestores.forEach(g => {
                    g.active = true;
                });
            } else {
                vm.gestores.forEach(g => {
                    g.active = false;
                });
            }
        }

        function cancelEdit() {
            $scope.selectedGestor = {};
        }

    }
})();
