angular.module('ui.bootstrap.demo', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('ModalDemoCtrl', function ($uibModal, $log, $document, $scope) {
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

angular.module('ui.bootstrap.demo').controller('ModalInstanceCtrl', function ($uibModalInstance, items, $scope) {
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
