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
        url: "../getUserBriefInfoById.action",
        method: "get",
        params: {
            id: angular.copy($scope.session.user.id)
        },
    }).then(function (resp) {
        console.log(resp);

        $scope.user = resp.data;

        // 提交问题
        $scope.question = {
            title: "",
            content: "",
            author_id: angular.copy($scope.user.id)
        }
        $scope.questionAlert = {
            titleHelp: "",
            contentHelp: ""
        }
        $scope.questionSubmit = function () {

            $scope.questionAlert = {
                titleHelp: "",
                contentHelp: ""
            }

            console.log("question submit clicked.");

            $scope.question.content = content_mde.value();
            console.log($scope.question);

            // 标题，问题内容校验
            titleRslt = checkTitle($scope.question.title);
            console.log(titleRslt);
            if (!titleRslt.status) {
                $scope.questionAlert.titleHelp = titleRslt.alert;
                return;
            }

            // 向服务器发送添加问题请求
            $http({
                url: "../addQuestion.action",
                method: "post",
                data: angular.copy($scope.question)
            }).then(function (resp) {
                console.log(resp);

                if(resp.data.result == "true"){
                    $window.location.href = "./home.html";
                }else{
                    $window.location.href = "./add_question.html";
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