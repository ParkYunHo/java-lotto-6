package lotto.module.view;

import lotto.global.utils.ValidatorUtils;
import lotto.module.domain.Lotto;

import java.util.Arrays;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

/**
 * @author yoonho
 * @since 2023.11.08
 */
public class Input {

    public static Integer readBuyAmount() {
        String input = readLine();
        return ValidatorUtils.validateBuyAmount(input);
    }

    public static Lotto readWinLotto() {
        String input = readLine();

        List<Integer> winNumbers = Arrays.stream(input.split(","))
                .map(ValidatorUtils::parseInt)
                .toList();

        return new Lotto(winNumbers);
    }

    public static Integer readBonusNumber(Lotto winLotto) {
        String input = readLine();
        return ValidatorUtils.validateBonusNumber(winLotto, input);
    }
}
