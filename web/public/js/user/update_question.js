let app = angular.module("app", []).config(function($locationProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $window, $location) {

    var content_mde = new SimpleMDE({ element: $("#content").get(0) });

    console.log("app controller loaded.");

    $scope.session = {
        user: {
            id: 1
        }
    }
    // 获取 session
    // TODO 获取 session

    console.log($location.search().id);
    let question_id = $location.search().id;

    // 获取文章信息
    $http({
        url : "../getQuestion.action" ,
        method : "get" ,
        params : {
            id : question_id
        } ,
    }).then(function(resp){
        console.log(resp);

        $scope.question = resp.data;
        content_mde.value($scope.question.content);

        // 获取作者简略信息
        $http({
            url : "../getUserBriefInfo.action" ,
            method : "get" ,
            params : {
                id : angular.copy($scope.question.author_id)
            } ,
        }).then(function(resp){
            console.log(resp);

            $scope.question.author = resp.data;
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

function checkTitle(t) {
    const title = t;

    result = {
        status: false,
        alert: ""
    }
    if (title.length < 3 || title.length > 32) {
        result.alert = "文章标题长度范围为3~32个字";
        return result;
    }

    result.status = true;
    return result;
}