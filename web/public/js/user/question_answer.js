let app = angular.module("app", ['ngSanitize']).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location, $window) {

    $scope.color = [
        "badge-primary",
        "badge-secondary",
        "badge-success",
        "badge-danger",
        "badge-warning",
        "badge-info",
        "badge-dark"
    ];

    console.log("app controller loaded.");

    var new_answer_mde = new SimpleMDE({ element: $("#new_answer").get(0) });

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

        // 获取问题id
        console.log($location.search());

        let question_id = $location.search().question_id;
        let answer_id = $location.search().answer_id;

        $http({
            url: "../getQuestion.action",
            method: "get",
            params: {
                id: question_id
            },
        }).then(function (resp) {
            console.log(resp);

            $scope.question = resp.data;
            
            for (var i = 0; i < $scope.question.labels.length; i++) {
                $scope.question.labels[i].color = angular.copy($scope.color[i % 7]);
            }

            $("#question_content").get(0).innerHTML = markdown.toHTML(resp.data.content);

            // 获取作者简略信息
            $http({
                url: "../getUserBriefInfo.action",
                method: "get",
                params: {
                    id: angular.copy($scope.question.author_id)
                },
            }).then(function (resp) {
                console.log(resp);

                $scope.question.author = resp.data;
            }, function (resp) {
                httpErr(resp);
            });


            // 获取答案by question_id
            $http({
                url: "../getAnswer.action",
                method: "get",
                params: {
                    id: answer_id
                },
            }).then(function (resp) {
                console.log("answer : ");
                console.log(resp);

                $scope.answer = resp.data;
                $scope.answer.content = markdown.toHTML($scope.answer.content);

                // 获取问题comments
                $http({
                    url: "../getAnswerComments.action",
                    method: "get",
                    params: {
                        answer_id: angular.copy($scope.answer.id)
                    },
                }).then(function (resp) {
                    console.log(resp);

                    $scope.answer.comments = resp.data;
                }, function (resp) {
                    httpErr(resp);
                });

                // 提交回复
                $scope.commentSubmit = function (answer_id) {

                    let newComment = $("#newComment").val();
                    console.log("comment : " + newComment);

                    $http({
                        url: "../addAnswerComment.action",
                        method: "post",
                        data: {
                            user_id: angular.copy($scope.session.user.id),
                            answer_id: answer_id,
                            content: newComment
                        },
                    }).then(function (resp) {
                        console.log(resp);

                        toastr.success("添加评论成功");

                        $("#newComment_" + answer_id).val("");
                    }, function (resp) {
                        httpErr(resp);
                    });

                }

            }, function (resp) {
                httpErr(resp);
            });

            // 提交答案
            $scope.answerSubmit = function () {

                console.log("answer submit clicked.");

                // 答案内容校验
                new_answer_content = new_answer_mde.value();
                if (new_answer_content.length <= 0) {
                    alert("答案内容不能为空");
                    return;
                }

                console.log(new_answer_content);

                newAnswer = {
                    question_id: question_id,
                    author_id: angular.copy($scope.session.user.id),
                    content: new_answer_content
                }

                $http({
                    url: "../addAnswer.action",
                    method: "post",
                    data: newAnswer,
                }).then(function (resp) {
                    console.log(resp);

                    if (resp.data.result == "true") {
                        $window.location.href = "./home.html"
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