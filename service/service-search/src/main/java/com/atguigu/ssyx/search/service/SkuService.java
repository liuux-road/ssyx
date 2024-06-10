package com.atguigu.ssyx.search.service;

import com.atguigu.ssyx.model.search.SkuEs;
import com.atguigu.ssyx.vo.search.SkuEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ClassName: SkuService
 * Package: com.atguigu.ssyx.search.service
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/6 16:22
 * @Version 1.0
 */
public interface SkuService {
    /**
     * 上架商品列表
     * @param skuId
     */
    void upperSku(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerSku(Long skuId);

    /**
     * 获取爆品商品
     * @return
     */
    List<SkuEs> findHotSkuList();

    /**
     * 搜索列表
     * @param skuEsQueryVo
     * @return
     */
    Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo);

    void incrHotScore(Long skuId);
}
