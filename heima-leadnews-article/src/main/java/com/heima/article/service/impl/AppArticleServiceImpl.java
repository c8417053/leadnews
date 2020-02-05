package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: czx
 * @create: 2020-02-03 20:41
 */
@Service
public class AppArticleServiceImpl implements AppArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserarticleListMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        if (dto==null) {
            dto = new ArticleHomeDto();
        }
        //时间参数校验
        if (dto.getMaxBehotTime()==null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime()==null) {
            dto.setMinBehotTime(new Date());
        }
        //分页参数校验
        Integer size = dto.getSize();
        if (size==null || size<=20) {
            size=20;
        }
        size=Math.min(size,50);
        dto.setSize(size);
        //文章
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }
        //类型参数校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE) &&
                !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)) {
            type =ArticleConstans.LOADTYPE_LOAD_MORE;
        }
        //获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        List<ApArticle> apArticleList = new ArrayList<>();
        if (user!=null) {
            //已登录 加载推荐文章
            apArticleList = getUserArticle(user,dto,type);
        }else{
            //未登录 加载默认文章
            apArticleList = getDefaultArticle(dto,type);
        }
        return ResponseResult.okResult(apArticleList);
    }

    /**
     * 加载默认的文章信息
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }


    /**
     * 先从用户的推荐表中查找文章信息，如果没有在从默认文章信息获取数据
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserarticleListMapper.loadArticleIdListByUser(user,dto,type);
        if (!list.isEmpty()) {
           return apArticleMapper.loadArticleListByIdList(list);
        }else{
           return getDefaultArticle(dto,type);
        }
    }
}
