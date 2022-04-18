function updateAvatarArea(){
    var fileInput = document.getElementById("avatar");
    var file = fileInput.files[0];
    //创建读取文件的对象
    var reader = new FileReader();
    //创建文件读取相关的变量
    var imgFile;
    //为文件读取成功设置事件
    reader.onload=function(e) {
        // alert('文件读取完成');
        imgFile = e.target.result;
        console.log(imgFile);
        $("#avatarArea").attr('src',imgFile);
    };

    //正式读取文件
    reader.readAsDataURL(file);
}

function updateBkArea(){
    var fileInput = document.getElementById("bkimg");
    var file = fileInput.files[0];
    //创建读取文件的对象
    var reader = new FileReader();
    //创建文件读取相关的变量
    var imgFile;
    //为文件读取成功设置事件
    reader.onload=function(e) {
        // alert('文件读取完成');
        imgFile = e.target.result;
        console.log(imgFile);
        $("#bkArea").attr('src',imgFile);
    };
    //正式读取文件
    reader.readAsDataURL(file);
}

$('#upbk').click(function () {
    var formData=new FormData();
    if (!$('#bkimg')[0].files[0]){
        layer.alert("上传文件不能为空！");
        return;
    }
    formData.append('file',$('#bkimg')[0].files[0]);
    $.ajax({
        url:'/upload/bkimg',
        type:'post',
        data:formData,
        processData:false,
        contentType:false,
        success:function (result) {
            if (result.code==200){
                layer.alert("背景图更新成功！");
            }else {
                layer.alert("更新失败!");
            }
        }
    });
});

$('#upavatar').click(function () {
    var formData=new FormData();
    if (!$('#avatar')[0].files[0]){
        layer.alert("上传文件不能为空！");
        return;
    }
    formData.append('file',$('#avatar')[0].files[0]);
    $.ajax({
        url:'/upload/avatar',
        type:'post',
        data:formData,
        processData:false,
        contentType:false,
        success:function (result) {
            if (result.code==200){
                layer.alert("头像更新成功！");
            }else {
                layer.alert("头像更新失败!");
            }
        }
    });
});

$('#upIntro').click(function () {
    var formData=new FormData();
    formData.append('content',$('#profile').val());
    $.ajax({
        url:'/updateInfo/intro',
        type:'post',
        data:formData,
        processData:false,
        contentType:false,
        success:function (result) {
            if (result.code==200){
                layer.alert("简介更新成功！");
                $('#profile').val('');
            }
            else {
                layer.alert("更新失败，请重试~");
            }
        }
    });
});