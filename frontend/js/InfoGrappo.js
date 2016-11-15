angular.module('InfoGrappoWeb', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('InfoGrappoWeb').controller('ModalDemoCtrl', function ($uibModal, $log, $document, $scope) {
  var $ctrl = this;
  $ctrl.items = ['item1', 'item2', 'item3'];

  $ctrl.animationsEnabled = true;

  $ctrl.openCreateTopic = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'createTopic.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: '$ctrl',
      size: size,
      appendTo: parentElem,
      resolve: {
        items: function () {
          return $ctrl.items;
        }
      }
    });

    modalInstance.result.then(function (result) {
          console.log(result);
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $ctrl.openAddRelation = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'addRelation.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: '$ctrl',
      size: size,
      appendTo: parentElem,
      resolve: {
        items: function () {
          return $ctrl.items;
        }
      }
    });

    modalInstance.result.then(function (result) {
      console.log(result);
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $ctrl.openCreatePost = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'createPost.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: '$ctrl',
      size: size,
      appendTo: parentElem,
      resolve: {
        items: function () {
          return $ctrl.items;
        }
      }
    });

    modalInstance.result.then(function (result) {
      console.log(result);
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $ctrl.openCreateComment = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'createComment.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: '$ctrl',
      size: size,
      appendTo: parentElem,
      resolve: {
        items: function () {
          return $ctrl.items;
        }
      }
    });

    modalInstance.result.then(function (result) {
      console.log(result);
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $ctrl.toggleAnimation = function () {
    $ctrl.animationsEnabled = !$ctrl.animationsEnabled;
  };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

angular.module('InfoGrappoWeb').controller('ModalInstanceCtrl', function ($uibModalInstance, items, $scope) {
  var $ctrl = this;
  $ctrl.items = items;
  $ctrl.selected = $scope.topicName;

  $ctrl.okCreateTopic = function () {
    console.log($scope.topicName);
    console.log($scope.topicTags);
    var result = {
      topicName:$scope.topicName,
      topicTags:$scope.topicTags
    };
    $uibModalInstance.close(result);
  };

  $ctrl.okAddRelation = function () {
    console.log($scope.topicName1);
    console.log($scope.topicName2);
    console.log($scope.relationName);
    var result = {
      topicName1:$scope.topicName1,
      topicName2:$scope.topicName2,
      relationName:$scope.relationName
    };
    $uibModalInstance.close(result);
  };

  $ctrl.okCreatePost = function () {
    console.log($scope.postHeader);
    console.log($scope.postContent);
    console.log($scope.postTags);
    var result = {
      postHeader:$scope.postHeader,
      postContent:$scope.postContent,
      postTags:$scope.postTags
    };
    $uibModalInstance.close(result);
  };

  $ctrl.okCreateComment = function () {
    console.log($scope.comment);
    var result = {
      comment:$scope.comment
    };
    $uibModalInstance.close(result);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});

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

angular.module('InfoGrappoWeb').controller('TopicPageCtrl',function($scope, Topics, Posts){
  $scope.topic = Topics.get(0);
  $scope.posts=Posts.all();
  console.log($scope.posts);
});

angular.module('InfoGrappoWeb').factory('Topics', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var topics = [{
    id: 0,
    name: 'Amerika',
    posts: "0 2"
  }, {
    id: 1,
    name: 'Max Lynx'
  }, {
    id: 2,
    name: 'Adam Bradleyson'
  }, {
    id: 3,
    name: 'Perry Governor'
  }, {
    id: 4,
    name: 'Mike Harrington'
  }];

  return {
    all: function() {
      return topics;
    },
    remove: function(topic) {
      topics.splice(topics.indexOf(topic), 1);
    },
    get: function(topicID) {
      for (var i = 0; i < topics.length; i++) {
        if (topics[i].id === parseInt(topicID)) {
          return topics[i];
        }
      }
      return null;
    }
  };
});

angular.module('InfoGrappoWeb').factory('Posts', function(){

  var posts = [{
    postID:0,
    postHeader:"header",
    postContent: "Lorem lorem lorem",
    postTags: "post.tag"
  },{
    postID:1,
    postHeader:"header",
    postContent: "Post2 buralar dolu",
    postTags: "post2.tag"
  },{
    postID:2,
    postHeader:"header",
    postContent: "buralar dolu asds buralar dolu asds buralar dolu asdsburalar dolu asds buralar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asds",
    postTags: "post2.tag"
  }];

  return {
    all: function() {
      return posts;
    },
    remove: function(post) {
      posts.splice(posts.indexOf(post), 1);
    },
    get: function(postID) {
      for (var i = 0; i < posts.length; i++) {
        if (posts[i].id === parseInt(postID)) {
          return posts[i];
        }
      }
      return null;
    }
  };
});