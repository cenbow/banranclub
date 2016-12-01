<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String tmp_path = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath();
    String currenCssPath = tmp_path + "/css";
    String tmp_jsPath = tmp_path + "/js";
%>
<script type="text/javascript" charset="utf-8"
        src="<%=tmp_jsPath%>/ueditor/ueditor.all.min.js"> </script>
<link rel="stylesheet" type="text/css" href="<%=currenCssPath%>/preview-article-group.css">
<style>
    .stRed{color: red;}
    .keyDiv{display: none;}
    .borDiv{height: 300px;margin-top: 5px;}
</style>
<div id="bottom_div">

    <!-- 隐藏素材图文ID -->
    <input type="hidden" id="material_id_hid">

    <!-- 隐藏是否启用模版 -->
    <input type="hidden" id="templet_flag_hid" value="100000000002">

    <!-- 隐藏文本消息 -->
    <input type="hidden" id="text_msg_hid">

    <!-- 文本消息 -->
    <div id="text_msg" style="line-height: 40px;margin-bottom: 23px;">
        <label style="font-size:13px;"><input id="templet_flag" type="checkbox"  onclick="cktemplet_flag()"/>&nbsp;<b>启用动态模板</b></label></br>
        <script id="templet_flag_TextArea" type="text/plain" ></script>
    </div>

    <!-- 其他消息 -->
    <div id="other_msg" style="border: solid 0px gray;height: 375px;">
        <table class="search-table">
            <tr>
                <th id="th_type" class="wd-20">
                    <label><span id="type"></span><span class="stRed" id="search_span">*<i class="icon-search" onclick="openDialog()"></i></span></label>
                </th>
                <td>
                    <input type="text" id="media_name" style="width:710px;" disabled="disabled">
                </td>
            </tr>
        </table>
        <div id="preview" class="easyui-panel" title="素材预览" style="width: 910px;height:317px;text-align: center;"></div>
    </div>

    <!-- 链接消息 -->
    <div id="link_msg" style="border: solid 0px gray;height: 375px;">
        <table class="search-table">
            <tr>
                <th class="wd-20">
                    <label><span id="type">链接地址</span><span class="stRed" id="search_span">*</span>：</label>
                </th>
                <td>
                    <input type="text" id="link_url" style="width:700px;" class="easyui-validatebox" data-options="validType:['url', 'length[0,200]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过200个字符'">
                </td>
            </tr>
        </table>
    </div>

    <!-- 自定义消息 -->
    <div id="action_msg" style="border: solid 0px gray;height: 375px;">
        <table class="search-table">
            <tr>
                <th class="wd-20">
                    <label><span id="type">自定义消息业务类</span><span class="stRed" id="search_span">*</span>：</label>
                </th>
                <td>
                    <input type="text" id="action_class" style="width:700px;" data-options="validType:['length[0,200]'],invalidMessage:'不能超过200个字符'">
                </td>
            </tr>
        </table>
    </div>
</div>
<script>


    /**
     使用说明：
     1.下拉列表绑定keySelect方法：下拉列表改变时，下方DIV内容改变
     2.新增页面加载完成后调用changePage方法：首次页面的显示；参数为下拉列表的VALUE值
     3.修改页面加载完成后调用CommonSelect.initEdit方法，完成初始化下方DIV
     4.详细页面加载完成后调用CommonSelect.initDetail方法，将对应要显示的内容加到相应的DIV块内
     */

