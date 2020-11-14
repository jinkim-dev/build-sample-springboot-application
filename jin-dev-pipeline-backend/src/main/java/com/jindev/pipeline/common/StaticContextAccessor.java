package com.jindev.pipeline.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StaticContextAccessor implements ApplicationContextAware {
  private static StaticContextAccessor staticContext;
  private ApplicationContext applicationContext;

  @PostConstruct
  public void register() {
    staticContext = this;
  }

  /**
   * class 타입으로 bean을 가져온다
   * @param clazz
   * @param <T>
   * @return
   */
  public static <T> T getBean(Class<T> clazz) {
    return staticContext.applicationContext.getBean(clazz);
  }

  /**
   * 이름으로 bean을 가져온다
   * @param beanName
   * @return
   */
  public static Object getBean(String beanName) {
    return staticContext.applicationContext.getBean(beanName);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
