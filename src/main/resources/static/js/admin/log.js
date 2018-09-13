
var url;

// noinspection JSUnusedGlobalSymbols
// noinspection JSUnusedLocalSymbols
// noinspection JSUnusedGlobalSymbols
function formatUser(val,row) {
    return val.trueName+"&nbsp;("+val.userName+")&nbsp;";
}

function searchLog() {
    $("#dg").datagrid('load',{
        "type":$("#s_type").combobox("getValue"),
        "user.trueName":$("#s_trueName").val(),
        "bTime":$("#s_btime").datetimebox("getValue"),
        "eTime":$("#s_etime").datetimebox("getValue")
    });
}


function resetValue() {
    $("#s_type").combobox("setValue","");
    $("#s_trueName").val("");
    $("#s_btime").datetimebox("setValue","");
    $("#s_etime").datetimebox("setValue","");
}



