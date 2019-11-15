
package com.deepexi.util;

import java.io.Serializable;

/**
 * 抽象实体基类
 * @author 黄文
 * 2019.10.26
 */
public abstract class AbstractEntity<ID> implements Serializable {

	public abstract ID getId();

	public abstract void setId(ID id);
}
