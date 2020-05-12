
app.controller('ActivityControl', [ '$scope', '$http', '$log','$rootScope', '$location', 'toastr', 'ActivityService', '$timeout', 'ActivityServiceGeneric', 'ProjectServiceGeneric',
function( $scope, $rootScope, $location, $http, $log, toastr, ActivityService, timeout, ActivityServiceGeneric, ProjectServiceGeneric ) {

    $scope.activity = []; // Model da lista de gêneros
    // obs eu poderia criar _self para o lugar do scope, não é o momento de alterar pois todo projeto usei scope mesmo

    $scope.formateDateTable = function(date) {
        let data = moment(date, "YYYY-MM-DD HH:mm:ss");
        return data.format("DD/MM/YYYY");
    }

    $scope.listTableActivity = function() {
        //List activity, createtable
        let arrayTable =[],
            tableActivity = "";

        ActivityServiceGeneric.query(function( activity ) {
            debugger;
            for (let i in activity) {
                if (activity[i].id != undefined ) {
                    tableActivity = {
                        id_activity: activity[i].id,
                        name_activity: activity[i].nameActivity,
                        date_initial: $scope.formateDateTable(activity[i].dateInitial),
                        date_end: $scope.formateDateTable(activity[i].dateFinal)
                    }
                    arrayTable.push(tableActivity);
                }
            }
            $scope.tableActivitys = arrayTable;
        }, function( error ) {
            toastr.error( error );
        });
    }

    //* --- Server --- *
    $scope.loadActivity = function() { // load all
        let objProduct = "",
            arrayTagSelected = [];

        // List project, field projects selected
       ProjectServiceGeneric.query(function( project ) {
            debugger;
           for (let i in project) {
               if (project[i].id != undefined ) {
                   objProduct = {
                       id: project[i].id,
                       label: project[i].nameProject,
                       subItem: {
                           name: project[i].nameProject
                       }
                   }
                   arrayTagSelected.push(objProduct);
               }
           }
           $scope.projects = arrayTagSelected;
           $scope.listTableActivity();

        }, function( error ) {
            toastr.error( error );
        });
    };
    //-- end load

    $scope.formateDateBackEnd = function(date) {
        let data = moment(date, "DD/MM/YYYY HH:mm:ss");
        return data.format("YYYY-MM-DD HH:mm:ss");
    }

    $scope.addActivity = function(e) {
        let activity = {
            dateInitial: $scope.formateDateBackEnd(e.dateInitial + " " + "00:00:00"),
            dateFinal:  $scope.formateDateBackEnd(e.finalDate + " " + "00:00:00"),
            nameActivity: e.description,
            type: 0,
            finished: false,
            idProject: e.project.id
        }

        if (activity == undefined) {
            toastr.warning("Please fill in the required fields");
        } else {
            ActivityService.save(activity , function(response) {
                toastr.success("Registered successfully!");
                $scope.loadActivity();
            }, function(err){
                toastr.error( 'Erro ' + err.data);
            })
        }
    }
    $scope.loadActivity();
}]);