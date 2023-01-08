package netty.intermediate.demo3.web;

import netty.intermediate.demo2.server.NettyServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author fun.pengzh
 * @class netty.intermediate.demo15.web.NettyController
 * @desc
 * @since 2022-05-15
 */
@Controller
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "xiaohao");
        return "index";
    }

}
