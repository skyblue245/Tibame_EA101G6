package com.msgrp.model;

import java.util.*;

public interface MsgrpDAO_interface {
          public void insert(MsgrpVO msgrpVO);
          public void update(MsgrpVO msgrpVO);
          public void delete(String msgrpno);
          public MsgrpVO findByPrimaryKey(String msgrpno);
          public List<MsgrpVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<msgrpVO> getAll(Map<String, String[]> map); 
}
