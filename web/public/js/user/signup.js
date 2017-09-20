let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http) {

    console.log("app ctronller loaded.");

    $scope.user = {
        name: "",
        email: "",
        password: ""
    }
    $scope.userAlert = {
        nameHelp: "",
        emailHelp: "",
        passwordHelp: ""
    }
    $scope.signup = function () {

        console.log($scope.user);

        // 检查用户名
        $http({
            url: "../checkUserName.action",
            method: "get",
            param: {
                name: angular.copy($scope.user.name)
            }
        }).then(function (resp) {
            if (resp.result == false) {
                $scope.userAlert.nameHelp = "user name invalid";
            } else {

                // 检查邮箱
                $http({
                    url: "../checkUserEmail.action",
                    method: "get",
                    param: {
                        email: angular.copy($scope.user.email)
                    }
                }).then(function (resp) {
                    if (resp.result == false) {
                        $scope.userAlert.emailHelp = "user email invalid";
                    } else {

                        // 检查密码
                        $http({
                            url: "../checkUserPassword.action",
                            method: "get",
                            param: {
                                password: angular.copy($scope.user.password)
                            }
                        }).then(function (resp) {
                            if (resp.result == false) {
                                $scope.userAlert.passwordHelp = "user password invalid";
                            } else {

                                // 检查完成，注册用户

                            }
                        }, function (resp) {
                            httpErr(resp);
                        });

                    }
                }, function (resp) {
                    httpErr(resp);
                });

            }
        }, function (resp) {
            httpErr(resp);
        });

    }

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}