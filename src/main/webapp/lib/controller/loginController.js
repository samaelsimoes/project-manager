/**
 * Arquivo Login em angular.
 * @author Samael Pereira Simões
 */

app.controller('LoginController', [ '$scope', '$rootScope', '$location', 'toastr', 'LoginService',
    function( $scope,  $rootScope,  $location, toastr, LoginService ) {

        $rootScope.activetab = $location.path();
        $scope.load = false;
        $scope.auth = function( user ) {

            if ( user == undefined) {
                toastr.warning("Login ou senha invalido");
            }else if (user != undefined ) {
                $scope.load = true;
                LoginService.query(user, function(success){
                    toastr.success(success.msg);

                    console.log(success);
                    console.log(success.sessao);

                    window.setTimeout(function() {
                        $('#search').addClass('custom-search');
                        $('#modal').modal('close');
                    }, 2000);
                    $scope.load = false;
                    window.setTimeout(function() {
                        clearInterval(window.setInterval(function() {	}, 50));
                        //alert("deu certo");
                        $(location).attr('href', '/project-control/project/private/home.html#!/productgrid');
                    }, 2000);
                }, function(err){
                    toastr.warning(err.data.msg);
                    window.setTimeout(function() {
                        $('#modal').modal('close');
                    }, 2000);
                })
            }
        }
    }]);

