package com.atguigu.ssyx.activity.mapper;

import com.atguigu.ssyx.activity.mapper.CouponInfoMapper;
import com.atguigu.ssyx.model.activity.CouponInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author liuxu
 * @since 2024-03-07
 */
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {
    /**
     * sku优惠券
     * @param skuId
     * @param categoryId
     * @param userId
     * @return
     */
    List<CouponInfo> selectCouponInfoList(@Param("skuId") Long skuId, @Param("categoryId") Long categoryId, @Param("userId") Long userId);


    /**
     * 获取用户全部优惠券
     * @param userId
     * @return
     */
    List<CouponInfo> selectCartCouponInfoList(@Param("userId")Long userId);
}
