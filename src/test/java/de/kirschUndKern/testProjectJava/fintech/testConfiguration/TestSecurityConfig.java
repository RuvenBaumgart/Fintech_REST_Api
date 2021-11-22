package de.kirschUndKern.testProjectJava.fintech.testConfiguration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@TestConfiguration
@Order(Ordered.LOWEST_PRECEDENCE)
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.csrf().disable()
      .authorizeRequests().anyRequest().permitAll();
  }
}
