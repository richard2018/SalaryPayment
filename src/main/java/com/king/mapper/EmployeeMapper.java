package com.king.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.king.data.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeDO> {
}
