package com.payno.guides.springs.spring4;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 异步RestTemplate的支持
 *      然后5就deprecated了，换成webClient
 * @author zhaolei22
 * @date 2020/07/21
 */
public class AsyncRestTemplateSupport {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create();
        String res = webClient
                .post()
                .uri("")
                .header("","")
                .contentType(MediaType.ALL)
                .accept(MediaType.ALL)
                .body("Str",String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
