let app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http, $window, $timeout) {

    $scope.color = [
        "badge-primary",
        "badge-secondary",
        "badge-success",
        "badge-danger",
        "badge-warning",
        "badge-info",
        "badge-dark"
    ]

    var content_mde = new SimpleMDE({ element: $("#content").get(0) });

    console.log("app controller loaded.");

    // $scope.session = {
    //     user: {
    //         id: 1
    //     }
    // }
    // 获取 session
    // TODO 获取 session
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

            $scope.labelInputSearch = "";

            // 获取标签
            $http({
                url: "../getLabels.action",
                method: "get",
            }).then(function (resp) {
                console.log("labels : ");
                console.log(resp);

                $scope.labels = resp.data;
                for (var i = 0; i < $scope.labels.length; i++) {
                    $scope.labels[i].color = angular.copy($scope.color[i % 7]);
                }
            }, function (resp) {
                httpErr(resp);
            });

            // 添加标签
            $scope.addLabelToQuestion = function (id) {
                console.log("add label to question. : " + id);

                for (var i = 0; i < $scope.question.labels.length; i++) {
                    if ($scope.question.labels[i].id == id) {
                        console.log("already added.");
                        return;
                    }
                }

                let labelTmp = {};
                for (var i = 0; i < $scope.labels.length; i++) {
                    if ($scope.labels[i].id == id) {
                        labelTmp = angular.copy($scope.labels[i]);
                        console.log(labelTmp);
                        break;
                    }
                }
                $scope.question.labels.push(angular.copy(labelTmp));
            }

            //删除标签
            $scope.deleteLabelFromQuestion = function (index) {
                console.log("delete label from question : " + index);

                $scope.question.labels.splice(index, 1);
                console.log("delete successed.");
            }

            // 提交问题
            $scope.question = {
                title: "",
                content: "",
                author_id: angular.copy($scope.user.id),
                labels: []
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

                    if (resp.data.result == "true" || resp.data.result == true) {
                        toastr.success("添加问题成功");

                        $timeout(function () {
                            $window.location.href = "./question.html?id=" + resp.data.id;
                        }, 1000);
                    } else {
                        toastr.error("添加问题失败");

                        // $window.location.href = "./add_question.html";
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

function checkTitle(t) {
    const title = t;

    result = {
        status: false,
        alert: ""
    }
    if (title.length < 3 || title.length > 32) {
        result.alert = "问题标题长度范围为3~32个字";
        return result;
    }

    result.status = true;
    return result;
}