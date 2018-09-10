
var url;

function formatEdit(val,row) {
    return "<a href=''><img style='margin-top:4px' src='/static/images/edit.gif'></a>";
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
    $("#fm").form("submit",{
        url:url,
        onSubmit:function(){
          return $(this).form("validate");
        },
        success:function(data){
            var data = eval('('+data+')');
            if(data.success){
                $.messager.alert("系统提示","保存成功!");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            }else{
                $.messager.alert("系统提示",data.errorInfo);
            }
        }
    });

}

function openUserAddDialog() {
    $("#userName").removeAttr("readonly");
    $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
    url = "/admin/user/save";
}

function openUserModifyDialog(){
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1){
        $.messager.alert("系统提示","请选择一条要修改的数据!");
        return;
    }
    var row =selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","修改用户信息");
    $("#fm").form("load", row);
    $("#userName").attr("readonly","readonly");
    url="/admin/user/save?id="+row.id;
}

function deleteUser() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length != 1){
            $.messager.alert("系统提示","请选择一条要删除的数据!");
           return;
    }
    var id = selectedRows[0].id;
    $.messager.confirm("系统提示","您确定要删除这条数据吗?",function (r) {
        if(r){
            $.post("/admin/user/delete",{id:id},function(data){
                if(data.success){
                    $.messager.alert("系统提示","数据成功删除!");
                    $("#dg").datagrid("reload");
                }else{
                   $.messager.alert("系统提示",data.errorInfo);
                }
            },"json");
        }
    });

}


$(document).ready(function () {
    $("#dg").datagrid({
        onDblClickRow:function (index,row) {
            $("#dlg").dialog("setTitle","修改用户信息");
            $("#fm").form("load",row);
            $("#userName").attr("readonly","readonly");
            url = "/admin/user/save?id="+row.id;
        }
    });
});