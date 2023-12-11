package dev.flynnpark.springcoreaop.pointcut;

import dev.flynnpark.springcoreaop.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class WithinTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact() {
        pointcut.setExpression("within(dev.flynnpark.springcoreaop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(dev.flynnpark.springcoreaop.member.*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(dev.flynnpark.springcoreaop..*)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 설정하면 안 된다.")
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(dev.flynnpark.springcoreaop.member.MemberService)");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution은 타입 기반, 인터페이스를 설정 가능.")
    void executionSuperTypeTrue() {
        pointcut.setExpression("execution(* dev.flynnpark.springcoreaop.member.MemberService.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}
