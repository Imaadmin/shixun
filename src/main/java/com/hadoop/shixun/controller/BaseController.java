package com.hadoop.shixun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hadoop.shixun.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseController {

    /**
     * 判断参数是否为空！
     *
     * @param args
     * @return
     */
    public boolean isEmpty(Object... args) {

        for (Object string : args) {
            if (string == null)
                return true;
            if (StringUtils.isEmpty( string.toString() ))
                return true;

        }
        return false;
    }

    /**
     * 500页面
     */
    public void run_500() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();
        try {
            response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            response.setCharacterEncoding( "UTF-8" );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//返回500
    }


    /**
     * 404页面
     */
    public void run_404() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();
        try {
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            response.setCharacterEncoding( "UTF-8" );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 403页面
     */
    public void run_403() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();
        try {
            response.sendError( HttpServletResponse.SC_FORBIDDEN );
            response.setCharacterEncoding( "UTF-8" );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 错误提示
     *
     * @param message
     * @return
     */
    protected String run_false(String message) {
        JSONObject obj = new JSONObject();
        obj.put( "status", false );
        obj.put( "code", 400 );
        obj.put( "msg", message );
        return obj.toJSONString();

    }

    protected String run_false() {
        JSONObject obj = new JSONObject();
        obj.put( "status", false );
        obj.put( "msg", "error" );
        obj.put( "code", 400 );
        return obj.toJSONString();

    }

    /**
     * 正确提示
     *
     * @param message
     * @return
     */
    protected String run_success(String message) {
        JSONObject obj = new JSONObject();
        obj.put( "status", true );
        obj.put( "code", 0 );
        obj.put( "msg", message );
        return obj.toJSONString();
    }

    protected String run_success() {
        JSONObject obj = new JSONObject();
        obj.put( "status", true );
        obj.put( "code", 0 );
        obj.put( "msg", "success" );
        return obj.toJSONString();
    }

    protected String run_success(String key, Object value) {
        JSONObject obj = new JSONObject();
        obj.put( "status", true );
        obj.put( "code", 0 );
        obj.put( key, value );
        return obj.toJSONString();
    }

    /**
     * 未登陆提示
     *
     * @return
     */
    protected String run_nologin() {
        JSONObject obj = new JSONObject();
        obj.put( "status", false );
        obj.put( "msg", "用户认证失败！" );
        obj.put( "code", 500 );
        return obj.toJSONString();
    }

    /**
     * 当前用户
     *
     * @return
     */
    protected User user() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Object object = request.getSession().getAttribute( "user" );
        return object == null ? null : (User) object;
    }

    /**
     * 未登陆提示
     *
     * @return
     */
    protected String run_noparam() {
        JSONObject obj = new JSONObject();
        obj.put( "status", false );
        obj.put( "msg", "请求参数不正确！" );
        return obj.toJSONString();
    }


    /**
     * JSON 转换
     *
     * @param object
     * @return
     */

    protected String toJsonP(Object object) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON( object );
        jsonObject.put( "status", true );
        jsonObject.put( "code", 0 );
        return JSON.toJSONString( jsonObject, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero );
    }

    protected String toJsonP(List<?> list) {
        if (list == null)
            list = new ArrayList<>();
        JSONArray array = (JSONArray) JSON.toJSON( list );
        return toJsonP( array );
    }

    protected String toJsonP(JSONArray array) {
        JSONObject jsonObject = new JSONObject();
        String rowsName = "data";
        jsonObject.put( rowsName, array );
        return toJsonP( jsonObject );
    }
}
