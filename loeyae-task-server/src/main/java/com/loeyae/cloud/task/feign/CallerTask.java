package com.loeyae.cloud.task.feign;

import com.alibaba.fastjson.JSONObject;
import com.loeyae.cloud.commons.common.ApiResult;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

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
public class CallerTask {

    Feign.Builder builder;

    @Autowired
    public CallerTask(Contract contract, Decoder decoder, Encoder encoder, Client client)
    {
        this.builder = Feign.builder().client(client).contract(contract).encoder(encoder).decoder(decoder);
    }

    /**
     * post
     *
     * @param service
     * @param url
     * @param dto
     * @return
     * @throws URISyntaxException
     */
    public ApiResult post(String service, String url, JSONObject dto) {
        TaskClient notifyClient = this.builder.target(TaskClient.class, "http://"+ service);
        return notifyClient.requestPost(url, dto);
    }

    /**
     * get
     *
     * @param service
     * @param url
     * @return
     */
    public ApiResult get(String service, String url) {
        TaskClient notifyClient = this.builder.target(TaskClient.class, "http://"+ service);
        return notifyClient.requestGet(url);
    }
}