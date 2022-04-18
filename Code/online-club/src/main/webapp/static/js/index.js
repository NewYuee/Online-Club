function reloadPage() {
    var index = layer.load(1, {
        shade: [0.1, '#fff'] //0.1透明度的白色背景
    });
    location.reload();
}

$(function () {
    $('#bxSlider').bxSlider({
        captions: true,//自动控制
        auto: true,
        pager:null,
        touchEnabled: false,
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

//富文本编辑器=======start
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

//发送一般动态
function sendshortActive(){
    var formData=new FormData(document.getElementById('active_form'));
    var input=$('#inputTextarea').val();
    console.log("input=="+input);
    if(input.length==0){
        layer.alert("内容不能为空！");
        return;
    }
    formData.append("inputStr",input);
    $.ajax({
        type:'POST',
        url:'active/short/insertOne',
        data:formData,
        processData:false,
        contentType:false,
        success:function(res){
            if(res.code==200){
                layer.msg("发送成功");
                setTimeout(reloadPage,1000);
            }
            else{
                layer.alert(res.data.dataInfo)
            }
        }
    });
}

//发送文章动态
function sendArticle(inputStr){
    var formData=new FormData(document.getElementById('article-form'));
    formData.append("inputStr",inputStr);
    $.ajax({
        url:'active/article/insertOne',
        type:'post',
        data:formData,
        processData:false,
        contentType:false,
        success:function(res){
            if(res.code==200){
                layer.msg("发送成功");
                setTimeout(reloadPage,1000);
            }
            else{
                layer.alert(res.data.dataInfo);
            }
        }
    });
}

function sendActive(){
    var markupStr = $('#summernote').summernote('code');
    if($('#home-tab').parent('li').hasClass('active')){
        sendshortActive();
    }
    else if($('#article-tab').parent('li').hasClass('active')){
        if($('#summernote').summernote('isEmpty')){
            layer.alert("请输入内容");
            return;
        }
        sendArticle(markupStr);
    }
    else{
        layer.msg("请选择你要发送的动态类型");
    }
}
