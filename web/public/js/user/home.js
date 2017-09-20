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


        // 获取问题列表by user_id
        $http({
            url : "../getQuestionsByUserId.action" ,
            method : "get" ,
            params : {
                author_id : angular.copy($scope.user.id),
                maxNumInOnePage : 5,
                pageNum : 1
            } ,
        }).then(function(resp){
            console.log(resp);

            $scope.questionList = resp.data;
        }, function(resp){
            httpErr(resp);
        });

        // 获取文章列表by user_id
        $http({
            url : "../getArticlesByUserId.action" ,
            method : "get" ,
            params : {
                author_id : angular.copy($scope.user.id),
                maxNumInOnePage : 5,
                pageNum : 1
            } ,
        }).then(function(resp){
            console.log(resp);

            $scope.articleList = resp.data;
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