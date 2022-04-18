package com.ljy.oneclub.utils;

import com.ljy.oneclub.entity.SysInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

@Component
public class SysInfoThread {
    private static LinkedHashMap<String, SysInfo> sysInfoMap = new LinkedHashMap<>();

    @Bean(name="taskThread")
    public String service() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        new Thread(()->{
            while (true) {
                SysInfo sysInfo = new SysInfo();
                int cpu = SysInfoUtil.cpuLoad();
                int memory = SysInfoUtil.memoryLoad();
                sysInfo.setCpuRatio(cpu);
                sysInfo.setMemoryRatio(memory);
                Date date = new Date();
                String format = simpleDateFormat.format(date);
                if (sysInfoMap.size() >= 60) {
                    Set<String> set = sysInfoMap.keySet();
                    for (String s : set) {
                        sysInfoMap.remove(s);
                        break;
                    }
                }
                sysInfoMap.put(format,sysInfo);
                try {
                    Thread.sleep(1000*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return null;
    }

    public LinkedHashMap<String, SysInfo> getSysInfoMap(){
        return sysInfoMap;
    }
}
