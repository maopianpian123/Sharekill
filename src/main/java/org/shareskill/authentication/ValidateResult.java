/**
 * 2016年2月24日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.authentication;

/**
 * 验证结果
 * @author hit_lacus@126.com
 */
public class ValidateResult {
	
	/**
	 * @param isValid
	 * @param data
	 */
    public ValidateResult(boolean isValid, String data) {
	    super();
	    this.isValid = isValid;
	    this.data = data;
    }

    /**
     * 是否通过验证
     */
	private boolean isValid;
	
	/**
	 * 提示信息,or错误信息
	 */
	private String data;

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

}
