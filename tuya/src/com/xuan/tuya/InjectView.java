package com.xuan.tuya;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ�����view��ע��
 * 
 * @author xuan
 * @version $Revision: 32935 $, $Date: 2012-11-30 14:17:17 +0800 (������, 30 ʮһ��
 *          2012) $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectView {

	/**
	 * view��id����
	 * 
	 * @return
	 */
	int value() default -1;

	/**
	 * ע��
	 * 
	 * @return
	 */
	String tag() default "";
}