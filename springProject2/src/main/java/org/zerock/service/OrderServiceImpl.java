package org.zerock.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.CommonHinmokuVO;
import org.zerock.domain.HinmokuVO;
import org.zerock.domain.JumunshoVO;
import org.zerock.domain.OrderVO;
import org.zerock.mapper.OrderMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    
    // 주문서 목록 조회 / 注文書のリストを取得
    @Override
    public List<OrderVO> getOrderList() {
        log.info("Fetching order list...");
        return orderMapper.getOrderList();
    }
    
    // 상태 목록 조회 / 状態リストを取得
    @Override
    public List<OrderVO> statusList(Map<String, Object> params) {
        return orderMapper.statusList(params);
    }
    
    // 주문서 출고 처리 리스트 조회 / 注文書出庫処理リストを取得
    @Override
    public List<OrderVO> getReleaseList() {
        return orderMapper.getReleaseList();
    }
    
    // 출고 처리 / 出庫処理
    @Transactional
    @Override
    public void processRelease(String hinmokuMei, String hitsukeNoRefertype, int release) throws Exception {
        int updatedRows = orderMapper.release(hinmokuMei, hitsukeNoRefertype, release);

        if (updatedRows == 0) {
            throw new Exception("출고 실패: 조건에 맞는 데이터가 없습니다."); // 出庫失敗: 条件に合うデータがありません。
        }
    }

    // 미판매 목록 조회 / 未販売リストを取得
    @Override
    public List<OrderVO> unsoldList() {
        log.info("Fetching unsold list...");
        return orderMapper.unsoldList();
    }
    
    // 품목 목록 조회 / 品目リストを取得
    @Override
    public List<HinmokuVO> getHinmokuList() {
        return orderMapper.getHinmokuList();
    }

    // 주문서 저장 / 注文書保存
    @Transactional
    public void saveOrder(JumunshoVO jumunshoVO) {
        if (jumunshoVO == null || jumunshoVO.getCommonHinmokuList() == null || jumunshoVO.getCommonHinmokuList().isEmpty()) {
            throw new IllegalArgumentException("주문서 또는 품목 리스트가 비어 있습니다."); // 注文書または品目リストが空です。
        }

        log.info("주문서 저장 시작: {}"+ jumunshoVO); // 注文書保存開始: {}

        try {
            // 주문서 삽입 / 注文書挿入
            orderMapper.insertJumunsho(jumunshoVO);

            // hitsukeNo 생성 / hitsukeNo生成
            String hitsuke = jumunshoVO.getHitsuke(); // YYYY-MM-DD
            LocalDate localDate = LocalDate.parse(hitsuke); // String -> LocalDate
            String formattedHitsuke = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // YYYY/MM/DD 형식 / YYYY/MM/DD形式
            
            String hitsukeNo = formattedHitsuke + "-" + jumunshoVO.getNo();

            // 각 품목 삽입 / 各品目挿入
            for (CommonHinmokuVO hinmoku : jumunshoVO.getCommonHinmokuList()) {
                if (hinmoku == null || isInvalidStringValue(hinmoku.getSuuryou())) {
                    log.warn("유효하지 않은 품목: {}"+ hinmoku); // 無効な品目: {}
                    continue;
                }

                // 필요 시 숫자 변환 / 必要に応じて数値に変換
                hinmoku.setSuuryou(hinmoku.getSuuryou().trim()); // 공백 제거 / 空白除去
                hinmoku.setHitsukeNo(hitsukeNo);
                log.info("품목 삽입 시작: {}"+ hinmoku); // 品目挿入開始: {}
                orderMapper.insertCommonHinmoku(hinmoku); // 개별 품목 삽입 / 個別品目挿入
            }

            log.info("주문서 저장 완료: {}"+ jumunshoVO); // 注文書保存完了: {}

        } catch (Exception e) {
            log.error("주문서 저장 중 오류 발생: {}"+ e.getMessage(), e); // 注文書保存中にエラー発生: {}
            throw e; // 트랜잭션 롤백 / トランザクションロールバック
        }
    }

    // 유효하지 않은 값 확인 / 無効な値確認
    private boolean isInvalidStringValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return true; // 유효하지 않은 값 / 無効な値
        }
        try {
            int intValue = Integer.parseInt(value.trim());
            return intValue <= 0; // 0 이하인 경우 유효하지 않음 / 0以下の場合無効
        } catch (NumberFormatException e) {
            return true; // 숫자로 변환 불가 / 数値に変換不可
        }
    }
}
