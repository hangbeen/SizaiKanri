package org.zerock.service;

import java.util.List;
import java.util.Map;

import org.zerock.domain.HinmokuVO;
import org.zerock.domain.JumunshoVO;
import org.zerock.domain.OrderVO;

public interface OrderService {
    // 주문서 목록 조회 / 注文書のリストを取得
    List<OrderVO> getOrderList();
    
    // 주문서 출고 처리 리스트 조회 / 注文書出庫処理リストを取得
    List<OrderVO> getReleaseList();
    
    // 출고 처리 / 出庫処理
    void processRelease(String hinmokuMei, String hitsukeNoRefertype, int release) throws Exception;
    
    // 상태 목록 조회 / 状態リストを取得
    List<OrderVO> statusList(Map<String, Object> params);
    
    // 미판매 목록 조회 / 未販売リストを取得
    List<OrderVO> unsoldList();

    // 주문서 저장 / 注文書保存
    void saveOrder(JumunshoVO jumunshoVO);
    
    // 품목 목록 조회 / 品目リストを取得
    List<HinmokuVO> getHinmokuList();

}