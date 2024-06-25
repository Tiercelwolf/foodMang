package cn.ruone.wxapp.shop.controller;

import cn.ruone.wxapp.shop.api.service.ScheduleTaskImplService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleTaskImpl implements Job{
    private ScheduleTaskImplService scheduleTaskImplService;
    @Autowired
    public void setStoreBookmarkService(ScheduleTaskImplService scheduleTaskImplService){
        this.scheduleTaskImplService = scheduleTaskImplService;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        scheduleTaskImplService.refund();
    }
}