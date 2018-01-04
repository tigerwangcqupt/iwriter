package com.yryz.writer.modules.articlefavorite.provider;
import com.yryz.common.web.ResponseModel;
import com.yryz.component.rpc.RpcResponse;
import com.yryz.component.rpc.dto.PageList;

import com.yryz.writer.modules.articlefavorite.ArticleFavoriteApi;
import com.yryz.writer.modules.articlefavorite.entity.ArticleFavorite;
import com.yryz.writer.modules.articlefavorite.vo.ArticleFavoriteVo;
import com.yryz.writer.modules.articlefavorite.dto.ArticleFavoriteDto;
import com.yryz.writer.modules.articlefavorite.service.ArticleFavoriteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章收藏服务
 */
@Service
public class ArticleFavoriteProvider implements ArticleFavoriteApi {

	private static final Logger logger = LoggerFactory.getLogger(ArticleFavoriteProvider.class);

	@Autowired
	private ArticleFavoriteService articleFavoriteService;

	/**
	*  获取ArticleFavorite明细
	*  @param  articleFavoriteId
	*  @return
	* */
	public RpcResponse<ArticleFavorite> get(Long articleFavoriteId) {
		try {
			return ResponseModel.returnObjectSuccess(articleFavoriteService.get(ArticleFavorite.class, articleFavoriteId));
		} catch (Exception e) {
			logger.error("获取ArticleFavorite明细失败", e);
			return ResponseModel.returnException(e);
		}
    }

	/**
	*  获取ArticleFavorite明细
	*  @param  articleFavoriteId
	*  @return
	* */
	public RpcResponse<ArticleFavoriteVo> detail(Long articleFavoriteId) {
		try {
			return ResponseModel.returnObjectSuccess(articleFavoriteService.detail(articleFavoriteId));
		} catch (Exception e) {
			logger.error("获取ArticleFavorite明细失败", e);
			return ResponseModel.returnException(e);
		}
	}

    /**
    * 获取ArticleFavorite列表
    * @param articleFavoriteDto
    * @return
    *
	*/
    public RpcResponse<PageList<ArticleFavoriteVo>> list(ArticleFavoriteDto articleFavoriteDto) {
        try {
			 return ResponseModel.returnListSuccess(articleFavoriteService.selectList(articleFavoriteDto));
        } catch (Exception e) {
        	logger.error("获取ArticleFavorite列表失败", e);
       		 return ResponseModel.returnException(e);
        }
    }

	@Override
	public RpcResponse<PageList<ArticleFavoriteVo>> listByWriter(ArticleFavoriteDto articleFavoriteDto) {
		try {
			return ResponseModel.returnListSuccess(articleFavoriteService.selectListByWriter(articleFavoriteDto));
		} catch (Exception e) {
			logger.error("获取ArticleFavorite列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

	@Override
	public RpcResponse<ArticleFavoriteVo> saveFavorite(ArticleFavorite articleFavorite) {
		try {
			ArticleFavoriteVo articleFavoriteVo = new ArticleFavoriteVo();
			if (articleFavoriteService.saveFavorite(articleFavorite) > 0){
				articleFavoriteVo.setSuccessNum("1");
			}else{
				articleFavoriteVo.setSuccessNum("0");
			}
			return ResponseModel.returnListSuccess(articleFavoriteVo);
		} catch (Exception e) {
			logger.error("获取ArticleFavorite列表失败", e);
			return ResponseModel.returnException(e);
		}
	}

}
