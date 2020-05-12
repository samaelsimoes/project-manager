app.factory('ActivityServiceGeneric', function($resource) {
    return $resource('/project-control/rest/activity/', null, {
        update: {
            method: 'PUT'
        },
        remove: {
            method: 'DELETE'
        }
    });
});