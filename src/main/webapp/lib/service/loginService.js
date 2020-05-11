
/**
 * Arquivo service Login em angular.
 * @author Samael Pereira Simões
 * Data 09/05/2020
 */

app.factory('LoginService', function($resource) {
    return $resource('/project-control/LoginServlet', null, {
        query:{
            method: 'GET'
        }
    });
});