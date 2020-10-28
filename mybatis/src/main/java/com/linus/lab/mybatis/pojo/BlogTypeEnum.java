package com.linus.lab.mybatis.pojo;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/28
 */
public enum BlogTypeEnum {

    DIARY(1), NOTE(2);

    int code;

    BlogTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BlogTypeEnum getEnumByCode(int code) {
        for (BlogTypeEnum blogTypeEnum : values()) {
            if (blogTypeEnum.getCode() == code) return blogTypeEnum;
        }
        return null;
    }
}
