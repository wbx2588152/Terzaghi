
$(function(){
    initTimeLimitSeckill();
    initYiYuanMiaoSha();
})

function initYiYuanMiaoSha(){
    $.ajax({
        url:'../seckill/querySeckillCommodityList',
        type:'post',
        data:{},
        dataType:'json',
        success:function(result){
            var html = "";
            var str = "";
            for(var i=0;i<result.length;i++){
                html += ' <li><a href="#">\n' +
                    '                <div><img src="'+result[i].commmondityImg+'"></div></a>\n' +
                    '                <div class="floor2_bottom_text"><input type="hidden" name="id" value="\'+result[i].id+\'">\n' +
                    '                    <div class="floor2_right_title">'+result[i].name+'</div>\n' +
                    '                    <div class="floor2_right_news">'+result[i].artNo+'</div>\n' +
                    '                    <div style="clear:both; height:15px;"></div>\n' +
                    '                    <div class="floor2_three">\n' +
                    '                        <div class="floor1_price"><div class="red floor1_three_fh">￥</div><div class="red floor1_three_size">'+result[i].seckillPrice+'</div><div class="yh">￥'+result[i].price+'</div></div>\n' +
                    '                        <div class="floor2_right">\n' +
                    '                            <div class="top">限量</div>\n' +
                    '                            <div class="bottom">'+result[i].seckillCount+'</div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                    <div style="clear:both;"></div>\n' +
                    '                </div>\n' +
                    '            </li>';
                if(i%2==0){
                    html += '<div class="center"></div>';
                }else{
                    html += '<div style="clear:both"></div>';
                }
            }
            $("#diyi").html(html);
        }
    })
}

function initTimeLimitSeckill(){
    $.ajax({
        url:'../seckill/queryTimeLimitSeckillList',
        type:'post',
        data:{},
        dataType:'json',
        success:function(result){
            var html = "";
            var str = "";
            for(var i=0;i<result.length;i++){
                html += '<div class="ms_border">\n' +
                    '            <div class="floor1_left"><img src="'+result[i].commmondityImg+'"></div>\n' +
                    '            <div class="floor1_right">\n' +
                    '            \t<div class="floor1_right_text"><input type="hidden" name="id" value="'+result[i].id+'">\n' +
                    '            \t\t<div class="floor1_right_title">'+result[i].name+'</div>\n' +
                    '                    <div class="floor1_right_news">'+result[i].artNo+'</div>\n' +
                    '                    <div class="floor1_time">\n' +
                    '                    \t<div class="time_tb"><img src="../images/time.png"></div>\n' +
                    '                        <div class="time_text">剩余：</div>\n' +
                    '                    \t<div id="countdown-timer1">\n' +
                    // '                            <div class="block"><span id="weeks"></span>0周</div>\n' +
                    // '                            <div class="space">&nbsp;</div>\n' +
                    // '                            <div class="block"><span id="daysLeft"></span>0天</div>\n' +
                    '                            <div class="space">&nbsp;</div>\n' +
                    '                            <div class="block"><span id="hours'+i+'"></span>0时</div>\n' +
                    '                            <div class="space">&nbsp;</div>\n' +
                    '                            <div class="block"><span id="minutes'+i+'"></span>分</div>\n' +
                    '                            <div class="space">&nbsp;</div>\n' +
                    '                            <div class="block"><span id="seconds'+i+'"></span>秒</div>\n' +
                    '                        </div>\n' +
                    '                        <div class="has"><img src="../images/has.png"></div>\n' +
                    '                        <div class="red" style="font-weight:bold;">'+result[i].residueCount+'</div>\n' +
                    '                        <div class="te">瓶已抢</div>\n' +
                    '                    </div>\n' +
                    '                    <div style="clear:both; height:85px;"></div>\n' +
                    '                    <div class="floor1_three">\n' +
                    '                    \t<div class="floor1_price"><div class="red floor1_three_fh">￥</div><div class="red floor1_three_size">'+result[i].seckillPrice+'</div><div class="yh">￥'+result[i].price+'</div></div>\n' +
                    '                        <a href="#"><div  class="subnow">马上抢</div></a>\n' +
                    '                    </div>\n' +
                    '            \t</div>\n' +
                    '            </div>\n' +
                    '            </div>\n' +
                    '            \n' +
                    '                   \t <div style="clear:both; height:20px"></div>';
                    daojishi(i,result[i].id,result[i].addTime);
            }
            $("#dier").html(html);
        }
    })
}
function daojishi(i,id,addTime) {
    $.ajax({
        url:"../seckill/queryDaoJiShi",
        type:"post",
        data:{
            id:id
        },
        dataType:"json",
        success:function(time){
            var timer = setInterval(function(){
                var temp = time --;
                $('#hours'+i).html(Math.floor(temp/60/60%24));
                $('#minutes'+i).html(Math.floor(temp/60%60));
                $('#seconds'+i).html(temp%60);
                if(temp<=0){
                    return;
                }
            },1000);
        }
    })
}

