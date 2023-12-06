package dev.flynnpark.springcoreaop.member;

import dev.flynnpark.springcoreaop.member.annotation.MethodAop;

public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
