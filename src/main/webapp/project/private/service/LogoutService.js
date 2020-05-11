/**
 * Arquivo service Logout em angular.
 * @author Samael Pereira Simões
 */
app.factory('LogoutService', function($resource) {
    return $resource('/project-control/LogoutServlet', null, {
        method: 'GET'
    });
});