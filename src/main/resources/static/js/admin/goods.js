var url;

$(document).ready(function () {
    $("#tree").tree({
        lines:true,
        url:'/basicData/admin/goodsType/loadTreeInfo',
        onLoadSuccess:function(){
            $("#tree").tree("expandAll");
        },
        onClick:function (node) {
            if(node.attributes.state==0){//假如是叶子节点，删除按钮恢复可用
                $("#del").linkbutton("enable");
            }else{
                $("#del").linkbutton("disable");
            }
            $("#dg").datagrid('load',{
                "name":$("#s_name").val(),
                "type.id":node.id
            });
        }
    });
});

function openGoodsTypeAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","新增商品类别");
}

function deleteGoodsType() {
    var node=$("#tree").tree("getSelected");//获取选中节点
    var id=node.id;
    $.post("/basicData/admin/goodsType/delete",{id:id},function(data){
        if(data.success){

            $("#tree").tree("reload");
            $("#del").linkbutton("disable");
        }else{
            // noinspection JSUnresolvedVariable
            $.messager.alert("系统提示",data.errorInfo);
        }
    },"json");

}


function saveGoodsType() {
    if(!$("#fm").form("validate")){
        return;
    }
    var goodsTypeName=$('#goodsTypeName').val();

    var node = $("#tree").tree("getSelected"); //获取选中节点
    var parentId;
    if(node === null){
        parentId =1;
    }else{
        parentId =node.id;
    }
    $.post("/basicData/admin/goodsType/save",{name:goodsTypeName,parentId:parentId},function(data){
        if(data.success){
            $("#tree").tree("reload");
            closeGoodsTypeDialog();
        }else{
            $.messager.alert("系统提示","提交失败，请联系管理员!");
        }
    },"json");

}

function closeGoodsTypeDialog() {
    $("#dlg").dialog("close");
    $('#goodsTypeName').val('');
}


//商品管理

function formatGoodsTypeId(val, row){
    return row.type.id;
}

function formatGoodsTypeName(val, row) {
    return row.type.name;
}

function formatPurchasingPrice(val,row){
    return "¥"+val;
}

function formatSellingPrice(val, row) {
    return "¥"+val;
}

function searchGoods() {
    $("#dg").datagrid('load',{
        "name":$("#s_name").val()
    });
}

function openGoodsAddDialog() {
    $("#dlg2").dialog("open").dialog("setTitle","添加商品信息");
    url="/power/admin/goods/save";
    var node = $("#tree").tree("getSelected");
    if(node !=null && node.id != 1){
        $("#typeId").val(node.id);
        $("#typeName").val(node.text);
    }else{
        $("#typeId").val("");
        $("#typeName").val("");
    }
    $.post("/power/admin/goods/genGoodsCode",{},function(data){
        $("#code").val(data);
    });
    $("#saveAddAddNextButton").show();
}

function openChooseGoodsTypeDialog() {
    $("#dlg3").dialog("open").dialog("setTitle","选择商品类别");
    $("#typeTree").tree({
        url:'/basicData/admin/goodsType/loadTreeInfo',
        onLoadSuccess:function(){
            var rootNode=$("#typeTree").tree("getRoot");
            $("#typeTree").tree("expand", rootNode.target);
        }
    });
}

function resetValue(){
    $("#typeId").val("");
    $("#typeName").val("");
    $("#code").val("");
    $("#name").val("");
    $("#model").val("");
    $("#unit").combobox("setValue","");
    $("#purchasingPrice").numberbox("setValue","");
    $("#sellingPrice").numberbox("setValue","");
    $("#minNum").numberbox("setValue","");
    $("#producer").val("");
    $("#remarks").val("");
}

function saveGoodsTypeChoose(){
    var node =$("#typeTree").tree("getSelected");
    if(node!=null && node.id != 1){
        $("#typeId").val(node.id);
        $("#typeName").val(node.text);
    }
    $("#dlg3").dialog("close");
}


