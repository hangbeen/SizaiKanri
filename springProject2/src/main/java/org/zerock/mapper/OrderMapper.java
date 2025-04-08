package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CommonHinmokuVO;
import org.zerock.domain.HinmokuVO;
import org.zerock.domain.JumunshoVO;
import org.zerock.domain.OrderVO;

public interface OrderMapper {
	// 주문서 목록 조회 / 注文書のリストを取得
    List<OrderVO> getOrderList();
    
    // 미판매 목록 조회 / 未販売リストを取得
    List<OrderVO> unsoldList();
    
    // 주문서 출고 처리 리스트 조회 / 注文書出庫処理リストを取得
    List<OrderVO> getReleaseList();
    
    // 출고 업데이트 / 出庫の更新
    int release(@Param("hinmokuMei") String hinmokuMei, // 품목명 / 品目名
            @Param("hitsukeNoRefertype") String hitsukeNoRefertype, // 참조 번호 / 参照番号
            @Param("release") int release); // 출고 수량 / 出庫数量

    // 상태 목록 조회 / 状態リストを取得
    List<OrderVO> statusList(Map<String, Object> params);
    
    // 주문서 데이터 삽입 / 注文書データの挿入
    void insertJumunsho(JumunshoVO jumunshoVO);

    // 주문서 ID로 Hitsuke_No 조회 / 注文書IDでHitsuke_Noを取得
    String getHitsukeNoByJumunshoID(String jumunshoID);

    // 공통 품목 데이터 삽입 / 共通品目データの挿入
    int insertCommonHinmoku(CommonHinmokuVO commonHinmokuVO);
    
    // 품목 리스트 조회 / 品目リストを取得
    List<HinmokuVO> getHinmokuList();
}