package dev.flynnpark.springcoreaop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import dev.flynnpark.springcoreaop.member.MemberServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

public class ArgsTest {
    Method method;
    
    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }
    
    private AspectJExpressionPointcut getPointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }
    
    @Test
    void args() {
        assertThat(getPointcut("args(String)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args(Object)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args()").matches(method, MemberServiceImpl.class)).isFalse();
        assertThat(getPointcut("args(..)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args(*)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args(String,..)").matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * execution (* *(java.io.Serializable)) : 메서드의 시그니처로 판단(정적)
     * args(java.io.Serializable) : 런타임에 전달된 인수로 판단(동적)
     */
    @Test
    void argsVsExecution() {
        // args
        assertThat(getPointcut("args(String)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args(java.io.Serializable)").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("args(Object)").matches(method, MemberServiceImpl.class)).isTrue();

        // execution
        assertThat(getPointcut("execution(* *(String))").matches(method, MemberServiceImpl.class)).isTrue();
        assertThat(getPointcut("execution(* *(java.io.Serializable))").matches(method, MemberServiceImpl.class)).isFalse();
        assertThat(getPointcut("execution(* *(Object))").matches(method, MemberServiceImpl.class)).isFalse();
    }
}
