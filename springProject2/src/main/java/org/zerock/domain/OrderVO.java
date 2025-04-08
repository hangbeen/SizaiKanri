package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {
    private String jumunshoID;           // 주문서 ID / 注文書ID
    private String hitsuke;             // 일자 / 日付
    private String no;                  // 주문서 번호 / 注文書番号
    private String hitsukeNo;           // 일자-No (결합 필드) / 日付-No（結合フィールド）
    private String hitsukeNoRefertype;  // 참조 유형 번호 / 参照タイプ番号
    
    private String mihanbaisuuryou;     // 미판매 수량 / 未販売数量
    private String mihanbaikyoukyukingaku; // 미판매 공급 금액 / 未販売供給金額
    private String mihanbaibugase;      // 미판매 부가세 / 未販売付加税
    
    private String hinmokuMei;          // 품목명 / 品目名
    private int otherHinmokuCount;      // 첫 번째 품목 외 나머지 개수 / 最初の品目以外の残りの数
    
    private String jumunKingakuGoukei;  // 주문 금액 합계 / 注文金額合計
    private String status;              // 진행 상태 (예: 진행중, 확인, 완료) / 進行状態（例: 進行中、確認、完了）
    
    private String commonHinmokuID;     // 공통 품목 ID / 共通品目ID
    private String referenceType;       // 참조 유형 / 参照タイプ
    private String itemCode;            // 품목 코드 / 品目コード
    private String kikaku;              // 규격 / 規格
    
    private String suuryou;             // 수량 / 数量
    private String tanka;               // 단가 / 単価
    private String kyoukyuKingaku;      // 공급 가액 / 供給価額
    private String bugase;              // 부가세 / 付加税
    private String tekiyo;              // 적요 / 摘要
    private String noukiIchija;         // 납기일자 / 納期日
    
    private String goukei;              // 총액 / 合計
    
    private String projectID;           // 프로젝트 ID / プロジェクトID
    private String projectMeimei;       // 프로젝트명 / プロジェクト名

    // 창고 정보 / 倉庫情報
    private String soukoID;             // 창고 ID / 倉庫ID
    private String soukoMei;            // 창고명 / 倉庫名

    // 담당자 정보 / 担当者情報
    private String tantoushaID;         // 담당자 ID / 担当者ID
    private String tantoushaMei;        // 담당자명 / 担当者名

    // 거래처 정보 / 取引先情報
    private String torihikisakiID;      // 거래처 ID / 取引先ID
    private String torihikisakiMei;     // 거래처명 / 取引先名

    // 통화 정보 / 通貨情報
    private String tsukaID;             // 통화 코드 (예: KRW, USD) / 通貨コード（例: KRW, USD）
    private String tsuukaMeimei;        // 통화명 / 通貨名
    
    // 추가: 품목 리스트 / 追加: 品目リスト
    private List<CommonHinmokuVO> items; // 품목 리스트 / 品目リスト

    public List<CommonHinmokuVO> getItems() {
        return items;
    }
}
