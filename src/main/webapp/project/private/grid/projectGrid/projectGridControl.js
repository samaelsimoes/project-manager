/**
 * Arquivo Controller em angular.
 * @author Samael Pereira Simões
 */

app.controller('ProjectGridControl', ['$scope', '$http', '$log','$rootScope', '$location', 'toastr', 'ProjectService', '$timeout', 'ProjectServiceGeneric',
    function($scope, $rootScope, $location, $http, $log, toastr, ProjectService, timeout, ProjectServiceGeneric) {

    $scope.project = []; // Model da lista de gêneros

    $scope.formateDateTable = function(date) {
        let data = moment(date, "YYYY-MM-DD HH:mm:ss");
        return data.format("DD/MM/YYYY");
    }

    //* --- Server --- *
    $scope.projects = function() { // load all projects
        ProjectServiceGeneric.query(function( product ) {
            debugger;
            let objTable = "",
                arrayTable = [];
            for (let i in product) {
                if (product[i].id != undefined) {

                    objTable = {
                        id_product: product[i].id,
                        name_project: product[i].nameProject,
                        date_initial: $scope.formateDateTable(product[i].dateInitial),
                        date_end: $scope.formateDateTable(product[i].dateFinal)
                    }
                    arrayTable.push(objTable);
                }
            }
            $scope.tableProjects = arrayTable;
        }, function( error ) {
            toastr.error( error );
        });
    };

    //-- end load
    $scope.formateDate = function(date) {
        let data = moment(date, "DD/MM/YYYY HH:mm:ss");
        return data.format("YYYY-MM-DD HH:mm:ss");
    }
    $scope.addProject = function(e) {
        let project = {
            dateInitial: $scope.formateDate(e.initialDate + " " + "00:00:00"),
            dateFinal:  $scope.formateDate(e.dateFinal + " " + "00:00:00"),
            nameProject: e.descriptionProject,
            type: 0
        }
        if (project == undefined || project == "") {
            toastr.warning("Please fill in the required fields");
        }else if (project != undefined) {
            ProjectService.save(project , function(response) {
                toastr.success("Registered successfully!");
                $scope.projects();
            }, function(err){
                toastr.error( 'Erro ' + err.data);
            });
        }
    }
    $scope.projects();
}]);