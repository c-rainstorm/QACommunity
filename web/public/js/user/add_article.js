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

    $scope.session = {};
    // 获取 session
    $http({
        url: "../getUserSession.action",
        method: "get"
    }).then(function (resp) {
        console.log("session : ");
        console.log(resp);

        if(resp.data.userLoginStatus == false){
            console.log("no session.");

            $window.location.href = "./login.html";
            return;
        }

        $scope.session.user = resp.data;
        // $scope.session.user = {
        //     id : 1
        // }

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

            $scope.labelInputSearch = ""

            // 获取标签
            $http({
                url : "../getLabels.action" ,
                method : "get" ,
            }).then(function(resp){
                console.log("labels : ");
                console.log(resp);

                $scope.labels = resp.data;
                for(var i = 0; i < $scope.labels.length; i++){
                    $scope.labels[i].color = angular.copy($scope.color[i % 7]);
                }
            }, function(resp){
                httpErr(resp);
            });

            // 添加标签
            $scope.addLabelToArticle = function(id){
                console.log("add label to article. : " + id);

                for(var i = 0; i < $scope.article.labels.length; i++){
                    if($scope.article.labels[i].id == id){
                        console.log("already added.");
                        return;
                    }
                }

                let labelTmp = {};
                for(var i = 0; i < $scope.labels.length; i++){
                    if($scope.labels[i].id == id){
                        labelTmp = angular.copy($scope.labels[i]);
                        console.log(labelTmp);
                        break;
                    }
                }
                $scope.article.labels.push(angular.copy(labelTmp));
            }

            //删除标签
            $scope.deleteLabelFromArticle = function(index){
                console.log("delete label from article : " + index);

                $scope.article.labels.splice(index, 1);
                console.log("delete successed.");
            }

            // 提交文章
            $scope.article = {
                title: "",
                content: "",
                author_id: angular.copy($scope.user.id),
                labels : []
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

                    if (resp.data.result == "true" || resp.data.result == true) {
                        toastr.success("添加文章成功");

                        $timeout(function(){
                            $window.location.href = "./article.html?id=" + resp.data.id;
                        }, 1000);
                    } else {
                        toastr.error("添加文章失败");
                    }
                }, function (resp) {
                    httpErr(resp);
                });

            }

        }, function (resp) {
            httpErr(resp);
        });

    }, function (resp) {
        httpErr
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