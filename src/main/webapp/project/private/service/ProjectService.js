/**
 * Arquivo Service Centro de custo  em angular.
 * @author Samael Pereira Simões
 */

app.factory('ProjectService', function($resource) {
    return $resource('/project-control/rest/product/', null, {
        update: {
            method: 'PUT'
        },
        remove: {
            method: 'DELETE'
        }
    });
});