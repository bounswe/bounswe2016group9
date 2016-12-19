/**
 * Created by serpilkuzu on 17.12.2016.
 */
angular.module('InfoGrappoWeb').controller('RelationCtrl', function($scope, $window, Topics){

    // get first topic
    Topics.get($window.localStorage.getItem("topic1")).then(function(response) {
        $scope.firstTopic = response.data;

        // get second topic
        Topics.get($window.localStorage.getItem("topic2")).then(function (response) {
            $scope.secondTopic = response.data;

            // set relation page title
            $scope.relationTitle = $scope.firstTopic.name + " - " + $scope.secondTopic.name;

            // get relations of current topic
            var relations = [];
            Relations.getRelation($scope.firstTopic.entityId).then(function(response){
                console.log("response2");
                for (var i = 0; i < response.length; i++) {
                    var pushArrayFrom = true;
                    var pushArrayTo = true;
                    for(var j = 0; j < relations.length; j++){
                        if(response[i].fromTopic.entityId === relations[j].entityId){
                            pushArrayFrom = false;
                        }else if(response[i].toTopic.entityId === relations[j].entityId){
                            pushArrayTo = false;
                        }
                    }
                    if(pushArrayFrom){
                        if(response[i].fromTopic.entityId != Topics.getTopic()){
                            relations.push(response[i].fromTopic);
                        }
                    }
                    if(pushArrayTo){
                        if(response[i].toTopic.entityId != Topics.getTopic()){
                            relations.push(response[i].toTopic);
                        }
                    }
                }
                $scope.relations=relations;
            });
        });
    });
});