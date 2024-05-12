package com.entity.vo;

import com.entity.YinyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 音乐
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yinyue")
public class YinyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 音乐名称
     */

    @TableField(value = "yinyue_name")
    private String yinyueName;


    /**
     * 音乐编号
     */

    @TableField(value = "yinyue_uuid_number")
    private String yinyueUuidNumber;


    /**
     * 图片
     */

    @TableField(value = "yinyue_photo")
    private String yinyuePhoto;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 关键字
     */

    @TableField(value = "yinyue_guanjianzi")
    private String yinyueGuanjianzi;


    /**
     * 音乐
     */

    @TableField(value = "yinyue_music")
    private String yinyueMusic;


    /**
     * 音乐类型
     */

    @TableField(value = "yinyue_types")
    private Integer yinyueTypes;


    /**
     * 二级类型
     */

    @TableField(value = "yinyue_erji_types")
    private Integer yinyueErjiTypes;


    /**
     * 音乐介绍
     */

    @TableField(value = "yinyue_content")
    private String yinyueContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "yinyue_delete")
    private Integer yinyueDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：音乐名称
	 */
    public String getYinyueName() {
        return yinyueName;
    }


    /**
	 * 获取：音乐名称
	 */

    public void setYinyueName(String yinyueName) {
        this.yinyueName = yinyueName;
    }
    /**
	 * 设置：音乐编号
	 */
    public String getYinyueUuidNumber() {
        return yinyueUuidNumber;
    }


    /**
	 * 获取：音乐编号
	 */

    public void setYinyueUuidNumber(String yinyueUuidNumber) {
        this.yinyueUuidNumber = yinyueUuidNumber;
    }
    /**
	 * 设置：图片
	 */
    public String getYinyuePhoto() {
        return yinyuePhoto;
    }


    /**
	 * 获取：图片
	 */

    public void setYinyuePhoto(String yinyuePhoto) {
        this.yinyuePhoto = yinyuePhoto;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：关键字
	 */
    public String getYinyueGuanjianzi() {
        return yinyueGuanjianzi;
    }


    /**
	 * 获取：关键字
	 */

    public void setYinyueGuanjianzi(String yinyueGuanjianzi) {
        this.yinyueGuanjianzi = yinyueGuanjianzi;
    }
    /**
	 * 设置：音乐
	 */
    public String getYinyueMusic() {
        return yinyueMusic;
    }


    /**
	 * 获取：音乐
	 */

    public void setYinyueMusic(String yinyueMusic) {
        this.yinyueMusic = yinyueMusic;
    }
    /**
	 * 设置：音乐类型
	 */
    public Integer getYinyueTypes() {
        return yinyueTypes;
    }


    /**
	 * 获取：音乐类型
	 */

    public void setYinyueTypes(Integer yinyueTypes) {
        this.yinyueTypes = yinyueTypes;
    }
    /**
	 * 设置：二级类型
	 */
    public Integer getYinyueErjiTypes() {
        return yinyueErjiTypes;
    }


    /**
	 * 获取：二级类型
	 */

    public void setYinyueErjiTypes(Integer yinyueErjiTypes) {
        this.yinyueErjiTypes = yinyueErjiTypes;
    }
    /**
	 * 设置：音乐介绍
	 */
    public String getYinyueContent() {
        return yinyueContent;
    }


    /**
	 * 获取：音乐介绍
	 */

    public void setYinyueContent(String yinyueContent) {
        this.yinyueContent = yinyueContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYinyueDelete() {
        return yinyueDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYinyueDelete(Integer yinyueDelete) {
        this.yinyueDelete = yinyueDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
