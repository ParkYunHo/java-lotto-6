package lotto.module.application;

import lotto.global.constant.WinAmount;
import lotto.module.api.LottoController;
import lotto.module.domain.Lotto;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yoonho
 * @since 2023.11.08
 */
public class LottoService {

    private static final LottoService instance = new LottoService();

    public static LottoService getInstance() {
        return instance;
    }

    /**
     * 로또번호 리스트 생성
     *
     * @param count {@link Integer}
     * @return {@link List}<{@link Lotto}>
     * @author yoonho
     * @since 2023.11.08
     */
    public List<Lotto> generateLottoList(int count) {
        List<Lotto> lottoList = Lists.newArrayList();
        for(int i=0; i < count; i++) {
            lottoList.add(new Lotto());
        }

        return lottoList;
    }

    /**
     * 일치개수별 게임횟수 계산
     *
     * @param userLottoList {@link List}<{@link Lotto}>
     * @param winLotto {@link Lotto}
     * @param bonusNumber {@link Integer}
     * @return {@link Map}<{@link Long}, {@link Integer}>
     * @author yoonho
     * @since 2023.11.08
     */
    public Map<Long, Integer> getStaticsMap(List<Lotto> userLottoList, Lotto winLotto, Integer bonusNumber) {
        Map<Long, Integer> statics = new HashMap<>();
        for(Lotto userLotto : userLottoList) {
            long winCount = userLotto.findWinCount(winLotto, bonusNumber);
            // 미당첨 게임은 Pass
            if(winCount == 0) {
                continue;
            }
            int count = statics.getOrDefault(winCount, 0);

            statics.put(winCount, ++count);
        }

        return statics;
    }

    /**
     * 전체 당첨금액 계산
     *
     * @param statics {@link Map}<{@link Long}, {@link Integer}>
     * @return {@link Long}
     * @author yoonho
     * @since 2023.11.08
     */
    public long calcTotalWinAmount(Map<Long, Integer> statics) {
        long totalAmount = 0L;
        for(Map.Entry<Long, Integer> item : statics.entrySet()) {
            long winAmount = WinAmount.findWinAmount(item.getKey());
            totalAmount += winAmount * item.getValue();
        }

        return totalAmount;
    }

    /**
     * 수익률 계산
     *
     * @param totalWinAmount {@link Long}
     * @param buyAmount {@link Integer}
     * @return {@link String}
     * @author yoonho
     * @since 2023.11.08
     */
    public String calcRate(long totalWinAmount, Integer buyAmount) {
        return String.format("%,.1f", (totalWinAmount / buyAmount.doubleValue()) * 100);
    }
}
