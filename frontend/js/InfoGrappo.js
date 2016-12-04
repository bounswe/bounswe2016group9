angular.module('InfoGrappoWeb', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('InfoGrappoWeb').controller('HomeCtrl', function($scope, $rootScope, Topics, $window, $document){
  
  $rootScope.user = {name:"Ali Çomar", age:18, city:"Adana/Turkey", email:"alicomar@comarci.com"};

  $scope.sendTopic = function(toTopicID){
    $window.localStorage.setItem("topic",toTopicID);
    console.log($window.localStorage.getItem("topic"));
  };
  Topics.all().then(function(response){
    $scope.topics = response;
    console.log($scope.topics.data);
    // create an array with nodes
    var nodes = [];
    var edges = [];
    for (var i = 0; i<$scope.topics.data.length; i++) {
      nodes.push({
        id :$scope.topics.data[i].entityId,
        label: $scope.topics.data[i].name
      });
      Topics.getRelation($scope.topics.data[i].entityId).then(function(response){
        for (var j = 0; j < response.data.length; j++) {
          edges.push({
              from: response.data[j].fromTopic.entityId,
              to:response.data[j].toTopic.entityId,
              value: response.data[j].voteCount
          });
        }

        // create a network
        var container = document.getElementById('mynetwork');

        // provide the data in the vis format
        var data = {
            nodes: nodes,
            edges: edges
        };
        var options = {
          nodes: {
            font: {
              color: 'white'
            },
            // color: '#51F7F2'
            //color: '#2ADAD5'
            color: '#C2478B'
          }
        };

        // initialize your network!
        var network = new vis.Network(container, data, options);
        //To get click on canvas
        network.on("click", function (params) {
          //If params has only edge(we do not take when click on node)
          if(params.edges.length >= 1 && params.nodes.length <= 0){
            //Search edges which are appear on canvas to find edges come from params
            for(var i=0; i<edges.length; i++){
              if(edges[i].id == params.edges[0]){
                var edge = edges[i];
                break;
              }
            }
            //Find topics which are connected via this edge
            var fromTopic;
            var toTopic;
            $scope.relation = {};
            Topics.getRelation(edge.from).then(function(res){
              for(var i=0; i<res.data.length; i++){
                if(res.data[i].toTopic.entityId == edge.to){
                  console.log("matched");
                  fromTopic = res.data[i].fromTopic;
                  toTopic = res.data[i].toTopic;
                  break;
                }
              }
              //Header of relation part
              $scope.relation.name = fromTopic.name+"--"+toTopic.name;
              //relation types
              $scope.relation.types = [{type : "ali"},{ type : "ayşe"},{ type: "fatma"}];
              console.log($scope.relation.types);
              $document.find('#relation').css('display','block'); 
            });
          }else{
            $document.find('#relation').css('display', 'none');
          }
        });
      });
    }
  });  
});



angular.module('InfoGrappoWeb').controller('TopicGraphCtrl', function($scope, Topics){
  $scope.sendTopic = function(toTopicID){
    Topics.sendTopic(toTopicID);
    console.log(Topics.getTopic());
  };
  Topics.all().then(function(response){
    $scope.topics = response;
    console.log($scope.topics.data);
    // create an array with nodes
    var nodes = [];
    var edges = [];

   

    for (var i = 0; i<$scope.topics.data.length; i++) {
      nodes.push({
        id :$scope.topics.data[i].entityId,
        label: $scope.topics.data[i].name
      });
      Topics.getRelation($scope.topics.data[i].entityId).then(function(response){
        for (var j = 0; j < response.data.length; j++) {
          edges.push({
              from: response.data[j].fromTopic.entityId,
              to:response.data[j].toTopic.entityId,
              value: response.data[j].voteCount
          });
        }

      // create a network
      var container2 = document.getElementById('topicnetwork');
      // provide the data in the vis format
      var data = {
          nodes: nodes,
          edges: edges
      };
      var options = {
        nodes: {
          font: {
            color: 'white'
          },
          // color: '#51F7F2'
          // color: '#2ADAD5'
          color: '#C2478B'
        },
        edges: {
          color: '#29CB51'
        }
      };
      // initialize your network!
      var network = new vis.Network(container2, data, options);
      });
    }
  });  
});
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

  $ctrl.openProfileSettings = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'profileSettings.html',
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

  $ctrl.openLogin = function (size, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'signIn.html',
      controller: 'ModalInstanceCtrl',
      controllerAs: '$ctrl',
      size: "lg",
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

angular.module('InfoGrappoWeb').controller('ModalInstanceCtrl', function ($uibModalInstance, items, $scope, $http) {
  var $ctrl = this;
  $ctrl.items = items;
  $ctrl.selected = $scope.topicName;

  $ctrl.okCreateTopic = function () {
    var parameter = {topic:{"name":$scope.topicName}, tags:[{"name":$scope.topicTags}]};
    console.log(parameter);
    $http.post("http://52.67.44.90:8080/topics", parameter).
      success(function(data, status, headers, config) {
        console.log(data);
      }).
      error(function(data, status, headers, config) {
        console.log("olmadı be !!!");
    });
    $uibModalInstance.close("result");
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
    console.log($scope.postContent);
    console.log($scope.postTags);
    var result = {
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

  $ctrl.okSaveProfile = function () {
    console.log($scope.user);
    var result = {
      user : $scope.user
    };
    $uibModalInstance.close(result);
  }

  $ctrl.okSignIn = function () {
    console.log($scope.signIn);
    var result = {
      signIn : $scope.signIn
    };
    $uibModalInstance.close(result);
  }

  $ctrl.okSignUp = function () {
    console.log($scope.signUp);
    var result = {
      signUp : $scope.signUp
    }
    $uibModalInstance.close(result);
  }

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

angular.module('InfoGrappoWeb').controller('TopicPageCtrl',function($scope, Topics, Posts, Comments, $window){
  Topics.get($window.localStorage.getItem("topic")).then(function(response){
    $scope.topic = response.data;
  });
  var relations = [];
  Topics.getRelation(Topics.getTopic()).then(function(response){
    console.log("response2");
    for (var i = 0; i < response.data.length; i++) {
      relations.push(response.data[i].toTopic);
    }
    $scope.relations=relations;
  })
  $scope.go = function(topicID){
    Topics.get(topicID).then(function(response){
      $scope.topic = response.data;
    });
    var relations = [];
    Topics.getRelation(topicID).then(function(response){
      console.log("response2");
      for (var i = 0; i < response.data.length; i++) {
        relations.push(response.data[i].toTopic);
      }
      $scope.relations=relations;
    })
  };
  $scope.posts=Posts.all();
  $scope.comments=Comments.all();
});


angular.module('InfoGrappoWeb').factory('Topics', function($http) {
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
      return $http({
        method: 'GET',
        url: "http://52.67.44.90:8080/topics/"+topicID+"/relationsFrom"
      }).then(function successCallback(data) {
        return data;
      }, function errorCallback(data) {
        console.log("Lanet olasıca backend çalışmıyor!!!!");
        return null;
      });
    }
  };
});

angular.module('InfoGrappoWeb').factory('Posts', function(){

  var posts = [{
    postID:0,
    postLikes:45,
    postHeader:"header",
    postContent: "Post 1 gönderilen ilk post burada",
    postTags: "post.tag"
  },{
    postID:1,
    postLikes:117,
    postHeader:"header",
    postContent: "Post2 buralar dolu",
    postTags: "post2.tag"
  },{
    postID:2,
    postLikes:22,
    postHeader:"header",
    postContent: "Post 3 buralar dolu asds buralar dolu asds buralar dolu asdsburalar dolu asds buralar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asds",
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

angular.module('InfoGrappoWeb').factory('Comments', function(){

  var comment = [{
    commentID:0,
    commentLikes:45,
    commentContent: "Comment 1",
    commentTags: "post.tag"
  },{
    commentID:1,
    commentLikes:117,
    commentContent: "Comment 2 buralar dolu",
    commentTags: "post2.tag"
  },{
    commentID:2,
    commentLikes:22,
    commentContent: " Comment 3 buralar dolu asds buralar dolu asds buralar dolu asdsburalar dolu asds buralar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asdsburalar dolu asds",
    commentTags: "post2.tag"
  }];

  return {
    all: function() {
      return comment;
    },
    remove: function(comment) {
      comment.splice(comment.indexOf(comment), 1);
    },
    get: function(commentID) {
      for (var i = 0; i < comment.length; i++) {
        if (comment[i].id === parseInt(commentID)) {
          return comment[i];
        }
      }
      return null;
    }
  };
});