package com.atguigu.ssyx.payment.service;

import com.atguigu.ssyx.vo.user.WeixinVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface WeixinService {

	/**
	 * 根据订单号下单，生成支付链接
	 * @param orderNo
	 * @return
	 */
	Map createJsapi(String orderNo);
	
	/**
	 * 根据订单号去微信第三方查询支付状态
	 * @param orderNo
	 * @return
	 */
	Map queryPayStatus(String orderNo, String paymentType);

}