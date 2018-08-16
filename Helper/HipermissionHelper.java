package com.flytoyou.baseapplication.Helper;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * HipermissionHelper权限申请帮助类
 * @author flytoyou
 * @version 1.0.0
 * https://www.jianshu.com/p/c83503f0384d
 * implementation 'me.weyye.hipermission:library:1.0.7'
 */
public class HipermissionHelper {

    private HipermissionHelper(){}

    private static class SingletonHolder{
        public static HipermissionHelper helper = new HipermissionHelper();
    }

    public HipermissionHelper getInstance(){
        return SingletonHolder.helper;
    }

    /**
     * 申请权限
     * @param context 调用者上下文
     * @param permissions 权限数组
     * @param permissionsName 权限名称数组（给用户看的内容）
     * @param imgs 权限图片（给用户看的内容）
     * @param callback 申请之后的回调函数
     */
    public void createPermission(Context context, List<String> permissions, List<String> permissionsName, List<Integer> imgs, PermissionCallback callback){
        List<PermissionItem> permissionItems = new ArrayList<>();
        for (int i=0;i<permissions.size();i++){
            permissionItems.add(new PermissionItem(permissions.get(i),permissionsName.get(i),imgs.get(i)));
        }
        HiPermission.create(context)
                .permissions(permissionItems)
                .checkMutiPermission(callback);
    }

}
