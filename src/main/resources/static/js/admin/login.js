/*<![CDATA[*/

$(document).ready(function() {
    //粒子背景特效
    $('body').particleground({
        dotColor: '#5cbdaa',
        lineColor: '#5cbdaa'
    });

    $("#loginBtn").click(function (){
        // noinspection JSUnresolvedFunction
        $.post("/user/login",
            {"userName":$("#userName").val(),"password":$("#password").val(),"pImageCode":$("#imageCode").val()},
            function (data) {
                if(data.success){
                    // noinspection JSUnresolvedVariable
                    if(data.roleSize===1){
                        // noinspection JSUnresolvedVariable
                        var roleId = data.roleList[0].id;
                        // noinspection JSUnresolvedFunction
                        $.post("/user/save/role",
                            {pRoleId:roleId},
                            function(data){
                                if(data.success){
                                    window.location.href="/main";
                                }
                            })
                    }else {
                        // noinspection JSJQueryEfficiency
                        $("#roleList").empty();
                        // noinspection JSUnresolvedVariable
                        var roles = data.roleList;
                        for (var i=0;i<roles.length;i++){
                            if(i===0){
                                $("#roleList").append("<input type='radio' checked=true name='role' value='"+roles[i].id+"'/>"+roles[i].name+"&nbsp;&nbsp;");
                            }else{
                                $("#roleList").append("<input type='radio' name='role' value='"+roles[i].id+"'/>"+roles[i].name+"&nbsp;&nbsp;");
                            }
                            $("#light").attr("style","display:block");
                            $("#fade").attr("style","display:block");
                        }
                    }
                }else{
                    // noinspection JSUnresolvedVariable
                    alert(data.errorInfo);
                }
            });
    });
});

function saveRole(){
    var roleId = $("input[name='role']:checked").val();
    // noinspection JSUnresolvedFunction
    $.post("/user/save/role",
        {pRoleId:roleId},
        function(data){
            if(data.success){
                window.location.href="/main";
            }
        })
}


/*]]>*/