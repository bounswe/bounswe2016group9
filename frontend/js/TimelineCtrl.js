/**
 * Created by serpilkuzu on 20.12.2016.
 */
angular.module('InfoGrappoWeb').controller('TimelineCtrl', function($scope, $window, Timeline){

    $scope.timeline = {};
    $scope.showTimeline = true;

    Timeline.postsOfTopics().then(function(response) {
        $scope.timeline.topicPosts = response.data;
    });
    Timeline.postsOfUsers().then(function(response) {
        $scope.timeline.posts = response.data;
    });
    Timeline.topicOfUsers().then(function(response) {
        $scope.timeline.topics = response.data;
    });
    Timeline.commentOfUsers().then(function(response) {
        $scope.timeline.comments = response.data;
    });

    // If user does not have anything in timeline, show a message
    if ( $scope.timeline.topicPosts == undefined && $scope.timeline.posts == undefined && $scope.timeline.topics == undefined && $scope.timeline.comments == undefined){
        $scope.showTimeline = false;
        $scope.timeline.message = "Nothing to show in timeline";
    }
});