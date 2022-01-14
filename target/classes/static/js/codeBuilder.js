$(function () {
    $("input.number").attr("readonly", "1").click(function (e) {
        var self = $(this);
        var x = e.pageX - self.offset().left;
        var y = e.pageY - self.offset().top;
        var d = 0;
        if (x <= 20) {
            self.css("background-image", "url(../images/bg_number_box3.png)");
            d = -1;
        }
        if (x >= 50) {
            self.css("background-image", "url(../images/bg_number_box4.png)");
            d = 1;
        }
        if (d != 0) {
            self.val(Math.min(Math.max((parseInt(self.val()) || 0) + d, parseInt(self.attr("min"))), parseInt(self.attr("max"))));
            setTimeout(function () {
                self.css("background-image", "");
            }, 200);
        }
    });
        $("#formId").ajaxForm(function (data) {
            var html = "";
            if(data.code == 200){
                $.each(data.data, function (index, item) {
                    var index1 = index +1;
                    html += "<h1 class=\"vintage1\">第"+index1+"个类</h1>"
                    $.each(item,function (index,item) {
                        html += "<h1 class=\"vintage4\">创建：" + item.name + "，</h1><h1 class=\"vintage6\">路径为：" + item.outputPath + "</h1>";
                    })
                })
                $("#div").html(html);
            }else{
                $("#div").html("<span class=\"vintage3\">\n"+data.msg+ "</span>");
            }
        })

        $("#mainFormId").ajaxForm(function (data) {
            var html = "";
            if(data.code == 200){
                $("#div").html("<span class=\"vintage1\">\n"+data.data+ "</span>");
            }else{
                $("#div").html("<span class=\"vintage3\">\n"+data.msg+ "</span>");
            }
        })
    $('#back').click(function () {
        window.location.href = "http://localhost:8888/index.html"
    })
    // $("#formId").validate({
    //     debug: true, //调试模式，即使验证成功也不会跳转到目标页面
    //     rules: {     //配置验证规则，key就是被验证的dom对象，value就是调用验证的方法(也是json格式)
    //         domains: {
    //             required: true,  //必填。如果验证方法不需要参数，则配置为true
    //             //验证用户名是否已存在
    //             checkDomain:true
    //         }
    //     },
    //     messages: {
    //         }
    //     })


});