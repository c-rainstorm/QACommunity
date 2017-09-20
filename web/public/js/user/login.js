let app = angular.module("app", []);

app.controller("appCtrl", function($scope){

    console.log("app ctroller loaded.");


    $scope.user = {
        email : "",
        password : ""
    }
    $scope.login = function(){
        console.log("login btn clicked.");

        // console.log($scope.user);
        if($scope.user.email == "undefined" || $scope.user.email)
    }

});