function saveGoods(type) {
    $("#fm2").form("submit", {
        url: url,
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {
            var data = eval('(' + data + ')');
            if (data.success) {
                $.messager.alert("系统提示", "保存成功");
                resetValue();
                if (type === 1) {
                    $("#dlg2").dialog("close");
                } else if (type === 2) {
                    var node = $("#tree").tree("getSelected");
                    if (node != null && node.id != 1) {
                        $("#typeId").val(node.id);
                        $("#typeName").val(node.text);
                    } else {
                        $("#typeId").val("");
                        $("#typeName").val("");
                    }
                    $.post("/power/admin/goods/genGoodsCode", {}, function (data) {
                        $("#code").val(data);
                    });
                }
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("系统提示", data.errorInfo);
            }
        }
    });
}

function closeGoodsDialog() {
    resetValue();
    $("#dlg2").dialog("close");
}


function closeGoodsTypeChooseDialog() {
    $("#dlg3").dialog("close");
}


function closeGoodsDialog(){
    resetValue();
    $("#dlg2").dialog("close");
}

function openGoodsModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要修改的数据");
        return;
    }
    var row = selectedRows[0];
    $("#dlg2").dialog("open").dialog("setTitle", "修改商品信息");
    $("#fm2").form("load", row);
    $("#typeId").val(row.type.id);
    $("#typeName").val(row.type.name);
    url = "/power/admin/goods/save?id="+row.id;
    $("#saveAddNextButton").hide();
}


function deleteGoods(){
    var selectedRows = $("#dg").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","数据已成功删除!");
        return;
        $("#dg").datagrid("reload");
    }

    var id=selectedRows[0].id;
    $.messager.confirm("系统提示","您确定要删除这条数据吗?",function (r) {
        if(r){
            $.post("/power/admin/goods/delete",{id:id},function (data) {
               if(data.success){
                   $.messager.alert("系统提示","数据已成功删除!");
                   $("#dg").datagrid("reload");
               }else{
                   $.messager.alert("系统提示","<font color=red>"+data.errorInfo+"</font>");
               }
            },"json");
        }
    });
}

//商品单位模块
function openChooseGoodsUnitDialog(){
    $("#dlg4").dialog("open").dialog("setTitle","单位");
}

function openGoodsUnitAddDialog() {
    $("#dlg5").dialog("open").dialog("setTitle","添加单位信息");
    url = "/power/admin/goodsUnit/save";
}

function deleteGoodsUnit(){
    var selectedRows = $("#dg4").datagrid("getSelections");
    if(selectedRows.length!=1){
        $.messager.alert("系统提示","请选择一条要删除的数据！");
        return ;
    }
    var id = selectedRows[0].id;
    $.messager.confirm("系统提示","您确定要删除这条数据吗?",function(r){
        if(r){
            $.post("/admin/goodsUnit/delete",{id:id},function(result){
                if(result.success){
                    $("#unit").combobox("reload");
                    $("#dg4").datagrid("reload");
                }else{
                    $.messager.alert("系统提示", result.errorInfo);
                }
            },"json");
        }
    });
}


function chooseGoodsUnit(){
    var selectedRows = $("#dg4").datagrid("getSelections");
    if(selectedRows.length!==1){
        $.messager.alert("系统提示","请选择单位！");
        return;
    }
    var name = selectedRows[0].name;
    $("#unit").combobox("reload");
    $("#unit").combobox("setValue",name);
    $("#dlg4").dialog("close");
}

function closeGoodsUnitDialog() {
    $("#dlg4").dialog("close");
}

function saveGoodsUnit() {
    $("#fm5").form("submit",{
        url:url,
        onSubmit:function () {
            return $(this).form("validate");
        },
        success:function(data){
            var data = eval('('+data+')');
            if(data.success){
                $.messager.alert("系统提示","保存成功!");
                closeGoodsUnitAddDialog();
                $("#unit").combobox("reload");
                $("#dg4").datagrid("reload");
            }else{
                $.messager.alert("系统提示",data.errorInfo);
            }
        }
    });
}

function closeGoodsUnitAddDialog(){
    $("#dlg5").dialog("close");
    $("#goodsUnitName").val("");
}




$(document).ready(function() {

    $("#dg4").datagrid({
        onDblClickRow:function(index,row){
            var name=row.name;
            $("#unit").combobox("reload");
            $("#unit").combobox("setValue",name);
            $("#dlg4").dialog("close");
        }
    });

});