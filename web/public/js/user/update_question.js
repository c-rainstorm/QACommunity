let app = angular.module("app", []);

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