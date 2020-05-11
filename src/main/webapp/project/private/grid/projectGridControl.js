/**
 * Arquivo Controller em angular.
 * @author Samael Pereira Simões
 */

app.controller('ProjectGridControl', ['$scope', '$http', '$log','$rootScope', '$location', 'toastr', 'ProjectService', '$timeout',
    function($scope, $rootScope, $location, $http, $log, toastr, ProjectService, timeout, ) {

    $scope.project = []; // Model da lista de gêneros
    //* --- Server --- *
    $scope.load = function() { // load all
        ProjectService.query(function( costcenters ) {

            $scope.project = project;
            //$scope.table.data = project;
        }, function( error ) {
            toastr.error( error );
        });
    };
    //-- end load

    $scope.addProject = function(ce) {
        debugger;
        if ( ce == undefined || ce == "" ) {
            toastr.warning("Gentileza preencher os campos obrigatório");
        }else if( ce != undefined || ce != "" ){
            LoginService.save(ce , function(response) {

                toastr.success("Cadastrado com sucesso!");
                $scope.load();
                $scope.project = null;
            }, function(err){
                toastr.error( 'Erro ' + err.data);
            })
        }
    }
}]);