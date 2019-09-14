package com.mytest.register.model;


import com.mytest.register.enums.ResourceType;

import java.util.Date;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 12:43
 */
public class Resource {
    /**
     * id
     */
    private int id;

    /**
     * resource alias
     */
    private String alias;

    /**
     * description
     */
    private String desc;

    /**
     * file alias
     */
    private String fileName;

    /**
     * user id
     */
    private int userId;

    /**
     * resource type
     */
    private ResourceType type;

    /**
     * resource size
     */
    private long size;

    /**
     * create time
     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    public Resource() {
    }

    public Resource(int id, String alias, String fileName, String desc, int userId,
                    ResourceType type, long size,
                    Date createTime, Date updateTime) {
        this.id = id;
        this.alias = alias;
        this.fileName = fileName;
        this.desc = desc;
        this.userId = userId;
        this.type = type;
        this.size = size;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Resource(String alias, String fileName, String desc, int userId, ResourceType type, long size, Date createTime, Date updateTime) {
        this.alias = alias;
        this.fileName = fileName;
        this.desc = desc;
        this.userId = userId;
        this.type = type;
        this.size = size;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", fileName='" + fileName + '\'' +
                ", desc='" + desc + '\'' +
                ", userId=" + userId +
                ", type=" + type +
                ", size=" + size +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource resource = (Resource) o;

        if (id != resource.id) {
            return false;
        }
        return alias.equals(resource.alias);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + alias.hashCode();
        return result;
    }
}
