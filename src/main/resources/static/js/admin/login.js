/*<![CDATA[*/

$(document).ready(function() {
    //粒子背景特效
    $('body').particleground({
        dotColor: '#5cbdaa',
        lineColor: '#5cbdaa'
    });

    $("#loginBtn").click(function (){
        $.post("/user/login",
            {"userName":$("#userName").val(),"password":$("#password").val(),"pImageCode":$("#imageCode").val()},
            function (data) {
                if(data.success){
                    if(data.roleSize==1){
                        var roleId = data.roleList[0].id;
                        $.post("/user/save/role",
                            {pRoleId:roleId},
                            function(data){
                                if(data.success){
                                    window.location.href="/main";
                                }
                            })
                    }else {
                        $("#roleList").empty();
                        var roles = data.roleList;
                        for (var i=0;i<roles.length;i++){
                            if(i==0){
                                $("#roleList").append("<input type='radio' checked=true name='role' value='"+roles[i].id+"'/>"+roles[i].name+"&nbsp;&nbsp;");
                            }else{
                                $("#roleList").append("<input type='radio' name='role' value='"+roles[i].id+"'/>"+roles[i].name+"&nbsp;&nbsp;");
                            }
                            $("#light").attr("style","display:block");
                            $("#fade").attr("style","display:block");
                        }
                    }
                }else{
                    alert(data.errorInfo);
                }
            });
    });
});

function saveRole(){
    var roleId = $("input[name='role']:checked").val();
    $.post("/user/save/role",
        {pRoleId:roleId},
        function(data){
            if(data.success){
                window.location.href="/main";
            }
        })
}


/*]]>*/