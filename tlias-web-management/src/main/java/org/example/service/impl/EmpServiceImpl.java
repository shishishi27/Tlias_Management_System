package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;
    public EmpServiceImpl(EmpMapper empMapper,EmpExprMapper empExprMapper) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //   基于pagehelper进行分页查询，pagehelper只会对紧跟在其后的第一条SQL语句进行分页处理
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public PageResult<Emp> page_where(EmpQueryParam empQueryParam) {
//        Long total =empMapper.count();
//        Integer start = (empQueryParam.getPage() - 1) * empQueryParam.getPageSize();
//        empQueryParam.setStart(start);
//        List<Emp> list = empMapper.list_where(empQueryParam);
//        return new PageResult<Emp>(total, list);
//    }

    // 事务管理，方法中多次操作数据库时使用，保证数据一致性，默认出现RuntimeException回滚；ACID特性：原子性，一致性，隔离性，持久性
    // 默认REQUIRED, propagation = Propagation.REQUIRED_NEW 当被另外一个事务方法调用时，如何控制（加入？新建？）
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteById(ids);
        empExprMapper.deleteByEmpId(ids);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }
    @Override
    public LogInfo login(Emp emp) {
        Emp empDB = empMapper.findByUsernameAndPassword(emp.getUsername(), emp.getPassword());
        if (empDB != null) {
            log.info("登录成功,员工信息：{}", empDB);
            // 生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empDB.getId());
            claims.put("username", empDB.getUsername());
            String token = JwtUtils.generateToken(claims);
            log.info("生成JWT令牌：{}", token);
            return new LogInfo(empDB.getId(), empDB.getUsername(), empDB.getName(), token);
        }
        return null;
    }
}
