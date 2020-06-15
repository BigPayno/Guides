package advisor.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author payno
 * @date 2020/6/15 10:02
 * @description
 */
@Feign
public interface FeignClient {
    @PostMapping("/hello/{name}")
    String get(@PathVariable("name") String name);
}
