package com.game.model;

import java.util.List;

import com.shop.model.ShopVO;

public class GameService {
	
	GameDAO_interface dao;
	
	public GameService() {
		dao = new GameDAO();
	}
	public void addGame(String gmname, byte[] gmimg, ShopVO shopVO) {
		GameVO gameVO = new GameVO();
		
		gameVO.setGmname(gmname);
		gameVO.setGmimg(gmimg);
		dao.insert(gameVO, shopVO);

	}
	public GameVO updateGame(String gmno, String gmname, byte[] gmimg) {
		GameVO gameVO = new GameVO();
		
		gameVO.setGmno(gmno);
		gameVO.setGmname(gmname);
		gameVO.setGmimg(gmimg);	
		dao.update(gameVO);
		
		return gameVO;
	}
	public List<GameVO> getSomeGames(String gmname) {
		return dao.findByGmnames(gmname);
	}
	public List<GameVO> getAll() {
		return dao.getAll();
	}
	public GameVO getOneGame(String gmno) {
		return dao.findByPrimaryKey(gmno);
	}
}
