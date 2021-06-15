package com.yuanfenge.valid.group.bean;

import com.yuanfenge.valid.group.custom.Status;
import com.yuanfenge.valid.group.group.AddUserGroup;
import com.yuanfenge.valid.group.group.CustomUseGroup;
import com.yuanfenge.valid.group.group.UpdateUseGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 * @author yuanfenge
 * @description
 * @createTime 2019 - 3 - 18 11:54
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    public static final int ONLINE=1;//在线
    public static final int OFFLINE=-1;//离线
    public static final int LEAVE=2;//离开
    public static final int BUSY=3;//忙碌

    @NotNull(message = "不能为空",groups = {UpdateUseGroup.class})
    private Long id;
    @NotNull(message = "不能为空",groups = {AddUserGroup.class})
    private String name;
    @NotNull(message = "不能为空",groups = {AddUserGroup.class,UpdateUseGroup.class})
    private Integer age;

    //自定义校验器Status
    @Status(values={ONLINE,OFFLINE,LEAVE,BUSY},groups = {CustomUseGroup.class})
    private Integer status;

}
