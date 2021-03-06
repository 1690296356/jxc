/*<![CDATA[*/
function showTime() {
    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth()+1;
    this.date = date.getDate();
    this.day = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"][date.getDay()];
    this.hour = date.getHours() < 10 ? "0"+date.getHours(): date.getHours();
    this.minute = date.getMinutes() < 10 ? "0"+date.getMinutes(): date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0"+date.getSeconds(): date.getSeconds();

    $("#clock").text("现在是:"+this.year +"年"+this.month+"月"+this.date+"日"+ this.hour +":"+this.minute+":"+this.second+" "+this.day);
}




$(document).ready(function(){

    window.setInterval("showTime()", 1000);

    $("#userInfo").load("/user/load/user-info");//加载用户信息

    var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:99%' src='/common/stockSearch.html'></iframe>";
    $("#tabs").tabs("add",{
        title:"首页",
        iconCls:"menu-25",
        closable:false,
        content:content
    });
    $("#tree").tree({
        lines:true,
        url:'/user/load/menu-info?pParentID=-1',
        onLoadSuccess:function(){
            $("#tree").tree("expandAll");
        },
        onClick:function (node) {
            if(node.id===6040){
                openPasswordModifyDialog();
            }else if(node.id===6050){
                logout();
            }else if(eval("("+node.attributes+")").url){
                openTab(node);
            }
        }


    });












// 监听右键事件，创建右键菜单
    // noinspection JSJQueryEfficiency
    $('#tabs').tabs({
        onContextMenu:function(e, title,index){
            e.preventDefault();
            if(index>0){
                $('#menu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });

// 右键菜单click
    $("#menu").menu({
        onClick : function (item) {
            closeTab(this, item.name);
        }
    });

    function closeTab(menu, type) {
        // noinspection JSJQueryEfficiency
        var allTabs = $("#tabs").tabs('tabs');
        var allTabtitle = [];
        $.each(allTabs, function (i, n) {
            var opt = $(n).panel('options');
            if (opt.closable)
                allTabtitle.push(opt.title);
        });
        var curTabTitle = $(menu).data("tabTitle");
        // noinspection JSJQueryEfficiency
        var curTabIndex = $("#tabs").tabs("getTabIndex", $("#tabs").tabs("getTab", curTabTitle));
        switch (type) {
            case "1": // 刷新当前标签页
                // noinspection JSUnusedLocalSymbols
                // noinspection JSJQueryEfficiency
                // noinspection JSUnusedLocalSymbols
                // noinspection JSJQueryEfficiency
                var panel = $("#tabs").tabs("getTab", curTabTitle).panel("refresh");
                break;
            case "2": // 关闭当前标签页
                // noinspection JSJQueryEfficiency
                $("#tabs").tabs("close", curTabIndex);
                return false;
                // noinspection UnreachableCodeJS
                break;
            case "3": // 关闭全部标签页
                // noinspection JSDuplicatedDeclaration
                for (var i = 0; i < allTabtitle.length; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                break;
            case "4": // 关闭其他标签页
                // noinspection JSDuplicatedDeclaration
                for (var i = 0; i < allTabtitle.length; i++) {
                    if (curTabTitle !== allTabtitle[i])
                        $('#tabs').tabs('close', allTabtitle[i]);
                }
                // noinspection JSJQueryEfficiency
                $('#tabs').tabs('select', curTabTitle);
                break;
            case "5": // 关闭右侧标签页
                // noinspection JSDuplicatedDeclaration
                for (var i = curTabIndex; i < allTabtitle.length; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                // noinspection JSJQueryEfficiency
                $('#tabs').tabs('select', curTabTitle);
                break;
            case "6": // 关闭左侧标签页
                // noinspection JSDuplicatedDeclaration
                for (var i = 0; i < curTabIndex - 1; i++) {
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                // noinspection JSJQueryEfficiency
                $('#tabs').tabs('select', curTabTitle);
                break;

        }

    }
    function openTab(node) {
        // noinspection JSJQueryEfficiency
        if($("#tabs").tabs("exists",node.text)){
            $("#tabs").tabs("select",node.text)
        }else{
            var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:99%' src='"+eval("("+node.attributes+")").url+"'></iframe>";

            $("#tabs").tabs("add",{
                title:node.text,
                iconCls:node.iconCls,
                closable:true,
                content:content
            });
        }
    }
    function openPasswordModifyDialog(){
        $("#dlg").dialog("open").dialog("setTitle","修改密码");
        url="/power/admin/user/modify/password";
    }

    function logout(){
        $.messager.confirm("系统提示","您确定要退出系统吗?",function (r) {
            if(r){
                window.location.href="/power/admin/user/logout";
            }
        });
    }
});

function modifyPassword(){
    // noinspection JSDuplicatedDeclaration
    $("#fm").form("submit",{
        url:url,
        onSubmit:function(){
            var newPassword = $("#newPassword").val();
            var newPasswordSec= $("#newPasswordSec").val();
            if(!$(this).form("validate")){
                return false;
            }
            if (newPassword !== newPasswordSec){
                $.messager.alert("系统提示","新密码输入错误!");
                return false;
            }
            return true;
        },
        success:function (data) {
            // noinspection JSDuplicatedDeclaration
            var data = eval('('+data+')');
            if(data.success){
                $.messager.alert("系统提示","密码修改成功,下一次登录生效!");
                resetValue();
                $("#dlg").dialog("close");
            }else{
                $.messager.alert("系统提示","密码修改失败，请联系管理员!");
            }
        }
    });
}

function closePasswordModifyDialog(){
    resetValue();
    $("#dlg").dialog("close");
}


function resetValue() {
    $("#newPassword").val("");
    $("#newPasswordSec").val("");
}

/*]]>*/