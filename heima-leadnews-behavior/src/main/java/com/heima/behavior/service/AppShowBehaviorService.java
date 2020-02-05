package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @author: czx
 * @create: 2020-02-05 19:44
 */
public interface AppShowBehaviorService {

    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
