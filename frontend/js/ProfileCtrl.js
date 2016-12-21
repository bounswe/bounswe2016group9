/**
 * Created by serpilkuzu on 21.12.2016.
 */
angular.module('InfoGrappoWeb').controller('ProfileCtrl', function($scope, $window, User){

    $scope.profile = {};
    $scope.userId = $window.localStorage.getItem("user");

    User.get($scope.userId).then(function (response) {
        $scope.profile.data = response;
    });

});