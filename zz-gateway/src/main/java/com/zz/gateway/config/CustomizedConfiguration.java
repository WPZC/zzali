package com.zz.gateway.config;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

public class CustomizedConfiguration{

    @Bean
    public Decoder feignDecoder() {
        return new FeignResultDecoder();
    }

}
