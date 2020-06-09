package com.itlike.web;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.Employee;
import com.itlike.domain.QueryVo;
import com.itlike.domain.pageListRes;
import com.itlike.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    /*注入业务层*/
    @Autowired
    private employeeService employeeService;

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    @RequestMapping("/employeeList")
    @ResponseBody
    public pageListRes employeeList(QueryVo queryVo){
//        System.out.println(queryVo);
        /*调用业务层查询员工*/
        pageListRes pageListRes = employeeService.getEmployee(queryVo);
        return pageListRes;
    }

    /*接收员工添加表单*/
    @RequestMapping("/saveEmployee")
    @ResponseBody
    public AjaxRes saveEmployee(Employee employee){
        System.out.println("----------------------"+employee);
        AjaxRes ajaxRes = new AjaxRes();
        try {
//            int i=1/0;
            /*调用业务层,保存用户*/
            employeeService.saveEmployee(employee);
            ajaxRes.setMsg("保存成功");
            employee.setState(true);
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }

    /*接收更新员工请求*/
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public AjaxRes updateEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*调用业务层,更新员工*/
            employeeService.updateEmployee(employee);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
        }
        return ajaxRes;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*调用业务层,更新员工*/
            employeeService.updateState(id);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
        }
        return ajaxRes;
    }

    //回显角色
    @RequestMapping("/getRoleById")
    @ResponseBody
    public List<Long> getRoleById(Long id){
        return employeeService.getRidByEid(id);
    }

//    设置异常
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView exceptionHandler(RuntimeException e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("Exception",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
