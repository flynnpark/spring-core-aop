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

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.MemberService.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution (* dev.flynnpark.springcoreaop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    // String 타입의 파라미터 허용
    @Test
    void argsMatch() throws NoSuchMethodException {
        pointcut.setExpression("execution (* *(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class, "Spring")).isTrue();
    }

    // 파라미터가 없어야 함
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution (* *())");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    // 정확히 하나의 파라미터 허용, 모든 타입 허용
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution (* *(*))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    // 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    // 파라미터가 없어도 됨
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution (* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    // String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    @Test
    void argsMatchComples() {
        pointcut.setExpression("execution(* *(STring, ..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}
