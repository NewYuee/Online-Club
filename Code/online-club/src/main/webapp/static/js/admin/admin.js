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

function delAppById(appId) {
    $.ajax({
        url:'/application/delete/'+appId,
        type:'GET',
        async:false,
        success:function (result) {
            if (result.code==200){
                return 1;
            }
            else {
                console.log("删除申请失败，appid="+appId);
                return 0;
            }
        }
    })
}

function delClubMember(memberId) {
    $.ajax({
        url:'/club/delete/member',
        data:'memberId='+memberId,
        type:'POST',
        async:false,
        success:function (result) {
            if (result.code==200){
                return 1;
            }
            else {
                console.log("删除成员失败，id="+memberId);
                return 0;
            }
        }
    })
}

function dealApplyStatus(appid,type,value) {
    $.ajax({
        url:'/club/dealApply',
        data:{'appId':appid,'type':type,'other':value},
        type:'POST',
        dataType:'json',
        async:false,
        success:function (result) {
            if (result.code!=200){
                console.log("操作失败,稍后再试");
                return 0;
            }
        }
    });
}