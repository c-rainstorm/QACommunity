let app = angular.module("app", ['ngSanitize', 'ngFileUpload']);

app.controller("appCtrl", function ($scope, $http, $window, Upload) {

    $scope.color = [
        'border-left-blue',
        'border-left-red',
        'border-left-grey',
        'border-left-green',
        'border-left-yellow'
    ];

    console.log("app controller loaded.");

    // $scope.session = {
    //     user : {
    //         id : 1
    //     }
    // }
    // 获取session
    // TODO 获取session
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

        // 获取个人信息by id

        $http({
            url: "../getUserDetails.action",
            method: "get",
            params: {
                id: angular.copy($scope.session.user.id)
            },
        }).then(function (resp) {
            console.log(resp);

            $scope.user = angular.copy(resp.data);


            // 更新avatar
            $scope.updateAvatar = function () {

                $("#avatar_file").trigger('click');

            }

            $scope.avatar_file = {};
            $("#avatar_file").on("change", function () {
                // alert("file uploaded.");

                let avatarFile = $("#avatar_file").get(0).files[0];
                console.log(avatarFile);
                $scope.upload(avatarFile, $scope.user.id);

            });

            // upload on file select or drop
            $scope.upload = function (file, user_id) {
                Upload.upload({
                    url: '../updateAvatars.action',
                    method : "post",
                    data: { 
                        file: file, 
                        user_id : user_id
                    }
                }).then(function (resp) {
                    console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
                }, function (resp) {
                    console.log('Error status: ' + resp.status);
                }, function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                });
            };

            // 获取问题列表by user_id
            $http({
                url: "../getQuestionsByUserId.action",
                method: "get",
                params: {
                    author_id: angular.copy($scope.user.id),
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                console.log("question list : ");
                console.log(resp);

                $scope.questionList = resp.data;
            }, function (resp) {
                httpErr(resp);
            });

            // 获取文章列表by user_id
            $http({
                url: "../getArticlesByUserId.action",
                method: "get",
                params: {
                    author_id: angular.copy($scope.user.id),
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                console.log("article list : ");
                console.log(resp);

                $scope.articleList = resp.data;

                for (var i = 0; i < $scope.articleList.length; i++) {
                    $scope.articleList[i].content = markdown.toHTML($scope.articleList[i].content);
                }
            }, function (resp) {
                httpErr(resp);
            });

            // 获取答案列表by user_id
            $http({
                url: "../getAnswersByUserId.action",
                method: "get",
                params: {
                    author_id: angular.copy($scope.user.id),
                    maxNumInOnePage: 5,
                    pageNum: 1
                },
            }).then(function (resp) {
                console.log("answer list : ");
                console.log(resp);

                $scope.answerList = resp.data;

                for (var i = 0; i < $scope.answerList.length; i++) {
                    $scope.answerList[i].border_color = angular.copy($scope.color[i % 5]);
                }
            }, function (resp) {
                httpErr(resp);
            });

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