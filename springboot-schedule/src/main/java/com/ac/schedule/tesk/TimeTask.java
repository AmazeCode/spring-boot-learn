package com.ac.schedule.tesk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  @Description: 定时
 *  @author: zhangyadong
 *  @Date: 2020/8/8 14:31
 *  @version: V1.0
 */
@Slf4j
@Component
public class TimeTask {
    private int[] people = {9000,2000,3000,1000};

    private int count = 0;

    /**
     * @Description 延迟5秒开始执行
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 14:33
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTask() throws InterruptedException {
        if (count < 4) {
            int timeConsuming = people[count];
            log.info("fixedDelayTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
            Thread.sleep(timeConsuming);
            count++;
        }
    }

    /**
     * @Description 准时执行
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 14:33
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void cronTask() throws InterruptedException {
        if (count < 4) {
            int timeConsuming = people[count];
            log.info("cronTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
            Thread.sleep(timeConsuming);
            count++;
        }
    }

    /**
     * @Description 如果超时,第二次执行在第一次结束后立即进行，若未超时，下次定时则按计划进行
     * @params []
     * @return void
     * @author zhangyadong
     * @date 2020/8/8 14:33
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRateTask() throws InterruptedException {
        if (count < 4) {
            int timeConsuming = people[count];
            log.info("fixedRateTask-----第 {} 个人开始如厕，耗时：{}ms",count+1, timeConsuming);
            Thread.sleep(timeConsuming);
            count++;
        }
    }
}
