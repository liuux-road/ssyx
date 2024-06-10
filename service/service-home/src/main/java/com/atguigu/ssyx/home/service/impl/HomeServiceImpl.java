package com.atguigu.ssyx.home.service.impl;


import com.atguigu.ssyx.home.service.HomeService;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.model.search.SkuEs;
import com.atguigu.ssyx.product.client.ProductFeignClient;
import com.atguigu.ssyx.search.client.SearchFeignClient;
import com.atguigu.ssyx.user.client.UserFeignClient;
import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class HomeServiceImpl implements HomeService {

    @Resource
    private ProductFeignClient productFeignClient;

//    @Resource
//    private SeckillFeignClient seckillFeignClient;

    @Resource
    private SearchFeignClient searchFeignClient;

    @Resource
    private UserFeignClient userFeignClient;

//    @Resource
//    private ActivityFeignClient activityFeignClient;

    @Override
    public Map<String, Object> home(Long userId) {
        Map<String, Object> result = new HashMap<>();

        //获取分类信息
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        result.put("categoryList", categoryList);

        //获取新人专享商品
        List<SkuInfo> newPersonSkuInfoList =  productFeignClient.findNewPersonSkuInfoList();
        result.put("newPersonSkuInfoList", newPersonSkuInfoList);

        //TODO 获取用户首页秒杀数据

        //提货点地址信息
        LeaderAddressVo leaderAddressVo = userFeignClient.getLeaderAddressVoByUserId(userId);
        result.put("leaderAddressVo", leaderAddressVo);

        //获取爆品商品
        List<SkuEs> hotSkuList = searchFeignClient.findHotSkuList();
//        //获取sku对应的促销活动标签
//        if(!CollectionUtils.isEmpty(hotSkuList)) {
//            List<Long> skuIdList = hotSkuList.stream().map(sku -> sku.getId()).collect(Collectors.toList());
//            Map<Long, List<String>> skuIdToRuleListMap = activityFeignClient.findActivity(skuIdList);
//            if(null != skuIdToRuleListMap) {
//                hotSkuList.forEach(skuEs -> {
//                    skuEs.setRuleList(skuIdToRuleListMap.get(skuEs.getId()));
//                });
//            }
//        }
        result.put("hotSkuList", hotSkuList);
        return result;
    }
}