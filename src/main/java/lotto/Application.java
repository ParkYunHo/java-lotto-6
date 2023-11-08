package lotto;

import lotto.module.api.LottoController;

/**
 * @author yoonho
 * @since 2023.11.08
 */
public class Application {

    public static void main(String[] args) {
        LottoController lottoController = LottoController.getInstance();
        lottoController.run();
    }
}
