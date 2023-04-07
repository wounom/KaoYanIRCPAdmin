package com.wounom.kaoyanircpadmin.entity;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  zhf
 * @Date 2023-04-07 
 */

@ApiModel(value = "tiewen_official")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiewenOfficial  implements Serializable {


	/**
	 * 贴文id
	 */
	@ApiModelProperty(value = "贴文id")
	private Long tiewenId;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long adminId;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;
	/**
	 * 贴文创建时间
	 */
	@ApiModelProperty(value = "贴文创建时间")
	private DateTime createTime;

	/**
	 * 贴文内容
	 */
	@ApiModelProperty(value = "贴文内容")
	private String content;

	/**
	 * 版块名字
	 */
	@ApiModelProperty(value = "版块名字")
	private String blockName;

}
