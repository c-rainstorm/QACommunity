let app = angular.module("app", []).config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller("appCtrl", function ($scope, $http, $window, $location, $timeout) {

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

        console.log($location.search().id);
        let question_id = $location.search().id;

        // 获取文章信息
        $http({
            url: "../getQuestion.action",
            method: "get",
            params: {
                id: question_id
            },
        }).then(function (resp) {
            console.log(resp);

            $scope.question = resp.data;
            content_mde.value($scope.question.content);

            for (var i = 0; i < $scope.question.labels.length; i++) {
                $scope.question.labels[i].color = angular.copy($scope.color[i % 7]);
            }

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

            // 获取所有标签
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
            $scope.addLabelToQuestion = function (id, index) {

                console.log("add label to question. : " + id);

                for (var i = 0; i < $scope.question.labels.length; i++) {
                    if ($scope.question.labels[i].id == id) {
                        console.log("already added.");
                        return;
                    }
                }

                $http({
                    url: "../addLabelToQuestion.action",
                    method: "get",
                    params: {
                        question_id: angular.copy($scope.question.id),
                        label_id: id
                    },
                }).then(function (resp) {
                    console.log(resp);

                    if(resp.data.result == "true"){
                        $scope.question.labels.push(angular.copy($scope.labels[index]));
                    }else{
                        toastr.error("添加标签失败");                        
                    }
                }, function (resp) {
                    httpErr(resp);
                });

            }

            // 显示删除标签modal
            $scope.deleteLabel = {};
            $scope.showDeleteLabelModal = function(label_id, title, label_index){
                $scope.deleteLabel = {
                    label_index : label_index,
                    label_id : label_id,
                    title : title
                }

                $("#deleteLabelModal").modal();
            }

            // 删除标签
            $scope.deleteLabelFromQuestion = function(){

                $http({
                    url : "../deleteLabelFromQuestion.action" ,
                    method : "get" ,
                    params : {
                        question_id : angular.copy($scope.question.id),
                        label_id: angular.copy($scope.deleteLabel.label_id)
                    } ,
                }).then(function(resp){
                    console.log(resp);

                    if(resp.data.result == "true"){
                        toastr.success("删除标签 " + $scope.deleteLabel.title + " 成功");

                        $scope.question.labels.splice($scope.deleteLabel.label_index, 1);
                    }else{
                        toastr.error("删除标签 " + $scope.deleteLabel.title + " 失败");
                    }

                    $scope.deleteLabel = {};
                }, function(resp){
                    httpErr(resp);
                });

            }

            // update question
            $scope.questionUpdate = function(){

                $scope.question.content = content_mde.value();
                
                $http({
                    url : "../updateQuestion.action" ,
                    method : "post" ,
                    data : {
                        id : angular.copy($scope.question.id),
                        title : angular.copy($scope.question.title),
                        content : angular.copy($scope.question.content)
                    } ,
                }).then(function(resp){
                    console.log(resp);

                    if(resp.data.result == "true"){
                        toastr.success("修改问题成功");

                        $timeout(function(){
                            $window.location.href = "./article.html?i=" + $scope.question.id;
                        }, 1000);
                    }else{
                        toastr.error("修改问题失败");
                    }
                }, function(resp){
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
        result.alert = "文章标题长度范围为3~32个字";
        return result;
    }

    result.status = true;
    return result;
}