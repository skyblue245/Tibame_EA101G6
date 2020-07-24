package com.game.model;
import java.util.List;

import com.game.model.GameVO;
import com.shop.model.ShopVO;
public interface GameDAO_interface {
	public void insert(GameVO gameVO, ShopVO shopVO);
	public void update(GameVO gameVO);
	public List<GameVO> findByGmnames(String gmname);
	public GameVO findByPrimaryKey(String gmno);
	public List<GameVO> getAll();
}
