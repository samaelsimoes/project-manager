
app.controller('ActivityControl', [ '$scope', '$http', '$log','$rootScope', '$location', 'toastr', 'ActivityService', '$timeout',
    function( $scope, $rootScope, $location, $http, $log, toastr, ActivityService, timeout ) {

        $scope.activity = []; // Model da lista de gêneros
        // obs eu poderia criar _self para o lugar do scope, não é o momento de alterar pois todo projeto usei scope mesmo

        //* --- Server --- *
        $scope.load = function() { // load all
            ActivityService.query(function( activity ) {

                $scope.activity = activity;
                //$scope.table.data = project;
            }, function( error ) {
                toastr.error( error );
            });
        };
        //-- end load

        $scope.formateDate = function(date) {
            let data = moment(date, "DD/MM/YYYY HH:mm:ss");
            return data.format("YYYY-MM-DD HH:mm:ss");
        }

        $scope.addActivity = function(e) {
            let activity = {
                dateInitial: $scope.formateDate(e.dateInitial + " " + "00:00:00"),
                dateFinal:  $scope.formateDate(e.finalDate + " " + "00:00:00"),
                description: e.description
            }

            if (activity == undefined) {
                toastr.warning("Gentileza preencher os campos obrigatório");
            } else {
                ActivityService.save(activity , function(response) {

                    toastr.success("Cadastrado com sucesso!");
                    debugger;
                    $scope.load();
                    $scope.activity = null;
                }, function(err){
                    toastr.error( 'Erro ' + err.data);
                })
            }
        }
    }]);