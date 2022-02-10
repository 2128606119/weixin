package openfeigncs.server;

import com.cn.entity.ResultEntity;
import com.cn.enttry.FriendApply;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "FRIEND-WEB")
public interface FriendFeingService {

    @RequestMapping(value = "/FriendApply/addFriendApply")
    ResultEntity addFriendApply(@RequestBody FriendApply friendApply);

    @RequestMapping(value = "/FriendApply/getbyuser")
    ResultEntity getbyuser(@RequestParam("wechatid") String wechatid, @RequestParam("t") int t, @RequestParam("f") int f);

    @RequestMapping(value = "/FriendApply/getUserById")
    ResultEntity getUserById(@RequestParam("id") Integer id);

    @RequestMapping(value = "/FriendApply/RupdateFriendApplyStatus")
    ResultEntity RupdateFriendApplyStatus(@RequestParam("status") Integer status, @RequestParam("id") Integer id);

    @RequestMapping(value = "/FriendApply/querybeen")
    ResultEntity querybeen(@RequestBody FriendApply friendApply);

    @RequestMapping(value = "/Friend/getFriendListByid")
    ResultEntity getFriendListByid(@RequestParam("uid") Integer uid);
}
