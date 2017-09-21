let app = angular.module("app", ['ngSanitize']).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $location, $window, $timeout) {

    console.log("app controller loaded.");

    var update_answer_mde = new SimpleMDE({ element: $("#update_answer").get(0) });

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

            $("#question_content").get(0).innerHTML = markdown.toHTML(resp.data.content);

            // 获取问题作者简略信息
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
                update_answer_mde.value($scope.answer.content);
                // $scope.answer.content = markdown.toHTML($scope.answer.content);

            }, function (resp) {
                httpErr(resp);
            });

            // 跟新答案
            $scope.answerUpdate = function () {

                console.log("answer update clicked.");

                // 答案内容校验
                update_answer_content = update_answer_mde.value();
                if (update_answer_content.length <= 0) {
                    alert("答案内容不能为空");
                    return;
                }

                console.log(update_answer_content);

                updatedAnswer = {
                    id : answer_id,
                    content: update_answer_content
                }

                $http({
                    url: "../updateAnswer.action",
                    method: "post",
                    data: updatedAnswer,
                }).then(function (resp) {
                    console.log(resp);

                    if (resp.data.result == "true") {
                        toastr.success("更新成功");

                        $timeout(function(){
                            $window.location.href = "./question_answer.html?question_id=" + question_id + "&answer_id=" + answer_id;
                        }, 1000);
                    }else{
                        toastr.error("更新失败");
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