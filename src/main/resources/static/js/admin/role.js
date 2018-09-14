
var url;

// noinspection JSUnusedGlobalSymbols
function formatEdit(val,row) {
    return "<a href=\"javascript:openMenuSetDialog("+row.id+")\"><img style='margin-top:4px' src='/static/images/edit.gif' /></a>";
}

// noinspection JSUnusedGlobalSymbols
function openMenuSetDialog(roleId){
    $("#dlg2").dialog("open").dialog("setTitle","权限菜单设置");
    $("#menuTree").tree({
        lines:true,
        checkbox:true,
        cascadeCheck:false,
        url:'/power/admin/role/load/check-menu-info?parentId=-1&roleId='+roleId,
        onLoadSuccess:function ( ) {
           $("#menuTree").tree("expandAll");
        },
        onCheck:function(node,checked){
            if(checked){
                checkNode($("#menuTree").tree("getParent",node.target));
            }
        }
    });
    $("#roleId").val(roleId);
}

function checkNode(node){
    if(!node){

    }else{
        // noinspection JSJQueryEfficiency
        checkNode($("#menuTree").tree("getParent",node.target));
        // noinspection JSJQueryEfficiency
        $("#menuTree").tree("check",node.target);
    }
}

function searchRole() {
    $("#dg").datagrid('load',{
        "name":$("#s_roleName").val()
    });
}


function resetValue() {
    $("#name").val("");
    $("#remarks").val("");
}

function closeRoleDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function saveRole() {
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

function openRoleAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加角色信息");
    url = "/power/admin/role/save";
}

function openRoleModifyDialog(){
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length !== 1){
        $.messager.alert("系统提示","请选择一条要修改的数据!");
        return;
    }
    var row =selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle","修改角色信息");
    $("#fm").form("load", row);
    url="/power/admin/role/save?id="+row.id;
}

function deleteRole() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length !== 1){
        $.messager.alert("系统提示","请选择一条要删除的数据!");
        return;
    }
    var id = selectedRows[0].id;
    $.messager.confirm("系统提示","您确定要删除这条数据吗?",function (r) {
        if(r){
            // noinspection JSUnresolvedFunction
            $.post("/power/admin/role/delete",{id:id},function(data){
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
            $("#dlg").dialog("open").dialog("setTitle","修改角色信息");
            $("#fm").form("load",row);
            url = "/power/admin/role/save?id="+row.id;
        }
    });
});

function saveMenuSet(){
    var roleId=$("#roleId").val();
    var nodes=$("#menuTree").tree("getChecked");
    var menuArrIds = [];
    for(var i=0;i<nodes.length;i++){
        menuArrIds.push(nodes[i].id);
    }
    var menuIds = menuArrIds.join(",");

    // noinspection JSUnresolvedFunction
    $.post("admin/role/save/menu-set",{menuIds:menuIds,roleId:roleId},function(data){
        if(data.success){
            closeMenuSetDialog();
        }else{
            $.messager.alert("系统提示","提交失败，请联系管理员!");
        }
    },"json");
}

function closeMenuSetDialog(){
    $("#dlg2").dialog("close");
}