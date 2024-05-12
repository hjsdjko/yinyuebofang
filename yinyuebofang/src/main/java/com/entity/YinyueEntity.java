package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 音乐
 *
 * @author 
 * @email
 */
@TableName("yinyue")
public class YinyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YinyueEntity() {

	}

	public YinyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 音乐名称
     */
    @ColumnInfo(comment="音乐名称",type="varchar(200)")
    @TableField(value = "yinyue_name")

    private String yinyueName;


    /**
     * 音乐编号
     */
    @ColumnInfo(comment="音乐编号",type="varchar(200)")
    @TableField(value = "yinyue_uuid_number")

    private String yinyueUuidNumber;


    /**
     * 图片
     */
    @ColumnInfo(comment="图片",type="varchar(200)")
    @TableField(value = "yinyue_photo")

    private String yinyuePhoto;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 关键字
     */
    @ColumnInfo(comment="关键字",type="varchar(200)")
    @TableField(value = "yinyue_guanjianzi")

    private String yinyueGuanjianzi;


    /**
     * 音乐
     */
    @ColumnInfo(comment="音乐",type="varchar(200)")
    @TableField(value = "yinyue_music")

    private String yinyueMusic;


    /**
     * 音乐类型
     */
    @ColumnInfo(comment="音乐类型",type="int(11)")
    @TableField(value = "yinyue_types")

    private Integer yinyueTypes;


    /**
     * 二级类型
     */
    @ColumnInfo(comment="二级类型",type="int(11)")
    @TableField(value = "yinyue_erji_types")

    private Integer yinyueErjiTypes;


    /**
     * 音乐介绍
     */
    @ColumnInfo(comment="音乐介绍",type="longtext")
    @TableField(value = "yinyue_content")

    private String yinyueContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yinyue_delete")

    private Integer yinyueDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：音乐名称
	 */
    public String getYinyueName() {
        return yinyueName;
    }
    /**
	 * 设置：音乐名称
	 */

    public void setYinyueName(String yinyueName) {
        this.yinyueName = yinyueName;
    }
    /**
	 * 获取：音乐编号
	 */
    public String getYinyueUuidNumber() {
        return yinyueUuidNumber;
    }
    /**
	 * 设置：音乐编号
	 */

    public void setYinyueUuidNumber(String yinyueUuidNumber) {
        this.yinyueUuidNumber = yinyueUuidNumber;
    }
    /**
	 * 获取：图片
	 */
    public String getYinyuePhoto() {
        return yinyuePhoto;
    }
    /**
	 * 设置：图片
	 */

    public void setYinyuePhoto(String yinyuePhoto) {
        this.yinyuePhoto = yinyuePhoto;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：关键字
	 */
    public String getYinyueGuanjianzi() {
        return yinyueGuanjianzi;
    }
    /**
	 * 设置：关键字
	 */

    public void setYinyueGuanjianzi(String yinyueGuanjianzi) {
        this.yinyueGuanjianzi = yinyueGuanjianzi;
    }
    /**
	 * 获取：音乐
	 */
    public String getYinyueMusic() {
        return yinyueMusic;
    }
    /**
	 * 设置：音乐
	 */

    public void setYinyueMusic(String yinyueMusic) {
        this.yinyueMusic = yinyueMusic;
    }
    /**
	 * 获取：音乐类型
	 */
    public Integer getYinyueTypes() {
        return yinyueTypes;
    }
    /**
	 * 设置：音乐类型
	 */

    public void setYinyueTypes(Integer yinyueTypes) {
        this.yinyueTypes = yinyueTypes;
    }
    /**
	 * 获取：二级类型
	 */
    public Integer getYinyueErjiTypes() {
        return yinyueErjiTypes;
    }
    /**
	 * 设置：二级类型
	 */

    public void setYinyueErjiTypes(Integer yinyueErjiTypes) {
        this.yinyueErjiTypes = yinyueErjiTypes;
    }
    /**
	 * 获取：音乐介绍
	 */
    public String getYinyueContent() {
        return yinyueContent;
    }
    /**
	 * 设置：音乐介绍
	 */

    public void setYinyueContent(String yinyueContent) {
        this.yinyueContent = yinyueContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYinyueDelete() {
        return yinyueDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYinyueDelete(Integer yinyueDelete) {
        this.yinyueDelete = yinyueDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yinyue{" +
            ", id=" + id +
            ", yinyueName=" + yinyueName +
            ", yinyueUuidNumber=" + yinyueUuidNumber +
            ", yinyuePhoto=" + yinyuePhoto +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", yinyueGuanjianzi=" + yinyueGuanjianzi +
            ", yinyueMusic=" + yinyueMusic +
            ", yinyueTypes=" + yinyueTypes +
            ", yinyueErjiTypes=" + yinyueErjiTypes +
            ", yinyueContent=" + yinyueContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", yinyueDelete=" + yinyueDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
