package com.atguigu.ssyx.sys.mapper;

import com.atguigu.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 地区表 Mapper 接口
 * </p>
 *
 * @author liuxu
 * @since 2024-03-04
 */
@Mapper
public interface RegionMapper extends BaseMapper<Region> {

}
