/**
 * Created by Williamhiler on 2016/11/9.
 */


var videoMask = document.getElementById('videoMask'),
    carousel = videoMask.getElementsByClassName('carousel'),
    nextBtn = document.getElementById('nextBtn'),
    lastBtn = document.getElementById('lastBtn'),
    playBtn = document.getElementById('btnPlay'),
    player = document.getElementById('player'),
    time = 5000; //循环播放间隔

var index = 0;
var timer =setInterval(nextView,time);
nextBtn.onclick = function (){
    clearInterval(timer);
    nextView();
    timer =setInterval(nextView,time)
}
lastBtn.onclick = function (){
    clearInterval(timer);

    carousel[index].style.display = "none";
    if(index == 0) {
        index = carousel.length-1;
    }else {
        index -= 1;
    }
    carousel[index].style.display = "block"

    timer =setInterval(nextView,time)

}


playBtn.onclick = function (){
    videoMask.classList.remove('poster_img')
    if(player.paused) {
        playVideo()
    } else {
        pauseVideo()
    }
}
player.onclick = function () {
    pauseVideo();
}
// 监听视频播放完毕时
player.addEventListener("ended",function(){
    videoMask.classList.add('poster_img')
    pauseVideo()
},false);
// 监听空格键
document.onkeydown = function (e){
    if(e.keyCode == 32) {
        e.preventDefault();
        if(player.paused) {
            playVideo()
        } else {
            pauseVideo()
        }
    }
}
function nextView() {
    carousel[index].style.display = "none";
    if(index == carousel.length-1) {
        index = 0;
    }else {
        index += 1;
    }
    carousel[index].style.display = "block"
}

// 播放
function playVideo (){
    player.play();
    videoMask.style.display = "none";
    playBtn.classList.toggle('played');
    playBtn.classList.toggle('paused');
    playBtn.querySelector('.play').style.display = "none";
    playBtn.querySelector('.pause').style.display = "block";
}
// 暂停
function pauseVideo(){
    player.pause();
    videoMask.style.display = "block";
    playBtn.classList.toggle('played');
    playBtn.classList.toggle('paused');
    playBtn.querySelector('.play').style.display = "block";
    playBtn.querySelector('.pause').style.display = "none";
}
