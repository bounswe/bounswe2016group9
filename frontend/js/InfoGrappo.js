var appData = {
  baseUrl : "http://52.67.44.90:8080/"
};
//change ngTagsInput eklendi
angular.module('InfoGrappoWeb', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngTagsInput']);
//change
angular.module('InfoGrappoWeb').config(function($httpProvider){
  //allows 401 cors error
  $httpProvider.defaults.withCredentials = true;
});
angular.module('InfoGrappoWeb').controller('GrappiCtrl', function ($scope, Topics, $window, $document, User) {
  $scope.sendTopic = function(toTopicID){
      $window.localStorage.setItem("topic",toTopicID);
      console.log($window.localStorage.getItem("topic"));
  };

  $scope.search = function (searchInput) {
    if (searchInput.entityId != undefined) {
      $window.localStorage.setItem("topic", searchInput.entityId);
      $window.location = "topic.html";
    } else {
      $window.localStorage.setItem("searchText", searchInput);
      console.log("Search: " + searchInput);
      $window.location = "search.html";
    }
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
              color: '#C2478B'
            }
          };

          // initialize your network!
          var network = new vis.Network(container, data, options);
          //To get click on canvas
          network.on("click", function (params) {
            //when click on node(topic)
            if(params.nodes.length >= 1){
              //node -> $scope.node
              //click same node
              if(params.nodes[0] == $scope.node){
                $window.localStorage.setItem("topic", $scope.node);
                $window.location = "topic.html";
                //click different node
              }else if(params.nodes[0] != $scope.node){
                $scope.node = params.nodes[0];
                $scope.relatedNodes = {nodes:[], edges:[]};
                Topics.getRelation($scope.node).then(function(response){
                  if(response[0].fromTopic.entityId == $scope.node){
                    $scope.relatedNodes.nodes.push({
                      id:response[0].fromTopic.entityId,
                      label:response[0].fromTopic.name
                    });
                  }else{
                    $scope.relatedNodes.nodes.push({
                      id:response[0].toTopic.entityId,
                      label:response[0].toTopic.name
                    });
                  }
                  for (var i = 0; i < response.length; i++) {
                    if(response[i].toTopic.entityId != $scope.node){
                      $scope.relatedNodes.nodes.push({
                        id:response[i].toTopic.entityId,
                        label:response[i].toTopic.name
                      })
                    }else if(response[i].fromTopic.entityId != $scope.node){
                      $scope.relatedNodes.nodes.push({
                        id:response[i].fromTopic.entityId,
                        label:response[i].fromTopic.name
                      })
                    }
                    $scope.relatedNodes.edges.push({
                      from: response[i].fromTopic.entityId,
                      to:response[i].toTopic.entityId,
                      value: response[i].voteCount,
                      arrows : "to",
                      length : 250
                    });
                    network.setData($scope.relatedNodes);
                  }
                }, function(error){
                  // if dont have edge
                  Topics.get(params.nodes[0]).then(function(response){
                    $scope.relatedNodes.nodes.push({
                      id : params.nodes[0],
                      label : response.data.name
                    });
                    network.setData($scope.relatedNodes);
                  });
                });
              }
            }
            //If params has only edge(we do not take when click on node)
            else if(params.edges.length >= 1 && params.nodes.length <= 0){
              //Search edges which are appear on canvas to find edges come from params
              for(var i=0; i<$scope.relatedNodes.edges.length; i++){
                if($scope.relatedNodes.edges[i].id == params.edges[0]){
                  var edge = $scope.relatedNodes.edges[i];
                }
              }
              //Find topics which are connected via this edge
              $scope.relation = {};
              Topics.getRelation(edge.from).then(function(res){
                for(var i=0; i<res.length; i++){
                  if(res[i].toTopic.entityId == edge.to){
                    console.log("matched");
                    $scope.fromTopic = res[i].fromTopic;
                    $scope.toTopic = res[i].toTopic;
                    break;
                  }
                }
                //Header of relation part
                $scope.relation.name = $scope.fromTopic.name+"--"+$scope.toTopic.name;
                //relation types
                $scope.relation.types = [{type : "ali"},{ type : "ayşe"},{ type: "fatma"}];
                console.log($scope.relation.types);
                $document.find('#relation').css('display','block');
              });
            }else{
              //change
              // when click on somewhere in map other than edges or nodes
              if($document.find('#relation').css('display') == 'block'){
                $document.find('#relation').css('display', 'none');
              }else{
                //node and edges reset
                $scope.relatedNodes = {nodes:[], edges:[]};
                network.setData({
                  nodes: nodes,
                  edges: []
                });
              }
              //change end
            }
          });
        }
      });
});
angular.module('InfoGrappoWeb').controller('HomeCtrl', function($scope, Topics, $window, $document, User, Autocomplete){
  if($window.localStorage.getItem('user') != undefined){
    $scope.showHome = true;
  } else {
    $scope.showHome = false;
  }

  // get created topics
  $scope.getTopics = function(keyword){
    return Autocomplete.topics(keyword).then(function(response){
      return response;
    });
  };

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
angular.module('InfoGrappoWeb').controller('ModalDemoCtrl', function ($uibModal, $log, $document, $scope, $window, Follow) {
  var $ctrl = this;
  $ctrl.items = ['item1', 'item2', 'item3'];

  $scope.getMoreRelations = function(){
    var topic1 = $scope.fromTopic.entityId;
    var topic2 = $scope.toTopic.entityId;
    $window.localStorage.setItem("topic1", topic1);
    $window.localStorage.setItem("topic2", topic2);
    console.log("Get relations between: " + $window.localStorage.getItem("topic1") + " and " + $window.localStorage.getItem("topic2"));
    $window.location = "moreRelations.html";
  };

  $ctrl.animationsEnabled = true;

  $ctrl.followTopic = function () {
    var topicId = $window.localStorage.getItem("topic");
    Follow.followTopic(topicId).then(function () {
      $ctrl.openFollowOk();
    }, function () {
      $log.info('Error on following topic  ' + new Date());
      $ctrl.openFollowTopicError();
    });
  };

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

  $ctrl.createComment = function(post){
    $scope.postId = post.entityId;
    $ctrl.openCreateComment();
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

  $ctrl.openFollowOk = function (size, parentSelector) {
    var parentElem = parentSelector ?
        angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'followTopicOk.html',
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

  $ctrl.openFollowTopicError = function (size, parentSelector) {
    var parentElem = parentSelector ?
        angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'followTopicError.html',
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

angular.module('InfoGrappoWeb').controller('ModalInstanceCtrl', function ($uibModalInstance, items, $scope, $http , User, $window, Autocomplete, Relations, Posts, Comments) {
  var $ctrl = this;
  $ctrl.items = items;
  $ctrl.selected = $scope.topicName;
  //changes Autocomplete i yukarı ekle
  $scope.tags = [];
  $ctrl.findTags = function(topicName){
    Autocomplete.tags(topicName).then(function(response){
      $scope.tags = response;
    });
  };
  //changes end
  $ctrl.okCreateTopic = function () {
    var tags = [];
    angular.forEach($scope.topicTags, function (topic) {
      tags.push({
        "name" : topic.text
      })
    });
    var parameter = {topic:{"name":$scope.topicName}, tags: tags, label:$scope.topicTags[0].text};
    console.log(parameter);
    $http.post(appData.baseUrl+"topics", parameter).
      success(function(data, status, headers, config) {
        console.log(data);
      }).
      error(function(data, status, headers, config) {
        console.log("olmadı be !!!");
    });
    $uibModalInstance.close("result");
  };

  // get created topics
  $ctrl.getTopics = function(keyword){
    return Autocomplete.topics(keyword).then(function(response){
      return response;
    });
  };

  // get relation types
  $ctrl.getRelations = function(keyword){
    return Autocomplete.relation(keyword).then(function(response){
      return response;
    });
  };

  $ctrl.okAddRelation = function () {
    console.log($scope.fromTopic);
    console.log($scope.toTopic);
    console.log($scope.relationName);
    var userId=$window.localStorage.getItem("user");
    User.get(userId).then(function (response) {
      $scope.user = response;
    }, function () {
      console.$log= "Error occurred when getting user information";
    });
    var requestParams = {
      fromTopic: {
        entityId : $scope.fromTopic.entityId
      },
      toTopic: {
        entityId: $scope.toTopic.entityId
      },
      relationType: {
        entityId: $scope.relationType.entityId
      },
      createdUser: {
        entityId: $scope.user.entityId
      }
    };
    Relations.add(requestParams);
    $window.location.reload();
    $uibModalInstance.close();
  };
  //changes end

  $ctrl.okCreatePost = function () {
    console.log($scope.postContent);
    console.log($scope.postTags);

    var userId = $window.localStorage.getItem("user");
    var topicId = $window.localStorage.getItem("topic");
    var requestParams = {
      content: $scope.postContent,
      topic: {
        entityId: topicId
      },
      createdUser: {
        entityId: userId
      }
    };
    Posts.add(requestParams).then(function (response) {
      $window.location.reload();
    }, function () {
      $window.location.reload();
      console.log("Error on saving post");
    });
    $uibModalInstance.close();
  };

  $ctrl.okCreateComment = function () {
    console.log($scope.comment);
    var result = {
      comment:$scope.comment
    };
    var userId=$window.localStorage.getItem("user");
    var topicId = $window.localStorage.getItem("topic");
    var requestParams = {
      comment: $scope.comment,
      topic: {
        entityId: topicId
      },
      post: {
        entityId: $scope.postId
      },
      createdUser : {
        entityId: userId
      }
    };
    Comments.add(requestParams);
    $window.location.reload();
    $uibModalInstance.close();
    $uibModalInstance.close(result);
    $uibModalInstance.close(result);
  };

  $ctrl.okSaveProfile = function () {
    console.log($scope.user);
    var result = {
      user : $scope.user
    };
    $uibModalInstance.close(result);
  };

  $ctrl.okSignIn = function () {
    User.login($scope.signIn).then(function(response){
      $uibModalInstance.close(response);
      $window.location.reload();
    });
  };

  $ctrl.okSignUp = function () {
    console.log($scope.signUp);
    var result = {
      signUp : $scope.signUp
    };
    $uibModalInstance.close(result);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});
// Controller for navbar.html
angular.module('InfoGrappoWeb').controller('NavbarCtrl', function($scope, $rootScope, $window, User){
  $scope.auth = false;
  if($window.localStorage.getItem('user') != undefined){
    User.get($window.localStorage.getItem('user')).then(function(response){
      $rootScope.user = response;
      $scope.auth = true;
    },function(error){
      $scope.auth = false;
    })
  }
  $scope.logout = function(){
    $window.localStorage.removeItem("user");
    $scope.auth = false;
    $window.location.reload();
  }
});
angular.module('InfoGrappoWeb').controller('SearchCtrl', function ($scope, $log, $window) {

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

    $scope.get = function (searchText) {
        // TODO
    }
});

angular.module('InfoGrappoWeb').controller('TopicPageCtrl',function($scope, Topics, Posts, Comments, $window){
  function getComments(postId, i){
    Posts.getComments(postId).then(function(res){
      $scope.posts[i].comments = res; 
    });
  }
  Topics.get($window.localStorage.getItem("topic")).then(function(response){
    $scope.topic = response.data;
    Topics.getPosts($scope.topic.entityId).then(function(data){
      $scope.posts = data;
      for(var i=0; i<$scope.posts.length; i++){
        getComments($scope.posts[i].entityId, i);
      }
    });
  });
  
  var relations = [];
  Topics.getRelation(Topics.getTopic()).then(function(response){
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

  $scope.go = function(topicID){
    Topics.get(topicID).then(function(response){
      $scope.topic = response.data;
      $scope.posts = [];
      Topics.getPosts($scope.topic.entityId).then(function(data){
        $scope.posts = data;
        for(var i=0; i<$scope.posts.length; i++){
          $scope.postForComment = $scope.posts[i];
          Posts.getComments($scope.posts[i].entityId).then(function(res){
            /*i bunun icinde 2 oluyo anlamadım bende neden oldugunu böyle yaptım */
            $scope.postForComment.comments = res; 
          });
          $scope.posts[i].comments = $scope.postForComment.comments;
        }
      });  
    });
    var relations = [];
    Topics.getRelation(topicID).then(function(response){
      console.log("response2");
      for (var i = 0; i < response.length; i++) {
        var pushArrayFrom = true;
        var pushArrayTo = true; 
        for(var j = 0; j < relations.length; j++){
          
          if(response[i].fromTopic.entityId == relations[j].entityId){
            pushArrayFrom = false;
          }else if(response[i].toTopic.entityId == relations[j].entityId){
            pushArrayTo = false;
          }
        }
        if(pushArrayFrom){
          if(response[i].fromTopic.entityId != topicID){
            relations.push(response[i].fromTopic);
          }
        }
        if(pushArrayTo){
          if(response[i].toTopic.entityId != topicID){
            relations.push(response[i].toTopic);
          }
        }
      }
      $scope.relations=relations;
    })
  };
});

angular.module('InfoGrappoWeb').factory('Topics', ['$http', '$q', '$window', function($http, $q, $window) {
  // Might use a resource here that returns a JSON array
  return {
    all: function() {
      return $http({
        method: 'GET',
        url: appData.baseUrl+"topics"
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
      //return localstorage data
      return $window.localStorage.getItem('topic');
    },
    get: function(topicID) {
      return $http({
        method: 'GET',
        url: appData.baseUrl+"topics/"+topicID
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
        url: appData.baseUrl+"topics/"+topicID+"/relationsTo"
      }).then(function successCallback(data){
        return data.data;
      }, function errorCallback(data){
        return null;
      });
      var getFrom = $http({
        method: 'GET',
        url: appData.baseUrl+"topics/"+topicID+"/relationsFrom"
      }).then(function successCallback(data) {
        return data.data;
      }, function errorCallback(data) {
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
        url : appData.baseUrl+"topics/"+topicId+"/posts",
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

angular.module('InfoGrappoWeb').factory('Posts', function($q, $http){
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
    add: function (params) {
      console.log(params)

      var deferred = $q.defer();
      var parameter = params;
      $http.post(appData.baseUrl+"posts", parameter).success(function (data, status, headers, config) {
        console.log(data);
        deferred.resolve();
      }).error(function (data, status, headers, config) {
        console.log("Error occurred when adding relation " + params);
        deferred.reject();
      });
      return deferred.promise;
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
    },
    getComments : function(postId){
      var deferred = $q.defer();
      $http({
        url : appData.baseUrl+"posts/"+postId+"/comments",
        method : "GET"
      }).then(function successCallback(data){
        deferred.resolve(data.data);
      }, function errorCallback(data){
        deferred.reject("no comment");
      });
      return deferred.promise;
    }
  };
});

angular.module('InfoGrappoWeb').factory('Comments', function($q, $http){

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
    add: function (params) {
      var deferred = $q.defer();
      var parameter = params;
      $http.post(appData.baseUrl+"comments", parameter).success(function (data, status, headers, config) {
        console.log(data);
        deferred.resolve();
      }).error(function (data, status, headers, config) {
        console.log("Error occurred when adding relation " + params);
        deferred.reject();
      });
      return deferred.promise;
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

angular.module('InfoGrappoWeb').factory('Relations', ['$http', '$q', function($http, $q, $window) {
  // Might use a resource here that returns a JSON array
  var toTopic = 1;
  return {
    add: function (params) {
      var deferred = $q.defer();
      var parameter = params;
      $http.post(appData.baseUrl+"relations", parameter).success(function (data, status, headers, config) {
        console.log(data);
        deferred.resolve();
      }).error(function (data, status, headers, config) {
        console.log("Error occurred when adding relation " + params);
        deferred.reject();
      });
      return deferred.promise;
    },
    all: function() {
      return $http({
        method: 'GET',
        url: appData.baseUrl + "relations"
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
    get: function(topicID) {
      return $http({
        method: 'GET',
        url: appData.baseUrl + "relations/"+topicID
      }).then(function successCallback(data) {
        console.log(data);
        return data;
      }, function errorCallback(data) {
        console.log("Lanet olasıca backend çalışmıyor!!!!");
        return null;
      });
    },
    getRelation: function(topicId, secondTopicId) {
      var deferred = $q.defer();
      var getTo = $http({
        method: 'GET',
        url: appData.baseUrl + "topics/"+topicId+"/relationsTo"
      }).then(function successCallback(data){
        return data.data;
      }, function errorCallback(data){
        console.log(data);
        return null;
      });
      var getFrom = $http({
        method: 'GET',
        url: appData.baseUrl + "topics/"+topicId+"/relationsFrom"
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
        url : appData.baseUrl + "topics/"+topicId+"/posts",
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
angular.module("InfoGrappoWeb").factory("User", function($http, $q, $window){
  return {
    // If user who login toweb site is not an admin sees his/her informations
    login : function(credentials){
     var deferred = $q.defer();
     credentials.email = credentials.email.split("@")[0];
     $http({
        url : appData.baseUrl+'users',
        method : 'GET',
        headers : {
          "Content-Type" : "application/json",
          "Authorization": "Basic "+btoa(credentials.email +":"+ credentials.password)
        },
        withCredentials : true
      }).then(function(res){
        $window.localStorage.setItem('user', res.data.entityId);
        deferred.resolve(res.data);
      },function(error){
        deferred.reject("Invalid Credentials");
      });
      return deferred.promise;
    },
    // get all info of user
    get : function(userId){
      var deferred = $q.defer();
      $http({
        url : appData.baseUrl+"users/"+userId,
        method : "GET"
      }).then(function(response){
        deferred.resolve(response.data);
      },function(error){
        deferred.reject(error);
      });
      return deferred.promise;
    }
  } 
});
angular.module("InfoGrappoWeb").factory("Timeline", function($http, $q, $window, $rootScope) {
  return {
    // Get posts from followed topics
    postsOfTopics: function () {
      var deferred = $q.defer();
      var userId = $window.localStorage.getItem("user");
      $http({
        method: 'GET',
        url: appData.baseUrl + 'timeline/' + userId + '/postOfTopics'
      }).then(function (response) {
        deferred.resolve(response.data);
      }, function (error) {
        deferred.reject("Error occurred when getting posts of followed topics");
      });
      return deferred.promise;
    },
    // Get posts written by followed users
    postsOfUsers: function () {
      var deferred = $q.defer();
      var userId = $window.localStorage.getItem("user");
      $http({
        method: 'GET',
        url: appData.baseUrl + 'timeline/' + userId + '/postOfUsers'
      }).then(function (response) {
        deferred.resolve(response.data);
      }, function (error) {
        deferred.reject("Error occurred when getting posts of followed users");
      });
      return deferred.promise;
    },
    // Get topics created by followed users
    topicOfUsers: function () {
      var deferred = $q.defer();
      var userId = $window.localStorage.getItem("user");
      $http({
        method: 'GET',
        url: appData.baseUrl + 'timeline/' + userId + '/topicOfUsers'
      }).then(function (response) {
        deferred.resolve(response.data);
      }, function (error) {
        deferred.reject("Error occurred when getting posts of followed users");
      });
      return deferred.promise;
    },
    // Get comments written by followed users
    commentOfUsers:  function () {
      var deferred = $q.defer();
      var userId = $window.localStorage.getItem("user");
      $http({
        method: 'GET',
        url: appData.baseUrl + 'timeline/' + userId + '/commentOfUsers'
      }).then(function (response) {
        deferred.resolve(response.data);
      }, function (error) {
        deferred.reject("Error occurred when getting comments of followed users");
      });
      return deferred.promise;
    }
  }
});
angular.module("InfoGrappoWeb").factory("Follow", function($http, $q, $window, $rootScope) {
    return {
      followTopic: function (topicId) {
        var deferred = $q.defer();
        var userId = $window.localStorage.getItem("user");
        var parameter = {topicId: topicId , userId: userId};
        $http.post(appData.baseUrl+"users/follow-topic", parameter).success(function (data, status, headers, config) {
          console.log(data);
          deferred.resolve();
        }).error(function (data, status, headers, config) {
          console.log("Error on following topic with id " + topicId)
          deferred.reject();
        });
        return deferred.promise;
      },
      followUser: function (followingId) {
        var userId = $window.localStorage.getItem("user");
        var parameter = {followingId: followingId , followerId: userId};
        $http.post(appData.baseUrl+"users/follow_user", parameter).success(function (data, status, headers, config) {
          console.log(data);
        }).error(function (data, status, headers, config) {
          console.log("Error on following topic with id " + topicId);
        });
      }
    }
});
//changes
angular.module("InfoGrappoWeb").factory("Autocomplete", function($http, $q, $window) {
  return {
    topics: function (word) {
      var deferred = $q.defer();
      $http({
        method: 'GET',
        url: appData.baseUrl + 'autoComp/topics?keyword=' + word
      }).then(function (response) {
        var topics = [];
        for(var i=0; i<response.data.length; i++){
          topics.push({
            entityId : response.data[i].entityId,
            name : response.data[i].name
          });
        }
        console.log(topics);
        deferred.resolve(topics);
      }, function (error) {
        deferred.reject("Error occurred when getting similar topic names");
      });
      return deferred.promise;
    },
    relation: function (word) {
      var deferred = $q.defer();
      $http({
        method: 'GET',
        url: appData.baseUrl + 'autoComp/relationTypes?keyword=' + word
      }).then(function (response) {
        console.log(response);
        deferred.resolve(response.data);
      }, function (error) {
        deferred.reject("Error occurred when getting similar relation types");
      });
      return deferred.promise;
    },
    tags : function (topic) {
      var deferred = $q.defer();
      $http({
        method :  "GET",
        url : appData.baseUrl + 'topics/semantic?topic=' + topic
      }).then(function(response){
        var labelValues = [];
        for(var i=0; i<response.data.results.bindings.length; i++){
          labelValues.push(response.data.results.bindings[i].label.value);
        }
        deferred.resolve(labelValues);
        console.log(labelValues);
      }, function(error){
        deferred.reject(error);
      });
      return deferred.promise;
    } 
  }
});
//changes end