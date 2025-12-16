package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public Long count();

    //   基于pagehelper进行分页查询,SQL语句最后不能有分号
    public List<Emp> list(EmpQueryParam empQueryParam);


    //新增员工
    //保存员工基本信息
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取插入数据的主键，并设置给emp对象
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);


    //批量删除员工
//    @Select("delete from emp where id in ${ids}")
    public void deleteById(List<Integer> ids);

    //查询员工信息
    Emp findById(Integer id);

    //更新员工信息
//    @Update("update emp set username=#{username}, name=#{name}, gender=#{gender}, phone=#{phone}, job=#{job}, salary=#{salary}, image=#{image}, entry_date=#{entryDate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    void updateById(Emp emp);


    @MapKey("pos")
    //统计职位人数
    List<Map<String, Object>> countEmpByJob();

    @MapKey("gen")
    //统计员工性别
    List<Map<String, Object>> countEmpByGender();

    //员工登录
    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp findByUsernameAndPassword(String username, String password);
}
