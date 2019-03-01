package cn.fan.cloud_note.controller;

import cn.fan.cloud_note.entity.Activity;
import cn.fan.cloud_note.service.ActivityService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * activity表相关的控制层
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource(name="activityService")
    private ActivityService activityService;

    @RequestMapping("/loadactivity.do")
    @ResponseBody
    //加载所有活动
    public NoteResult<List<Activity>> loadActivity(){
        return activityService.loadActivity();
    }
}
