let app = angular.module("app", ['ngSanitize']);

app.controller("appCtrl", function ($scope, $http, $window) {

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
            $scope.updateAvatar = function(){

                $("#avatar_file").trigger('click');

            }

            $("#avatar_file").on("change", function(){
                // alert("file uploaded.");

                let $elem = $("#avatar_file");
                let f = $elem[0].files[0];
                console.log($elem[0].files[0]);
        
                if(f == undefined){
                    console.log("there is no file need to be uploaded.");

                }else{
                    let fr = new FileReader();
                    fr.onloadend = function(){
                        let avatar_file = fr.result;
                        console.log("reading file.");
            
                        $http({
                            url : "../updateAvatars.action" ,
                            method : "post" ,
                            headers: {'Content-Type': undefined},
                            transformRequest: (data, headersGetter) => {
                                
                                let formData = new FormData();
                                angular.forEach(data, function (value, key) {
                            
                                    formData.append(key, value);
                            
                                });
                            
                                return formData;
                            
                            },
                            data : {
                                user_id : angular.copy($scope.user.id),
                                avatar : avatar_file
                            } ,
                        }).then(function(resp){
                            console.log(resp);
                        }, function(resp){
                            httpErr(resp);
                        });
                    }
            
                    fr.readAsBinaryString(f);
                }
            })

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