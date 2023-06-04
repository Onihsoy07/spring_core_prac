package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        MyLogger myLogger = myLoggerProvider.getObject();
        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        Thread.sleep(500);
        logDemoService.logic("test id");

        return "OK";
    }

}