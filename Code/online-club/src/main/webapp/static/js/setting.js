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