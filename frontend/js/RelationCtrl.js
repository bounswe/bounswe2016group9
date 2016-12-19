/**
 * Created by serpilkuzu on 17.12.2016.
 */
angular.module('InfoGrappoWeb').controller('RelationCtrl', function($scope, $window, Topics){
    // get current topic id
    Topics.get($window.localStorage.getItem("topic")).then(function(response){
        $scope.topic = response.data;
        $scope.secondId = 5;

        // get second topic
        Topics.get($scope.secondId).then(function (response) {
           $scope.secondTopic = response.data;
        });

        // get relations of current topic
        var relations = [];
        Topics.getRelation($scope.topic.entityId).then(function(response){
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


angular.module('InfoGrappoWeb').factory('Relations', ['$http', '$q', function($http, $q) {
    // Might use a resource here that returns a JSON array
    var toTopic = 1;
    return {
        all: function() {
            return $http({
                method: 'GET',
                url: "http://52.67.44.90:8080/topics"
            }).then(function successCallback(data) {
                console.log(data);
                return data;
            }, function errorCallback(data) {
                console.log("Lanet olasıca backend çalışmıyor!!!!");
                return null;
            });
        },
        remove: function(topic) {
            topics.splice(topics.indexOf(topic), 1);
        },
        sendTopic:function(toTopic2) {
            toTopic = toTopic2;
        },
        getTopic:function(){
            return toTopic;
        },
        get: function(topicID) {
            return $http({
                method: 'GET',
                url: "http://52.67.44.90:8080/topics/"+topicID
            }).then(function successCallback(data) {
                console.log(data);
                return data;
            }, function errorCallback(data) {
                console.log("Lanet olasıca backend çalışmıyor!!!!");
                return null;
            });
        },
        getRelation: function(topicID) {
            var deferred = $q.defer();
            var getTo = $http({
                method: 'GET',
                url: "http://52.67.44.90:8080/topics/"+topicID+"/relationsTo"
            }).then(function successCallback(data){
                return data.data;
            }, function errorCallback(data){
                console.log(data);
                return null;
            });
            var getFrom = $http({
                method: 'GET',
                url: "http://52.67.44.90:8080/topics/"+topicID+"/relationsFrom"
            }).then(function successCallback(data) {
                return data.data;
            }, function errorCallback(data) {
                console.log(data);
                return null;
            });
            $q.all([getFrom, getTo]).then(function(data){
                data[3] = [];
                if (Array.isArray(data[0])){
                    for (var i=0; i<data[0].length; i++){
                        data[3].push(data[0][i])
                    }
                }
                if(Array.isArray(data[1])){
                    for (var i=0; i<data[1].length; i++){
                        data[3].push(data[1][i]);
                    }
                }
                if(Array.isArray(data[0]) || Array.isArray(data[1])){
                    deferred.resolve(data[3]);
                }else{
                    deferred.reject("not relations");
                }
            }, function(data){
                deferred.reject("not relation");
            });
            return deferred.promise;
        },
        getPosts : function(topicId){
            var deferred = $q.defer();
            $http({
                url : "http://52.67.44.90:8080/topics/"+topicId+"/posts",
                method : "GET"
            }).then(function successCallback(data){
                deferred.resolve(data.data);
            }, function errorCallback(data){
                deferred.reject("not posts");
            });
            return deferred.promise;
        }
    };
}]);