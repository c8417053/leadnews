package com.heima.article.apis;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * 用户的行为接口
 * @author: czx
 * @create: 2020-02-04 19:20
 */
public interface BehaviorControllerApi {

    /**
     * 保存用户点击文章的行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);

}
