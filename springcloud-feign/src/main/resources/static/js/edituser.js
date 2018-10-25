

layui.use('form', function(){

    var element = layui.element;
    $(function(){


        initUpdateUser();
    })

    function initUpdateUser(){
        $.ajax({
            url:'/wbx/toEdit2',
            type:'post',
            data:{id:$('#x').val()},
            dataType:'json',
            success:function(result){
                var html = "";
                var x=""
                var role = result.role;
                var roles = result.roles;
                for(var i=0;i<role.length;i++){
                    for(var j=0;j<roles.length;j++){
                        if(roles[j].id==role[i].id){

                            x= "<input  type='checkbox' name='roleid' th:value='"+role[i].id+"' th:title='"+role[i].name+"' checked=''/><br>";
                            break;
                        }else{
                            x= "<input  type='checkbox' name='roleid' th:value='"+role[i].id+"' th:title='"+role[i].name+"' /><br>";
                        }

                    }
                    html+=x;
                }
                alert(html)
                var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                form.render("checkbox");
                $("#userListTable").append(html);

            }
        })
    }

    //……
    //但是，如果你的HTML是动态生成的，自动渲染就会失效
    //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();

});