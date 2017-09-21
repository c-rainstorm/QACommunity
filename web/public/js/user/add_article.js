let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http, $window) {

    var content_mde = new SimpleMDE({ element: $("#content").get(0) });

    console.log("app controller loaded.");

    $scope.session = {
        user: {
            id: 1
        }
    }
    // 获取 session
    // TODO 获取 session


    // 获取用户简略信息
    $http({
        url: "../getUserBriefInfo.action",
        method: "get",
        params: {
            id: angular.copy($scope.session.user.id)
        },
    }).then(function (resp) {
        console.log(resp);

        $scope.user = resp.data;

        // 提交文章
        $scope.article = {
            title: "",
            content: "",
            author_id: angular.copy($scope.user.id)
        }
        $scope.articleAlert = {
            titleHelp: "",
            contentHelp: ""
        }
        $scope.articleSubmit = function () {

            $scope.articleAlert = {
                titleHelp: "",
                contentHelp: ""
            }

            console.log("article submit clicked.");

            $scope.article.content = content_mde.value();
            console.log($scope.article);

            // 标题，文章内容校验
            titleRslt = checkTitle($scope.article.title);
            console.log(titleRslt);
            if (!titleRslt.status) {
                $scope.articleAlert.titleHelp = titleRslt.alert;
                return;
            }

            // 向服务器发送添加文章请求
            $http({
                url: "../addArticle.action",
                method: "post",
                data: angular.copy($scope.article)
            }).then(function (resp) {
                console.log(resp);

                if(resp.data.result == "true"){
                    $window.location.href = "./home.html";
                }else{
                    $window.location.href = "./add_article.html";
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