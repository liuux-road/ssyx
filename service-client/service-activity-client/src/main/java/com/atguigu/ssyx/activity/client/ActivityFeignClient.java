package com.atguigu.ssyx.activity.client;

import com.atguigu.ssyx.model.activity.CouponInfo;
import com.atguigu.ssyx.model.order.CartInfo;
import com.atguigu.ssyx.vo.order.CartInfoVo;
import com.atguigu.ssyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@FeignClient("service-activity")
public interface ActivityFeignClient {

    /**
     * 根据skuId获取促销与优惠券信息
     *
     * @param skuId
     * @param userId
     * @return
     */
    @GetMapping("/api/activity/inner/findActivityAndCoupon/{skuId}/{userId}")
    Map<String, Object> findActivityAndCoupon(@PathVariable Long skuId, @PathVariable("userId") Long userId);

    /**
     * 获取购物车满足条件的促销与优惠券信息
     *
     * @param cartInfoList
     * @param userId
     * @return
     */
    @PostMapping("/api/activity/inner/findCartActivityAndCoupon/{userId}")
    OrderConfirmVo findCartActivityAndCoupon(@RequestBody List<CartInfo> cartInfoList, @PathVariable("userId") Long userId);

    /**
     * 更新优惠券支付时间
     *
     * @param couponId
     * @param userId
     * @return
     */
    @GetMapping(value = "/api/activity/inner/updateCouponInfoUsedTime/{couponId}/{userId}")
    Boolean updateCouponInfoUsedTime(@PathVariable("couponId") Long couponId, @PathVariable("userId") Long userId);

    /**
     * 更新优惠券使用状态
     *
     * @param couponId
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping(value = "/api/activity/inner/updateCouponInfoUseStatus/{couponId}/{userId}/{orderId}")
    Boolean updateCouponInfoUseStatus(@PathVariable("couponId") Long couponId, @PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId);

    /**
     * 获取购物车对应的促销活动
     *
     * @param cartInfoList
     * @return
     */
    @PostMapping(value = "/api/activity/inner/findCartActivityList")
    List<CartInfoVo> findCartActivityList(@RequestBody List<CartInfo> cartInfoList);

    /**
     * 获取优惠券范围对应的购物车列表
     *
     * @param cartInfoList
     * @param couponId
     * @return
     */
    @PostMapping(value = "/api/activity/inner/findRangeSkuIdList/{couponId}")
    CouponInfo findRangeSkuIdList(@RequestBody List<CartInfo> cartInfoList, @PathVariable("couponId") Long couponId);

    /**
     * 根据skuId列表获取促销信息
     * @param skuIdList
     * @return
     */
    @PostMapping("/api/activity/inner/findActivity")
    Map<Long, List<String>> findActivity(@RequestBody List<Long> skuIdList);

    @ApiOperation(value = "根据活动id获取活动skuid列表")
    @GetMapping(value = "/api/activity/inner/findSkuIdList/{activityId}")
    List<Long> findSkuIdList(@PathVariable("activityId") Long activityId);


}