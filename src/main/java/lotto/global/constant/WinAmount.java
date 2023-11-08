package lotto.global.constant;

/**
 * 로또번호 일치개수별 당첨금액
 *
 * @author yoonho
 * @since  2023.11.08
 */
public enum WinAmount {
    THIRD(3, 5000),
    FOURTH(4, 50000),
    FIFTH(5, 1500000),
    FIFTH_WITH_BONUS(51, 30000000),
    SIXTH(6, 2000000000);

    private final long matchCount;
    private final long amount;

    WinAmount(long matchCount, long amount) {
        this.matchCount = matchCount;
        this.amount = amount;
    }

    public long getMatchCount() {
        return this.matchCount;
    }

    /**
     * 당첨개수별 당첨금액 조회
     *
     * @param matchCount {@link Long}
     * @return 당첨금액 {@link Long}
     * @author john.09
     * @since 2023.11.08
     */
    public static long findWinAmount(long matchCount) {
        for(WinAmount item : WinAmount.values()) {
            if(item.matchCount == matchCount) {
                return item.amount;
            }
        }
        return 0;
    }
}
