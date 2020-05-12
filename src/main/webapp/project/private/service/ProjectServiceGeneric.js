/**
 * Arquivo Service Centro de custo  em angular.
 * @author Samael Pereira Simões
 */

app.factory('ProjectServiceGeneric', function($resource) {
    return $resource('/project-control/rest/project/', null, {
        update: {
            method: 'PUT'
        },
        remove: {
            method: 'DELETE'
        }
    });
});