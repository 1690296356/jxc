
var url;

// noinspection JSUnusedGlobalSymbols
function formatEdit(val,row) {
    return "<a href=\"javascript:openRoleChooseDialog('"+row.roles+"',"+row.id+")\"><img style='margin-top:4px' src='/static/images/edit.gif' /></a>";
}

// noinspection JSUnusedGlobalSymbols
function openRoleChooseDialog(roles,userId){
    var rolesArr = roles.split(",");
    $("#dlg2").dialog("open").dialog("setTitle","选择角色");
    // noinspection JSUnusedLocalSymbols
    $("#dg2").datagrid({
        url:'/power/admin/role/list/all-role',
        onLoadSuccess:function (data) {
            // noinspection JSJQueryEfficiency
            var allRows=$("#dg2").datagrid("getRows");
            for(var i=0;i<allRows.length;i++){
                var name = allRows[i].name;
                if($.inArray(name,rolesArr)>=0){
                    $("#dg2").datagrid("checkRow",i);
                }
            }
        }
    });
    $("#userId").val(userId);
}

function searchUser() {
    $("#dg").datagrid('load',{
        "userName":$("#s_userName").val()
    });
}


function resetValue() {
    $("#userName").val("");
    $("#password").val("");
    $("#trueName").val("");
    $("#remarks").val("");
}

function closeUserDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function saveUser() {
    // noinspection JSDuplicatedDeclaration
    $("#fm").form("submit",{
        url:url,
        onSubmit:function(){
            return $(this).form("validate");
        },
        success:function(data){
            // noinspection JSDuplicatedDeclaration
            var data = eval('('+data+')');
            if(data.success){
                $.messager.alert("系统提示","保存成功!");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }else{
                // noinspection JSUnresolvedVariable
                $.messager.alert("系统提示",data.errorInfo);
            }
        }
    });

}

function openUserAddDialog() {
    $("#userName").removeAttr("readonly");
    $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
    url = "/power/admin/user/save";
}

function openUserModifyDialog(){
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length !== 1){
        $.messager.alert("系统提示","请选择一条要修改的数据!");
        return;
    }
    var row =selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","修改用户信息");
    $("#fm").form("load", row);
    $("#userName").attr("readonly","readonly");
    url="/power/admin/user/save?id="+row.id;
}

function deleteUser() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length !== 1){
        $.messager.alert("系统提示","请选择一条要删除的数据!");
        return;
    }
    var id = selectedRows[0].id;
    $.messager.confirm("系统提示","您确定要删除这条数据吗?",function (r) {
        if(r){
            // noinspection JSUnresolvedFunction
            $.post("/power/admin/user/delete",{id:id},function(data){
                if(data.success){
                    $.messager.alert("系统提示","数据成功删除!");
                    $("#dg").datagrid("reload");
                }else{
                    // noinspection JSUnresolvedVariable
                    $.messager.alert("系统提示",data.errorInfo);
                }
            },"json");
        }
    });

}


$(document).ready(function () {
    $("#dg").datagrid({
        onDblClickRow:function (index,row) {
            $("#dlg").dialog("open").dialog("setTitle","修改用户信息");
            $("#fm").form("load",row);
            $("#userName").attr("readonly","readonly");
            url = "/power/admin/user/save?id="+row.id;
        }
    });
});

function saveRoleSet(){
    var userID=$("#userId").val();
    var selectedRows=$("#dg2").datagrid("getSelections");
    var strRoleIds = [];
    for(var i=0;i<selectedRows.length;i++){
        strRoleIds.push(selectedRows[i].id);
    }
    var roleIds = strRoleIds.join(",");

    // noinspection JSUnresolvedFunction
    $.post("admin/user/save/role",{roleIDS:roleIds,userID:userID},function(data){
        if(data.success){
            closeRoleSetDialog();
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示","提交失败，请联系管理员!");
        }
    });
}

function closeRoleSetDialog(){
    $("#dlg2").dialog("close");
}