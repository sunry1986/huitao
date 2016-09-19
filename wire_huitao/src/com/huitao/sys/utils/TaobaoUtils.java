package com.huitao.sys.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huitao.sys.commons.Constants4System;
import com.huitao.sys.vo.TaoBaoAccessVo;
import com.taobao.api.internal.util.WebUtils;



public class TaobaoUtils {
	
	
    public  static TaoBaoAccessVo getAccessToken(String code){
    	
    	String url="https://oauth.taobao.com/token";
        Map<String,String> props=new HashMap<String, String>();
        props.put("grant_type","authorization_code");
        props.put("code",code);
        props.put("client_id",Constants4System.APPKEY);
        props.put("client_secret",Constants4System.APPSECRET);
        props.put("redirect_uri",Constants4System.REDIRECT_URI);
        props.put("view","web");
        String s="";
        TaoBaoAccessVo taoBaoAccessVo=null;
        try{s=WebUtils.doPost(url, props, 30000, 30000);
        System.out.println(s);
         taoBaoAccessVo=getTaoBaoAccessVo(s);
        }catch(IOException e){
            e.printStackTrace();}
    	return taoBaoAccessVo;
    }
    
    
    public  static TaoBaoAccessVo getTaoBaoAccessVo(String jsonStr){
    	
    	if(jsonStr==null || jsonStr.equals("")){
    		return null;
    	}
 		JSONObject obj = JSONObject.fromObject(jsonStr);
 		if(toString(obj.get("error"))==null || !toString(obj.get("error")).equals("")){
 			return null;
 		}else{
 			String access_token=toString(obj.get("access_token"));
 	 		String taobao_user_id=toString(obj.get("taobao_user_id"));
 	 		String sub_taobao_user_id=toString(obj.get("sub_taobao_user_id"));
 	 		String sub_taobao_user_nick="";
 	 		String taobao_user_nick="";
 	 		try {
 				taobao_user_nick=URLDecoder.decode(toString(obj.get("taobao_user_nick")), "utf-8");
 				sub_taobao_user_nick=URLDecoder.decode(toString(obj.get("sub_taobao_user_nick")), "utf-8");
 			} catch (UnsupportedEncodingException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	 		TaoBaoAccessVo taoBaoAccessVo=new TaoBaoAccessVo();
 	 		taoBaoAccessVo.setAccess_token(access_token);
 	 		taoBaoAccessVo.setTaobao_user_id(taobao_user_id);
 	 		taoBaoAccessVo.setTaobao_user_nick(taobao_user_nick);
 	 		taoBaoAccessVo.setSub_taobao_user_id(sub_taobao_user_id);
 	 		taoBaoAccessVo.setSub_taobao_user_nick(sub_taobao_user_nick);
 	 		return taoBaoAccessVo;
 		}
    }
	public static String toString(Object val) {
		if(val==null || val=="") {
			return "";
		} else {
			return val.toString();
		}
	}
 	
    

}
