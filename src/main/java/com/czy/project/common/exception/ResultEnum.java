package com.czy.project.common.exception;

public enum ResultEnum {
	
    UNKONW_ERROR(-1, "未知错误"),
    MysqlDataTruncation(1,"数据长度异常"),
    NullPointerException(2,"NULL空值异常"),
    IndexOutOfBoundsException(3,"下标越界异常"),
    IllegalArgumentException(4,"参数异常"),
    ClassNotFoundException(5,"找不到类文件"),
    SelectException(6,"查询失败"),
    SaveException(7,"操作失败"),
    SUCCESS(0, "成功"),
    SUCCESS_DATA(0000, "成功"),
    NULLUSERPHONE(4011,"手机号不存在"),
    NULLUSERNAME(4012,"账号或手机号不正确"),
    NULLUSERPASSWORD(4013,"密码不正确"),
    NULLUSERCODE(4014,"验证码不正确"),
    UserRepeatLogin(4015,"用户已登录"),
    ExecutionFailure(9999,"数据执行失败"),
    NoData(9998,"暂无数据"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    /**
     * ************************************************
     * 功能描述：通过提示信息获取代码
     * @param message
     * @return
     * @author create: TODO 人员:【qiangdongaki】类型:【新增方法】日期:【2019年4月10日】
     * @author modify:
     */
    public static Integer getCode(String message) {
        for (ResultEnum c : ResultEnum.values()) {
            if (c.getMsg().equals(message.trim())) {
                return c.code;
            }
        }
        return -1;
    }
    /**
     * ************************************************
     * 功能描述：通过代码获取提示信息
     * @param code
     * @return
     * @author create: TODO 人员:【qiangdongaki】类型:【新增方法】日期:【2019年4月10日】
     * @author modify:
     */
    public static String getMessage(Integer code) {
        for (ResultEnum c : ResultEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getMsg();
            }
        }
        return "未知错误";
    }
}
