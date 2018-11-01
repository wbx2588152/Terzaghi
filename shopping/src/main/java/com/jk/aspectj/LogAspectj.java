package com.jk.aspectj;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.jk.model.LogBean;
import com.jk.utils.ConnectionUtils;
import com.jk.utils.StringUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jk.utils.SessionUserUtil;



@Aspect
@Component
public class LogAspectj {
	@Autowired
	private MongoTemplate mongoTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

	@Pointcut("execution(* com.jk.service..*.*(..))")
	public void logPointCut() {}
	
	@AfterReturning(value="logPointCut()", returning = "rtv")
	public void saveLog(JoinPoint joinPoint, Object rtv) throws Exception {
        // 判断参数
		if (joinPoint.getArgs() == null) {// 没有参数
			return;
		}
		LogBean logBean = new LogBean();
		//获取方法请求参数
		Object[] os = joinPoint.getArgs();
		 //获取类名  
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logBean.setClassName(className);
        //获取方法名  
        String methodName = joinPoint.getSignature().getName(); 
        logBean.setMethodName(methodName);
        String param = className + "." + methodName + ":";  
        for (int i = 0; i < os.length; i++) {  
            param += "参数[" + i + "]:" + os[i].toString();  
        }
        logBean.setParam(param);
        logBean.setCreateTime(new Date());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        if(sra != null) {
        	 HttpServletRequest request = sra.getRequest();
        	 
        	 String userId = SessionUserUtil.getUserId(request);
        	
        	 logBean.setUserId(userId);
        	
             logBean.setIp(getIp(request));
        }
        amqpTemplate.convertAndSend("logqueue",logBean);
        System.out.println("我发送了一条消息");
       /* mongoTemplate.save(logBean);*/
	}
      //获取客户端ip  
        public static String getIp(HttpServletRequest request) {  
            String ip = request.getHeader("X-Forwarded-For");  
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                //多次反向代理后会有多个ip值，第一个ip才是真实ip  
                int index = ip.indexOf(",");  
                if(index != -1){  
                    return ip.substring(0,index);  
                }else{  
                    return ip;  
                }  
            }
            ip = request.getHeader("X-Real-IP");  
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){  
                return ip;  
            }  
            return request.getRemoteAddr();  
        }  

	
	

}
