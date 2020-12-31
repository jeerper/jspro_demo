package com.summit.util;

import com.summit.util.page.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: jeerper
 * @since: 2020/12/24 15:04
 */
public class ResponseUtil {
     /**
     * 操作成功返回没有分页列表
     */
     public static  RequestMessage sucessListResponse(List records){
         RequestMessage message =new  RequestMessage();
         message.setCode(RequestMessage.SUCCESS_GETS_CODE);
         message.setMessage(RequestMessage.SUCCESS_GETS_MSG);
         message.setData(records);
         return message;
     }
    /**
     * 操作成功返回分页列表
     */
    public static  RequestMessage sucessPageResponse(Page page){
        RequestMessage message =new  RequestMessage();
        message.setCode(RequestMessage.SUCCESS_GETS_CODE);
        message.setMessage(RequestMessage.SUCCESS_GETS_MSG);
        message.setData(page.getRecords());
        message.setCurrent(page.getCurrentPage());
        message.setSize(page.getPageSize());
        return message;
    }

    /**
     * 返回单个对象
     */
    public static  RequestMessage sucessObjectResponse(Object obj){
        RequestMessage message =new  RequestMessage();
        message.setCode(RequestMessage.SUCCESS_GETS_CODE);
        message.setMessage(RequestMessage.SUCCESS_GETS_MSG);
        message.setData(obj);
        return message;
    }

    /**
     * 只返回结果，没有记录
     */
    public static  RequestMessage sucessResponse(String msg){
        RequestMessage message =new  RequestMessage();
        message.setCode(RequestMessage.SUCCESS_GETS_CODE);
        if (StringUtils.isBlank(msg)){
            message.setMessage(RequestMessage.SUCCESS_GETS_MSG);
        }else{
            message.setMessage(msg);
        }
        return message;
    }

    /**
     * 操作结果失败
     */
    public static  RequestMessage failedResponse(String msg,String error){
        RequestMessage message =new  RequestMessage();
        message.setCode(RequestMessage.ERROR_CODE);
        if (StringUtils.isBlank(msg)){
            message.setMessage(RequestMessage.ERROR_MSG);
        }else{
            message.setMessage(msg);
        }
        message.setError(error);
        return message;
    }
}
