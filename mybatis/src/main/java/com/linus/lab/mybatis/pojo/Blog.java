package com.linus.lab.mybatis.pojo;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/23
 */
public class Blog {

    private Long id;

    private String title;

    private BlogTypeEnum type;

    private String content;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogTypeEnum getType() {
        return type;
    }

    public void setType(BlogTypeEnum type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
