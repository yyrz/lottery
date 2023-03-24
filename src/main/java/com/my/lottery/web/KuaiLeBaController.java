package com.my.lottery.web;


import com.my.lottery.base.BaseController;
import com.my.lottery.base.ResponseObject;
import com.my.lottery.service.KuaiLeBaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author laure2
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/api/kuaileba/")
public class KuaiLeBaController extends BaseController {
    @Autowired
    private KuaiLeBaService kuaiLeBaService;

    @ApiOperation("热力图")
    @PostMapping("/punchCard")
    public ResponseObject<Map<String, Object>> punchCard() throws Exception {
        return success(kuaiLeBaService.punchCard());
    }

}
