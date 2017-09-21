let app = angular.module("app", ['ngSanitize']).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http) {

    console.log("app controller loaded.");

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

        $scope.search = function () {

            let val = $("#search_content").val();
            console.log(val);

            // 搜索问题
            $http({
                url: "../getQuestionsByKeyword.action",
                method: "get",
                params: {
                    keyword: val,
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                console.log(resp);

                $scope.questionList = resp.data;

                for (var i in $scope.questionList) {
                    $scope.questionList[i].content = markdown.toHTML($scope.questionList[i].content);
                }
            }, function (resp) {
                httpErr(resp);
            });

            // 搜索文章
            $http({
                url: "../getArticlesByKeyword.action",
                method: "get",
                params: {
                    keyword: val,
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                console.log(resp);

                $scope.articleList = resp.data;

                for (var i in $scope.articleList) {
                    $scope.articleList[i].content = markdown.toHTML($scope.articleList[i].content);
                }
            }, function (resp) {
                httpErr(resp);
            });

        }


    }, function (resp) {
        httpErr(resp);
    });

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}