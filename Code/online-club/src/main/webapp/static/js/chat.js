$.ajax(to_footer());
function to_footer() {
    var div = document.getElementById('content');
    div.scrollTop = div.scrollHeight;
}

//发送消息
function setMsginHtml(text,userProfilePic){
    //let text = document.querySelector('#textarea').value;
    let item = document.createElement('div');
    item.className = 'item item-right';
    item.innerHTML = '<div class="bubble bubble-left">'+text+'</div><div class="avatar"><img src="'+userProfilePic+'"/></div>';
    document.querySelector('#messageContent').appendChild(item);
    document.querySelector('#textarea').value = '';
    document.querySelector('#textarea').focus();
    //滚动条置底
    let height = document.querySelector('.content').scrollHeight;
    document.querySelector(".content").scrollTop = height;
}

//接收消息
function receive(innerHTML,toUserProfilePic) {
    let item = document.createElement('div');
    item.className = 'item item-left';
    item.innerHTML = '<div class="avatar"><img src="'+toUserProfilePic+'" /></div><div class="bubble bubble-right">'+innerHTML+'</div>';
    document.querySelector('#messageContent').appendChild(item);
    document.querySelector('#textarea').focus();
    //滚动条置底
    let height = document.querySelector('.content').scrollHeight;
    document.querySelector(".content").scrollTop = height;
}
