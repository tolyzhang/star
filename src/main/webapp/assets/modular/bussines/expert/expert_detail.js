layui.use(['layer', 'form', 'admin', 'ax','layedit'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
   // admin.iframeAuto();

    //获取信息
    var ajax = new $ax(Feng.ctxPath + "/expert/detail/" + Feng.getUrlParam("id"));
    var result = ajax.start();
    var ajaxt = new $ax(Feng.ctxPath+"/expert/annexImg/"+result.itemNo);
    var resultImg = ajaxt.start();
    var ajaxPart = new $ax(Feng.ctxPath+"/expert/part/"+result.itemNo);
    var resultPart = ajaxPart.start();
    form.val('expertForm',result);
    var html="";
    for(var i=0;i<resultImg.length;i++){
        html+="<label class=\"layui-form-label\">附件</label>"+
            "<div class=\"layui-input-block\" >"+
            "<img src=\"../"+resultImg[i].annexUrlAdd+"\" alt=\"\" id=\"annexImgst\" style=\"width:80%\" /></div>";
    }
    $("#annexIm").append(html);



    });
