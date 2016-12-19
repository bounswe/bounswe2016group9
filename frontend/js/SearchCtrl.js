/**
 * Created by serpilkuzu on 17.12.2016.
 */
angular.module('InfoGrappoWeb').controller('SearchCtrl', function ($scope, $log) {

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