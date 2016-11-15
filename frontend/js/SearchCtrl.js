/*
This controller is created in order to handle search options
 */

angular.module('ui.bootstrap.demo', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('SearchCtrl', function ($scope, $log) {

    $scope.dataset = [
        'Apple',
        'Banana',
        'Pink',
        'Donald Trump',
        'Hillary Clinton',
        'USA Elections',
        'Walter White',
        'InfoGrappo',
        'Boğaziçi University'
    ];

    $scope.search = function () {
        var x = $scope.searchText;
        console.log("Search: " + x);
    };

    $scope.get = function (searchText) {
        // TODO
    }

});