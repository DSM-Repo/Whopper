package com.dsm.repo.internal.common.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync(proxyTargetClass = true)
class AsyncConfiguration implements AsyncConfigurer {

    @Bean(name = "taskExecutor")
    Executor taskExecutor() {
        return new VirtualThreadTaskExecutor("repo-virtual-");
    }

    @Bean(destroyMethod = "close")
    ExecutorService virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor();
    }

}
