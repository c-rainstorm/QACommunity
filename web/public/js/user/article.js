let app = angular.module("app", []).config(function($locationProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location){

    console.log("app controller loaded.");

    // TODO get session

    // 获取文章id
    console.log($location.search());

    let article_id = $location.search().id;

    $http({
        url : "../getArticle.action" ,
        method : "get" ,
        params : {
            id : article_id
        } ,
    }).then(function(resp){
        console.log(resp);

        $scope.article = resp.data;

        $("#article_content").get(0).innerHTML = markdown.toHTML(resp.data.content);

        // 获取作者简略信息
        $http({
            url : "../getUserBriefInfoById.action" ,
            method : "get" ,
            params : {
                id : angular.copy($scope.article.author_id)
            } ,
        }).then(function(resp){
            console.log(resp);

            $scope.article.author = resp.data;
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