//全局变量
    var dialog_href = '';	//打开窗口连接
    var callBackParam='';	//回调方法参数
    var CommonSelect={};


    //以下全局变量供不同类型素材最后一次选择项目预览的备份
    var last_picture={};
    var last_voice={};
    var last_video={};
    var last_artcleGroup={};
    var link_url = '';
    var action_class_name = '';

    var ue;

    $(document).ready(function(){

        ue =  UE.getEditor('templet_flag_TextArea');

//修改时初始化下方预览（素材类型code，素材名称，素材url（图文组的单/多图文TYPE），是否启用动态模版，解析后的文本消息）
        CommonSelect.initEdit = function(combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg,action_class){
            changePage(combox_value);							//首先：显示需要显示的块
            $("#media_name").val(material_name);				//设置素材名称
            $("#material_id_hid").val(material_id);			//设置素材ID

            console.log(combox_value)
            if(	combox_value	==	'505200000001'	||	'501100000002'	==	combox_value	|| '506200000003' == combox_value){			//选择：图文组
                last_artcleGroup = {'material_id':material_id,'material_name':material_name,'material_url':material_url};
                $("#type").text('图文组名称');					//设置图文组名称前的提示字符串
//                //设置图文组预览
//                $.ajax({url: "showArtcleGroupAction.action",post: 'post',data:{"tArticleRelaQueryBean.article_group_id": material_id,"articleGroupVo.article_type":material_url},
//                    success: function(data){
//                        $("#preview").html("");//清空info内容
//                        data = JSON.parse(data);
//                        $("#preview").html(data.html);
//                    }
//                });
            }else if(combox_value=='505200000002'	||	'501100000003'	==	combox_value	|| '506200000001' == combox_value){			//选择：文本
                last_text = {'text_msg':text_msg,'material_tmpFlag':material_tmpFlag};
                $("#templet_flag_hid").val(material_tmpFlag);		//设置隐藏的是否启用动态模版
                ue.ready(function() {
                    UE.getEditor('templet_flag_TextArea').setContent(text_msg);
                });
                if(material_tmpFlag == '100000000001' ){
                    $('#templet_flag').attr('checked',true);	 //选中动态模版
                }
            }else if(combox_value=='505200000003'	||	'501100000004'	==	combox_value	|| '506200000002' == combox_value){			// 选择：图片
                last_picture = {'material_id':material_id,'material_name':material_name,'material_url':material_url};
                $("#type").text('图片名称');						//设置图片名称前的提示字符串
                //设置图片预览
                $("#preview").html('<img alt="图片无法显示" src="'+material_url+'"></img>');

            }else if(combox_value=='505200000004'   ||  '501100000005'	==  combox_value	||	'506200000004' == combox_value){			//	选择：音频
                last_voice = {'material_id':material_id,'material_name':material_name,'material_url':material_url};
                $("#type").text('音频名称');						//设置音频名称前的提示字符串
                //设置音频预览
                $("#preview").html('<audio controls="controls" src="'+material_url+'" >您的浏览器不支持音频播放</audio>');

            }else if(combox_value=='505200000005' 	||  '501100000006'	==	combox_value	||	'506200000005' == combox_value){			//  选择：视频
                last_video = {'material_id':material_id,'material_name':material_name,'material_url':material_url};
                $("#type").text('视频名称');						//设置视频名称前的提示字符串
                //设置视频预览
                //ie
                if (window.navigator.userAgent.indexOf("MSIE") >= 0) {
                    //预览本地文件
                    $("#preview").html('<object width="100%" height="100%" type="video/x-ms-asf" '
                            +' url="'+material_url+'" data="'+material_url+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">'
                            +' <param id="param_url" name="url" value="'+material_url+'">'
                            +' <param id="param_filename" name="filename" value="'+material_url+'" >'
                            +' <param name="autostart" value="0">'
                            +' <param name="uiMode" value="full" />'
                            +' <param name="autosize" value="1">'
                            +' <param name="playcount" value="1">'
                            +' <embed type="application/x-mplayer2" src="'+material_url+'" width="100%" height="100%" '
                            +' autostart="true" showcontrols="true" '
                            +' pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"></embed></object>');
                    //支持HTML5标签的 浏览器
                } else {
                    $('#preview').panel({content:'<video src="'+material_url+'" height="285px;" controls="controls">'
                    +'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'});
                }
            }else if(combox_value=='501100000001'){
                link_url = material_url;
                $("#other_msg").hide();		//隐藏多媒体预览块
                $("#text_msg").hide();		//隐藏文本消息块
                $("#link_msg").show();		//显示链接地址预览块
                $("#link_url").val(link_url);
            }else if(combox_value=='501100000007'){
                action_class_name = action_class
                $("#other_msg").hide();		//隐藏多媒体预览块
                $("#text_msg").hide();		//隐藏文本消息块
                $("#action_msg").show();		//显示自定义消息预览块
                $("#action_class").val(action_class);
            }

        }

//详细画面预览（预览的div块，素材类型，素材名称，素材连接，是否启用动态模版，文本消息）
        CommonSelect.initDetail= function(tag_id,combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg){
            $("#media_name").css("width","625px");					//适应详细画面宽度
            CommonSelect.initEdit(combox_value,material_id,material_name,material_url,material_tmpFlag,text_msg);	//首先：对页面内容赋值
            $("#templet_flag").attr("disabled",true);				//设置动态模板只读
            $("#search_span").remove();								//去除选择图标
            ue.ready(function() {
                UE.getEditor('templet_flag_TextArea').setDisabled();
            });
            if(combox_value=='505200000002'||	'501100000003'	==	combox_value	|| '506200000001' == combox_value){
                $("#text_msg").appendTo($("#"+tag_id));		//文本时：将文本块放进预览div内
            }else{
                $("#other_msg").appendTo($("#"+tag_id));	//其他多媒体消息时：将多媒体消息块放进预览div内
            }

            //隐藏其他不需要显示的块
            $("#bottom_div").hide();
        }

//图文组预览
        CommonSelect.ArticleGroupCallback = function(group_id,group_name,group_url){
            console.log(group_id)
            console.log(group_name)
            console.log(group_url)
            last_artcleGroup = {'material_id':group_id,'material_name':group_name,'material_url':group_url};
            $("#material_id_hid").val(group_id);	//设置图文组ID
            $("#media_name").val(group_name);		//设置图文组名称
            $("#preview").empty();					//清空预览块
//            //设置图文组预览内容
//            $.ajax({url: "showArtcleGroupAction.action",post: 'post',data:{"tArticleRelaQueryBean.article_group_id": group_id,"articleGroupVo.article_type":group_url},
//                success: function(data){
//                    $("#preview").html("");//清空info内容
//                    data = JSON.parse(data);
//                    $("#preview").html(data.html);
//                }
//            });
            $('#horder_dialog').dialog('close');	//关闭选择画面
        }

//图片预览
        CommonSelect.pictureSelectCallback = function(picture_id,picture_name,picture_url){
            last_picture = {'material_id':picture_id,'material_name':picture_name,'material_url':picture_url};
            $("#material_id_hid").val(picture_id);		//设置图片ID
            $("#media_name").val(picture_name);		//设置图片名称
            $("#preview").empty();						//清空预览块
            //设置图片预览内容
            $("#preview").html('<img alt="图片无法显示" src="'+picture_url+'"></img>');
            $('#horder_dialog').dialog('close');		//关闭选择画面
        }

//视频预览
        CommonSelect.videoSelectCallback = function(video_id,video_name,video_url){
            last_video = {'material_id':video_id,'material_name':video_name,'material_url':video_url};
            $("#material_id_hid").val(video_id);	//设置视频素材ID
            $("#media_name").val(video_name);		//设置视频素材名称
            $("#preview").empty();					//清空预览块
            //设置视频预览内容
            //ie
            if (window.navigator.userAgent.indexOf("MSIE") >= 0) {
                //预览本地文件
                $("#preview").html('<object width="100%" height="100%" type="video/x-ms-asf" '
                        +' url="'+video_url+'" data="'+video_url+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">'
                        +' <param id="param_url" name="url" value="'+video_url+'">'
                        +' <param id="param_filename" name="filename" value="'+video_url+'" >'
                        +' <param name="autostart" value="0">'
                        +' <param name="uiMode" value="full" />'
                        +' <param name="autosize" value="1">'
                        +' <param name="playcount" value="1">'
                        +' <embed type="application/x-mplayer2" src="'+video_url+'" width="100%" height="100%" '
                        +' autostart="true" showcontrols="true" '
                        +' pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"></embed></object>');
                //支持HTML5标签的 浏览器
            } else {

                $('#preview').panel({content:'<video src="'+video_url+'" height="285px;" controls="controls">'
                +'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'});
            }
            $('#horder_dialog').dialog('close');	//关闭选择画面
        }

//音频预览回调方法
        CommonSelect.voiceSelectCallback = function(voice_id,voice_name,voice_url){
            last_voice = {'material_id':voice_id,'material_name':voice_name,'material_url':voice_url};
            $("#material_id_hid").val(voice_id);	//设置音频素材ID
            $("#media_name").val(voice_name);		//设置音频素材名称
            $("#preview").empty();					//清空预览块
            //设置音频预览内容
            $("#preview").html('<audio controls="controls" src="'+voice_url+'" >您的浏览器不支持音频播放</audio>');
            $('#horder_dialog').dialog('close');	//关闭选择画面
        }
    });

    //下拉列表改变出发事件
    function keySelect(){

        var combox_value=$(this).combobox("getValue");
        changePage(combox_value);
    }

    //下拉列表改变时，改变窗口链接,改变类型名称
    function changePage(combox_value){

        // 下拉列表改变时
        $("#material_id_hid").val('');		//清空素材ID
        $("#media_name").val('');			//清空素材名称
        $('#text_msg_hid').val('');			//清空隐藏文本消息
        $('#link_url').val('');				//清空连接消息
        $('#action_class').val('');				//清空action_class信息

        //选择：未输入
        if(combox_value == '505200000000' || '' == combox_value || '501100000000'== combox_value	||	'506200000000' == combox_value	||	""	==	combox_value){
            $("#bottom_div").hide();	//选择未输入时隐藏下方div块

            //选择：文本消息
        }else if(combox_value == '505200000002' || '501100000003' == combox_value	|| '506200000001' == combox_value){
            $("#bottom_div").show();	//显示总DIV块
            $("#other_msg").hide();		//隐藏多媒体预览块
            $("#link_msg").hide();		//隐藏链接地址预览块
            $("#action_msg").hide();		//隐藏action_class地址预览块
            $("#text_msg").show();		//显示文本消息块

            // 其他消息：适用于需要设置预览效果的消息类型
        }else{
            $("#bottom_div").show();	//总div显示
            $("#text_msg").hide();		//隐藏文本消息块
            $("#link_msg").hide();		//隐藏链接地址预览块
            $("#other_msg").show();		//显示多媒体消息预览块
            $("#preview").empty();		//清空预览块
            $("#action_msg").hide();		//隐藏action_class地址预览块

            //选择：图文组
            if(combox_value=='505200000001'	|| '501100000002'	==	combox_value	||	 '506200000003' == combox_value){

                $("#type").text('图文组名称');	//设置显示的素材名称提示
                dialog_href="selectTWxNewsAction.action";		//设置要打开的选择页面的URL
                //TODO
                callBackParam={'jsCallback':'CommonSelect.ArticleGroupCallback'};	//设置选择后的回调函数

                if ('' == last_artcleGroup.material_id || undefined == last_artcleGroup.material_id){
                    return false;
                }
//                //设置预览
//                $.ajax({url: "showArtcleGroupAction.action",post: 'post',data:{"tArticleRelaQueryBean.article_group_id": last_artcleGroup.material_id,"articleGroupVo.article_type":last_artcleGroup.material_url},
//                    success: function(data){
//                        $("#preview").html("");//清空info内容
//                        data = JSON.parse(data);
//                        $("#preview").html(data.html);
//                    }
//                });
                $("#material_id_hid").val(last_artcleGroup.material_id);	//设置图文组素材ID
                $("#media_name").val(last_artcleGroup.material_name);		//设置图文组素材名称

                // 选择：图片
            }else if(combox_value=='505200000003'	||	'501100000004'	==	combox_value	||  '506200000002' == combox_value){

                $("#type").text('图片名称');		//设置显示的素材名称提示
                dialog_href="selectTMaterialPictureAction.action";	//设置要打开的选择页面的URL
                callBackParam={'jsCallback':'CommonSelect.pictureSelectCallback'};	//设置选择后的回调函数

                if ('' == last_picture.material_id || undefined == last_picture.material_id){
                    return false;
                }

                $("#preview").html('<img alt="图片无法显示" src="'+last_picture.material_url+'"></img>');
                $("#material_id_hid").val(last_picture.material_id);	//设置图片素材ID
                $("#media_name").val(last_picture.material_name);		//设置图片素材名称

                //	选择：音频
            }else if(combox_value=='505200000004'	||	'501100000005'	==	combox_value	||	'506200000004' == combox_value){

                $("#type").text('音频名称');		//设置显示的素材名称提示
                dialog_href="selectTMaterialVoiceAction.action";	//设置要打开的选择页面的URL
                callBackParam={'jsCallback':'CommonSelect.voiceSelectCallback'};	//设置选择后的回调函数

                if ('' == last_voice.material_id || undefined == last_voice.material_id){
                    return false;
                }

                $("#preview").html('<audio controls="controls" src="'+last_voice.material_url+'" >您的浏览器不支持音频播放</audio>');
                $("#material_id_hid").val(last_voice.material_id);	//设置音频素材ID
                $("#media_name").val(last_voice.material_name);		//设置音频素材名称


                //  选择：视频
            }else if(combox_value=='505200000005' || '501100000006' == combox_value	||		'506200000005' == combox_value){

                $("#type").text('视频名称');		//设置显示的素材名称提示
                dialog_href="selectTMaterialVideoAction.action";	//设置要打开的选择页面的URL
                callBackParam={'jsCallback':'CommonSelect.videoSelectCallback'};	//设置选择后的回调函数

                if ('' == last_video.material_id || undefined == last_video.material_id){
                    return false;
                }

                //ie
                if (window.navigator.userAgent.indexOf("MSIE") >= 0) {
                    //预览本地文件
                    $("#preview").html('<object width="100%" height="100%" type="video/x-ms-asf" '
                            +' url="'+last_video.material_url+'" data="'+last_video.material_url+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">'
                            +' <param id="param_url" name="url" value="'+last_video.material_url+'">'
                            +' <param id="param_filename" name="filename" value="'+last_video.material_url+'" >'
                            +' <param name="autostart" value="0">'
                            +' <param name="uiMode" value="full" />'
                            +' <param name="autosize" value="1">'
                            +' <param name="playcount" value="1">'
                            +' <embed type="application/x-mplayer2" src="'+last_video.material_url+'" width="100%" height="100%" '
                            +' autostart="true" showcontrols="true" '
                            +' pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"></embed></object>');
                    //支持HTML5标签的 浏览器
                } else {
                    $('#preview').panel({content:'<video src="'+last_video.material_url+'" height="285px;" controls="controls">'
                    +'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'});
                }
                $("#material_id_hid").val(last_video.material_id);	//设置视频素材ID
                $("#media_name").val(last_video.material_name);		//设置视频素材名称

            }else if(combox_value=='501100000001'){
                $("#other_msg").hide();		//隐藏多媒体预览块
                $("#text_msg").hide();		//隐藏文本消息块
                $("#link_msg").show();		//显示链接地址预览块
                $("#link_url").val(link_url);
            }else if(combox_value=='501100000007'){
                $("#other_msg").hide();		//隐藏多媒体预览块
                $("#text_msg").hide();		//隐藏文本消息块
                $("#action_msg").show();		//显示自定义消息预览块
                $("#action_class").val(action_class_name);
            }
        }
        return false;
    }

    // 打开窗口，选择后，回调处理
    function openDialog(){
        $('<div id="horder_dialog"></div>').dialog({
            title:'素材选择',
            width:1050,
            height:589,
            modal: true,
            href:dialog_href,	 //打开页面的URL
            params:callBackParam,//页面回调
            onClose:function(){
                $(this).dialog("destroy");
            }
        });
    }

    // 是否启用动态模版
    function cktemplet_flag(){

        //启用动态模版
        if($('#templet_flag').attr('checked')){
            $("#templet_flag_hid").val('100000000001');

            //不启用动态模版
        }else{
            $("#templet_flag_hid").val('100000000002');
        }
    }
</script>
