package dev.flynnpark.springcoreaop.proxyBattle;

import dev.flynnpark.springcoreaop.member.MemberService;
import dev.flynnpark.springcoreaop.member.MemberServiceImpl;
import dev.flynnpark.springcoreaop.proxyBattle.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // JDK
@Import(ProxyDiAspect.class)
public class ProxyDiTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
