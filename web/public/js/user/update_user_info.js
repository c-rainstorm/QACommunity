let app = angular.module("app", []);

app.controller("appCtrl", function($scope, $http, $window, $timeout){

    console.log("app controller loaded.");

    $scope.sex = {
        male : false,
        female : true
    };

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

        // 更新用户信息
        $scope.updateUserInfoSubmit = function(){

            console.log("update...");

            $http({
                url: "../updateUser.action",
                method: "post",
                data: angular.copy($scope.user)
            }).then(function(resp){
                console.log(resp);

                if(resp.data.result == "true"){
                    toastr.success("个人信息更新成功");

                    $timeout(function(){
                        $window.location.href = "./home.html";
                    }, 1000);
                }else{
                    toastr.error("个人信息更新失败");
                }
            }, function(resp){
                httpErr(resp);
            });

            // let $elem = $("#avatar");
            // let f = $elem[0].files[0];
            // console.log($elem[0].files[0]);
    
            // if(f == undefined){
            //     console.log("there is no file need to be uploaded.");


            // }else{
            //     let fr = new FileReader();
            //     fr.onloadend = function(){
            //         let avatar_file = fr.result;
            //         console.log("reading file.");
        
                    
            //     }
        
            //     fr.readAsBinaryString(f);
            // }

        }
    
    }, function(resp){
        httpErr(resp);
    });

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}