let app = angular.module("app", []);

app.controller("appCtrl", function($scope, $http, $timeout, $window){

    console.log("app ctroller loaded.");


    $scope.admin = {
        id : "",
        password : ""
    }
    $scope.login = function(){
        console.log("login btn clicked.");

        // console.log($scope.admin);
        let result = checkAdmin($scope.admin);
        if(result.status == false){
            console.log(result.alert);
            $scope.adminAlert = angular.copy(result.alert);
            return;
        }

        $http({
            url : "../checkAdminLogin.action" ,
            method : "post" ,
            data : angular.copy($scope.admin) ,
        }).then(function(resp){
            console.log(resp);

            if(resp.data.result == "true"){
                console.log("Login successed.");
                toastr.success("登录成功.");

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

function checkAdmin(admin){
    result = {
        status : false,
        alert : {
            idHelp : "",
            passwordHelp : ""
        }
    };

    // check id
    // let idExgex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    // console.log(idExgex.test(admin.id));
    if(admin.id.length < 6 || admin.id.length > 32){
        result.alert.idHelp = "id长度在6~32之间";
        return result;
    }

    // check password
    if(admin.password.length < 6 || admin.password.length > 64){
        result.alert.passwordHelp = "密码长度在6~64之间";
        return result;
    }

    result.status = true;
    return result;
}