$(function(){
    initSaveOrderForm();
})

    function initSaveOrderForm(){
        $.ajax({
            url:'../seckill/queryRegionList',
            type:'post',
            data:{},
            dataType:'json',
            success:function(result){
                var html = "";/*onclick="biankuang('+i+')"*/
                for(var i=0;i<result.length;i++){
                    html +='<a class="address-item" id="address'+i+'" onclick="biankuang('+i+')">\n' +
                        '                <div class="address-title">\n' +
                        '                    <span class="city">'+result[i].sheng+' '+result[i].shi+'</span> <span class="name">（ '+result[i].name+' 收 ）</span>\n' +
                        '                </div>\n' +
                        '                <div class="address-detail"> '+result[i].inDetail+' 18611339101 </div>\n' +
                        '                <div class="address-opera">\n' +
                        '                    <span class="link address-update">编辑</span>\n' +
                        '                    <span class="link address-delete">删除</span>\n' +
                        '                </div>\n' +
                        '            </a>';
                }$('#dizhi').html(html);
            }
        })
    }

    function biankuang(i){
        $(".address-item").attr("class","address-item");
        $("#address"+i).attr("class","address-item active");
    }

    function addRegion(){
        $.ajax({
            url:'../seckill/addRegion',
            data:{},
            type:'post',
            dataType:'json',
            success:function(){
                alert();
                initSaveOrderForm();
            }
        })
    }

    $("#closeRegionTanKuang").click(function(){
        $(".show_info span").text("修改后的内容！");
    })
