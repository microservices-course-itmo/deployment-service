package com.wine.to.up.deployment.service.command.response.parser;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Qualifier("firstRealization")
public class GetImagesCommandResponseParser {
    public void parse(String str, Map<String, Object> values){}
}
