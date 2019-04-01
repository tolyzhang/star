weibo_load()
function weibo_load() {
    if (window.addEventListener) {
        window.addEventListener("load", weibo_txtload, false);
    }
    else if (window.attachEvent) {
        window.attachEvent("onload", weibo_txtload);
    }
}
//推荐分享按钮
function weibo_txtload() {
    var innerHtml = "<span>分享到：</span><img src=\"/style/assets/plugin/share/img/xinl.png\" onclick=\"data_sendto('tsina');return false;\" alt=\"分享到新浪微博\" title=\"分享到新浪微博\" />" +
	"<img src=\"/style/assets/plugin/share/img/weixin.png\"  onclick=\"data_share();return false;\" alt=\"扫描二维码\" title=\"扫描二维码\" />"
	+"<div id=\"share_QRcode_s\"><div id=\"output\"  style=\"display: none;\"></div></div>"
    document.getElementById("weibo_pos").innerHTML = innerHtml;
	//加载二维码图片
    var ec = encodeURIComponent,
		turl=document.getElementById('url');
		if(turl != null){ 
			U = document.getElementById('url').value;
		}else{ 
			U="";
		}
         A = 'http://service.shanghai.gov.cn/QRcode/ShareQRcode.aspx',
	    C = '?url=' + ec(U || document.location) ;
        codeurl = A + C;
		var innerHtmlCode="<img src=\"" + codeurl +     "\" class=\"qrshare\" /><a onclick=\"data_close();return false;\" id=\"tip_share_close\"></a><i id=\"tipxjt\"></i></span><p class=\"share_qr_intro\">用微信扫描二维码<br>分享至好友和朋友圈</p>"
		document.getElementById("output").innerHTML = innerHtmlCode;
}
function data_share(){	
	document.getElementById('output').style.display="block";
	}
function data_close(){	
	document.getElementById('output').style.display="none";
	}
function data_sendto(a) {
    try { var conf = jiathis_config || {}; } catch (e) { var conf = {}; };
    var ec = encodeURIComponent,
		UU=document.getElementById('url');
		TT = document.getElementById('title'),
		SS = document.getElementById('summary'),
		KK = document.getElementById('nodeid'),
		OO = document.getElementById('objname'),
		IDID = document.getElementById('idleaf');
		if(UU != null){U = document.getElementById('url').value;}else{ U="";}
		if(TT != null){T = document.getElementById('title').value;}else{ T="";}
		if(SS != null){S = document.getElementById('summary').value;}else{ S="";}
		if(KK != null){K = parseInt(document.getElementById('nodeid').value)}else{ K="";}
		if(OO != null){O = document.getElementById('objname').value;}else{ O="";}
		if(IDID != null){ID = document.getElementById('idleaf').value;}else{ ID="";}
        A = 'http://service.shanghai.gov.cn/zhsh/Share/ArticleInfo.aspx',
	C = '?type=' + a + '&url=' + ec(U || document.location) + '&title=' + ec(T || document.title) + (S ? '&summary=' + ec(S) : '')
		    + (O ? '&objname=' + O : '') + '&nodeid=' + ec(K || document.nodeid) + '&idleaf=' + ec(ID || document.idleaf);
    try {
        window.open(A + C, '');
    } catch (e) {
    }
    return false;
}


var originalprint = window.print;
//window.print = shprint;
function shprint ()
{
	if ((navigator.appName == "Microsoft Internet Explorer") && document.getElementById("ivs_title") && document.getElementById("ivs_content"))
	{
		printdoc = "<html><head>"
		headtitle = document.getElementsByTagName("title");
		if (headtitle.length > 0)
		{
			printdoc += headtitle[0].outerHTML;
		}
		links = document.getElementsByTagName("link");
		for (i = 0; i < links.length; i++)
		{
			link = links[i];
			if (link.rel && link.rel.toLowerCase() == "stylesheet")
			{
				printdoc += link.outerHTML;
			}
		}
		printdoc += "</head><body onload='javascript:window.print();'>"
		printdoc += "<div id=\"box_bottom\"><h3>"
		printdoc += document.getElementById("ivs_title").innerHTML;
		if (document.getElementById("fbdate"))
		{
			printdoc += "<div id=\"fbdate\">" + document.getElementById("fbdate").innerHTML + "</div>";
		}
		printdoc += "</div></h3><div id=\"wenzi\"><div id=\"maincontent\"><div>"
		printdoc += document.getElementById("ivs_content").innerHTML;

		printdoc += "</div></div></div></body></html>";
		var printdocpage = window.open('about:blank');
		printdocpage.document.write(printdoc);
		printdocpage.document.location.reload();

	}
	else
	{
		originalprint();
	}
}
