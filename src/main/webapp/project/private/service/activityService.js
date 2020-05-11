/**
 * Arquivo Service Centro de custo  em angular.
 * @author Samael Pereira Simões
 */

app.factory('ActivityService', function($resource) {
    return $resource('/project-control/rest/activity/', null, {
        query:{
            method: 'POST'
        }
    });
});