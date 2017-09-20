let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http) {

    $scope.color = [
        'border-left-blue',
        'border-left-red',
        'border-left-grey',
        'border-left-green',
        'border-left-yellow'
    ];
    
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
            console.log("question list : ");            
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
            console.log("article list : ");            
            console.log(resp);

            $scope.articleList = resp.data;
        }, function(resp){
            httpErr(resp);
        });

        // 获取答案列表by user_id
        $http({
            url : "../getAnswersByUserId.action" ,
            method : "get" ,
            params : {
                author_id : angular.copy($scope.user.id),
                maxNumInOnePage : 5,
                pageNum : 1
            } ,
        }).then(function(resp){
            console.log("answer list : ");
            console.log(resp);

            $scope.answerList = resp.data;

            for(var i = 0; i < $scope.answerList.length; i++){
                $scope.answerList[i].border_color = angular.copy($scope.color[i % 5]);
            }
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