let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http) {

    console.log("app controller loaded.");

    $scope.session = {
        user : {
            id : 1
        }
    }
    // 获取session
    // TODO 获取session

    // 获取个人信息by id
    
    $http({
        url : "../getUserDetails.action" ,
        method : "get" ,
        params : {
            id : angular.copy($scope.session.user.id)
        },
    }).then(function(resp){
        console.log(resp);

        $scope.user = angular.copy(resp.data);
    }, function(resp){
        httpErr(resp);
    });

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}