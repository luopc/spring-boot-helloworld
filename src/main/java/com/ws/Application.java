package com.ws;

import com.ws.listener.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        //禁止通过命令行修改端口号
        app.setAddCommandLineProperties(false);
        //配置监听器
        app.addListeners(new MyApplicationStartedEventListener());
        app.run(args);
    }
}
