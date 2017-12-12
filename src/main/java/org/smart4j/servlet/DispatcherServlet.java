package org.smart4j.servlet;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.smart4j.bean.Data;
import org.smart4j.bean.Handler;
import org.smart4j.bean.Param;
import org.smart4j.bean.View;
import org.smart4j.util.BeanHelper;
import org.smart4j.util.ControllerHelper;
import org.smart4j.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Lee on 2017/12/4
 */
public class DispatcherServlet extends HttpServlet{


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getRequestURI();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null){
            Class<?> controllerCls = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerCls);
            Map< String, Object> paramMap = new HashMap<>();

            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String key = paramNames.nextElement();
                String value = req.getParameter(key);
                paramMap.put(key, value);
            }
//            String query = "";
//            if (!query.isEmpty()){
//                String[] params = query.split("&");
//                if (params.length > 0){
//                    for (String param:params){
//                        String[] array = param.split("=");
//                        String key = array[0];
//                        String value = array[1];
//                        paramMap.put(key, value);
//                    }
//                }
//            }

            Param param = new Param(paramMap);
            Method method = handler.getActionMethod();

            Object result = ReflectionUtil.invoke(controllerBean, method, param);
            //返回jsp
            if (result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if (path.startsWith("/")){
                    resp.sendRedirect(req.getContextPath() + path);
                }else {
                    Map<String, Object> model = view.getModel();
                    for (Map.Entry<String, Object> entry: model.entrySet()){
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(path).forward(req, resp);
                }

            }else if (result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    Gson gson = new Gson();
                    String json = gson.toJson(model);
                    writer.write(json);
                    writer.flush();;
                    writer.close();
                }
            }

        }

        super.service(req, resp);
    }
}
