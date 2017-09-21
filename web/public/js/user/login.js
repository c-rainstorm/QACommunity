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
        let result = checkUser($scope.user);
        if(result.status == false){
            console.log(result.alert);
            $scope.userAlert = angular.copy(result.alert);
            return;
        }

        $http({
            url : "../checkUserLogin.action" ,
            method : "post" ,
            data : angular.copy($scope.user) ,
        }).then(function(resp){
            console.log(resp);

            if(resp.data.result == "true"){
                console.log("Login successed.");
                toastr.info("Login successed.");

                $timeout(function(){
                    $window.location.href = "./home.html";
                }, 1000);
            }else{
                console.log("login failed.");
                toastr.info("邮箱或密码错误，请重新登录");                

                // $timeout(function() {
                //     $window.location.href = "./login.html";
                // }, 2000);
            }
        }, function(resp){
            httpErr(resp);
        });

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
            emailHelp : "",
            passwordHelp : ""
        }
    };

    // check email
    let emailExgex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    // console.log(emailExgex.test(user.email));
    if(!emailExgex.test(user.email)){
        result.alert.emailHelp = "email格式错误";
        return result;
    }

    // check password
    if(user.password.length < 6 || user.password.length > 64){
        result.alert.passwordHelp = "密码长度在3~16之间";
        return result;
    }

    result.status = true;
    return result;
}