package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YinyueLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 音乐评论
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yinyue_liuyan")
public class YinyueLiuyanView extends YinyueLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 音乐
		/**
		* 音乐名称
		*/

		@ColumnInfo(comment="音乐名称",type="varchar(200)")
		private String yinyueName;
		/**
		* 音乐编号
		*/

		@ColumnInfo(comment="音乐编号",type="varchar(200)")
		private String yinyueUuidNumber;
		/**
		* 图片
		*/

		@ColumnInfo(comment="图片",type="varchar(200)")
		private String yinyuePhoto;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 关键字
		*/

		@ColumnInfo(comment="关键字",type="varchar(200)")
		private String yinyueGuanjianzi;
		/**
		* 音乐
		*/

		@ColumnInfo(comment="音乐",type="varchar(200)")
		private String yinyueMusic;
		/**
		* 音乐类型
		*/
		@ColumnInfo(comment="音乐类型",type="int(11)")
		private Integer yinyueTypes;
			/**
			* 音乐类型的值
			*/
			@ColumnInfo(comment="音乐类型的字典表值",type="varchar(200)")
			private String yinyueValue;
		/**
		* 二级类型
		*/
		@ColumnInfo(comment="二级类型",type="int(11)")
		private Integer yinyueErjiTypes;
			/**
			* 二级类型的值
			*/
			@ColumnInfo(comment="二级类型的字典表值",type="varchar(200)")
			private String yinyueErjiValue;
		/**
		* 音乐介绍
		*/

		@ColumnInfo(comment="音乐介绍",type="longtext")
		private String yinyueContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yinyueDelete;
	//级联表 用户
		/**
		* 用户编号
		*/

		@ColumnInfo(comment="用户编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 账户状态
		*/
		@ColumnInfo(comment="账户状态",type="int(11)")
		private Integer jinyongTypes;
			/**
			* 账户状态的值
			*/
			@ColumnInfo(comment="账户状态的字典表值",type="varchar(200)")
			private String jinyongValue;



	public YinyueLiuyanView() {

	}

	public YinyueLiuyanView(YinyueLiuyanEntity yinyueLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, yinyueLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 音乐

		/**
		* 获取： 音乐名称
		*/
		public String getYinyueName() {
			return yinyueName;
		}
		/**
		* 设置： 音乐名称
		*/
		public void setYinyueName(String yinyueName) {
			this.yinyueName = yinyueName;
		}

		/**
		* 获取： 音乐编号
		*/
		public String getYinyueUuidNumber() {
			return yinyueUuidNumber;
		}
		/**
		* 设置： 音乐编号
		*/
		public void setYinyueUuidNumber(String yinyueUuidNumber) {
			this.yinyueUuidNumber = yinyueUuidNumber;
		}

		/**
		* 获取： 图片
		*/
		public String getYinyuePhoto() {
			return yinyuePhoto;
		}
		/**
		* 设置： 图片
		*/
		public void setYinyuePhoto(String yinyuePhoto) {
			this.yinyuePhoto = yinyuePhoto;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}

		/**
		* 获取： 关键字
		*/
		public String getYinyueGuanjianzi() {
			return yinyueGuanjianzi;
		}
		/**
		* 设置： 关键字
		*/
		public void setYinyueGuanjianzi(String yinyueGuanjianzi) {
			this.yinyueGuanjianzi = yinyueGuanjianzi;
		}

		/**
		* 获取： 音乐
		*/
		public String getYinyueMusic() {
			return yinyueMusic;
		}
		/**
		* 设置： 音乐
		*/
		public void setYinyueMusic(String yinyueMusic) {
			this.yinyueMusic = yinyueMusic;
		}
		/**
		* 获取： 音乐类型
		*/
		public Integer getYinyueTypes() {
			return yinyueTypes;
		}
		/**
		* 设置： 音乐类型
		*/
		public void setYinyueTypes(Integer yinyueTypes) {
			this.yinyueTypes = yinyueTypes;
		}


			/**
			* 获取： 音乐类型的值
			*/
			public String getYinyueValue() {
				return yinyueValue;
			}
			/**
			* 设置： 音乐类型的值
			*/
			public void setYinyueValue(String yinyueValue) {
				this.yinyueValue = yinyueValue;
			}
		/**
		* 获取： 二级类型
		*/
		public Integer getYinyueErjiTypes() {
			return yinyueErjiTypes;
		}
		/**
		* 设置： 二级类型
		*/
		public void setYinyueErjiTypes(Integer yinyueErjiTypes) {
			this.yinyueErjiTypes = yinyueErjiTypes;
		}


			/**
			* 获取： 二级类型的值
			*/
			public String getYinyueErjiValue() {
				return yinyueErjiValue;
			}
			/**
			* 设置： 二级类型的值
			*/
			public void setYinyueErjiValue(String yinyueErjiValue) {
				this.yinyueErjiValue = yinyueErjiValue;
			}

		/**
		* 获取： 音乐介绍
		*/
		public String getYinyueContent() {
			return yinyueContent;
		}
		/**
		* 设置： 音乐介绍
		*/
		public void setYinyueContent(String yinyueContent) {
			this.yinyueContent = yinyueContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYinyueDelete() {
			return yinyueDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYinyueDelete(Integer yinyueDelete) {
			this.yinyueDelete = yinyueDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 用户编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}
		/**
		* 获取： 账户状态
		*/
		public Integer getJinyongTypes() {
			return jinyongTypes;
		}
		/**
		* 设置： 账户状态
		*/
		public void setJinyongTypes(Integer jinyongTypes) {
			this.jinyongTypes = jinyongTypes;
		}


			/**
			* 获取： 账户状态的值
			*/
			public String getJinyongValue() {
				return jinyongValue;
			}
			/**
			* 设置： 账户状态的值
			*/
			public void setJinyongValue(String jinyongValue) {
				this.jinyongValue = jinyongValue;
			}


	@Override
	public String toString() {
		return "YinyueLiuyanView{" +
			", yinyueName=" + yinyueName +
			", yinyueUuidNumber=" + yinyueUuidNumber +
			", yinyuePhoto=" + yinyuePhoto +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", yinyueGuanjianzi=" + yinyueGuanjianzi +
			", yinyueMusic=" + yinyueMusic +
			", yinyueContent=" + yinyueContent +
			", yinyueDelete=" + yinyueDelete +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
