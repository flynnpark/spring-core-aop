package dev.flynnpark.springcoreaop.pointcut;

import static org.assertj.core.api.Assertions.*;

import dev.flynnpark.springcoreaop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String dev.flynnpark.springcoreaop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("method: {}", method);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution (public String dev.flynnpark.springcoreaop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution (* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution (* hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution (* hell*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution (* *ll*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution (* hello123(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void exactPackageMatch1() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void exactPackageMatch2() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchFalse() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}
