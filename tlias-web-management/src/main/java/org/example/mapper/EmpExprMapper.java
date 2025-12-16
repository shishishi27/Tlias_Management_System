package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    //批量保存员工工作经历信息
    void insertBatch(List<EmpExpr> exprList);

    //删除员工所有工作经历信息
    void deleteByEmpId(List<Integer> empIds);

}
