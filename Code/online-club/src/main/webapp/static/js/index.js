function reloadPage() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    location.reload();
}


//富文本编辑器=======start
$(function () {
    $('#bxSlider').bxSlider({
        captions: true,//自动控制
        auto: true,
        pager:null,
        minSlides: 3,
        maxSlides: 3,
        moveSlides: 1,
        slideWidth: 300,
        randomStart:true,
        controls:true,
        autoHover:true
    });
});

$(function (){
    $("[data-toggle='popover']").popover();
});
$(document).ready(function(){
    $('#summernote').summernote({
        focus:true,
        height:250,
        lang:'zh-CN',
        placeholder:'开始你的创作...',
        toolbar:[
            ['style', ['style']],
            ['font', ['bold', 'underline']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['picture']],
            ['view', ['fullscreen']],
        ],
        callbacks:{
            onImageUpload:function(files){
                uploadImg('#summernote',files);
            }
        }
    });
});

//富文本编辑器的图片上传
function uploadImg(editor,files){
    var formData=new FormData();
    formData.append("file",files[0]);
    console.log("开始图片上传");
    $.ajax({
        url:'active/file/upload',
        type:'post',
        data:formData,
        processData:false,
        contentType:false,
        success:function(res){
            if(res.code==200){
                console.log(res.data.imgPath);
                $('#summernote').summernote('insertImage',res.data.imgPath,'img');
            }
            else{
                layer.msg(res.code,{shift:5});
            }
        }
    });
}

//富文本编辑器==========end