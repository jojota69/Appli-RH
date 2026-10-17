package com.appliRh.RH.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.appliRh.RH.annotation.PareFeu;
import com.appliRh.RH.model.Employee;
import com.appliRh.RH.service.EmployeeService;

@Aspect
@Component
public class PareFeuAspect {

    private final EmployeeService employeeService;

    public PareFeuAspect(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Around("@annotation(pareFeu)")
    public Object verifierRoles(ProceedingJoinPoint joinPoint, PareFeu pareFeu) throws Throwable {
        String[] rolesRequis = pareFeu.roles();

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return "redirect:/";
        }

        HttpServletRequest request = attrs.getRequest();
        HttpSession session = request != null ? request.getSession(false) : null;
        Integer employeeId = session != null ? (Integer) session.getAttribute("employeeId") : null;
        if (employeeId == null) {
            return "redirect:/";
        }

        if (rolesRequis.length == 0) {
            return joinPoint.proceed();
        }

        Employee employee;
        try {
            employee = employeeService.findById(employeeId);
        } catch (RuntimeException exception) {
            session.invalidate();
            return "redirect:/";
        }

        String roleUtilisateur = employee.getRole().getLibelle();
        for (String r : rolesRequis) {
            if (r.equals(roleUtilisateur)) {
                return joinPoint.proceed();
            }
        }

        return "redirect:/";
    }
}
        