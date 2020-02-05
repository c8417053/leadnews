package com.heima.article.service;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author: czx
 * @create: 2020-02-03 20:33
 */

public interface AppArticleService {

    /**
     *
     * @param dto
     * @param type 1:加载更多 2：加载更新
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short type);
}
