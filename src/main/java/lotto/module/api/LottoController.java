package lotto.module.api;

import lotto.global.constant.CommonCode;
import lotto.module.application.LottoService;
import lotto.module.domain.Lotto;
import lotto.module.view.Input;
import lotto.module.view.Output;

import java.util.List;
import java.util.Map;

/**
 * @author yoonho
 * @since 2023.11.08
 */
public class LottoController {
    private static final LottoController instance = new LottoController();
    private static final LottoService lottoService = LottoService.getInstance();

    private LottoController() {
    }

    public static LottoController getInstance() {
        return instance;
    }

    /**
     * run
     *
     * @author yoonho
     * @since 2023.11.08
     */
    public void run() {
        Integer buyAmount = this.getBuyAmount();
        List<Lotto> userLottoList = this.generateUserLottoList(buyAmount);

        Lotto winLotto = this.getWinLotto();
        int bonusNumber = this.getBonusNumber(winLotto);

        this.calcStatics(userLottoList, winLotto, bonusNumber, buyAmount);
    }

    /**
     * 로또구매금액 입력
     *
     * @return {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public Integer getBuyAmount() {
        do {
            try {
                Output.printEnterBuyAmount();
                return Input.readBuyAmount();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    /**
     * 사용자 로또번호 생성
     *
     * @param buyAmount {@link Integer}
     * @return {@link List}<{@link Lotto}>
     * @author yoonho
     * @since 2023.11.08
     */
    public List<Lotto> generateUserLottoList(Integer buyAmount) {
        int gameCount = buyAmount / CommonCode.BUY_MIN_UNIT;
        Output.printGameCount(gameCount);

        List<Lotto> userLottoList = lottoService.generateLottoList(gameCount);
        userLottoList.forEach(Lotto::print);
        return userLottoList;
    }

    /**
     * 당첨로또번호 생성
     *
     * @return {@link Lotto}
     * @author yoonho
     * @since 2023.11.08
     */
    public Lotto getWinLotto() {
        do {
            try {
                Output.printEnterWinNumbers();
                return Input.readWinLotto();
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    /**
     * 보너스번호 입력
     *
     * @param winLotto {@link Lotto}
     * @return {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public Integer getBonusNumber(Lotto winLotto) {
        do {
            try {
                Output.printEnterBonusNumber();
                return Input.readBonusNumber(winLotto);
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    /**
     * 당첨통계자료 생성 및 출력
     *
     * @param userLottoList {@link List}<{@link Lotto}>
     * @param winLotto {@link Lotto}
     * @param bonusNumber {@link Integer}
     * @param buyAmount {@link Integer}
     * @author yoonho
     * @since 2023.11.08
     */
    public void calcStatics(List<Lotto> userLottoList, Lotto winLotto, Integer bonusNumber, Integer buyAmount) {
        Map<Long, Integer> statics = lottoService.getStaticsMap(userLottoList, winLotto, bonusNumber);
        long totalWinAmount = lottoService.calcTotalWinAmount(statics);

        String calcRate = lottoService.calcRate(totalWinAmount, buyAmount);
        Output.printReport(statics, calcRate);
    }
}
