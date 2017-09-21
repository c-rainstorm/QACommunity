let app = angular.module("app", []);

app.controller("appCtrl", function($scope, $http){

    $scope.color = [
        "badge-primary",
        "badge-secondary",
        "badge-success",
        "badge-danger",
        "badge-warning",
        "badge-info",
        "badge-light",
        "badge-dark"
    ]

    console.log("app loaded..");

    // 获取所有标签
    $http({
        url : "../getLabels.action" ,
        method : "get" ,
    }).then(function(resp){
        console.log(resp);

        $scope.labels = resp.data;
        for(var i = 0; i < $scope.labels.length; i++){
            $scope.labels[i].color = angular.copy($scope.color[i % 8]);
        }
    }, function(resp){
        httpErr(resp);
    });

    $scope.newLabel = ""
    $scope.addLabel = function(){

        console.log($scope.newLabel);

        // 添加标签
        $http({
            url : "../addLabel.action" ,
            method : "post" ,
            data : {
                title : angular.copy($scope.newLabel)
            } ,
        }).then(function(resp){
            console.log(resp);

            if(resp.data.result == "true"){
                toastr.success("标签 " + $scope.newLabel + " 添加成功.");                

                $scope.labels.push({
                    title : angular.copy($scope.newLabel),
                    color : angular.copy($scope.color[($scope.labels.length) % 8])
                })
            }else{
                toastr.error("标签 " + $scope.newLabel + " 添加失败.");
            }
            $scope.newLabel = "";
        }, function(resp){
            httpErr(resp);
        });

    }

});

function httpErr(msg) {
    console.log("http status : " + msg.status);
    console.log("http statusText : " + msg.statusText);
}