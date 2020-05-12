var app = angular.module('appHome',['ngMask', 'ngAnimate', 'ngTouch', 'ui.grid.selection', 'ui.grid.exporter', 'ui.grid', 'ui.grid.pagination', 'ui.grid.cellNav', 'ui.grid.expandable',
    'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.saveState', 'ui.grid.resizeColumns', 'ui.grid.pinning', 'ui.grid.moveColumns', 'ui.utils.masks',
    'ui.grid.infiniteScroll', 'ui.grid.importer', 'ui.grid.grouping', 'toastr', 'ngResource',
    'ngRoute', 'ngTouch']);

app.pathRest = 'rest';
app.config(function($routeProvider, $locationProvider){

    // remove o # da url
    $locationProvider.html5Mode({
        enabled: false,
        requireBase: false
    });

    $routeProvider

    .when('#!/home', {
        templateUrl: '/home.html/',
    })
    .when('/projectGrid', {
        templateUrl: 'grid/projectGrid/projectGrid.html',
        controller: 'ProjectGridControl',
    })
    .when('/activityGrid', {
        templateUrl: 'grid/activityGrid/activityGrid.html',
        controller: 'ActivityControl',
    })
    .when('/logout', {
        templateUrl: '../../../',
        controller: 'LogoutController',
    })
    .otherwise ({
        redirectTo: '/home'
    });
});



