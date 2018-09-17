
$(document).ready(function () {
    $("#tree").tree({
        lines:true,
        url:'admin/goodsType/loadTreeInfo',
        onLoadSuccess:function(){
            $("#tree").tree("expandAll");
        },
        onClick:function (node) {
            if(node.attributes.state==0){//假如是叶子节点，删除按钮恢复可用
                $("#del").linkbutton("enable");
            }else{
                $("#del").linkbutton("disable");
            }
        }
    });
});

function openGoodsTypeAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加商品信息");
}

function deleteGoodsType() {
    var node=$("#tree").tree("getSelected");//获取选中节点
    var id=node.id;
    $.post("/basicData/admin/goodsType/delete",{id:id},function(data){
        if(data.success){
            $.messager.alert("系统提示","数据成功删除!");
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

   var goodsTypeName = $("#goodsTypeName").val();
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
    $("#goodsTypeName").val();
}


//商品管理

function formatGoodsTypeId(val, row){
    return row.type.id;
}

function formatGoodsTypeName(val, row) {
    return row.type.name;
}

function formatPurchasePrice(val, row) {
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