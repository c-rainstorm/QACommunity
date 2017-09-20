let app = angular.module("app", []).config(function($locationProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location){

    console.log("app controller loaded.");

    // TODO get session

    // 获取问题id
    console.log($location.search());

    let question_id = $location.search().id;

    $http({
        url : "../getQuestion.action" ,
        method : "get" ,
        params : {
            id : question_id
        } ,
    }).then(function(resp){
        console.log(resp);

        $scope.question = resp.data;

        $("#question_content").get(0).innerHTML = markdown.toHTML(resp.data.content);

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