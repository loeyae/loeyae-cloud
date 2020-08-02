package com.loeyae.cloud.message.feign;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * CallerNotify .
 *
 * @date: 2020-08-02
 * @version: 1.0
 * @author: zhangyi07@beyondsoft.com
 */
@Slf4j
@Component
@Import(FeignClientsConfiguration.class)
public class CallerNotify {

    Feign.Builder builder;

    @Autowired
    public CallerNotify(Contract contract, Decoder decoder, Encoder encoder, Client client)
    {
        this.builder = Feign.builder().client(client).contract(contract).encoder(encoder).decoder(decoder);
    }

    public ApiResult notify(String service, String from, String action, JSONObject dto) throws URISyntaxException {
        NotifyClient notifyClient = this.builder.target(NotifyClient.class, "http://"+ service);
        return notifyClient.notify(from, action, dto);
    }
}