package com.wine.to.up.deployment.service.application.impl;

import com.wine.to.up.deployment.service.application.Application;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationImpl implements Application {
    //private static final RequestMethod GET = ;
    private int id;
    private String name;

    @RequestMapping(
            value = "app/start",
            headers = "Accept=application/json")
    @ResponseBody
    public String startApp()    {
        return "asdasd";
    }

}
