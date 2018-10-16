package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-14 10:38:42
 */
@TableName("user_rate")
public class UserRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long die;
	/**
	 * 
	 */
	private Long general;
	/**
	 * 
	 */
	private Long senior;

	/**
	 * 设置：
	 */
	public void setDie(Long die) {
		this.die = die;
	}
	/**
	 * 获取：
	 */
	public Long getDie() {
		return die;
	}
	/**
	 * 设置：
	 */
	public void setGeneral(Long general) {
		this.general = general;
	}
	/**
	 * 获取：
	 */
	public Long getGeneral() {
		return general;
	}
	/**
	 * 设置：
	 */
	public void setSenior(Long senior) {
		this.senior = senior;
	}
	/**
	 * 获取：
	 */
	public Long getSenior() {
		return senior;
	}
}
