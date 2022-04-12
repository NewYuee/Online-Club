//根据id删除用户
function delUserByid(uid) {
    $.ajax({
        url:'/user/delete',
        data:'uid='+uid,
        type:'POST',
        async:false,
        success:function (result) {
            if (result.code==200){
                return 1;
            }
            else {
                console.log("删除用户失败，uid="+uid);
                return 0;
            }
        }
    })
}

//根据id删除活动
function delActiveByid(aid) {
    $.ajax({
        url:'/active/admin/deleteActive',
        data:'aid='+aid,
        type:'POST',
        async:false,
        success:function (result) {
            if (result.code==200){
                return 1;
            }
            else {
                console.log("删除活动失败，aid="+id);
                return 0;
            }
        }
    })
}

function delCommentByid(cid) {
    $.ajax({
        url:'/comment/admin/delete',
        data:'cid='+cid,
        type:'POST',
        async:false,
        success:function (result) {
            if (result.code==200){
                return 1;
            }
            else {
                console.log("删除评论失败，uid="+uid);
                return 0;
            }
        }
    })
}

function reloadPage() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    location.reload();
}