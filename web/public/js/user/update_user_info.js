let app = angular.module("app", []);

app.controller("appCtrl", function($scope, $http){

    console.log("app controller loaded.");

    $scope.session = {};
    // 获取 session
    $http({
        url: "../getUserSession.action",
        method: "get"
    }).then(function (resp) {
        console.log("session : ");
        console.log(resp);

        if (resp.data.userLoginStatus == false) {
            console.log("no session.");

            $window.location.href = "./login.html";
            return;
        }

        $scope.session.user = resp.data;

        // 获取个人信息by id

        $http({
            url: "../getUserDetails.action",
            method: "get",
            params: {
                id: angular.copy($scope.session.user.id)
            },
        }).then(function (resp) {
            console.log(resp);

            $scope.user = angular.copy(resp.data);

        }, function(resp){
            httpErr(resp);
        });
    
    }, function(resp){
        httpErr(resp);
    });

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}