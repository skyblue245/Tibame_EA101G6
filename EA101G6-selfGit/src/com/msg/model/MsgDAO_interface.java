package com.msg.model;

import java.util.*;

public interface MsgDAO_interface {
          public void insert(MsgVO msgVO);
          public void update(MsgVO msgVO);
          public void delete(String msgno);
          public MsgVO findByPrimaryKey(String msgno);
          public List<MsgVO> getAll();
          public List<MsgVO> getAllByArtno(String artno);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<msgVO> getAll(Map<String, String[]> map); 
}
