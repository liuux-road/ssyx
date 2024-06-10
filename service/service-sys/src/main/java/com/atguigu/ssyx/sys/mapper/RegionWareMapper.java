package com.atguigu.ssyx.sys.mapper;

import com.atguigu.ssyx.model.sys.RegionWare;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 城市仓库关联表 Mapper 接口
 * </p>
 *
 * @author liuxu
 * @since 2024-03-04
 */
@Mapper
public interface RegionWareMapper extends BaseMapper<RegionWare> {

}
