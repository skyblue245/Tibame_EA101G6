package com.mallOrDt.model;


import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.mall.model.MallVO;
import com.mallOr.model.MallOrVO;

public interface MallOrDtDao_interface {

	public void add(MallOrDtVO mallOrDt);
	public void delete(String mallOrNo,String commNo);
	public void update(MallOrDtVO mallOrDt);
	public Set<MallOrDtVO> getAll();
	public Set<MallOrDtVO> getByOrNo(String mallOrNo);
	public MallOrDtVO findOneByFk(String mallOrNo,String commNo);
	public void insertWithEmps(MallOrDtVO mallOrDt,Connection conn);
}
