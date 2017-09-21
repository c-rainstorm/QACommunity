let app = angular.module("app", ['ngSanitize']).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location, $window, $timeout) {

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

        let question_id = $location.search().id;

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


            // 获取该问题下的所有答案
            $http({
                url: "../getAnswersByQuestionId.action",
                method: "get",
                params: {
                    question_id: question_id,
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                $scope.answerList = resp.data;

                for (var i in $scope.answerList) {
                    $scope.answerList[i].content = markdown.toHTML($scope.answerList[i].content);
                    $scope.answerList[i].comments = [];
                }

                console.log("answer list : ");
                console.log(resp);
            }, function (resp) {
                httpErr(resp);
            });


            // 获得某答案下的所有评论
            $scope.getComments = function (answer_id, answer_index) {

                if ($scope.answerList[answer_index].comments.length != 0) {
                    return;
                }

                console.log("get all comments");

                $http({
                    url: "../getAnswerComments.action",
                    method: "get",
                    params: {
                        answer_id: answer_id
                    },
                }).then(function (resp) {
                    console.log("comments: ");
                    console.log(resp);

                    $scope.answerList[answer_index].comments = resp.data;
                }, function (resp) {
                    httpErr(resp);
                });

            }


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

                    if (resp.data.result == "true" || resp.data.result == true) {
                        toastr.success("添加答案成功");
                        
                        $timeout(function(){
                            $window.location.href = "./question_answer.html?question_id=" + $scope.question.id + "&answer_id=" + resp.data.id;
                        }, 1000);
                    }else{
                        toastr.error("添加答案失败");
                    }
                }, function (resp) {
                    httpErr(resp);
                });

            }

            // 提交回复
            $scope.commentSubmit = function (answer_id, answer_index) {

                let newComment = $("#newComment_" + answer_id).val();
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

                    if (resp.data.result == "true" || resp.data.result == true) {
                        toastr.success("添加评论成功");
                        
                        $scope.answerList[answer_index].comments.push({
                            content : newComment,
                            id : resp.data.id,
                            user_id : session.user.id,
                            user_avatar : session.user.avatar,
                            user_name : session.user.name,
                            up : 0,
                            down : 0
                        });
                    }else{
                        toastr.error("添加评论失败");
                    }

                    $("#newComment_" + answer_id).val("");
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