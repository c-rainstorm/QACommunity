let app = angular.module("app", []).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location) {

    console.log("app controller loaded.");

    // $scope.session = {
    //     user : {
    //         id : 1
    //     }
    // }
    // TODO get session
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

        // 获取文章id
        console.log($location.search());

        let article_id = $location.search().id;

        $http({
            url: "../getArticle.action",
            method: "get",
            params: {
                id: article_id
            },
        }).then(function (resp) {
            console.log(resp);

            $scope.article = resp.data;

            $("#article_content").get(0).innerHTML = markdown.toHTML(resp.data.content);

            // 获取作者简略信息
            $http({
                url: "../getUserBriefInfo.action",
                method: "get",
                params: {
                    id: angular.copy($scope.article.author_id)
                },
            }).then(function (resp) {
                console.log(resp);

                $scope.article.author = resp.data;
            }, function (resp) {
                httpErr(resp);
            });

            // 获取文章comments
            $http({
                url: "../getArticleComments.action",
                method: "get",
                params: {
                    article_id: angular.copy($scope.article.id)
                },
            }).then(function (resp) {
                console.log(resp);

                $scope.article.comments = resp.data;
            }, function (resp) {
                httpErr(resp);
            });

            // 添加Comments
            $scope.newComment = "";
            $scope.commentSubmit = function () {

                console.log($scope.newComment);

                $http({
                    url: "../addArticleComment.action",
                    method: "post",
                    data: {
                        user_id: angular.copy($scope.session.user.id),
                        article_id: angular.copy($scope.article.id),
                        content: angular.copy($scope.newComment)
                    },
                }).then(function (resp) {
                    console.log(resp);

                    if (resp.data.result == "true") {
                        toastr.success("添加评论成功");
                        $scope.newComment = "";
                    } else {
                        toastr.error("添加评论失败");
                    }
                }, function (resp) {
                    httpErr(resp);
                });

            }

        }, function (resp) {
            httpErr(resp);
        });


    }, function (resp) {
        httpErr(resp);
    });

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}