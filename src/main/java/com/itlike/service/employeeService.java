package com.itlike.service;

import com.itlike.domain.Employee;
import com.itlike.domain.QueryVo;
import com.itlike.domain.pageListRes;

import java.util.List;

public interface employeeService {
    //分页 需要两个参数
    public pageListRes getEmployee(QueryVo queryVo);

    //保存员工
    public void saveEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    void updateState(Long id);

    List<Long> getRidByEid(Long id);
}
