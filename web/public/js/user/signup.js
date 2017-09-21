let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http, $window, $timeout) {

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
        $scope.userAlert = {
            nameHelp: "",
            emailHelp: "",
            passwordHelp: ""
        }

        console.log(angular.copy($scope.user));

        // TODO check name, email, password
        let result = checkUser(angular.copy($scope.user));
        if(result.status == false){
            console.log(result.alert);
            $scope.userAlert = angular.copy(result.alert);
            return;
        }

        $http({
            url : "../addUser.action",
            method : "post",
            data : angular.copy($scope.user)
        }).then(function(resp){
            console.log(resp);
            let data = resp.data;
            if(data.result == "true"){
                console.log("singup successed.");
                toastr.info("Singup successed.");

                $timeout(function(){
                    $window.location.href = "./home.html";
                }, 1000);
            }else{
                console.log("something wrong on server. signup failed.");
                toastr.info("Something wrong on server. Signup failed.");                

                $timeout(function() {
                    $window.location.href = "./signup.html";
                }, 2000);
            }
        }, function(resp){
            httpErr(resp);
        });

        // $http({
        //     url : "../checkUserEmail.action",
        //     method : "get",
        //     params : {
        //         email : angular.copy($scope.user.email)
        //     }
        // }).then(function(resp){
        //     // console.log(resp);

        //     let data = resp.data;
        //     console.log(data);
            
        //     if(data.result == "true"){
        //         console.log("email checked.");

        //         $http({
        //             url : "../addUser.action",
        //             method : "get",
        //             params : angular.copy($scope.user)
        //         }).then(function(resp){
        //             console.log(resp);
        //         }, function(resp){
        //             httpErr(resp);
        //         });
        //     }else{
        //         console.log("email invalid");
        //     }

        // }, function(resp){
        //     httpErr(resp);
        // });

        // 检查用户名
        // $http({
        //     url: "../checkUserName.action",
        //     method: "get",
        //     param: {
        //         name: angular.copy($scope.user.name)
        //     }
        // }).then(function (resp) {
        //     if (resp.result == false) {
        //         $scope.userAlert.nameHelp = "user name invalid";
        //     } else {

        //         // 检查邮箱
        //         $http({
        //             url: "../checkUserEmail.action",
        //             method: "get",
        //             param: {
        //                 email: angular.copy($scope.user.email)
        //             }
        //         }).then(function (resp) {
        //             if (resp.result == false) {
        //                 $scope.userAlert.emailHelp = "user email invalid";
        //             } else {

        //                 // 检查密码
        //                 $http({
        //                     url: "../checkUserPassword.action",
        //                     method: "get",
        //                     param: {
        //                         password: angular.copy($scope.user.password)
        //                     }
        //                 }).then(function (resp) {
        //                     if (resp.result == false) {
        //                         $scope.userAlert.passwordHelp = "user password invalid";
        //                     } else {

        //                         // 检查完成，注册用户

        //                     }
        //                 }, function (resp) {
        //                     httpErr(resp);
        //                 });

        //             }
        //         }, function (resp) {
        //             httpErr(resp);
        //         });

        //     }
        // }, function (resp) {
        //     httpErr(resp);
        // });

    }

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}

function checkUser(user){
    result = {
        status : false,
        alert : {
            nameHelp : "",
            emailHelp : "",
            passwordHelp : ""
        }
    };
    // check name
    if(user.name.length <2 || user.name.length > 32){
        result.alert.nameHelp = "名字长度在2~32之间";
        return result;
    }

    // check email
    let emailExgex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    // console.log(emailExgex.test(user.email));
    if(!emailExgex.test(user.email)){
        result.alert.emailHelp = "email格式错误";
        return result;
    }

    // check password
    if(user.password.length < 6 || user.password.length > 64){
        result.alert.passwordHelp = "密码长度在6~64之间";
        return result;
    }

    result.status = true;
    return result;
}