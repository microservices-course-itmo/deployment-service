package com.wine.to.up.deployment.service.command.response.parser;

import com.wine.to.up.deployment.service.vo.ImageVO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GetImagesCommandResponseParser {
    public List<ImageVO> parse(String str){
        return Collections.emptyList();
    }
}